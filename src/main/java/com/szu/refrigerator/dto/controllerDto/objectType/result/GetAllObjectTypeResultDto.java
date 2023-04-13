package com.szu.refrigerator.dto.controllerDto.objectType.result;

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
public class GetAllObjectTypeResultDto {
    /**
     * 物品分类分类
     */
    String classification;
    /**
     * 该分类下所有物品类型
     */
    List<ObjectType> objectTypeList;

}
