package com.szu.refrigerator.controller;


import com.szu.refrigerator.Enum.MSG_DATA;
import com.szu.refrigerator.common.R;
import com.szu.refrigerator.dto.controllerDto.object.param.StoreObjectParamDto;
import com.szu.refrigerator.dto.controllerDto.object.param.TakeOutObjectParamDto;
import com.szu.refrigerator.dto.controllerDto.object.result.FetFridgeObjectResultDto;
import com.szu.refrigerator.service.ObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 物品控制器
 *
 * @author 冼铧城
 * @date 2023/03/07
 */
@RestController
@RequestMapping("/object")
public class ObjectController {

@Autowired
    ObjectService objectService;


    /**
     * 获取用户默认冰箱里面的物品(迭代2）
     *
     * @param token token
     * @return {@link R}<{@link List}<{@link FetFridgeObjectResultDto}>>
     */
    @GetMapping("getDefaultFridgeObjects")
     public R<List<FetFridgeObjectResultDto>> getDefaultFridgeObjects(@RequestHeader("token")String token){
        return  objectService.getDefaultFridgeObjects(token);
     }

    /**
     * 存储物品（迭代2）(v3)
     *
     * @param storeObjectParamDto 存储物品参数dto
     * @param token               token
     * @return {@link R}<{@link MSG_DATA}>
     */
    @PostMapping("storeObjects")
    public R<MSG_DATA> storeObjects(@RequestBody StoreObjectParamDto storeObjectParamDto, @RequestHeader("token")String token){

        return objectService.storeObjects(storeObjectParamDto,token);
     }

    /**
     * 取出物品(迭代2）(v3)
     *
     * @param takeOutObjectParamDto 取出物品参数dto
     * @param token                 token
     * @return {@link R}<{@link MSG_DATA}>
     */
    @DeleteMapping
   public R<MSG_DATA> takeOutObject(@RequestBody TakeOutObjectParamDto takeOutObjectParamDto,@RequestHeader("token")String token){
      return objectService.takeOutObject(takeOutObjectParamDto,token);
   }






}

