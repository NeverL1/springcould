package com.ljc.eurekasupport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.ljc.*"})
public class EurekaSupportApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaSupportApplication.class, args);
    }

}
