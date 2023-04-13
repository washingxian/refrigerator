package com.szu.refrigerator.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2023-03-06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class Model implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    *  id
    */
    @TableId(value = "mid", type = IdType.AUTO)
    private Integer mid;

    /**
    *  型号名称
    */
    @TableField("`name`")
    private String name;


    /**
    *  品牌Id
    */
    @TableField("bid")
    private Integer bid;

    /**
     * 图片路径
     */
    @TableField("imgSrc")
    private String imgSrc;


    /**
     * 冰箱结构
     */
    @TableField("structure")
    private String structure;





}
