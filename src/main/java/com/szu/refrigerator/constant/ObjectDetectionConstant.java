package com.szu.refrigerator.constant;


import com.szu.refrigerator.factory.YamlPropertySourceFactory;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@PropertySource(value = { "classpath:/config/objectDetection.yml" }, factory = YamlPropertySourceFactory.class)
@ConfigurationProperties
public class ObjectDetectionConstant {


    @Data
    public static class ObjectInfo {
        private String name;
        private int id;
        private int fridgeId;
    }

    @Data
    public static class Mappings {
        private List<ObjectInfo> objects;
    }

    private String serverAddress;
    private String ocrServerAddress;
    private Mappings mappings;


}
