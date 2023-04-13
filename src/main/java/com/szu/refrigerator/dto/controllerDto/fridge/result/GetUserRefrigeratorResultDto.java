package com.szu.refrigerator.dto.controllerDto.fridge.result;

import com.szu.refrigerator.entity.Refrigerator;
import com.szu.refrigerator.util.MyObjectUtil;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUserRefrigeratorResultDto extends Refrigerator {



    /**
     * 冰箱品牌名称
     */
    String brand;

    /**
     * 冰箱型号名称
     */
    String model;


    /**
     * 背景图地址
     */
    String backgroundUrl;

    /**
     * 是否为默认冰箱
     */
    boolean fDefault;

}
