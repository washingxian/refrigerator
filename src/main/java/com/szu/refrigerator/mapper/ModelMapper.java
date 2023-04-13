package com.szu.refrigerator.mapper;

import com.szu.refrigerator.dto.controllerDto.model.result.GetAllModelInfoResultDto;
import com.szu.refrigerator.entity.Model;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 
 * @since 2023-03-06
 */

@Mapper
public interface ModelMapper extends BaseMapper<Model> {
void updateAll(List<Model> list);
List<GetAllModelInfoResultDto> getAllModelInfo();
}
