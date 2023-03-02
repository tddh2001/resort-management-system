package com.manager.utils;

import com.manager.model.Contract;
import com.manager.model.PosException;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ContractValidate {

	public static void validate(Contract contract) {
		LocalDate startDate = contract.getContract_start_date();
		LocalDate endDate = contract.getContract_end_date();
		if (ChronoUnit.DAYS.between(startDate, endDate) < 1) {
			throw new PosException("End date must be greater than start date.");
		}
		if (contract.getCustomer() == null) {
			throw new PosException("Please select customer.");
		}
		if (contract.getEmployee() == null) {
			throw new PosException("Please select employee.");
		}
		if (contract.getService() == null) {
			throw new PosException("Please select service.");
		}
		if (StringUtils.isEmpty(contract.getContract_start_date())) {
			throw new PosException("Please enter start date.");
		}
		if (StringUtils.isEmpty(contract.getContract_end_date())) {
			throw new PosException("Please enter end date.");
		}
		if (contract.getContract_deposite() == 0) {
			throw new PosException("Please enter deposite.");
		}
		if (contract.getContract_total_money() == 0) {
			throw new PosException("Please enter total money.");
		}

	}
}
