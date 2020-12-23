package com.gogools.wb.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface WinePointsRepo  extends ReactiveMongoRepository<WinePointsHistoryRepo, String>  {

}
