package com.kenzie.appserver.service;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.kenzie.appserver.repositories.TransactionRepository;
import com.kenzie.appserver.repositories.model.TransactionRecord;
import com.kenzie.appserver.service.model.Product;
import com.kenzie.appserver.service.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
    private TransactionRepository transactionRepository;

    static AmazonDynamoDBClient client = new AmazonDynamoDBClient();
    static String tableName = "Transaction";

    private ProductService productService;

    private UserService userService;


    public TransactionService(){
    }

    @Autowired
    public TransactionService(TransactionRepository repository){this.transactionRepository = repository;}


    public TransactionRecord generateTransaction(Product product, int itemsPurchased){
        Transaction transactionGenerator = new Transaction();// default constructor creates transaction ID

        TransactionRecord generatedTransaction = new TransactionRecord();
        generatedTransaction.setTransactionID(transactionGenerator.getTransactionID());
        generatedTransaction.setQuantity(itemsPurchased);
        generatedTransaction.setDate(LocalDateTime.now().toString());
        generatedTransaction.setProductID(product.getProductID());
        generatedTransaction.setCustomerID(transactionGenerator.getCustomerID());
        generatedTransaction.setTotalSale(product.getPrice() * itemsPurchased);

        transactionRepository.save(generatedTransaction);
        return generatedTransaction;
    }


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

    public Transaction findTransactionByDate(String date){
        Optional<TransactionRecord> record = transactionRepository.findById(date);

        if(record.isPresent()){
            TransactionRecord transactionRecord = record.get();

            return new Transaction(
                    transactionRecord.getDate(),
                    transactionRecord.getCustomerID(),
                    transactionRecord.getProductID(),
                    transactionRecord.getQuantity(),
                    transactionRecord.getTotalSale(),transactionRecord.getTransactionID());
        } else {
            return null;
        }
    }
}
