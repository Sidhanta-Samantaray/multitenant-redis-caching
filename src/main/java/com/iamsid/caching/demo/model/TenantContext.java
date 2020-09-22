package com.iamsid.caching.demo.model;

public class TenantContext {
    private static ThreadLocal<String> tenant = new ThreadLocal<>();

    public static ThreadLocal<String> getTenant() {
        return tenant;
    }

    public static void setTenant(ThreadLocal<String> tenant) {
        TenantContext.tenant = tenant;
    }
}
