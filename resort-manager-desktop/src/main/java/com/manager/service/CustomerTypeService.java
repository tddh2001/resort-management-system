package com.manager.service;

import com.manager.model.CustomerType;
import com.manager.repository.CustomerTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerTypeService {

	@Autowired
	private CustomerTypeRepository customerTypeRepository;

	public List<CustomerType> findAll() {
		return customerTypeRepository.findAll();
	}
}
