package com.szu.refrigerator.Enum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Code {
    success(1,"成功"),
    error(0,"失败");
    int code;
    String msg;
}
