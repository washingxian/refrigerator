package com.szu.refrigerator.dto.controllerDto.object.result;

import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class FetFridgeObjectResultDto {

    /**
     * 这几个物品中最短的剩余保质时间
     * @mock 10
     *
     */

    int shortestWarrantyTime ;

    /**
     * 物品数量
     * @mock 10
     *
     */
    int num;

    /**
     * 物品图片地址
     * @mock  http://23.435.23.34/images/icon.jpg
     *
     */
    String imgSrc;

    /**
     * 物品名称
     * @mock 苹果
     *
     */
    String name;

    /**
     * 物品类型Id
     * @mock 10
     *
     */
    int typeId;

    /**
     * 颜色
     * @mock #ffd08c
     */
    String color;

    /**
     * 位置
     */
    String location;

    /**
     * 分类
     */
    String classification;


    /**
     * 单位
     * @mock 个
     */
    String unit;

}
