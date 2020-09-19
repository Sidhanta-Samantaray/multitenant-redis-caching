package com.iamsid.geek.vibes.caching.demo.admin.service;

import com.iamsid.geek.vibes.caching.demo.config.TenantContext;
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
        ThreadLocal<String> tenant = new ThreadLocal<>();
        tenant.set("SUPER-ADMIN");
        TenantContext.setTenant(tenant);
        this.redisCacheManager.getCacheNames()
                .forEach(cacheName -> Objects.requireNonNull(this.redisCacheManager.getCache(cacheName)).invalidate());


    }
}
