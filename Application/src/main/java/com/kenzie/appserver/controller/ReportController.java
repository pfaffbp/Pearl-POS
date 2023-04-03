package com.kenzie.appserver.controller;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.kenzie.appserver.service.model.Transaction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
    public class ReportController {

        private final AmazonDynamoDB dynamoDB;

        public ReportController(AmazonDynamoDB dynamoDB) {
            this.dynamoDB = dynamoDB;
        }

    @GetMapping("/report")
    public ResponseEntity<String> generateReport() {
        DynamoDBMapper mapper = new DynamoDBMapper(dynamoDB);
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        List<Transaction> transactions = mapper.scan(Transaction.class, scanExpression);
        // Generate report using the transactions
        String report = "Report";
        return ResponseEntity.ok(report);
    }
    }
