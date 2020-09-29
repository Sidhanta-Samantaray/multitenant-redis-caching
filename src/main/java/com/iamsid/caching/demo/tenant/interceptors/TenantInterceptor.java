package com.iamsid.caching.demo.tenant.interceptors;

import com.iamsid.caching.demo.config.Constants;
import com.iamsid.caching.demo.model.TenantContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.HandlerInterceptor;
import sun.rmi.transport.tcp.TCPEndpoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class TenantInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info(request.getRequestURI());
        ThreadLocal<String> tenantId = new ThreadLocal<>();
        tenantId.set(request.getHeader(Constants.TENANT_HTTP_HEADER));
        if (tenantId.get() == null) {
            log.error(Constants.TENANT_HTTP_HEADER + " Can not Be Blank");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Missing Header",
                    new Throwable(String.format("%s Missing", Constants.TENANT_HTTP_HEADER)));
        }
        /*
         * Perform Tenant Validation here
         *If Valid TENANT ID supplied
         *If User & Tenant Combination is valid
         */
        TenantContext.setTenant(tenantId);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        TenantContext.clear();
        log.info("Life Cycle End");
    }
}
