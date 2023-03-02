package com.manager.repository;

import com.manager.model.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Integer> {

    @Query(value = "SELECT * FROM contract WHERE service_id = :service_id",
            nativeQuery = true)
    Optional<Contract> findByService(Integer service_id);
}
