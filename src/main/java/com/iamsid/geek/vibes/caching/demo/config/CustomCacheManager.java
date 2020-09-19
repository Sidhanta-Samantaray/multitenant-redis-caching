package com.iamsid.geek.vibes.caching.demo.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;


@Slf4j
public class CustomCacheManager extends RedisCacheManager {
    public CustomCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration) {
        super(cacheWriter, defaultCacheConfiguration);

    }


    @Override
    public Cache getCache(String name) {
        log.info("Inside getCache:" + name);
        String tenantId = TenantContext.getTenant().get();
        if (tenantId.equals("SUPER-ADMIN")) {
            return super.getCache(name);
        }
        return super.getCache(tenantId + "_" + name);
    }

}
