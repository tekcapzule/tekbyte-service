package com.tekcapzule.tekbyte.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.tekcapzule.tekbyte","com.tekcapzule.core"})
public class TekbyteApplication {

    public static void main(String[] args) {
        SpringApplication.run(TekbyteApplication.class, args);
    }
}
