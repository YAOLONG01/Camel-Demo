package com.why.camel.http;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:camelContext.xml")
public class CamelDemoSpringBootHttpApplication {

    public static void main(String[] args) {
        SpringApplication.run(CamelDemoSpringBootHttpApplication.class, args);
    }
}
