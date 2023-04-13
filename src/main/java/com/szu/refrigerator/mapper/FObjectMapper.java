package com.szu.refrigerator.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.szu.refrigerator.dto.controllerDto.object.result.FetFridgeObjectResultDto;
import com.szu.refrigerator.entity.FObject;
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
public interface FObjectMapper extends BaseMapper<FObject> {
void updateAll(List<FObject> list);

void insertAll(List<FObject> list);
    List<FetFridgeObjectResultDto> getDefaultFridgeObjects(@Param("uid") String uid);

    List<FetFridgeObjectResultDto> getFridgeObjects(@Param("fid") int fid);

    boolean takeOutObjects(@Param("fid")int fid,@Param("typeId")int typeId,@Param("location")String location,@Param("num")int num);
}
