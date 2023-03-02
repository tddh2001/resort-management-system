package com.manager.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.Past;
//import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "customer")
@Data
public class Customer {
    @Id
//    @Pattern(regexp = "^(KH-)[\\d]{4}$", message = "The customer ID has the format KH-XXXX (X : 0-9)")
//    @NotBlank(message = "ID cannot be empty!!")
    @Column(length = 50)
    private String customerId;
//    @NotBlank(message = "Name cannot be empty!!")
    private String customerName;
//    @Past(message = "Invalid date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate customerBirthday;
//    @Pattern(regexp = "^([\\d]{9}|[\\d]{12})$", message = "IDcard invalid")
//    @NotBlank(message = "ID card cannot be empty!!")
    private String customerIDCard;
//    @Pattern(regexp = "^((\\(84\\)\\+)|(0))((91)|(90)|(84)|(85)|(93)|(94)|(96)|(38))[\\d]{7}$", message = "Invalid phone number")
//    @NotBlank(message = "Phone number cannot be empty!")
    private String customerPhone;
//    @Pattern(regexp = "^(.+)@(.+)$", message = "Invalid email")
//    @NotBlank(message = "Email cannot be empty!!")
    private String customerEmail;
//    @NotBlank(message = "Address cannot be empty!!")
    private String customerAddress;
    @ManyToOne
    @JoinColumn(name = "customer_type_id")
    private CustomerType customerType;
    @ManyToOne
    @JoinColumn(name = "gender_id")
    private Gender customerGender;

    @OneToMany(mappedBy = "customer")
    private Set<Contract> contracts;

    @Override
    public String toString() {
        return customerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return customerId.equals(customer.customerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId);
    }
}
