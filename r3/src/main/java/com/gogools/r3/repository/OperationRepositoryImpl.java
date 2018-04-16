package com.gogools.r3.repository;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.gogools.r3.domain.Operation;
import com.gogools.utils.DateUtil;

public class OperationRepositoryImpl implements OperationRepositoryCustom {
	
	@Autowired
    MongoTemplate mongoTemplate;

	@Override
	public Stream<Operation> findByIdClientAndSysDate(String idClient, Date sysDate, Date windowSysDate) {
		
		Query query = new Query(Criteria.where("idClient").is(idClient)
									    .and("sysDate")
        									    .gte(sysDate)	
        									    .lt(windowSysDate != null ? windowSysDate : DateUtil.addFactor(sysDate, DateUtil.DateFactor.DAY, 1)));

        List<Operation> result = mongoTemplate.find(query.with(new Sort(Sort.Direction.DESC, "sysDate")), Operation.class);

        if( result != null ) {
        	
            return result.stream();
        } 
        else {
        	
            return Stream.empty();
        }
	}

	@Override
	public Stream<Operation> findByIdClientCardAndSysDate(String idClient, String idCard, Date sysDate, Date windowSysDate) {
		
		Query query = new Query(Criteria.where("idClient").is(idClient)
										 .and("idCard").is(idCard)
                            			     .and("sysDate")
                            			     	.gte(sysDate)
                            			     	.lt(windowSysDate != null ? windowSysDate : DateUtil.addFactor(sysDate, DateUtil.DateFactor.DAY, 1)));

        List<Operation> result = mongoTemplate.find(query.with(new Sort(Sort.Direction.DESC, "sysDate")), Operation.class);
        
        if( result != null ) {
        
        		return result.stream();
        } 
        else {
        
        		return Stream.empty();
        }
	}

	@Override
	public Stream<Operation> findByIdClientCardOpAndSysDate(String idClient, String idCard, String op, Date sysDate, Date windowSysDate) {
		
		Query query = new Query(Criteria.where("idClient").is(idClient)
                            				 .and("idCard").is(idCard)
                            				 .and("idOperation").is(op)
                               			     .and("sysDate")
                               			     	.gte(sysDate)
                               			     	.lt(windowSysDate != null ? windowSysDate : DateUtil.addFactor(sysDate, DateUtil.DateFactor.DAY, 1)));

        List<Operation> result = mongoTemplate.find(query.with(new Sort(Sort.Direction.DESC, "sysDate")), Operation.class);
        
        if( result != null ) {
        
        		return result.stream();
        } 
        else {
        
        		return Stream.empty();
        }
	}

}
