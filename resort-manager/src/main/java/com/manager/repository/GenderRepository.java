package com.manager.repository;

import com.manager.model.Gender;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface GenderRepository extends PagingAndSortingRepository<Gender, Integer> {
}
