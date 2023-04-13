package com.szu.refrigerator.mapper;

import com.szu.refrigerator.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 
 * @since 2023-03-05
 */

@Mapper
public interface UserMapper extends BaseMapper<User> {
void updateAll(List<User> list);
}
