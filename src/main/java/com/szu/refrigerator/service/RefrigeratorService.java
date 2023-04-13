package com.szu.refrigerator.service;

import com.szu.refrigerator.Enum.MSG_DATA;
import com.szu.refrigerator.common.R;
import com.szu.refrigerator.dto.controllerDto.fridge.result.GetUserRefrigeratorResultDto;
import com.szu.refrigerator.dto.controllerDto.fridge.result.getCurrentRefrigeratorStructure;
import com.szu.refrigerator.entity.Refrigerator;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2023-03-05
 */
public interface RefrigeratorService extends IService<Refrigerator> {

    void saveWithUser(Refrigerator refrigerator,String uid);

    List<GetUserRefrigeratorResultDto> getUserRefrigerator(String uid);

     R<MSG_DATA> setDefaultFridge( Integer fid , String token);


     R<MSG_DATA> updateFridge(@RequestBody Refrigerator refrigerator);
     R<MSG_DATA> unboundById( Integer fid,String token);

    R<List<getCurrentRefrigeratorStructure>> getCurrentRefrigeratorStructure(String token);
}
