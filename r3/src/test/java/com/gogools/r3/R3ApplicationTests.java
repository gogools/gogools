package com.gogools.r3;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import com.gogools.r3.domain.Operation;
import com.gogools.utils.ConsolePrinter;
import com.gogools.utils.DateUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class R3ApplicationTests {
	
	@Autowired
    MongoTemplate mongoTemplate;

	@Test
	public void testQryByDate() {
		
		Date date = DateUtil.getDateFromString("14-01-2018");
		
		assertThat(queryByDate(date)).isGreaterThan(0);
	}

	
	@Test
	public void testQryById() {
		
		assertThat(queryByIdOperation("08")).isGreaterThan(0);
	}
	
	
	private int queryByDate(Date date) {

        Query query = new Query(Criteria.where("sysDate").gte(date).lt(DateUtil.addFactor(date, DateUtil.DateFactor.DAY, 1)));

        List<Operation> result = mongoTemplate.find(query, Operation.class);
        
        ConsolePrinter.prettyJson(result);

        if(result!=null) {
        	
            return result.size();
        } 
        else {
        	
            return -1;
        }
    }
	
	private int queryByIdOperation(String id) {

        Query query = new Query(Criteria.where("idOperation").is(id));

        List<Operation> result = mongoTemplate.find(query, Operation.class);
        
        ConsolePrinter.prettyJson(result);

        if(result!=null) {
        	
            return result.size();
        } 
        else {
        	
            return -1;
        }
    }
}
