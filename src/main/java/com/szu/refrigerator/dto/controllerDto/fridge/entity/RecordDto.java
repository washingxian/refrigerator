package com.szu.refrigerator.dto.controllerDto.fridge.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecordDto {
    /**
     * 物品图片路径
     * @mock http://123.342.23.11/images/apple.png
     */
    String objectImgPath;


    /**
     * 物品名称
     * @mock 苹果
     */
    String objectName;

    /**
     * 操作类型
     * @mock 存入
     */
    String operateType;

    /**
     * 操作者昵称
     * @mock 小明
     */
    String operatorName;
    /**
     * 操作数量
     *@mock 3
     */
    String operateNum;


    /**
     * 操作后库存数量
     * @mock 5
     */
    String repositoryNum;


    /**
     * 单位
     * @mock 千克
     */
     String unit;

}
