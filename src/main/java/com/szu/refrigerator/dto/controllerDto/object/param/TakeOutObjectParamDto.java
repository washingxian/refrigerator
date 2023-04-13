package com.szu.refrigerator.dto.controllerDto.object.param;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TakeOutObjectParamDto {

    /**
     * 冰箱Id
     * @required
     */
    int fid;

    /**
     * 物品类型Id
     * @required
     */
    int typeId;

    /**
     * 数量
     * @required
     */
    int num;


    /**
     * 位置
     * @required
     * @mock 保鲜层/第一层
     */
    String location;
}
