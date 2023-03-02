package com.manager;

import com.manager.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CaseStudyApplicationTests {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    GenderService genderService;
    @Autowired
    EducationDegreeService degreeService;
    @Autowired
    DivisionService divisionService;
    @Autowired
    PositionService positionService;

    @Test
    void contextLoads() {
//        Employee employee = new Employee();
//        employee.setEmployeeName("aaa");
//        employee.setEmployeeIDCard("1123");
//        employee.setEmployeeBirthday(LocalDate.of(2001,11,18));
//        employee.setEmployeeEmail("abcd");
//        employee.setEmployeePhone("0987654321");
//        employee.setEmployeeAddress("avc");
//        employee.setEmployeeSalary(19d);
//        employee.setEmployeeGender(genderService.findById(1));
//        employee.setEducationDegree(degreeService.findById(1));
//        employee.setDivision(divisionService.findById(1));
//        employee.setPosition(positionService.findById(1));
//
//        employeeService.save(employee);
        System.out.println("Hel");
    }

}
