package com.llzdemo.hazelcastfortest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(scanBasePackages = {"com.llzdemo.hazelcastfortest"},exclude = {DataSourceAutoConfiguration.class})
public class HazelCastForTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(HazelCastForTestApplication.class, args);
    }

}
