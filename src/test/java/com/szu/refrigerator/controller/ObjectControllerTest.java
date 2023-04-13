package com.szu.refrigerator.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.szu.refrigerator.dto.controllerDto.object.param.StoreObjectParamDto;
import com.szu.refrigerator.dto.controllerDto.object.param.TakeOutObjectParamDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ObjectControllerTest {

    @Resource
    private MockMvc mockMvc;

    @Resource
    private ObjectMapper objectMapper;

    private String token;
    private Integer fridgeId;
    private Integer objectId;

    @BeforeEach
    void login() {
        token = "oAqhB5AqzbdZcbJZdfWJs_oEyK5c";
    }

    @Test
    void testGetFridgeObjects() throws Exception {

        mockMvc.perform(get("/object/getFridgeObjects/"+fridgeId.toString())
                        .param("fridgeId", String.valueOf(fridgeId))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

    }

    @Test
    void testGetDefaultFridgeObjects() throws Exception {

        mockMvc.perform(get("/object/getDefaultFridgeObjects")
                        .header("token", token))
                        //.param("token", token))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

    }

    /**
     * 测试存入物品(v3)
     * @throws Exception
     */
    @Test
    void testStoreObjects() throws Exception {
        //new StoreObject
        StoreObjectParamDto storeObjectParamDto = new StoreObjectParamDto();
        storeObjectParamDto.setFid(24);
        List<StoreObjectParamDto.ObjectInfo> objectInfoList = new ArrayList<>();
        StoreObjectParamDto.ObjectInfo objectInfo = new StoreObjectParamDto.ObjectInfo();

        objectInfo.setOtId(5);
        objectInfo.setWarrantTime(15);
        objectInfo.setNum(2);
        //TODO(v3-物品位置)
        objectInfo.setLocation("保鲜层-第二层");

        objectInfoList.add(objectInfo);
        storeObjectParamDto.setObjectInfoList(objectInfoList);


        mockMvc.perform(post("/object/storeObjects")
                        .header("token", token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(storeObjectParamDto))
                        .param("token", token))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

    }

    /**
     * 测试取出物品(v3)
     * @throws Exception
     */
    @Test
    void testTakeOutObject() throws Exception {
        TakeOutObjectParamDto takeOutObjectParamDto = new TakeOutObjectParamDto();
        takeOutObjectParamDto.setFid(24);
        takeOutObjectParamDto.setTypeId(5);
        takeOutObjectParamDto.setNum(1);
        takeOutObjectParamDto.setLocation("保鲜层-第二层");

        MvcResult result = mockMvc.perform(delete("/object")
                        .header("token", token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(takeOutObjectParamDto))
                        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("1"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

    }
}