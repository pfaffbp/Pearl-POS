package com.kenzie.appserver.service;

<<<<<<< HEAD
<<<<<<< HEAD
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
=======
>>>>>>> 9fcaff4 (prep to merg 4/3 8:46)
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
=======
import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.kenzie.appserver.config.DynamoDbConfig;
>>>>>>> e772bb6 (Added more get methods for Transaction service)
import com.kenzie.appserver.repositories.TransactionRepository;
import com.kenzie.appserver.repositories.model.TransactionRecord;
import com.kenzie.appserver.service.model.Product;
import com.kenzie.appserver.service.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
<<<<<<< HEAD
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
=======
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;
<<<<<<< HEAD
>>>>>>> d60c250 (Made purchaseProducts accept multiple product id, and made Transaction service accept multiple productIDS)
=======
import java.util.stream.Collectors;
>>>>>>> aa4f162 (merging new transations with maps to uses if needed)

@Service
public class TransactionService {
    private TransactionRepository transactionRepository;

    static AmazonDynamoDBClient client = new AmazonDynamoDBClient();
    static String tableName = "Transaction";

    private ProductService productService;

    private UserService userService;


    public TransactionService(){
    }

    private final DynamoDbConfig dynamoDbConfig;

<<<<<<< HEAD
<<<<<<< HEAD

    public TransactionRecord generateTransaction(Product product, int itemsPurchased){
        Transaction transactionGenerator = new Transaction();// default constructor creates transaction ID

        TransactionRecord generatedTransaction = new TransactionRecord();
        generatedTransaction.setTransactionID(transactionGenerator.getTransactionID());
        generatedTransaction.setQuantity(itemsPurchased);
        generatedTransaction.setDate(LocalDateTime.now().toString());
        generatedTransaction.setProductID(product.getProductID());
        generatedTransaction.setCustomerID(transactionGenerator.getCustomerID());
        generatedTransaction.setTotalSale(product.getPrice() * itemsPurchased);
=======
    public TransactionRecord generateTransaction(List<Product> product, List<Integer> itemsPurchased){
=======
    private final DynamoDBMapper mapper;

    private static final String TRANSACTION_CUSTOMER_ID = "TransactionsByCustomerID";

    private static final String TRANSACTION_BY_DATE = "TransactionsByDate";

    @Autowired
    public TransactionService(TransactionRepository repository, DynamoDbConfig dynamoDbConfig) {
        this.transactionRepository = repository;
        this.dynamoDbConfig = dynamoDbConfig;
        this.mapper = new DynamoDBMapper(dynamoDbConfig.defaultAmazonDynamoDb());
    }

    public TransactionRecord generateTransaction(List<Product> product, List<Integer> itemsPurchased) {
>>>>>>> e772bb6 (Added more get methods for Transaction service)
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
        generatedTransaction.setDate(LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG, FormatStyle.MEDIUM)));
        generatedTransaction.setQuantity(quantity);
        generatedTransaction.setCustomerID("TestCustomer");
        generatedTransaction.setTotalSale(totalSales);
        generatedTransaction.setAmountPurchasedPerProduct(itemsPurchased);
>>>>>>> d60c250 (Made purchaseProducts accept multiple product id, and made Transaction service accept multiple productIDS)

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


    public List<Transaction> getAllTransactions() {
        List<Transaction> allTransactions = new ArrayList<>();
        transactionRepository.findAll().forEach(transactionRecord -> allTransactions.add(transactionHelperMethod(transactionRecord)));
        return allTransactions;
    }

    public Transaction transactionHelperMethod(TransactionRecord transaction){
        Transaction createNewTransaction = new Transaction();
        createNewTransaction.setDate(transaction.getDate());
        createNewTransaction.setCustomerID(transaction.getCustomerID());
        createNewTransaction.setProductID(transaction.getProductID());
        createNewTransaction.setQuantity(transaction.getQuantity());
        createNewTransaction.setTotalSale(transaction.getTotalSale());
        createNewTransaction.setTransactionID(transaction.getTransactionID());

        return createNewTransaction;

    }

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
<<<<<<< HEAD
    public List<Transaction> getAllTransactions() {
        List<Transaction> allTransactions = new ArrayList<>();
        transactionRepository.findAll().forEach(transactionRecord -> allTransactions.add(transactionHelperMethod(transactionRecord)));
        return allTransactions;
    }

    public Transaction transactionHelperMethod(TransactionRecord transaction){
        Transaction createNewTransaction = new Transaction();
        createNewTransaction.setDate(transaction.getDate());
        createNewTransaction.setCustomerID(transaction.getCustomerID());
        createNewTransaction.setProductID(transaction.getProductID());
        createNewTransaction.setQuantity(transaction.getQuantity());
        createNewTransaction.setTotalSale(transaction.getTotalSale());
        createNewTransaction.setTransactionID(transaction.getTransactionID());
        createNewTransaction.setAmountPurchasedPerProduct(transaction.getAmountPurchasedPerProduct());

        return createNewTransaction;

    }

=======
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
>>>>>>> e772bb6 (Added more get methods for Transaction service)


//    public PaginatedQueryList<TransactionRecord> transactionByDate(String date){
//        DynamoDBMapper mapper = new DynamoDBMapper(dynamoDbConfig.defaultAmazonDynamoDb());
//
//        Map<String, AttributeValue> valueMap = new HashMap<>();
//        valueMap.put(":date", new AttributeValue().withS(date));
//
//        DynamoDBQueryExpression<TransactionRecord> queryExpression = new DynamoDBQueryExpression<TransactionRecord>()
//                .withIndexName(TRANSACTION_BY_DATE)
//                .withConsistentRead(false)
//                .withKeyConditionExpression("date = :date")
//                .withExpressionAttributeValues(valueMap);
//
//        PaginatedQueryList<TransactionRecord> TransactionList = mapper.query(TransactionRecord.class, queryExpression);
//        return TransactionList;
//    }

/*    public List<TransactionRecord> transactionByDate(String date){
            DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
            PaginatedScanList<TransactionRecord> records =  mapper.scan(TransactionRecord.class, scanExpression);
            List<TransactionRecord> transactionRecords = new ArrayList<>();
            System.out.println("Test");


            for(int i = 0; i < records.size(); i++){
                if(records.get(i).getDate().contains(date)){
                    transactionRecords.add(records.get(i));
                }
            }
            return transactionRecords;
        }*/

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