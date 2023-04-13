package com.szu.refrigerator.service;

import com.szu.refrigerator.common.R;
import com.szu.refrigerator.dto.controllerDto.object.param.StoreObjectParamDto;
import org.springframework.web.multipart.MultipartFile;

public interface OcrService {
    R<StoreObjectParamDto> recognizeObjectsOnTheShopping(MultipartFile image);
}
