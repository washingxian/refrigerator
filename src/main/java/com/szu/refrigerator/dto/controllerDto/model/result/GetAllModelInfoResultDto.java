package com.szu.refrigerator.dto.controllerDto.model.result;

import com.szu.refrigerator.entity.Brand;
import com.szu.refrigerator.entity.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllModelInfoResultDto {

        /**
         * 品牌
         */
        Brand brand;
        /**
         * 模型列表
         */
        List<Model> modelList;

}
