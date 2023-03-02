package com.manager.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "education_degree")
@Data
public class EducationDegree {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int education_degree_id;
    private String education_degree_name;

    @OneToMany(mappedBy = "educationDegree")
    private List<Employee> employeeList;

    public EducationDegree() {
    }

    @Override
    public String toString() {
        return education_degree_name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EducationDegree that = (EducationDegree) o;
        return education_degree_id == that.education_degree_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(education_degree_id);
    }
}
