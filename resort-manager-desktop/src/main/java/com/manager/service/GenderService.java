package com.manager.service;

import com.manager.model.Gender;
import com.manager.repository.GenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenderService {

	@Autowired
	private GenderRepository repository;

	public List<Gender> findAll() {
		return repository.findAll();
	}

	public Gender findById(int i) {
		return repository.findById(i).orElse(null);
	}
}
