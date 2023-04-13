package com.szu.refrigerator.controller;

import com.szu.refrigerator.common.R;
import com.szu.refrigerator.dto.controllerDto.homepage.result.HomepageInfoResultDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 主页模块
 *
 * @author 86188
 * @date 2023/04/09
 */
@RequestMapping("homepage")
@RestController
public class HomepageController {
    /**
     * 获取主页信息(v4)
     *
     * @return {@link R}<{@link HomepageInfoResultDto}>
     */
    @GetMapping
   R<HomepageInfoResultDto>  getHomepageInf(){
       return  null;
   }
}
