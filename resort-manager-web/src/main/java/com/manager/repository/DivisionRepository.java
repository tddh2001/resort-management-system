package com.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import com.manager.model.Division;

@Service
public interface DivisionRepository extends JpaRepository<Division, Integer> {
}
