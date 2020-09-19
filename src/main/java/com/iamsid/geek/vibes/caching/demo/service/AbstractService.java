package com.iamsid.geek.vibes.caching.demo.service;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Getter
public class AbstractService {
    @Autowired
    @PersistenceContext
    protected EntityManager entityManager;
}
