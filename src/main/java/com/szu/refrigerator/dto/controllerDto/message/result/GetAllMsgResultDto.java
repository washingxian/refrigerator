package com.szu.refrigerator.dto.controllerDto.message.result;

import com.szu.refrigerator.dto.controllerDto.message.entity.MsgDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetAllMsgResultDto {
    /**
     * 未读数量
     */
    int notReadNum;

    /**
     * 信息列表
     */
    List<MsgDto> msgList;
}
