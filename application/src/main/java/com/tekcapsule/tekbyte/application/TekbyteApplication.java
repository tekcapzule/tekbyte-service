package com.tekcapsule.tekbyte.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.tekcapsule.tekbyte","com.tekcapsule.core"})
public class TekbyteApplication {

    public static void main(String[] args) {
        SpringApplication.run(TekbyteApplication.class, args);
    }
}
