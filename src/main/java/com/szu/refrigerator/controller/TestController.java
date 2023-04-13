package com.szu.refrigerator.controller;

import com.szu.refrigerator.common.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("")
   public R<String> test(){
        return  R.success("项目正在运行0.0");
    }
}
