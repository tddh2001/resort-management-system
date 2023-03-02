package com.manager.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "position")
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int position_id;
    private String position_name;

    @OneToMany(mappedBy = "position")
    private Set<Employee> employeeList;

    public Position() {
    }

    public Position(String position_name, Set<Employee> employeeList) {
        this.position_name = position_name;
        this.employeeList = employeeList;
    }

    public Position(int position_id, String position_name, Set<Employee> employeeList) {
        this.position_id = position_id;
        this.position_name = position_name;
        this.employeeList = employeeList;
    }

    public int getPosition_id() {
        return position_id;
    }

    public void setPosition_id(int position_id) {
        this.position_id = position_id;
    }

    public String getPosition_name() {
        return position_name;
    }

    public void setPosition_name(String position_name) {
        this.position_name = position_name;
    }

    public Set<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(Set<Employee> employeeList) {
        this.employeeList = employeeList;
    }
}
