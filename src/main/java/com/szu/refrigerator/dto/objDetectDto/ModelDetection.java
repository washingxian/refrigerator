package com.szu.refrigerator.dto.objDetectDto;

import lombok.Data;

/**
 * 这个class是调用模型接口返回的原始数据
 *
 * @author 林思彤
 * @date 2023/03/22
 */
@Data
public class ModelDetection {
    float x1, y1, x2, y2;
    float confidence;
    int id;
}
