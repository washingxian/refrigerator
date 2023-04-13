package com.szu.refrigerator.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.szu.refrigerator.common.CustomException;
import com.szu.refrigerator.common.R;
import com.szu.refrigerator.constant.ConstantFromYml;
import com.szu.refrigerator.constant.ObjectDetectionConstant;
import com.szu.refrigerator.dto.objDetectDto.DetectionObjectInfo;
import com.szu.refrigerator.dto.objDetectDto.DetectionResult;
import com.szu.refrigerator.entity.ObjectType;
import com.szu.refrigerator.mapper.ObjectTypeMapper;
import com.szu.refrigerator.proto.ObjectDetector.DetectResult;
import com.szu.refrigerator.proto.ObjectDetector.ObjectDetectorService;
import com.szu.refrigerator.service.ObjectDetectionService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.concurrent.Future;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
public class ObjectDetectionServiceImpl implements ObjectDetectionService {

    @Autowired
    ConstantFromYml constantFromYml;

    @Resource
    ObjectDetectionConstant objectDetectionConstant;

    @Autowired
    ObjectTypeMapper objectTypeMapper;

    @Autowired
    ObjectDetectorService objectDetectorService;

    private RestTemplate restTemplate;

    private Map<Integer, ObjectType> idMap;

    @PostConstruct
    private void init() {
        restTemplate = new RestTemplate();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        messageConverters.add(converter);

        restTemplate.setMessageConverters(messageConverters);

        idMap = new HashMap<>();
        List<ObjectType> objectTypeList = objectTypeMapper.selectList(new QueryWrapper<ObjectType>());
        for (ObjectDetectionConstant.ObjectInfo obj : objectDetectionConstant.getMappings().getObjects()) {
            if (obj.getFridgeId() != -1) {
                ObjectType objectType = objectTypeList.stream()
                        .filter(type -> type.getOtId() == obj.getFridgeId())
                        .findAny()
                        .orElse(null);

                if (objectType != null) {
                    objectType.setImgSrc(constantFromYml.getObjectImgPath() + objectType.getImgSrc());
                    idMap.put(obj.getId(), objectType);
                }

            }
        }
    }

