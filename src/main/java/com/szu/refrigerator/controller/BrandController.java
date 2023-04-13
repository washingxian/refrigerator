package com.szu.refrigerator.controller;


import com.szu.refrigerator.common.R;
import com.szu.refrigerator.dto.controllerDto.brand.result.BrandListAlphabetDto;
import com.szu.refrigerator.dto.controllerDto.brand.result.BrandModelsDto;
import com.szu.refrigerator.entity.Brand;
import com.szu.refrigerator.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 品牌控制器
 *
 * @author apple
 * @date 2023/03/07
 */
@RestController
@RequestMapping("/brand")
public class BrandController {
    @Autowired
    BrandService brandService;

    /**
     * 获取所有
     *
     * @return {@link R}<{@link List}<{@link Brand}>>
     */
    @GetMapping
    public R<List<Brand>> getAll(){
        return R.success(brandService.list());
    }

    /**
     * 获得品牌列表按首字母排序（迭代2）
     *
     * @return {@link List}<{@link BrandListAlphabetDto}>
     */
    @GetMapping("/getBrandListAlphabet")
    public R<List<BrandListAlphabetDto>> getBrandListAlphabet() {
        return brandService.getBrandListAlphabet();
    }


    /**
     * 获取指定品牌的所有型号（迭代2）
     *
     * @param id id
     * @return {@link BrandModelsDto}
     */
    @GetMapping("/getBrandModels")
    public R<BrandModelsDto> getBrandModels(@RequestParam Integer id) {
        return brandService.getBrandModels(id);
    }


}

