package com.iamsid.caching.demo.tenant.controller;

import com.iamsid.caching.demo.tenant.entity.Customer;
import com.iamsid.caching.demo.tenant.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/tenant/customer", produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    /*@PostMapping
    public void createCustomers(@RequestBody List<Customer> customerList){
        this.customerService.createBulk(customerList);
    }*/
    @GetMapping
    public List<Customer> getCustomers() {
        return this.customerService.getAllCustomer();
    }
    @DeleteMapping(value = "/evict")
    public void evictCaches(){
        this.customerService.evictAll();
    }
}
