package com.iamsid.caching.demo.config;

import com.iamsid.caching.demo.admin.interceptors.AdminTenantInterceptor;
import com.iamsid.caching.demo.tenant.interceptors.TenantInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TenantInterceptor())
                .addPathPatterns("/tenant/**");
        registry.addInterceptor(new AdminTenantInterceptor())
                .addPathPatterns("/admin/**");
    }
}
