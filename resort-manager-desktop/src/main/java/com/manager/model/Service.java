package com.manager.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "service")
@Data
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int service_id;
    private String service_name;
    private int service_area;
    private double service_cost;
    private int service_max_people;
    private String standard_room;
    private String description_other_convenience;
    private double pool_area;
    private int number_of_floors;

    private boolean isValid;

    @ManyToOne
    @JoinColumn(name = "rent_type")
    private RentType rentType;
    @ManyToOne
    @JoinColumn(name = "service_type")
    private ServiceType serviceType;

    @OneToMany(mappedBy = "service")
    private Set<Contract> contracts;

    public Service() {
    }

    @Override
    public String toString() {
        return service_name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Service service = (Service) o;
        return service_id == service.service_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(service_id);
    }
}
