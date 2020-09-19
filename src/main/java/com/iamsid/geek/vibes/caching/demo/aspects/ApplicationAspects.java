package com.iamsid.geek.vibes.caching.demo.aspects;

import com.iamsid.geek.vibes.caching.demo.config.TenantContext;
import com.iamsid.geek.vibes.caching.demo.service.AbstractService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class ApplicationAspects {
    @Before(value = "execution(* com.iamsid.geek.vibes.caching.demo.service..*.*(..)) && target(abstractService)")
    public void before(JoinPoint pjp, AbstractService abstractService) {
        log.info(abstractService.toString());
        final Session session = abstractService.getEntityManager().unwrap(Session.class);
        Filter filter = session.enableFilter("tenantFilter");
        filter.setParameter("tenantKey", TenantContext.getTenant().get());
        filter.validate();

    }

    @After(value = "execution(* com.iamsid.geek.vibes.caching.demo.service..*.*(..)) && target(abstractService)")
    public void after(JoinPoint pjp, AbstractService abstractService) {
        log.info(abstractService.toString());
        final Session session = abstractService.getEntityManager().unwrap(Session.class);
        session.clear();
        session.close();
    }

}
