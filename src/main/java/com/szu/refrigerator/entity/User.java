package com.szu.refrigerator.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author
 * @since 2023-03-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@AllArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    *  uid
    */
    @TableField("`uid`")
    private String uid;

    /**
    *  昵称
    */
    @TableField("`name`")
    private String name;

    /**
    *  用户头像地址
    */
    @TableField("`avatar`")
    private String avatar;

    /**
    *  手机号码
    */
    @TableField("phoneNumber")
    private Long phoneNumber;

    /**
    *  年龄
    */
    @TableField("`age`")
    private Integer age;

    /**
    *  性别（0女1男）
    */
    @TableField("`gender`")
    private Integer gender;


}
