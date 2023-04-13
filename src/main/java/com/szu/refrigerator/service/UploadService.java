package com.szu.refrigerator.service;

import com.szu.refrigerator.Enum.MSG_DATA;
import com.szu.refrigerator.common.R;
import com.szu.refrigerator.constant.ConstantFromYml;
import com.szu.refrigerator.constant.ProjectConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class UploadService {
    @Autowired
    ConstantFromYml constantFromYml;
    public R<String> uploadAvatar(MultipartFile image) throws IOException {
        String fileName = System.currentTimeMillis()+".png";
        String filePath = ProjectConstant.projectPath+constantFromYml.getAvatarImgPath()+fileName;
        image.transferTo(new File(filePath));
        return R.success(filePath);

    }

}
