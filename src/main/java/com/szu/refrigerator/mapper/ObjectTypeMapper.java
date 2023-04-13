package com.szu.refrigerator.mapper;

import com.szu.refrigerator.dto.controllerDto.object.result.GetObjectsOfSpecifiedClassificationResultDto;
import com.szu.refrigerator.dto.controllerDto.objectType.result.GetAllObjectTypeResultDto;
import com.szu.refrigerator.entity.ObjectType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 
 * @since 2023-03-12
 */

@Mapper
public interface ObjectTypeMapper extends BaseMapper<ObjectType> {
void updateAll(List<ObjectType> list);

     List<GetObjectsOfSpecifiedClassificationResultDto> getObjectsOfSpecifiedClassification(@Param("classification") String classification);

     List<GetAllObjectTypeResultDto> getAllObjectType();

}
