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
public class ObjectTag implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    *  物品标签Id
    */
    @TableField("`tag_id`")
    private Integer tag_id;

    /**
    *  标签（eg:有助于减肥，高蛋白）
    */
    @TableId("`tag`")
    private String tag;


}
