package com.szu.refrigerator.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.szu.refrigerator.common.R;
import com.szu.refrigerator.dto.controllerDto.model.result.GetAllModelInfoResultDto;
import com.szu.refrigerator.entity.Model;
import com.szu.refrigerator.service.ModelService;
import com.szu.refrigerator.service.impl.ModelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 模型控制器
 *
 * @author apple
 * @date 2023/03/07
 */
@RestController
@RequestMapping("/model")
public class ModelController {

    @Autowired
    private ModelService modelService;

    @Autowired
    ModelServiceImpl modelServiceImpl;


    /**
     * 获取所有模型通过品牌id
     *
     * @param bid 品牌id
     * @return {@link R}<{@link List}<{@link Model}>>
     */
    @GetMapping("{bid}")
    public R<List<Model>> getByBid(@PathVariable String bid){
        return R.success(modelService.list(new LambdaQueryWrapper<Model>().eq(Model::getBid,bid)));
    }

    /**
     * 通过型号id获取型号
     *
     * @param mid 模型id
     * @return {@link R}<{@link Model}>
     */
    @GetMapping("getById/{mid}")
    public R<Model> getByMid(@PathVariable int mid){
        return  R.success(modelService.getOne(new QueryWrapper<Model>().eq("mid",mid)));
    }


    /**
     * 通过模型id获取一个模型
     *
     * @param mid 模型id
     * @return {@link R}<{@link Model}>
     */
    @GetMapping("/getOneByMid/{mid}")
        public R<Model> getOneByMid(@PathVariable int mid ){
        return  R.success(modelService.getOne(new QueryWrapper<Model>().eq("mid",mid)));
        }


    /**
     * 获取所有冰箱型号信息(迭代2)
     *
     * @return {@link R}<{@link GetAllModelInfoResultDto}>
     */
    @GetMapping("/getAllModelInfo")
        public R<List<GetAllModelInfoResultDto>> getAllModelInfo(){


        return  modelService.getAllModelInfo();


        }


}

