package com.manager.service;

import com.manager.model.Service;
import com.manager.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class ServiceService {
	@Autowired
	private ServiceRepository serviceRepository;

	public List<Service> findAll() {
		return serviceRepository.findAll();
	}
}
