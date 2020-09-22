package com.iamsid.caching.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class MultiTenantRedisCachingApplication {

    public static void main(String[] args) {
        SpringApplication.run(MultiTenantRedisCachingApplication.class, args);
    }

}
