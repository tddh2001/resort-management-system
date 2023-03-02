package com.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import com.manager.model.EducationDegree;

@Service
public interface EducationDegreeRepository  extends JpaRepository<EducationDegree, Integer> {
}
