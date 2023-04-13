package com.szu.refrigerator.controller;

import com.szu.refrigerator.service.ObjectDetectionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import javax.annotation.Resource;
import java.io.File;
import java.nio.file.Files;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ObjectDetectionControllerTest {
    @Autowired
    ObjectDetectionService objectDetectionService;

    @Resource
    private MockMvc mockMvc;

    /**
     * 测试字符串分析(v3)
     */
    @Test
    void testAnalyzeShoppingList(){
        String str = "乐购超市\n" +
                "日期: 2023年3月31日\n" +
                "时间: 21:51\n" +
                "销售员工号: 016\n" +
                "条目   货物     数量     价格     总计\n" +
                "1      葡萄     2条     ¥25/条   ¥50\n" +
                "2      草莓     1斤     ¥40/斤   ¥40\n" +
                "3      樱桃    2包     ¥15/包   ¥30\n" +
                "4      酸奶     4盒     ¥4/盒    ¥16\n" +
                "5      果酱     1瓶     ¥10/瓶   ¥10\n" +
                "6      奶酪片    1盒     ¥12/盒   ¥12\n" +
                "7      生菜     1斤     ¥4/斤    ¥4\n" +
                "8      黄瓜     1斤     ¥3.5/斤  ¥3.5\n" +
                "鲈鱼     1条     ¥30/条   ¥30\n" +
                "10     西红柿    1斤     ¥3/斤    ¥3";
        String string = "{\"data\":\"乐购超市 日期：2023年3月31日 时间：21:14 销售员工号：001 条目 货物 数量 价格 总计 1 牛奶 2升 ￥6/升 ￥12 2 鸡蛋 10个 ￥0.5 ￥5 3 奶酪 1块 ￥15 ￥15 4 果汁 1瓶 ￥10 ￥10 5 冰淇淋 1盒 ￥20 ￥20 总计： 62 付款方式：支付宝 \"}";

        System.out.println(objectDetectionService.analyzeString(string));
    }

    /**
     * 测试图像识别--迭代3
     * @throws Exception
     */
    @Test
    void testDetectObject() throws Exception {
        File image = new ClassPathResource("img/7ce753af40c52973736194a30e08e9a.jpg").getFile();
        MockMultipartFile file = new MockMultipartFile("image", image.getName(), String.valueOf(MediaType.IMAGE_JPEG), Files.readAllBytes(image.toPath()));

        mockMvc.perform(MockMvcRequestBuilders.multipart("/objectDetection/detect")
                        .file(file)
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());

    }

    /**
     * 测试图文识别--迭代3
     * @throws Exception
     */
    @Test
    void testDetectByShoppingList() throws Exception {
        File image = new ClassPathResource("img/96df18e2f7fa6d0bfb180796558d66e.png").getFile();
        MockMultipartFile file = new MockMultipartFile("image", image.getName(), String.valueOf(MediaType.IMAGE_PNG), Files.readAllBytes(image.toPath()));

        mockMvc.perform(MockMvcRequestBuilders.multipart("/objectDetection/detectByShoppingList")
                        .file(file)
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }
}