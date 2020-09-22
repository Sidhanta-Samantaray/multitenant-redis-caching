package com.iamsid.caching.demo.admin.interceptors;

import com.iamsid.caching.demo.config.Constants;
import com.iamsid.caching.demo.model.TenantContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class AdminTenantInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info(request.getRequestURI());
        ThreadLocal<String> tenantId = new ThreadLocal<>();
        tenantId.set(Constants.SUPER_ADMIN_TENANT);
        TenantContext.setTenant(tenantId);
        return true;
    }

}
