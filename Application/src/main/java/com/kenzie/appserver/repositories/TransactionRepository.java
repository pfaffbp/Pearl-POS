package com.kenzie.appserver.repositories;

import com.kenzie.appserver.repositories.model.TransactionRecord;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface TransactionRepository extends CrudRepository<TransactionRecord, String> {
}
