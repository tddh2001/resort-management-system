package com.manager.service;

import com.manager.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();

    void save(Employee employee);

    void remove(int id);

    Employee findById(int id);
}
