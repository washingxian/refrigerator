package com.szu.refrigerator.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Builder;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2023-03-09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class UserTarget implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    *  目标Id
    */
    @TableId(value = "ut_id", type = IdType.AUTO)
    private Integer ut_id;

    /**
    *  目标
    */
    @TableField("`target`")
    private String target;

    /**
    *  用户Id
    */
    @TableField("`uid`")
    private String uid;


}
