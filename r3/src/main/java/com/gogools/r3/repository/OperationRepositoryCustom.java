package com.gogools.r3.repository;

import java.util.Date;
import java.util.stream.Stream;

import com.gogools.r3.domain.Operation;

public interface OperationRepositoryCustom {

	Stream<Operation> findByIdClientAndSysDate(String idClient, Date sysDate, Date windowSysDate);
	
	Stream<Operation> findByIdClientCardAndSysDate(String idClient, String idCard, Date sysDate, Date windowSysDate);
	
	Stream<Operation> findByIdClientCardOpAndSysDate(String idClient, String idCard, String op, Date sysDate, Date windowSysDate);
}
