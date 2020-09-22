package com.iamsid.caching.demo.admin.controller;

import com.iamsid.caching.demo.admin.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(value = "/admin/caches", produces = MediaType.APPLICATION_JSON_VALUE)
public class CacheController {
    @Autowired
    private CacheService cacheService;

    @GetMapping(value = "")
    public Collection<String> cacheNames() {
        return this.cacheService.getCacheNames();
    }

    @DeleteMapping(value = "/evict")
    public void evictAll() {
        this.cacheService.evictAll();
    }


}
