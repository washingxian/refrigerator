package com.szu.refrigerator.dto.controllerDto.account.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginParamDto {
    /**
     * 用户进入小程序后全段可以拿到code
     */
    String code;

}
