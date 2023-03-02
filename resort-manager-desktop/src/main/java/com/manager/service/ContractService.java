package com.manager.service;

import com.manager.model.Contract;
import com.manager.model.PosException;
import com.manager.repository.ContractRepository;
import com.manager.utils.ContractValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ContractService {
	@Autowired
	private ContractRepository contractRepository;

	public List<Contract> search(com.manager.model.Service service) {
//		return contractRepository.findAll();
		StringBuffer sb = new StringBuffer("select p from Contract p where 1 = 1");
		Map<String, Object> params = new HashMap<>();

		if (null != service) {
			sb.append(" and p.service = :service");
			params.put("service", service);
		}
		return contractRepository.findByQuery(sb.toString(), params);
	}

	public void save(Contract contract) {
		ContractValidate.validate(contract);
		Optional<Contract> contractOpt = contractRepository.findById(contract.getContract_id());
		Optional<Contract> service = contractRepository.findByService(contract.getService().getService_id());
		if (!contractOpt.isPresent()) {
			if (service.isPresent()) {
				throw new PosException("This service is being used.");
			}
		}
		contractRepository.save(contract);
	}

	public void delete(Contract contract) {
		contractRepository.delete(contract);
	}
}
