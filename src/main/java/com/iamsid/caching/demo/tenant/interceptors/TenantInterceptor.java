package com.iamsid.caching.demo.tenant.interceptors;

import com.iamsid.caching.demo.config.Constants;
import com.iamsid.caching.demo.model.TenantContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class TenantInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info(request.getRequestURI());
        ThreadLocal<String> tenantId = new ThreadLocal<>();
        tenantId.set(request.getHeader(Constants.TENANT_HTTP_HEADER));
        TenantContext.setTenant(tenantId);
        return true;
    }

}
