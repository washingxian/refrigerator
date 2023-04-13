package com.szu.refrigerator.dto.objDetectDto;

import lombok.Builder;
import lombok.Data;


/**
 * 图片中一个对象的一个检测结果
 *
 * @author 林思彤
 * @date 2023/03/22
 */
@Data
@Builder
public class DetectionInfo {

    /**
     * 置信度
     */
    float confidence;


    /**
     * 两个对角线的坐标，以图片的像素pixel为单位
     */
    float x1, y1;
    float x2, y2;

}
