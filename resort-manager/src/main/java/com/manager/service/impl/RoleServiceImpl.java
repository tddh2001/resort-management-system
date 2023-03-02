package com.manager.service.impl;

import com.manager.model.Role;
import com.manager.repository.RoleRepository;
import com.manager.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository repository;

	@Override
	public Role findById(int id) {
		return repository.findById(id).orElse(null);
	}
}
