package com.szu.refrigerator.dto.controllerDto.fridge.result;

import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class getCurrentRefrigeratorStructure {
    /**
     * 对用户展示的内容
     */
    String text;
    /**
     * 给前端识别的值
     */
    String value;
    /**
     * 下一级
     */
    List<getCurrentRefrigeratorStructure> children;

}
