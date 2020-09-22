package com.iamsid.caching.demo.model;


import com.iamsid.caching.demo.config.Constants;
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

    /**
     * @param name
     * @return Prefix the cache store name with the TENANT KEY
     * For SUPER ADMIN no prefix applied
     */
    @Override
    public Cache getCache(String name) {
        log.info("Inside getCache:" + name);
        String tenantId = TenantContext.getTenant().get();
        if (tenantId.equals(Constants.SUPER_ADMIN_TENANT)) {
            return super.getCache(name);
        } else if (name.startsWith(tenantId)) {
            return super.getCache(name);
        }
        return super.getCache(tenantId + "_" + name);
    }

}
