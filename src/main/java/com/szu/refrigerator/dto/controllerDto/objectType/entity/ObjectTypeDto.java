package com.szu.refrigerator.dto.controllerDto.objectType.entity;

import com.szu.refrigerator.entity.ObjectType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ObjectTypeDto extends ObjectType {
    /**
     * 单位列表（v4)
     */
    List<String> unitList;
}
