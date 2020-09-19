package com.iamsid.geek.vibes.caching.demo.interceptors;

import com.iamsid.geek.vibes.caching.demo.config.TenantContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class TenantInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        ThreadLocal<String> tenantId = new ThreadLocal<>();
        tenantId.set(request.getHeader("x-tenant-id"));
        TenantContext.setTenant(tenantId);
        return true;
    }

}
