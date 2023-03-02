package com.manager.model;

import com.manager.utils.FormatUtil;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
//import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "employee")
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employeeId;
//    @NotBlank(message = "Not Blank!")
    private String employeeName;
//    @Past(message = "Invalid")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate employeeBirthday;
//    @Pattern(regexp = "^([\\d]{9}|[\\d]{12})$", message = "Invalid")
//    @NotBlank(message = "Not blank!")
    private String employeeIDCard;
//    @NotNull(message = "Not blank!")
//    @DecimalMin(value = "0.0", message = "Must be greater than 0")
    private double employeeSalary;
//    @Pattern(regexp = "^((\\(84\\)\\+)|(0))((91)|(90)|(84)|(85)|(93)|(94)|(96)|(38))[\\d]{7}$", message = "Invalid phone number")
//    @NotBlank(message = "Not blank!")
    private String employeePhone;
//    @Pattern(regexp = "^(.+)@(.+)$", message = "Invalid email")
//    @NotBlank(message = "Not blank!")
    private String employeeEmail;
//    @NotBlank(message = "Not blank!")
    private String employeeAddress;
    @ManyToOne
    @JoinColumn(name = "gender_id")
    private Gender employeeGender;
    @ManyToOne(targetEntity = Position.class)
    @JoinColumn(name = "position_id")
    private Position position;
    @ManyToOne
    @JoinColumn(name = "education_degree_id")
    private EducationDegree educationDegree;
    @ManyToOne
    @JoinColumn(name = "division_id")
    private Division division;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username")
    private User user;

    @OneToMany(mappedBy = "employee")
    private Set<Contract> contracts;

    public Employee() {
    }

    public String getSalaryStr() {
        return FormatUtil.formatNumber(employeeSalary);
    }

    @Override
    public String toString() {
        return employeeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return employeeId == employee.employeeId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId);
    }
}
