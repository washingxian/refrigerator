package com.szu.refrigerator.dto.controllerDto.fridge.result;

import com.szu.refrigerator.dto.controllerDto.fridge.entity.RecordDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetAllRecordResultDto {
    /**
     * 月份
     */
    String month;

    /**
     * 记录列表
     */
    List<RecordDto> recordList;
}
