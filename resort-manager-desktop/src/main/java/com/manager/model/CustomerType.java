package com.manager.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "customer_type")
@Data
public class CustomerType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customer_type_id;
    private String customer_type_name;

    public CustomerType(String name) {
        this.customer_type_name = name;
    }

    public CustomerType(){}

    @Override
    public String toString() {
        return customer_type_name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerType that = (CustomerType) o;
        return customer_type_id == that.customer_type_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer_type_id);
    }

}