    @Async
    public Future<DetectResult> asyncDetectObjectInternal(MultipartFile imageBytes) {
        try {
            Future<DetectResult> result = new AsyncResult<>(objectDetectorService.detect(
                    imageBytes.getInputStream()).get(0));
            imageBytes.getInputStream().close();
            return result;
        } catch (IOException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    @Override
    public R<DetectionResult> detectObjects(@NonNull MultipartFile[] uploadFiles) {
        List<Future<DetectResult>> futureResultList = new ArrayList<>();

        for (MultipartFile file : uploadFiles) {
            Future<DetectResult> future = asyncDetectObjectInternal(file);

            if (future != null) {
                futureResultList.add(future);
            }

        }

        List<com.szu.refrigerator.proto.ObjectDetector.Detection> resultList = new ArrayList<>();
        /// wait all complete
        try {
            for (Future<DetectResult> future : futureResultList) {
//                future.wait();
                resultList.addAll(future.get().getDetectionList());
            }
        } catch (Exception e) {
            return R.error(e.getLocalizedMessage());
        }

        return processDetectionResultListInternal(resultList);
    }

    private R<DetectionResult> processDetectionResultListInternal(
            List<com.szu.refrigerator.proto.ObjectDetector.Detection> resultList) {
        Map<Integer, List<com.szu.refrigerator.proto.ObjectDetector.Detection>> detectionMap = new HashMap<>();

        if (resultList != null) {
            for (com.szu.refrigerator.proto.ObjectDetector.Detection detection : resultList) {

                if (!detectionMap.containsKey(detection.getId())) {
                    detectionMap.put(detection.getId(), new ArrayList<>());
                }

                detectionMap.get(detection.getId()).add(detection);

            }
        }

        List<DetectionObjectInfo> detectionObjectInfoList = new ArrayList<>();

        detectionMap.forEach((id, detections1) -> {
            if (!idMap.containsKey(id)) {
                return;
            }

//            objectTypeMapper.selectList(new QueryWrapper<ObjectType>().eq)

            detectionObjectInfoList.add(DetectionObjectInfo.builder()
                    .object(idMap.get(id))
                    .detectNum(detections1.size())
                    .build());

        });

        return R.success(DetectionResult.builder()
                .objectInfos(detectionObjectInfoList)
                .build());
    }

    @Override
    public R<DetectionResult> detectObject(@NonNull MultipartFile uploadFile) {
        DetectResult detectResultList = null;

        try {

            detectResultList = objectDetectorService.detect(uploadFile.getInputStream()).get(0);
            uploadFile.getInputStream().close();

        } catch (IOException exception) {
            return R.error(exception.getLocalizedMessage());
        }
        return processDetectionResultListInternal(detectResultList.getDetectionList());
    }


    /**
     * 迭代3
     * 通过购物清单识别物品
     * @param uploadFile
     * @return
     */
    @Override
    public R<DetectionResult> detectByShoppingList(@NonNull MultipartFile uploadFile) {

        JSONObject jsonObject = ocrPost(uploadFile);
        String str = jsonObject.toJSONString().replaceAll("\\\\n", "\n");

        log.info("图片转字符串");
        log.info(str);
        return analyzeString(str);

    }

    /**
     * 分析购物清单字符串
     *
     * @param shoppingList
     * @return
     */
    @Override
    public R<DetectionResult> analyzeString(String shoppingList) {
        //TODO("物品数量为小数还没做")\d+(.\d+)?+/
        String regex = "(\\d+)?\\s+([^\\s]+)\\s+(\\d+(.\\d+)?+[^\\s]*)\\s+(￥[^\\s]+)\\s+([^\\s]+)";
        //String regex = "\\d+\\s+\\S+\\s+\\d+\\S+\\s+\\d+";
        //log.info("字符串分析");
        //log.info(shoppingList);

        Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE); // 多行模式
        Matcher matcher = pattern.matcher(shoppingList);

        Map<String, Integer> objectNumMap = new HashMap<>();
        while (matcher.find()) {
            String name = matcher.group(2);
            String numStr = matcher.group(3);
            Integer num = Integer.parseInt(numStr.replaceAll("[^0-9]", "")); // 提取数字

            objectNumMap.put(name, num);
            log.info("name: " + name + ", num: " + numStr);
        }

        //log.info("----检测map是否有数据----");
        //log.info(objectNumMap.toString());

        // 创建一个List对象，用来存储每种物品及其购买数量
        List<DetectionObjectInfo> detectionObjectInfoList = new ArrayList<>();
        objectNumMap.forEach((name, num) -> {
            try {
                ObjectType fObject = objectTypeMapper.selectOne(new LambdaQueryWrapper<ObjectType>().eq(ObjectType::getName, name));
                fObject.setImgSrc(constantFromYml.getObjectImgPath() + fObject.getImgSrc());

                detectionObjectInfoList.add(DetectionObjectInfo.builder()
                        .object(fObject)
                        .detectNum(num)
                        .build());
            } catch (Exception e) {
                //TODO("数据库没有该物品信息的处理")
                log.info("数据库中没有" + name);
                new CustomException("数据库中没有" + name);
            }
        });

        return R.success(DetectionResult.builder()
                .objectInfos(detectionObjectInfoList)
                .build());
    }


    @Override
    public JSONObject ocrPost(@NonNull MultipartFile uploadFile) {
        String urlString = objectDetectionConstant.getOcrServerAddress() + "/ocr";

        JSONObject jsonObject = null;
        try {

            // 换行符
            final String newLine = "\r\n";
            final String boundaryPrefix = "--";
            // 定义数据分隔线
            String BOUNDARY = "------------------------526159930283260523776726";
            // 服务器的域名
//            CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
            URL url = new URL(urlString);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // 设置为POST情
            conn.setRequestMethod("POST");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestProperty("Accept", "*/*");
            conn.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
            // 设置请求头参数
            conn.setRequestProperty("Connection", "keep-Alive");
            conn.setRequestProperty("Charset", "UTF-8");
            conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);

            OutputStream out = new DataOutputStream(conn.getOutputStream());

            // 上传文件

            StringBuilder sb = new StringBuilder();
            sb.append(boundaryPrefix + BOUNDARY + newLine);

            // 文件参数,photo参数名可以随意修改
            sb.append("Content-Disposition: form-data; name=\"file\"; filename=\"" + uploadFile.getOriginalFilename() + "\"" + newLine);
            sb.append("Content-Type: image/png");

            // 参数头设置完以后需要两个换行，然后才是参数内容
            sb.append(newLine);
            sb.append(newLine);
            // 将参数头的数据写入到输出流中
            out.write(sb.toString().getBytes());

            out.write(uploadFile.getBytes());
            // 数据写入到输出流中


            // 定义最后数据分隔线，即--加上BOUNDARY再加上--。
            byte[] end_data = (newLine + boundaryPrefix + BOUNDARY + boundaryPrefix + newLine)
                    .getBytes();
            // 写上结尾标识
            out.write(end_data);
            out.flush();
//            out.close();

            CharacterEncodingFilter filter = new CharacterEncodingFilter();
            filter.setEncoding("UTF-8");

            int responseCode = conn.getResponseCode();
            System.out.println(responseCode);
            System.out.println(conn.getResponseMessage());
            if (responseCode == HttpURLConnection.HTTP_OK) {
                //得到响应流
                InputStream inputStream = conn.getInputStream();
                //获取响应
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }

                jsonObject = JSON.parseObject(stringBuilder.toString());
                reader.close();
                //该干的都干完了,记得把连接断了
                conn.disconnect();
            }

        } catch (Exception e) {
            System.out.println("发送POST请求出现异常！" + e);
            e.printStackTrace();
        }

        return jsonObject;
    }
}
