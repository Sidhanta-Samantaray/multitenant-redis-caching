package com.iamsid.caching.demo.tenant.service;

import com.iamsid.caching.demo.model.TenantContext;
import com.iamsid.caching.demo.tenant.entity.Customer;
import com.iamsid.caching.demo.tenant.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CustomerService extends AbstractService {
    @Autowired
    private CustomerRepository repository;

    public void createBulk(List<Customer> customerList) {
        this.repository.saveAll(customerList);
    }

    public List<Customer> getAllCustomer() {
        log.info("Tenant:-" + TenantContext.getTenant().get());
        return this.repository.findAll();
    }

    /**
     * Used to Clean entries for specific tenant
     */
    @CacheEvict(value = {"customers"}, allEntries = true)
    public void evictAll() {
    }
}
