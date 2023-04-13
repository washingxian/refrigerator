package com.szu.refrigerator.service;

import com.szu.refrigerator.Enum.MSG_DATA;
import com.szu.refrigerator.common.R;
import com.szu.refrigerator.dto.controllerDto.account.param.UpdateAccountInfoParamDto;
import com.szu.refrigerator.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2023-03-05
 */
public interface UserService extends IService<User> {
  R<MSG_DATA> updateAccountInfo(UpdateAccountInfoParamDto dto,String token);
}
