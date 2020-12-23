package com.app.mongo.cascade;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.util.ReflectionUtils;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class CascadeSaveMongoEventListener extends AbstractMongoEventListener<Object> {
	
    private final MongoOperations mongoOperations;
    
    @Override
    public void onBeforeConvert(final BeforeConvertEvent<Object> event) {
    	
        final Object source = event.getSource();
        ReflectionUtils.doWithFields(source.getClass(), new CascadeCallback(source, mongoOperations));
        
    }

}
