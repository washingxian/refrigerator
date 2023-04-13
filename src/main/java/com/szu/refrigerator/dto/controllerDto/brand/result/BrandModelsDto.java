package com.szu.refrigerator.dto.controllerDto.brand.result;

import com.szu.refrigerator.entity.Brand;
import com.szu.refrigerator.entity.Model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BrandModelsDto {

    Brand brand;

    List<Model> models;
}
