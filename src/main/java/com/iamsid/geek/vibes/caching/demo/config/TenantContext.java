package com.iamsid.geek.vibes.caching.demo.config;

public class TenantContext {
    private static ThreadLocal<String> tenant = new ThreadLocal<>();

    public static ThreadLocal<String> getTenant() {
        return tenant;
    }

    public static void setTenant(ThreadLocal<String> tenant) {
        TenantContext.tenant = tenant;
    }
}
