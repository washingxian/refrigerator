package com.szu.refrigerator.mapper;

import com.szu.refrigerator.entity.UserTarget;
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
public interface UserTargetMapper extends BaseMapper<UserTarget> {
void updateAll(List<UserTarget> list);
}
