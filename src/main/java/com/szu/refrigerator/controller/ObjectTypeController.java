package com.szu.refrigerator.controller;


import com.szu.refrigerator.Enum.OBJECT_CLASSIFICATION;
import com.szu.refrigerator.common.R;
import com.szu.refrigerator.dto.controllerDto.object.result.GetObjectsOfSpecifiedClassificationResultDto;
import com.szu.refrigerator.dto.controllerDto.objectType.result.GetAllObjectTypeResultDto;
import com.szu.refrigerator.entity.FObject;
import com.szu.refrigerator.service.ObjectTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 物品类型控制器
 *
 * @author apple
 * @date 2023/03/07
 */
@RestController
@RequestMapping("/objectType")
public class ObjectTypeController {
    @Autowired
    ObjectTypeService objectTypeService;


    /**
     * 获取所有物品分类（迭代2)
     *
     * @return {@link R}<{@link List}<{@link String}>>
     */
    @GetMapping("getAllObjectClassification")
    public  R<List<OBJECT_CLASSIFICATION>> getAllObjectClassification(){

        return objectTypeService.getAllObjectClassification();
    }



    /**
     * 获取指定分类的物品（迭代2）
     * @param classification 分类
     * @return {@link R}<{@link List}<{@link FObject}>>
     */
    @GetMapping("getObjectsOfSpecifiedClassification/{classification}")
    public R<List<GetObjectsOfSpecifiedClassificationResultDto>> getObjectsOfSpecifiedClassification(@PathVariable String classification){
        return objectTypeService.getObjectsOfSpecifiedClassification(classification);
    }


    /**
     * 获取所有物品类型,根据不同分类分组（迭代2）
     *
     * @return {@link R}<{@link List}<{@link GetAllObjectTypeResultDto}>>
     */
    @GetMapping("getAllObjectType")
   public R<List<GetAllObjectTypeResultDto>> getAllObjectType(){
        return  objectTypeService.getAllObjectType();
   }
}

