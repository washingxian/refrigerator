package com.szu.refrigerator.service;

import com.szu.refrigerator.common.R;
import com.szu.refrigerator.dto.controllerDto.model.result.GetAllModelInfoResultDto;
import com.szu.refrigerator.entity.Model;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2023-03-05
 */
public interface ModelService extends IService<Model> {
    public R<List<GetAllModelInfoResultDto>> getAllModelInfo();
}
