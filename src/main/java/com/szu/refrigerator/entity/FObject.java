package com.szu.refrigerator.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

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
public class FObject implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    *  物品Id
    */
    @TableId(value = "oid", type = IdType.AUTO)
    private Integer oid;

    /**
    *  物品类型Id
    */
    @TableField("typeId")
    private Integer typeId;

    /**
    *  冰箱Id
    */
    @TableField("`fid`")
    private Integer fid;

    /**
    *  保质时间(天）
    */
    @TableField("warrantTime")
    private Integer warrantTime;

    /**
     *  存入冰箱的日期
     */
    @TableField("storedDate")
    private Date storedDate;

    /**
     *  存放位置
     */
    @TableField("location")
    private String location;



}
