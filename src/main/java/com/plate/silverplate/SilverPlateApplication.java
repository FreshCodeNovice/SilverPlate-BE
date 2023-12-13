package com.plate.silverplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SilverPlateApplication {

    public static void main(String[] args) {
        SpringApplication.run(SilverPlateApplication.class, args);
    }

}
