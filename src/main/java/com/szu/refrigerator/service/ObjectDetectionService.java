package com.szu.refrigerator.service;

import com.alibaba.fastjson.JSONObject;
import com.szu.refrigerator.common.R;
import com.szu.refrigerator.dto.objDetectDto.DetectionResult;
import lombok.NonNull;
import org.springframework.web.multipart.MultipartFile;

public interface ObjectDetectionService {

    R<DetectionResult> detectObject(@NonNull MultipartFile uploadFile);

    R<DetectionResult> detectObjects(@NonNull MultipartFile[] uploadFiles);

    R<DetectionResult> detectByShoppingList(@NonNull MultipartFile uploadFile);

    R<DetectionResult> analyzeString(String shoppingList);

    JSONObject ocrPost(@NonNull MultipartFile uploadFile);
}
