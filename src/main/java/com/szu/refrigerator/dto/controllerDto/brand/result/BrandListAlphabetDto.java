package com.szu.refrigerator.dto.controllerDto.brand.result;

import java.util.List;
import com.szu.refrigerator.entity.Brand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BrandListAlphabetDto {

    char letter;

    List<Brand> data;
}
