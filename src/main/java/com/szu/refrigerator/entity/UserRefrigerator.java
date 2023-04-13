package com.szu.refrigerator.entity;

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
public class UserRefrigerator implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    *  用户Id
    */
    @TableField("`uid`")
    private String uid;

    /**
    *  冰箱Id
    */
    @TableField("`fid`")
    private Integer fid;

    /**
    *  为默认冰箱
    */
    @TableField("`fDefault`")
    private Boolean fDefault;


}
