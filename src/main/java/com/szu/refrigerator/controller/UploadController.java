package com.szu.refrigerator.controller;

import com.szu.refrigerator.common.R;
import com.szu.refrigerator.service.UploadService;
import lombok.Data;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 上传控制器
 *
 * @author apple
 * @date 2023/04/04
 */
@RestController
@RequestMapping("upload")
public class UploadController {
    @Autowired
    UploadService uploadService;
    @PostMapping("avatar")
  R<String> uploadAvatar(@NonNull  @RequestParam MultipartFile avatar) throws IOException {
      return  uploadService.uploadAvatar(avatar);
  }
}
