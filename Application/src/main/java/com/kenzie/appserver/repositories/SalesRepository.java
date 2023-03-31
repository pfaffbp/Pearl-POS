package com.kenzie.appserver.repositories;

import com.kenzie.appserver.repositories.model.SalesRecord;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface SalesRepository extends CrudRepository<SalesRecord, String> {
}
