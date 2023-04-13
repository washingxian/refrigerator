package com.szu.refrigerator.controller;

import com.szu.refrigerator.common.R;
import com.szu.refrigerator.dto.objDetectDto.DetectionResult;
import com.szu.refrigerator.service.ObjectDetectionService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

/**
 * 物品检测模块
 *
 * @author 86188
 * @date 2023/03/27
 */
@RestController
@RequestMapping("/objectDetection")
public class ObjectDetectionController {

    @Autowired
    ObjectDetectionService objectDetectionService;


    /**
     * 迭代3
     * 上传一张物品图片进行物品检测
     *
     * @param image 图像
     * @return {@link R}<{@link DetectionResult}>
     */
    @PostMapping("/detect")
    R<DetectionResult> detectObject(@NotNull @RequestParam MultipartFile image) {
        return objectDetectionService.detectObject(image);
    }

    @PostMapping("/detectMulti")
    public R<DetectionResult> detectObjects(@NonNull MultipartFile[] images) {
        return objectDetectionService.detectObjects(images);
    }

    /**
     * 迭代3
     * 通过购物清单识别物品
     *
     * @param image 图像
     * @return {@link R}<{@link DetectionResult}>
     */
    @PostMapping("/detectByShoppingList")
    R<DetectionResult> detectByShoppingList(@NotNull @RequestParam MultipartFile image) {
        return objectDetectionService.detectByShoppingList(image);
    }



}
