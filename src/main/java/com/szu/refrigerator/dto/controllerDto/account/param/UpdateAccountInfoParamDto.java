package com.szu.refrigerator.dto.controllerDto.account.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAccountInfoParamDto {

    /**
     * 头像路径
     */
    String avatarUrl;
    /**
     * 昵称
     */
    String nickName;
    /**
     * 电话
     */
    String phone;

    /**
     * 个性签名
     */
    String personalizedSignature;
}
