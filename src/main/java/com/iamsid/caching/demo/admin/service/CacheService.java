package com.iamsid.caching.demo.admin.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;

@Service
@Slf4j
public class CacheService {
    @Autowired
    private RedisCacheManager redisCacheManager;

    public Collection<String> getCacheNames() {
        return this.redisCacheManager.getCacheNames();
    }

    /**
     * Use by System Admin for cleaning up all the cache entries
     */
    public void evictAll() {
        log.info("Admin Service-Clear all Cache");
        log.info(this.redisCacheManager.getCacheNames().toString());
        this.redisCacheManager.getCacheNames()
                .forEach(cacheName -> Objects.requireNonNull(this.redisCacheManager.getCache(cacheName)).clear());
    }
}
