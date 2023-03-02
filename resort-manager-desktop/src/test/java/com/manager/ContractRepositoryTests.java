package com.manager;

import com.manager.model.Contract;
import com.manager.model.Service;
import com.manager.repository.ContractRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContractRepositoryTests {
	@Autowired
	private ContractRepository repository;

	@Test
	public void testFindService() {
		Optional<Contract> contractOtp = repository.findById(19);
		if (contractOtp.isPresent()) {
			Contract contract = contractOtp.get();
//			Optional<Service> service = repository.findByService(contract.getService());
//			System.out.println(service);
		}

	}
}
