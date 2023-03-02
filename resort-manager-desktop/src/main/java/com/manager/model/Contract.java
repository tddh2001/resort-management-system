package com.manager.model;

import com.manager.utils.FormatUtil;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
//import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "contract")
@Data
public class Contract {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int contract_id;
	//    @NotBlank(message = "Start date must be not empty")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate contract_start_date;
	//    @NotBlank(message = "End date must be not empty")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate contract_end_date;
	//    @Positive
	private double contract_deposite;
	//    @Positive
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
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "contract", cascade = CascadeType.ALL)
	private Set<ContractDetail> contractDetails;

	public Contract() {
	}

	public String getDepositeStr() {
		return FormatUtil.formatNumber(contract_deposite);
	}

	public String getTotalMoneyStr() {
		return FormatUtil.formatNumber(contract_total_money);
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Contract contract = (Contract) o;
		return contract_id == contract.contract_id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(contract_id);
	}
}
