package com.szu.refrigerator.dto.objDetectDto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * 检测结果
 *
 * @author 林思彤
 * @date 2023/03/22
 */
@Data
@Builder
public class DetectionResult {

    /**
     * 物品信息
     */
    List<DetectionObjectInfo> objectInfos;

}
