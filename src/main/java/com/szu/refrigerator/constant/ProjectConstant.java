package com.szu.refrigerator.constant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class ProjectConstant {

public static final  String projectPath = System.getProperty("user.dir")+"/";

public static final String  ocrScriptPath = "PaddleOCR/tools/infer/predict_system.py";

}
