package com.szu.refrigerator.dto.controllerDto.account.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BindCellPhoneNumParamDto {
    /**
     * 验证码
     */
   String code;
    /**
     * 手机号
     */
    String phoneNum;
}
