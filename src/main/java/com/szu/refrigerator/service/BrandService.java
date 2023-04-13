package com.szu.refrigerator.service;

import com.szu.refrigerator.common.R;
import com.szu.refrigerator.dto.controllerDto.brand.result.BrandListAlphabetDto;
import com.szu.refrigerator.dto.controllerDto.brand.result.BrandModelsDto;
import com.szu.refrigerator.entity.Brand;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2023-03-05
 */
public interface BrandService extends IService<Brand> {
    R<List<BrandListAlphabetDto>> getBrandListAlphabet();

    R<BrandModelsDto> getBrandModels(Integer id);
}
