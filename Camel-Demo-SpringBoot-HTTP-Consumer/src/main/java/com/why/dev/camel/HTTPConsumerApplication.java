package com.why.dev.camel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:camelContext.xml")
public class HTTPConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(HTTPConsumerApplication.class, args);
    }
}
