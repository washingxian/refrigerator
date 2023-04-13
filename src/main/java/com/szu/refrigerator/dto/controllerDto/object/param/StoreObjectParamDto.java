package com.szu.refrigerator.dto.controllerDto.object.param;

import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 存储物品参数dto
 *
 * @author 86188
 * @date 2023/03/12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class StoreObjectParamDto {


    /**
     * 冰箱Id,指定存放的冰箱
     * @required
     */
    int fid;

    /**
     * 物品和数量列表
     * @required
     */
    List<ObjectInfo>  objectInfoList;


    /**
     * 物品信息
     *
     * @author 冼铧城
     * @date 2023/03/12
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ObjectInfo{

        /**
         *  物品类型Id
         * @required
         */
        private Integer otId;

        /**
         *  保质时间(天）
         * @required
         */
        private Integer warrantTime;

        /**
         * 数量
         * @requierd
         */
        int num;


        /**
         * 存放的位置(v3)
         * @mock 1-2
         */
        String location;

        /**
         * 单位
         * @mock 个
         */
        String unit;
    }

}
