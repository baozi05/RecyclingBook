package org.example.baozi.recyclingbook;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.example.baozi.recyclingbook.mapper")
public class RecyclingBookApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecyclingBookApplication.class, args);
    }

}
