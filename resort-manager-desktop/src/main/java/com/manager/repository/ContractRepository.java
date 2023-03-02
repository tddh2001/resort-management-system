package com.manager.repository;

import com.manager.model.Contract;
import com.manager.model.Service;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface ContractRepository extends BaseRepository<Contract, Integer> {
		@Query(value = "SELECT * FROM contract WHERE service_id = :service_id",
			nativeQuery = true)
	Optional<Contract> findByService(Integer service_id);

}
