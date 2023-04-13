package com.szu.refrigerator.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Builder;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * 物品类型
 * <p>
 *
 * </p>
 *
 * @author 86188
 * @date 2023/04/09
 * @since 2023-03-12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class ObjectType implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    *  物品类型Id
    */
    @TableId("otId")
    private Integer otId;

    /**
    *  物品类型名
    */
    @TableField("`name`")
    private String name;

    /**
    *  默认保质时间（天）
    */
    @TableField("warrantTime")
    private Integer warrantTime;

    /**
    *  物品分类
    */
    @TableField("`classification`")
    private String classification;

    /**
     * 物品图片
     */
    @TableField("`imgSrc`")
    String imgSrc;


}
