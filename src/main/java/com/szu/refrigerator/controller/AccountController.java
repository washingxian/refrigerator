package com.szu.refrigerator.controller;

import com.szu.refrigerator.Enum.MSG_DATA;
import com.szu.refrigerator.common.R;
import com.szu.refrigerator.dto.controllerDto.account.param.BindCellPhoneNumParamDto;
import com.szu.refrigerator.dto.controllerDto.account.param.LoginParamDto;
import com.szu.refrigerator.dto.controllerDto.account.param.UpdateAccountInfoParamDto;
import com.szu.refrigerator.dto.controllerDto.account.result.LoginResultDto;
import com.szu.refrigerator.service.LoginService;
import com.szu.refrigerator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 账号模块
 *
 * @author 86188
 * @date 2023/03/01
 */
@RestController
@RequestMapping("account")
public class AccountController {

    @Autowired
    LoginService loginService;
    @Autowired
    UserService userService;

    /**
     * 登录
     * @param dto dto
     * @return {@link R}<{@link LoginResultDto}>
     */
    @PostMapping("login")
    R<LoginResultDto> login(@RequestBody LoginParamDto dto){
       return loginService.login(dto);
    }


    /**
     * 更新账号信息(v4)
     *
     * @param dto   dto
     * @param token token
     * @return {@link R}<{@link MSG_DATA}>
     */
    @PutMapping("/update")
    R<MSG_DATA> updateAccountInfo(@RequestBody UpdateAccountInfoParamDto dto,@RequestHeader("token")String token){
     return userService.updateAccountInfo(dto,token);
    }

    /**
     * 获取手机验证码（v4）
     *
     * @param token token
     * @param phoneNum 手机号
     * @return {@link R}<{@link MSG_DATA}>
     */
    @GetMapping("/getCellPhoneVerificationCode")
    R<MSG_DATA> getCellPhoneVerificationCode(@RequestHeader("token")String token,String phoneNum){
        return  null;
    }


    /**
     * 绑定手机号（v4)
     *
     * @param dto   dto
     * @param token token
     * @return {@link R}<{@link MSG_DATA}>
     */
    @PostMapping("bindPhoneNumber")
    R<MSG_DATA> bindPhoneNum(@RequestBody BindCellPhoneNumParamDto dto,@RequestHeader("token")String token){
        return  null;
    }



}
