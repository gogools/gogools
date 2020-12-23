package com.gogools;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import com.gogools.mongo.converters.ZonedDateTimeReadConverter;
import com.gogools.mongo.converters.ZonedDateTimeWriteConverter;
import com.mongodb.MongoClient;

@Configuration
public class MongoConfig extends AbstractMongoConfiguration {
	
	@Value("${spring.data.mongodb.host}")
    private String mongoHost;
 
    @Value("${spring.data.mongodb.port}")
    private int mongoPort;
 
    @Value("${spring.data.mongodb.database}")
    private String mongoDatabase;

    private final List<Converter<?, ?>> converters = new ArrayList<Converter<?, ?>>();
    
	@Override
    public MongoCustomConversions customConversions() {
		
        converters.add(new ZonedDateTimeReadConverter());
        converters.add(new ZonedDateTimeWriteConverter());
        
        return new MongoCustomConversions(converters);
    }

	@Override
	public MongoClient mongoClient() {
		
		return new MongoClient(mongoHost, mongoPort);
	}

	@Override
	protected String getDatabaseName() {

		return mongoDatabase;
	}
	
	@Bean
	@Override
	public MappingMongoConverter mappingMongoConverter() throws Exception {

		DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDbFactory());
		MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, mongoMappingContext());
		converter.setCustomConversions(customConversions());
		
		converter.setTypeMapper(new DefaultMongoTypeMapper(null));
		
		return converter;
	}
}
