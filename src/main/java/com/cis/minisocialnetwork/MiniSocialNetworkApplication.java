package com.cis.minisocialnetwork;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class}
)
public class MiniSocialNetworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiniSocialNetworkApplication.class, args);
    }

}
