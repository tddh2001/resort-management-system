package com.manager.service;

import com.manager.model.Contract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ContractService {
	Page<Contract> findAll(Pageable pageable);
	List<Contract> findAll();
	Contract findById(int id);
	void create(Contract contract);
	void update(Contract contract);
	void delete(int id);
}
