package com.szu.refrigerator.mapper;

import com.szu.refrigerator.entity.Brand;
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
public interface BrandMapper extends BaseMapper<Brand> {
void updateAll(List<Brand> list);
}
