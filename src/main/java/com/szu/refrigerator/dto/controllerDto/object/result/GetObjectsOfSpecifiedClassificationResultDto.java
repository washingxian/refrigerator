package com.szu.refrigerator.dto.controllerDto.object.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetObjectsOfSpecifiedClassificationResultDto {

    /**
     * 名字
     * @mock 香蕉
     */
    String name;
    /**
     * 类型id
     */
    String typeId;

    /**
     * 图片路径
     * @mock a/b/c/apple.png
     */
    String imgSrc;

    /**
     * 默认保质时间(天）
     * @mock 10
     */
    private Integer warrantTime;

}
