package com.szu.refrigerator.service;

import com.szu.refrigerator.Enum.OBJECT_CLASSIFICATION;
import com.szu.refrigerator.common.R;
import com.szu.refrigerator.dto.controllerDto.object.result.GetObjectsOfSpecifiedClassificationResultDto;
import com.szu.refrigerator.dto.controllerDto.objectType.result.GetAllObjectTypeResultDto;
import com.szu.refrigerator.entity.ObjectType;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2023-03-05
 */
public interface ObjectTypeService extends IService<ObjectType> {
    R<List<GetObjectsOfSpecifiedClassificationResultDto>> getObjectsOfSpecifiedClassification(@PathVariable String classification);

    R<List<OBJECT_CLASSIFICATION>>  getAllObjectClassification();

    R<List<GetAllObjectTypeResultDto>> getAllObjectType();
}
