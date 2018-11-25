package com.why.dev.springbootcamelrest;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Component;

@ImportResource("classpath:camelContext.xml")
@SpringBootApplication
public class SpringBootCamelRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootCamelRestApplication.class, args);
    }
}
