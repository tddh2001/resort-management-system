package com.manager.repository;

import com.manager.model.CustomerType;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CustomerTypeRepository extends PagingAndSortingRepository<CustomerType, Integer> {
}
