package com.szu.refrigerator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.szu.refrigerator.Enum.MSG_DATA;
import com.szu.refrigerator.common.R;
import com.szu.refrigerator.constant.ConstantFromYml;
import com.szu.refrigerator.dto.controllerDto.object.param.StoreObjectParamDto;
import com.szu.refrigerator.dto.controllerDto.object.param.TakeOutObjectParamDto;
import com.szu.refrigerator.dto.controllerDto.object.result.FetFridgeObjectResultDto;
import com.szu.refrigerator.entity.FObject;
import com.szu.refrigerator.mapper.FObjectMapper;
import com.szu.refrigerator.mapper.UserRefrigeratorMapper;
import com.szu.refrigerator.service.ObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2023-03-05
 */
@Service
public class ObjectServiceImpl extends ServiceImpl<FObjectMapper, FObject> implements ObjectService {

    @Autowired
    FObjectMapper fObjectMapper;

    @Autowired
    UserRefrigeratorMapper userRefrigeratorMapper;
   @Autowired
    ConstantFromYml constantFromYml;


    @Override
    public R<List<FetFridgeObjectResultDto>> getDefaultFridgeObjects(String token) {
        String uid = token;
        /**
         * 具体查询逻辑通过sql语句执行
         */
        List<FetFridgeObjectResultDto> defaultFridgeObjects = fObjectMapper.getDefaultFridgeObjects(uid);
        defaultFridgeObjects.forEach(obj-> {
            /**
             * 拼接图片路径
             */
            obj.setImgSrc(constantFromYml.getObjectImgPath()+obj.getImgSrc());

            /**
             * 根据物品的剩余保质时间设置物品颜色
             */
            if(obj.getShortestWarrantyTime()<0){
                obj.setColor(constantFromYml.getColor_out_of_day());
            }
            else if(obj.getShortestWarrantyTime()<constantFromYml.getClose_to_out_of_day()){
                obj.setColor(constantFromYml.getColor_close_to_out_of_day());
            }
            else {
                obj.setColor(constantFromYml.getColor_normal());
            }

        });

        return  R.success(defaultFridgeObjects);
    }

    @Override
    public R<List<FetFridgeObjectResultDto>> getFridgeObjects(int fid) {

        /**
         * 具体查询逻辑通过sql语句执行
         */
        List<FetFridgeObjectResultDto> fridgeObjects = fObjectMapper.getFridgeObjects(fid);

        fridgeObjects.forEach(obj-> {
            /**
             * 拼接图片路径
             */
            obj.setImgSrc(constantFromYml.getObjectImgPath()+obj.getImgSrc());

            /**
             * 根据物品的剩余保质时间设置物品颜色
             */
            if(obj.getShortestWarrantyTime()<0){
                obj.setColor(constantFromYml.getColor_out_of_day());
            }
            else if(obj.getShortestWarrantyTime()<constantFromYml.getClose_to_out_of_day()){
                obj.setColor(constantFromYml.getColor_close_to_out_of_day());
            }
            else {
                obj.setColor(constantFromYml.getColor_normal());
            }

        });

        return  R.success(fridgeObjects);



    }

    /**
     * 目前token还没有用，后面拿来生成存放记录。
     */
    @Override
    public R<MSG_DATA> storeObjects(StoreObjectParamDto storeObjectParamDto, String token) {

        /**
         * 这些物品要存到这个冰箱
         */
        int fid = storeObjectParamDto.getFid();

        ArrayList<FObject> fObjects = new ArrayList<>();

        /**
         * 参数里面有几类物品，每类物品有多个，虽然目前ui一次只存一种物品，但是后面有图像识别的时候，一次就要存一堆不同种类的物品
         */
        storeObjectParamDto.getObjectInfoList().forEach(objectInfo ->{

            /**
             * 每类物品有多少数量就生成多少冰箱物品对象
             */
            for (int i = 0; i < objectInfo.getNum(); i++) {
                System.out.println(objectInfo.getLocation());
                fObjects.add(FObject.builder()
                                .fid(fid)
                                .warrantTime(objectInfo.getWarrantTime())
                                .typeId(objectInfo.getOtId())
                                .location(objectInfo.getLocation())
                                .build());
            }
        });
        /**
         * 一次性存入数据库
         */
        fObjectMapper.insertAll(fObjects);

        return R.success(MSG_DATA.成功);
    }

    @Override
    public R<MSG_DATA> takeOutObject(TakeOutObjectParamDto takeOutObjectParamDto, String token) {
        /**
         * 从fid冰箱里拿出num数量的typeId类型的物品，默认拿最快过期的那几个,具体实现在sql语句中
         */
        boolean take = fObjectMapper.takeOutObjects(takeOutObjectParamDto.getFid(), takeOutObjectParamDto.getTypeId(), takeOutObjectParamDto.getLocation(), takeOutObjectParamDto.getNum());
        if (take)
            return R.success(MSG_DATA.成功);
        return R.error(MSG_DATA.失败.toString());
    }
}
