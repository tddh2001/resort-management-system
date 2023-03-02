package com.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import com.manager.model.Employee;

@Service
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
