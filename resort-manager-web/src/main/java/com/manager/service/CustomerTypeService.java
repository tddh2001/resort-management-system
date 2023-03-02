package com.manager.service;

import com.manager.model.CustomerType;

public interface CustomerTypeService {
    Iterable<CustomerType> findAll();
    CustomerType findById(int id);
    void save(CustomerType customerType);
    void remove(int id);
}
