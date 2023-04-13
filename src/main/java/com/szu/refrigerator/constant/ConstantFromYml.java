package com.szu.refrigerator.constant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
@Component
@Data
public class ConstantFromYml {

    @Value("${serverIpAddress}")
    String serverIpAddress;

    @Value("${staticResourcesPath}")
    String staticResourcesPath;

     @Value("${objectImgPath}")
    String objectImgPath;


    @Value("${modelImgPath}")
    String   modelImgPath;


    @Value("${brandImgPath}")
    String   brandImgPath;

    @Value("${avatarImgPath}")
    String   avatarImgPath;

    /**
     * 颜色正常
     */
    @Value("${color_normal}")
    String   color_normal;

    /**
     * 过期颜色
     */
    @Value("${color_out_of_day}")
    String color_out_of_day;

    /**
     * 接近过期颜色
     */
    @Value("${color_close_to_out_of_day}")
    String color_close_to_out_of_day;

    /**
     * 接近过期的时间
     */
    @Value("${close_to_out_of_day}")
    int close_to_out_of_day;




}
