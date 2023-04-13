package com.szu.refrigerator;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;
@Slf4j
@SpringBootApplication
@EnableTransactionManagement
@EnableCaching
@MapperScan({"com.szu.refrigerator.mapper","com.szu.refrigerator.mapper"})
public class RefrigeratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(RefrigeratorApplication.class, args);
        log.info("项目启动成功");
    }

}
