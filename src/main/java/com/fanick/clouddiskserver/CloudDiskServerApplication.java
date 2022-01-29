package com.fanick.clouddiskserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
@ComponentScan(basePackages = {"com.fanick"})
@EnableTransactionManagement
public class CloudDiskServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudDiskServerApplication.class, args);
    }

}
