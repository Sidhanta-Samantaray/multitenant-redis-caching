package com.iamsid.geek.vibes.caching.demo.service;

import com.iamsid.geek.vibes.caching.demo.config.TenantContext;
import com.iamsid.geek.vibes.caching.demo.entity.Customer;
import com.iamsid.geek.vibes.caching.demo.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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


}
