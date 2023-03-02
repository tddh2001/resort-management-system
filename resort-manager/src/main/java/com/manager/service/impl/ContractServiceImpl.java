package com.manager.service.impl;

import com.manager.model.Contract;
import com.manager.repository.ContractRepository;
import com.manager.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractServiceImpl implements ContractService {
	@Autowired
	private ContractRepository contractRepository;

	@Override
	public Page<Contract> findAll(Pageable pageable) {
		return contractRepository.findAll(pageable);
	}

	@Override
	public List<Contract> findAll() {
		return contractRepository.findAll();
	}

	@Override
	public Contract findById(int id) {
		return contractRepository.findById(id).orElse(null);
	}

	@Override
	public void create(Contract contract) {
		contractRepository.save(contract);
	}

	@Override
	public void update(Contract contract) {
		contractRepository.save(contract);
	}

	@Override
	public void delete(int id) {
		contractRepository.deleteById(id);
	}
}
