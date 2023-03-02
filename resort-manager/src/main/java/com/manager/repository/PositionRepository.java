package com.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import com.manager.model.Position;

@Service
public interface PositionRepository extends JpaRepository<Position, Integer> {
}
