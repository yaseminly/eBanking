package org.sid.compteservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class CompteServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CompteServiceApplication.class, args);
    }

}
