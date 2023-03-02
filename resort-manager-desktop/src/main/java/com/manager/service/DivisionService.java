package com.manager.service;

import com.manager.model.Division;
import com.manager.repository.DivisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DivisionService {
	@Autowired
	private DivisionRepository divisionRepository;

	public List<Division> findAll() {
		return divisionRepository.findAll();
	}

	public Division findById(int i) {
		return divisionRepository.findById(i).orElse(null);
	}
}
