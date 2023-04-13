package com.szu.refrigerator.mapper;

import com.szu.refrigerator.entity.ObjectTag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
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
public interface ObjectTagMapper extends BaseMapper<ObjectTag> {
void updateAll(List<ObjectTag> list);
}
