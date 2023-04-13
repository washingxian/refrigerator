package com.szu.refrigerator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.szu.refrigerator.Enum.MSG_DATA;
import com.szu.refrigerator.common.R;
import com.szu.refrigerator.dto.controllerDto.object.param.StoreObjectParamDto;
import com.szu.refrigerator.dto.controllerDto.object.param.TakeOutObjectParamDto;
import com.szu.refrigerator.dto.controllerDto.object.result.FetFridgeObjectResultDto;
import com.szu.refrigerator.entity.FObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2023-03-05
 */
public interface ObjectService extends IService<FObject> {

      R<List<FetFridgeObjectResultDto>> getDefaultFridgeObjects(String uid);

      R<List<FetFridgeObjectResultDto>> getFridgeObjects( int fid);


      R<MSG_DATA> storeObjects(StoreObjectParamDto storeObjectParamDto,String token);


      R<MSG_DATA> takeOutObject(@RequestBody TakeOutObjectParamDto takeOutObjectParamDto, @RequestHeader("token")String token);
}
