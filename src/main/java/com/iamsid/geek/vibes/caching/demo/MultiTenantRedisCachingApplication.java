package com.iamsid.geek.vibes.caching.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableCaching
@EnableAspectJAutoProxy
public class MultiTenantRedisCachingApplication {

    public static void main(String[] args) {
        SpringApplication.run(MultiTenantRedisCachingApplication.class, args);
    }

}
