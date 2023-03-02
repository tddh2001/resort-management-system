package com.manager.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "contract")
public class Contract {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int contract_id;
	//    @NotBlank(message = "Start date must be not empty")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date contract_start_date;
	//    @NotBlank(message = "End date must be not empty")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date contract_end_date;
	@Positive
	private double contract_deposite;
	@Positive
	private double contract_total_money;
	@ManyToOne
	@JoinColumn(name = "employee_id")
	private Employee employee;
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	@ManyToOne
	@JoinColumn(name = "service_id")
	private Service service;
	@OneToMany(mappedBy = "contract", cascade = CascadeType.ALL)
	private Set<ContractDetail> contractDetails;

	public Contract() {
	}


	public int getContract_id() {
		return contract_id;
	}

	public void setContract_id(int contract_id) {
		this.contract_id = contract_id;
	}

	public Date getContract_start_date() {
		return contract_start_date;
	}

	public void setContract_start_date(Date contract_start_date) {
		this.contract_start_date = contract_start_date;
	}

	public Date getContract_end_date() {
		return contract_end_date;
	}

	public void setContract_end_date(Date contract_end_date) {
		this.contract_end_date = contract_end_date;
	}

	public double getContract_deposite() {
		return contract_deposite;
	}

	public void setContract_deposite(double contract_deposite) {
		this.contract_deposite = contract_deposite;
	}

	public double getContract_total_money() {
		return contract_total_money;
	}

	public void setContract_total_money(double contract_total_money) {
		this.contract_total_money = contract_total_money;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}
}
