package com.szu.refrigerator.dto.objDetectDto;

import com.szu.refrigerator.entity.ObjectType;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * 检测对象（单个对象）的结果信息
 *
 * @author 林思彤
 * @date 2023/03/22
 */
@Data
@Builder
public class DetectionObjectInfo {
    /**
     * 物品类型
     */
    ObjectType object;

    /**
     * 检测出来的数量
     */
    int detectNum;

}
