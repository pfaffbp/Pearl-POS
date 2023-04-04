package com.kenzie.appserver.repositories;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.kenzie.appserver.repositories.model.UserRecord;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Repository
public class UserRepository {

    private static final AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
    private static final DynamoDBMapper mapper = new DynamoDBMapper(client);

    public void createUser(String password, String email) {
        UserRecord userRecord = new UserRecord(password, email);
        mapper.save(userRecord);
    }

    public UserRecord findByEmail(String email) {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":val1", new AttributeValue().withS(email));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("email = :val1")
                .withExpressionAttributeValues(eav);

        PaginatedScanList<UserRecord> result = mapper.scan(UserRecord.class, scanExpression);
        if (result.isEmpty()) {
            return null;
        } else {
            return result.get(0);
        }
    }

    public List<UserRecord> findAll() {
        return mapper.scan(UserRecord.class, new DynamoDBScanExpression());
    }

    public void save(UserRecord record) {
        mapper.save(record);
    }
}
