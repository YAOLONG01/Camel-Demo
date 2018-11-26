package com.why.dev.camel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:camelContext1.xml")
public class FileCopyApplication {
    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(FileCopyApplication.class, args);
        Thread.sleep(1000);
    }
}
