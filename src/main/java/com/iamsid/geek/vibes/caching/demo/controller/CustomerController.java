package com.iamsid.geek.vibes.caching.demo.controller;

import com.iamsid.geek.vibes.caching.demo.entity.Customer;
import com.iamsid.geek.vibes.caching.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/customer", produces = MediaType.APPLICATION_JSON_VALUE)
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
}
