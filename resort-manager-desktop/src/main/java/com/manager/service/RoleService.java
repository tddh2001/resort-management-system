package com.manager.service;

import com.manager.model.Role;
import com.manager.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
	@Autowired
	private RoleRepository roleRepository;

	Role findById(int id) {
		return roleRepository.findById(id).orElse(null);
	}
}
