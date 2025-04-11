package com.estsoft.restapiaccessdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class RestApiAccessDataApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestApiAccessDataApplication.class, args);
    }

}
