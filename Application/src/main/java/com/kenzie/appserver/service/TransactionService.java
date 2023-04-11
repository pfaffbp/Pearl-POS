package com.kenzie.appserver.service;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.kenzie.appserver.config.DynamoDbConfig;

import com.kenzie.appserver.repositories.TransactionRepository;
import com.kenzie.appserver.repositories.model.ProductRecord;
import com.kenzie.appserver.repositories.model.TransactionRecord;
import com.kenzie.appserver.service.model.Product;
import com.kenzie.appserver.service.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    //    private final DynamoDbConfig dynamoDbConfig;
    private final DynamoDBMapper mapper;
    private static final String TRANSACTION_CUSTOMER_ID = "TransactionsByCustomerID";

    private static final String TRANSACTION_BY_DATE = "TransactionsByDate";

    @Autowired
    public TransactionService(TransactionRepository repository, DynamoDBMapper mapper) {
        this.transactionRepository = repository;
//        this.dynamoDbConfig = dynamoDbConfig;
        this.mapper = mapper;
    }

    public TransactionRecord generateTransaction(List<Product> product, List<Integer> itemsPurchased, String username) {
        List<String> productIDS = new ArrayList<>();
        double totalSales = 0;
        Integer quantity = 0;

        for (int i = 0; i < product.size(); i++) {
            productIDS.add(product.get(i).getProductID());
            totalSales += itemsPurchased.get(i) * product.get(i).getPrice();
            quantity += itemsPurchased.get(i);

        }
        Transaction transactionGenerator = new Transaction();

        TransactionRecord generatedTransaction = new TransactionRecord();
        generatedTransaction.setTransactionID(transactionGenerator.getTransactionID());
        generatedTransaction.setProductID(productIDS);
        generatedTransaction.setDate(
                LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG, FormatStyle.MEDIUM)));
        generatedTransaction.setQuantity(quantity);
        generatedTransaction.setCustomerID(username);
        generatedTransaction.setTotalSale(totalSales);
        generatedTransaction.setAmountPurchasedPerProduct(itemsPurchased);

        transactionRepository.save(generatedTransaction);
        return generatedTransaction;
    }

//todo michael look at this
/*    public TransactionRecord generateTransation(Map<Product, Integer> productsPurchased){
        List<String> productIDS = new ArrayList<>();
        double totalSales = 0;
        Integer quantity = 0;
        for (Map.Entry<Product, Integer> productIntegerEntry : productsPurchased.entrySet()){
            productIDS.add(productIntegerEntry.getKey().getProductID());
            totalSales += productIntegerEntry.getValue() * productIntegerEntry.getKey().getPrice();
            quantity += productIntegerEntry.getValue();
        }
        TransactionRecord generatedTransaction = new TransactionRecord();
        generatedTransaction.setTransactionID(UUID.randomUUID().toString());
        generatedTransaction.setProductID(productIDS);
        generatedTransaction.setDate(LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG, FormatStyle.MEDIUM)));
        generatedTransaction.setQuantity(quantity);
        generatedTransaction.setCustomerID("TestCustomer");
        generatedTransaction.setTotalSale(totalSales);
        generatedTransaction.setAmountPurchasedPerProduct(productsPurchased.values().stream().collect(Collectors.toList()));
        transactionRepository.save(generatedTransaction);
        return generatedTransaction;
    }*/

    public Transaction findTransactionID(String transactionID) {
        Optional<TransactionRecord> record = transactionRepository.findById(transactionID);

        if (record.isPresent()) {
            TransactionRecord transactionRecord = record.get();

            return new Transaction(
                    transactionRecord.getDate(),
                    transactionRecord.getCustomerID(),
                    transactionRecord.getProductID(),
                    transactionRecord.getQuantity(),
                    transactionRecord.getTotalSale(),
                    transactionRecord.getTransactionID(),
                    transactionRecord.getAmountPurchasedPerProduct());
        } else {
            return null;
        }

    }

    public List<Transaction> getAllTransactions() {
        List<Transaction> allTransactions = new ArrayList<>();
        transactionRepository.findAll().forEach(transactionRecord -> allTransactions.add(recordIntoTransaction(transactionRecord)));
        return allTransactions;
    }



    public PaginatedQueryList<TransactionRecord> transactionByCustomerID(String customerID){
        Map<String, AttributeValue> valueMap = new HashMap<>();
        valueMap.put(":customerID", new AttributeValue().withS(customerID));

        DynamoDBQueryExpression<TransactionRecord> queryExpression = new DynamoDBQueryExpression<TransactionRecord>()
                .withIndexName(TRANSACTION_CUSTOMER_ID)
                .withConsistentRead(false)
                .withKeyConditionExpression("customerID = :customerID")
                .withExpressionAttributeValues(valueMap);

        PaginatedQueryList<TransactionRecord> TransactionList = mapper.query(TransactionRecord.class, queryExpression);
        return TransactionList;

    }

//    public List<TransactionRecord> transactionByDate(String date){
//        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
//        PaginatedScanList<TransactionRecord> records =  mapper.scan(TransactionRecord.class, scanExpression);
//
//        List<TransactionRecord> transactionRecords = new ArrayList<>();
//
//        for(int i = 0; i < records.size(); i++){
//            if(records.get(i).getDate().contains(date)){
//                transactionRecords.add(records.get(i));
//            }
//        }
//        return transactionRecords;
//    }


    public Transaction recordIntoTransaction(TransactionRecord record) {
        Transaction transaction = new Transaction();
        transaction.setTransactionID(record.getTransactionID());
        transaction.setDate(record.getDate());
        transaction.setQuantity(record.getQuantity());
        transaction.setCustomerID(record.getCustomerID());
        transaction.setTotalSale(record.getTotalSale());
        transaction.setProductID(record.getProductID());
        transaction.setAmountPurchasedPerProduct(record.getAmountPurchasedPerProduct());

        return transaction;
    }
}