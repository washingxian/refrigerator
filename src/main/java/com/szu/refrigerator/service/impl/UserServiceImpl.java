package com.szu.refrigerator.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.szu.refrigerator.Enum.MSG_DATA;
import com.szu.refrigerator.common.R;
import com.szu.refrigerator.dto.controllerDto.account.param.UpdateAccountInfoParamDto;
import com.szu.refrigerator.entity.User;
import com.szu.refrigerator.mapper.UserMapper;
import com.szu.refrigerator.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2023-03-05
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public R<MSG_DATA> updateAccountInfo(UpdateAccountInfoParamDto dto,String token) {
        String uid = token;

        userMapper.update(null,new UpdateWrapper<User>()
                .eq("uid",uid)
                .set("name",dto.getNickName())
                .set("avatar",dto.getAvatarUrl())
                .set("phoneNumber",dto.getPhone())
        );
        return R.success(MSG_DATA.成功);


    }


}
