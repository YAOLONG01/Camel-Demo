package com.why.dev.camel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

import java.io.IOException;

@SpringBootApplication
@ImportResource("classpath:camelContext.xml")
public class StreamApplication {
    public static void main(String[] args) throws IOException {
        SpringApplication.run(StreamApplication.class, args);
        System.in.read();
    }
}
