package com.szu.refrigerator.dto.controllerDto.message.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MsgDto {
    /**
     * 是否已读
     *
     */
    boolean read;

    /**
     * 信息类型
     * @mock 过期提醒
     */
    String type;

    /**
     * 信息接收时间
     * @mock 2023/4/9 11：27：00
     */
    String receivedTime;

    /**
     * 信息内容
     * @mock 您冰箱里的苹果剩余不多，记得抽空采购哦
     */
    String content;

}
