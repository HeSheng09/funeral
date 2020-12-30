package com.iois.funeral;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.iois.funeral.dao")
public class FuneralApplication {

    public static void main(String[] args) {
        SpringApplication.run(FuneralApplication.class, args);
    }

}
