package com.szu.refrigerator.common;

import com.szu.refrigerator.Enum.Code;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
public class R<T> implements Serializable {

    /**
     * 返回码
     * @mock 1
     */
    @ApiModelProperty(value = "编码：1成功，0和其它数字为失败")
    private Integer code; //编码：1成功，0和其它数字为失败
    /**
     * 错误信息
     * @mock 接口调用成功
     */
    @ApiModelProperty(value = "错误信息")
    private String msg; //错误信息
    /**
     * 数据
     */
    @ApiModelProperty(value = "数据")

    private T data; //数据
    /**
     * 动态数据
     */
    @ApiModelProperty(value = "动态数据")
    private Map map = new HashMap(); //动态数据

    public static <T> R<T> success(T object) {
        R<T> r = new R<T>();
        r.data = object;
        r.code = Code.success.getCode();
        return r;
    }

    public static <T> R<T> error(String msg) {
        R r = new R();
        r.msg = msg;
        r.code = Code.error.getCode();
        return r;
    }

    public R<T> add(String key, Object value) {
        this.map.put(key, value);
        return this;
    }

}
