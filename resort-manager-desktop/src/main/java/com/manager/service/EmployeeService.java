package com.manager.service;

import com.manager.repository.EmployeeRepository;
import com.manager.utils.EmployeeValidation;
import com.manager.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private RoleService roleService;

	public List<Employee> search(Position position, String employeeName) {
		StringBuffer sb = new StringBuffer("select p from Employee p where 1 = 1");
		Map<String, Object> params = new HashMap<>();

		if(null != position) {
			sb.append(" and p.position = :position");
			params.put("position", position);
		}

		if(!StringUtils.isEmpty(employeeName)) {
			sb.append(" and lower(p.employeeName) like lower(:employeeName)");
			params.put("employeeName", employeeName.concat("%"));
		}

		return employeeRepository.findByQuery(sb.toString(), params);
//		return employeeRepository.findAll();
	}

	public void save(Employee employee) {

		Random rn = new Random();
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		EmployeeValidation.validate(employee);

		Optional<Employee> emplOtp = employeeRepository.findById(employee.getEmployeeId());
		if (!emplOtp.isPresent()) {

			int rdi = rn.nextInt();

			User user = new User();
			user.setUsername("user"+rdi);
			user.setPassword(encoder.encode("123456"));
			Set<Role> roles = new HashSet<>();
			if (employee.getPosition().getPosition_id() == 6 || employee.getPosition().getPosition_id() == 5) {
				roles.add(roleService.findById(2));
			} else {
				roles.add(roleService.findById(1));
			}
			user.setRoleList(roles);
			employee.setUser(user);

			employeeRepository.save(employee);
		}
		User user = new User();
		user.setUsername(employee.getUser().getUsername());
		user.setPassword(encoder.encode("123456"));
		Set<Role> roles = new HashSet<>();
		if (employee.getPosition().getPosition_id() == 6 || employee.getPosition().getPosition_id() == 5) {
			roles.add(roleService.findById(2));
		} else {
			roles.add(roleService.findById(1));
		}
		user.setRoleList(roles);
		employee.setUser(user);

		employeeRepository.save(employee);
	}
	public void delete(Employee employee) {
		try {
			employeeRepository.delete(employee);
		} catch (DataIntegrityViolationException e) {
			throw new PosException("This Employee is using the service, please try again later.");
		}
	}

	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}
}
