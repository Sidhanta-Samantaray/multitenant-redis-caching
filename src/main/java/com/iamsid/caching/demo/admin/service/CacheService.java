package com.iamsid.caching.demo.admin.service;

import com.iamsid.caching.demo.config.Constants;
import com.iamsid.caching.demo.model.TenantContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;

@Service
public class CacheService {
    @Autowired
    private RedisCacheManager redisCacheManager;

    public Collection<String> getCacheNames() {
        return this.redisCacheManager.getCacheNames();
    }

    public void evictAll() {
        this.redisCacheManager.getCacheNames()
                .forEach(cacheName -> Objects.requireNonNull(this.redisCacheManager.getCache(cacheName)).clear());
    }

}
