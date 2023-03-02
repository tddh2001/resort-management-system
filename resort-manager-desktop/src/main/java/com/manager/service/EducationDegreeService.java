package com.manager.service;

import com.manager.model.EducationDegree;
import com.manager.repository.EducationDegreeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationDegreeService {
	@Autowired
	private EducationDegreeRepository educationDegreeRepository;

	public List<EducationDegree> findAll() {
		return educationDegreeRepository.findAll();
	}

	public EducationDegree findById(int i) {
		return educationDegreeRepository.findById(i).orElse(null);
	}
}
