package com.szu.refrigerator.mapper;

import com.szu.refrigerator.dto.controllerDto.fridge.result.GetUserRefrigeratorResultDto;
import com.szu.refrigerator.entity.Refrigerator;
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
 * @since 2023-03-09
 */

@Mapper
public interface RefrigeratorMapper extends BaseMapper<Refrigerator> {
void updateAll(List<Refrigerator> list);

List<GetUserRefrigeratorResultDto>  getUserRefrigerator(@Param("uid") String uid);

    String  getCurrentRefrigeratorStructure(@Param("uid") String uid);

}
