package com.szu.refrigerator.service.impl;

import com.szu.refrigerator.common.R;
import com.szu.refrigerator.constant.ProjectConstant;
import com.szu.refrigerator.dto.controllerDto.object.param.StoreObjectParamDto;
import com.szu.refrigerator.service.OcrService;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

public class OcrServiceImpl implements OcrService {

    /**
     * 可用进程
     */
//    ArrayBlockingQueue<Object>  usableProcesses =new ArrayBlockingQueue<>();
    @Override
    public R<StoreObjectParamDto> recognizeObjectsOnTheShopping(MultipartFile image) {

        /**
         * 根据时间戳和图片名称生成临时图片地址
         */
        String tempPath = ProjectConstant.projectPath + "tempImage/" + image.getName().substring(0, image.getName().lastIndexOf(".")) + System.currentTimeMillis() + ".jpg";

        try {
            /**
             * 将图片保存
             */
            InputStream inputStream = image.getInputStream();
            FileOutputStream fileOutputStream = new FileOutputStream(tempPath);
            byte[] bytes = new byte[1024];
            while (inputStream.read(bytes) != -1) {
                fileOutputStream.write(bytes);
            }
            fileOutputStream.close();
            inputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        /**
         * 调佣python程序识别图片内容
         */
        String ocrScriptPath = ProjectConstant.projectPath + ProjectConstant.ocrScriptPath;
        String imagePath = "--image_dir=" + tempPath;
        String detModelPath = String.format("--det_model_dir=%s/PaddleOCR/inference/ch_ppocr_mobile_v2.0_det_infer", ProjectConstant.projectPath);
        String recModelPath = String.format("--rec_model_dir=%s/PaddleOCR/inference/ch_ppocr_mobile_v2.0_rec_infer", ProjectConstant.projectPath);
        String clsModelPath = String.format("--cls_model_dir=%s/PaddleOCR/inference/ch_ppocr_mobile_v2.0_cls_infer", ProjectConstant.projectPath);
        Process python;
        try {
            python = new ProcessBuilder("python", ocrScriptPath, imagePath, detModelPath, recModelPath, clsModelPath).start();
            python.waitFor();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);

        }
        return null;
    }


}