package com.szu.refrigerator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.szu.refrigerator.Enum.OBJECT_CLASSIFICATION;
import com.szu.refrigerator.common.R;
import com.szu.refrigerator.constant.ConstantFromYml;
import com.szu.refrigerator.dto.controllerDto.object.result.GetObjectsOfSpecifiedClassificationResultDto;
import com.szu.refrigerator.dto.controllerDto.objectType.result.GetAllObjectTypeResultDto;
import com.szu.refrigerator.entity.ObjectType;
import com.szu.refrigerator.mapper.ObjectTypeMapper;
import com.szu.refrigerator.service.ObjectTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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
public class ObjectTypeServiceImpl extends ServiceImpl<ObjectTypeMapper, ObjectType> implements ObjectTypeService {

    @Autowired
    ObjectTypeMapper objectTypeMapper;

    @Autowired
    ConstantFromYml constantFromYml;
    @Override
    public R<List<GetObjectsOfSpecifiedClassificationResultDto>> getObjectsOfSpecifiedClassification(String classification) {
        /**
         * 获取指定分类的物品类型
         * 具体实现在sql语句中
         */
        List<GetObjectsOfSpecifiedClassificationResultDto> objectsOfSpecifiedClassification = objectTypeMapper.getObjectsOfSpecifiedClassification(classification);

        /**
         * 拼接图片路径
         */
        objectsOfSpecifiedClassification.forEach(o->o.setImgSrc(constantFromYml.getObjectImgPath()+o.getImgSrc()));


        return  R.success(objectsOfSpecifiedClassification);
    }

    @Override
    public R<List<OBJECT_CLASSIFICATION>> getAllObjectClassification() {

        /**
         * 从枚举类中获取所有物品分类
         */
        return  R.success(Arrays.asList(OBJECT_CLASSIFICATION.values()));
    }

    @Override
    public R<List<GetAllObjectTypeResultDto>> getAllObjectType() {

        List<GetAllObjectTypeResultDto> allObjectType = objectTypeMapper.getAllObjectType();

        /**
         * 拼接图片路径
         */
       allObjectType.forEach(classification->classification.getObjectTypeList().forEach(objectType -> objectType.setImgSrc(constantFromYml.getObjectImgPath()+objectType.getImgSrc())));

        return  R.success(allObjectType);

    }

}
