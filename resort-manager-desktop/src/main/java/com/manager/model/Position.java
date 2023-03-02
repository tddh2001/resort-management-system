package com.manager.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "position")
@Data
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int position_id;
    private String position_name;

    @OneToMany(mappedBy = "position")
    private Set<Employee> employeeList;

    public Position() {
    }

    @Override
    public String toString() {
        return position_name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return position_id == position.position_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position_id);
    }
}
