package com.manager.service;

import com.manager.model.Customer;
import com.manager.model.CustomerType;
import com.manager.model.PosException;
import com.manager.repository.CustomerRepository;
import com.manager.utils.CustomerValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepository customerRepository;

	public List<Customer> search(CustomerType customerType, String customerName) {
		StringBuffer sb = new StringBuffer("select p from Customer p where 1 = 1");
		Map<String, Object> params = new HashMap<>();

		if (null != customerType) {
			sb.append(" and p.customerType = :customerType");
			params.put("customerType", customerType);
		}

		if (!StringUtils.isEmpty(customerName)) {
			sb.append(" and lower(p.customerName) like lower(:customerName)");
			params.put("customerName", customerName.concat("%"));
		}

		return customerRepository.findByQuery(sb.toString(), params);
//		return customerRepository.findAll();
	}

	public void save(Customer customer) {
		CustomerValidation.validate(customer);
		customerRepository.save(customer);
	}

	public void delete(Customer customer) {
		try {
			customerRepository.delete(customer);
		} catch (DataIntegrityViolationException e) {
			throw new PosException("This Customer is using the service, please try again later.");
		}
	}

	public Optional<Customer> findById(String id) {
		return customerRepository.findById(id);
	}

	public List<Customer> findAll() {
		return customerRepository.findAll();
	}
}
