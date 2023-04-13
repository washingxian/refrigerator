package com.szu.refrigerator.controller;

import com.alibaba.fastjson.JSONObject;
import com.szu.refrigerator.entity.UserTarget;
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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class UserTargetControllerTest {

    MockMvc mvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testGetByUid() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/userTarget")
                        .header("token", "dfvsdgs")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void testUpdate() throws Exception {
        UserTarget target = UserTarget.builder()
                .ut_id(8)
                .uid("212648dd-f050-49bf-a0bb-71688035be7a")
                .target("摸鱼")
                .build();
        String json = JSONObject.toJSONString(target);

        mvc.perform(MockMvcRequestBuilders.put("/userTarget")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void testSave() throws Exception {
        UserTarget target = UserTarget.builder()
                .uid("212648dd-f050-49bf-a0bb-71688035be7a")
                .target("划水")
                .build();
        String json = JSONObject.toJSONString(target);

        mvc.perform(MockMvcRequestBuilders.post("/userTarget")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void testDelete() throws Exception {

        mvc.perform(MockMvcRequestBuilders.delete("/userTarget/"+new Integer(9).toString())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
}