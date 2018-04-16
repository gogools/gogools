package com.gogools.r3.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.gogools.r3.domain.Operation;

public interface OperationRepository extends MongoRepository<Operation, BigInteger>, OperationRepositoryCustom {

    public List<Operation> findByIdClientOrderBySysDate(String idClient);
    
    public List<Operation> findByIdClientAndIdCardOrderBySysDate(String idClient, String idCard);

}
