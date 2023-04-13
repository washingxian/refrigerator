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
public class Refrigerator implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    *  冰箱Id
     * @required
     *
    */
    @TableId(value = "fid", type = IdType.AUTO)
    private Integer fid;

    /**
    *  冰箱昵称
     * @mock fridge1
    */
    @TableField("`name`")
    private String name;

    /**
    *  冰箱型号Id
    */
    @TableField("`mid`")
    private int mid;


}
