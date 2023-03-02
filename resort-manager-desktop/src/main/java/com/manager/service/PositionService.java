package com.manager.service;

import com.manager.model.Position;
import com.manager.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionService {
	@Autowired
	private PositionRepository positionRepository;

	public List<Position> findAll() {
		return positionRepository.findAll();
	}

	public Position findById(int i) {
		return positionRepository.findById(i).orElse(null);
	}
}
