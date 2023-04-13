package com.szu.refrigerator.dto.controllerDto.account.result;

import com.szu.refrigerator.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LoginResultDto {
    /**
     * 用户基本信息
     */
  User baseInfo;
}
