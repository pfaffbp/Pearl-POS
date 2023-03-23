package com.kenzie.appserver.repositories;

import com.kenzie.appserver.repositories.model.ProductRecord;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@EnableScan
public interface ProductRepository extends CrudRepository<ProductRecord, String> {
}
