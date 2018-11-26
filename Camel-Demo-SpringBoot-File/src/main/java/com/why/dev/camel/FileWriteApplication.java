package com.why.dev.camel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:camelContext2.xml")
public class FileWriteApplication {
    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(FileWriteApplication.class, args);
        synchronized (FileWriteApplication.class) {
            FileWriteApplication.class.wait();
        }
    }
}