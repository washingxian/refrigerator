package com.szu.refrigerator.controller;

import com.alibaba.fastjson.JSONObject;
import com.szu.refrigerator.entity.Refrigerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class RefrigeratorControllerTest {
    MockMvc mvc;
    @Autowired
    WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @Test
    void save() throws Exception {

        Refrigerator model = Refrigerator.builder().name("我的冰箱").mid(9).build();
        String json = JSONObject.toJSONString(model);
        mvc.perform(MockMvcRequestBuilders.post("/refrigerator")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("1"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    void getByUid() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/refrigerator")
                        .header("token","susususufajkga")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    void testUpdateFridge() throws Exception {
        Refrigerator model = Refrigerator.builder().fid(2).name("我的冰箱").mid(8).build();
        String json = JSONObject.toJSONString(model);
        mvc.perform(MockMvcRequestBuilders.put("/refrigerator")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("1"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    /**
     * 测试通过冰箱Id解绑冰箱(迭代2）
     * @throws Exception
     */
    @Test
    void testUnboundById() throws Exception {
        Integer fid = new Integer(24);
        mvc.perform(MockMvcRequestBuilders.delete("/refrigerator/"+fid.toString() )
                        .header("token", "oAqhB5AqzbdZcbJZdfWJs_oEyK5c")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("1"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    /**
     * 测试设置默认冰箱(迭代2)
     * @throws Exception
     */
    @Test
    void testSetDefaultFridge() throws Exception {
        Integer fid = new Integer(129);
        mvc.perform(MockMvcRequestBuilders.delete("/refrigerator/"+fid.toString() )
                        .header("token", "oAqhB5AqzbdZcbJZdfWJs_oEyK5c")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("1"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    /**
     * 测试获取当前冰箱结构(v3）
     * @throws Exception
     */
    @Test
    void testGetCurrentRefrigeratorStructure() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/refrigerator/getCurrentRefrigeratorStructure")
                        .header("token","oAqhB5Aexc-0MmYbSA2GVuZvVsGQ")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
}