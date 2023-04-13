package com.szu.refrigerator.controller;

import com.szu.refrigerator.service.BrandService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.annotation.Resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BrandControllerTest {

    @Resource
    private MockMvc mockMvc;

    @Autowired
    BrandService brandService;

    @Test
    public void testGetAll() throws Exception {

        mockMvc.perform(get("/brand")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].bid").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].name").value("格力"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[1].bid").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[1].name").value("美的"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void testGetBrandListAlphabet() throws Exception {

        mockMvc.perform(get("/brand/getBrandListAlphabet")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].letter").value("A"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].data[0].bid").value(9))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].data[0].name").value("奥马"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[1].letter").value("B"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[1].data[0].bid").value(23))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[1].data[0].name").value("博世"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void testGetBrandModels() throws Exception {
        Integer brandId = 2;

        mockMvc.perform(get("/brand/getBrandModels")
                        .param("id", String.valueOf(brandId)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.brand.bid").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.brand.name").value("美的"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.models[0].mid").value(6))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.models[0].name").value("GR-RM429WE-PG2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.models[1].mid").value(7))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.models[1].name").value("BCD-481W(KME49A20TI)"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

}