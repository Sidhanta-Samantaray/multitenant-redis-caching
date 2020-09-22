package com.iamsid.caching.demo.tenant.repository;


import com.iamsid.caching.demo.tenant.entity.Customer;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
    @Override
    @Cacheable(value = "customers")
    List<Customer> findAll();
}
