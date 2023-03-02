package com.manager.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "gender")
@Data
public class Gender {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int genderId;
    private String gender;

    @OneToMany(mappedBy = "customerGender")
    private List<Customer> customerList;

    @OneToMany(mappedBy = "employeeGender")
    private List<Employee> employeeList;

    public Gender() {}

    public Gender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return gender;
    }

    @Override
    public int hashCode() {
        return Objects.hash(genderId);
    }
}
