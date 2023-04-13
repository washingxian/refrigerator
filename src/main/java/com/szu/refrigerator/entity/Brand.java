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
 * @since 2023-03-05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class Brand implements Serializable {

    private static final long serialVersionUID = 1L;



    /**
    *  唯一标识
    */
    @TableId(value = "bid", type = IdType.AUTO)
    private Integer bid;

    /**
    *  品牌名称
    */
    @TableField("`name`")
    private String name;



    /**
     *  品牌图片地址
     */
    @TableField("`imgSrc`")
    private String imgSrc;

}
