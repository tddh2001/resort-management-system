package com.manager.utils;

import com.manager.model.Employee;
import com.manager.model.PosException;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class EmployeeValidation {
	private static final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
	private static final String PHONE_REGEX = "^((\\(84\\)\\+)|(0))((91)|(90)|(84)|(85)|(93)|(94)|(96)|(38))[\\d]{7}$";
	private static final String IDCARD_REGEX = "^([\\d]{9}|[\\d]{12})$";

	public static void validate(Employee employee) {
		if (employee.getPosition() == null) {
			throw new PosException("Please select position.");
		}
		if (employee.getDivision() == null) {
			throw new PosException("Please select division.");
		}
		if (employee.getEducationDegree() == null) {
			throw new PosException("Please select education degree.");
		}
		if (employee.getEmployeeGender() == null) {
			throw new PosException("Please select gender.");
		}

		if (StringUtils.isEmpty(employee.getEmployeeName())) {
			throw new PosException("Please enter Employee name.");
		}
		if (StringUtils.isEmpty(employee.getEmployeeBirthday())) {
			throw new PosException("Please enter Employee birth day.");
		}
		if (StringUtils.isEmpty(employee.getEmployeeIDCard())) {
			throw new PosException("Please enter ID card.");
		}
		if (StringUtils.isEmpty(employee.getEmployeeEmail())) {
			throw new PosException("Please enter email.");
		}
		if (StringUtils.isEmpty(employee.getEmployeePhone())) {
			throw new PosException("Please enter phone.");
		}
		if (StringUtils.isEmpty(employee.getEmployeeAddress())) {
			throw new PosException("Please enter address.");
		}
		if (employee.getEmployeeSalary() == 0) {
			throw new PosException("Please enter salary.");
		}
		LocalDate currentDate = LocalDate.now();
		if (ChronoUnit.DAYS.between(employee.getEmployeeBirthday(), currentDate) < 1) {
			throw new PosException("Invalid Date Of Birth.");
		}
		if (!employee.getEmployeeEmail().matches(EMAIL_REGEX)) {
			throw new PosException("Invalid Email.");
		}
		if (!employee.getEmployeePhone().matches(PHONE_REGEX)) {
			throw new PosException("Invalid Phone.");
		}
		if (!employee.getEmployeeIDCard().matches(IDCARD_REGEX)) {
			throw new PosException("ID card must contain 9 digits.");
		}
	}
}
