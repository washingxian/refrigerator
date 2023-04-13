package com.szu.refrigerator.dto.wxApiDto.resultDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WxLoginResultDto {
    String session_key;
    String openid;
}
