package com.manager.utils;

import com.manager.model.Customer;
import com.manager.model.PosException;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class CustomerValidation {
	private static final String ID_REGEX = "^(KH-)[\\d]{4}$";
	private static final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
	private static final String PHONE_REGEX = "^((\\(84\\)\\+)|(0))((91)|(90)|(84)|(85)|(93)|(94)|(96)|(38))[\\d]{7}$";
	private static final String IDCARD_REGEX = "^([\\d]{9}|[\\d]{12})$";

	public static void validate(Customer customer) {
		if (customer.getCustomerId() == null) {
			throw new PosException("Please enter Customer ID.");
		}
		if (customer.getCustomerType() == null) {
			throw new PosException("Please select Type of Customer.");
		}
		if (customer.getCustomerGender() == null) {
			throw new PosException("Please select Gender of Customer.");
		}
		if (StringUtils.isEmpty(customer.getCustomerName())) {
			throw new PosException("Please enter Customer Name.");
		}
		if (StringUtils.isEmpty(customer.getCustomerAddress())) {
			throw new PosException("Please enter Customer Address.");
		}
		if (StringUtils.isEmpty(customer.getCustomerEmail())) {
			throw new PosException("Please enter Customer Email.");
		}
		if (StringUtils.isEmpty(customer.getCustomerPhone())) {
			throw new PosException("Please enter Customer Phone.");
		}
		if (StringUtils.isEmpty(customer.getCustomerIDCard())) {
			throw new PosException("Please enter Customer ID Card.");
		}
		if (StringUtils.isEmpty(customer.getCustomerBirthday())) {
			throw new PosException("Please enter Customer Birth Day.");
		}
		LocalDate currentDate = LocalDate.now();
		if (ChronoUnit.DAYS.between(customer.getCustomerBirthday(), currentDate) < 1) {
			throw new PosException("Invalid Date Of Birth.");
		}
		if (!customer.getCustomerEmail().matches(EMAIL_REGEX)) {
			throw new PosException("Invalid Email.");
		}
		if (!customer.getCustomerPhone().matches(PHONE_REGEX)) {
			throw new PosException("Invalid Phone.");
		}
		if (!customer.getCustomerIDCard().matches(IDCARD_REGEX)) {
			throw new PosException("ID card must contain 9 digits.");
		}
		if (!customer.getCustomerId().matches(ID_REGEX)) {
			throw new PosException("The Customer ID has the format KH-XXXX (X : 0-9)");
		}
	}
}
