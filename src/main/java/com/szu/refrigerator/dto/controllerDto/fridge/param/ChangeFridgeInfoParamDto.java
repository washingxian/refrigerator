package com.szu.refrigerator.dto.controllerDto.fridge.param;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangeFridgeInfoParamDto {
    /**
     *  冰箱Id
     * @mock 10
     * @required
     */
    private Integer fid;

    /**
     *  冰箱昵称
     * @mock fridge1
     */
    @TableField("`name`")
    private String name;

    /**
     *  冰箱型号Id
     * @mock 2
     */
    @TableField("`mid`")
    private int mid;
}
