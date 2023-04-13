package com.szu.refrigerator.proto;


import com.szu.refrigerator.proto.ObjectDetector.DetectResult;
import com.szu.refrigerator.proto.ObjectDetector.ObjectDetectorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ObjectDetectorServiceTest {


    @Autowired
    ObjectDetectorService objectDetectorService;

    @Test
    public void test() {
        File file = new File("C:\\Users\\aojoie\\Desktop\\detect\\3.jpg");
        byte[] byteArray = new byte[(int) file.length()];
        try (FileInputStream fis = new FileInputStream(file);
             ByteArrayOutputStream bos = new ByteArrayOutputStream()) {

            List<DetectResult> detectResultList = objectDetectorService.detect(fis);


            for (DetectResult result : detectResultList) {
                System.out.println(result.toString());
            }

//            int len;
//            byte[] buffer = new byte[1024];
//            while ((len = fis.read(buffer)) != -1) {
//                bos.write(buffer, 0, len);
//            }
//            byteArray = bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    

}
