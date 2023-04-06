package com.kenzie.appserver.service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.kenzie.appserver.config.DynamoDbConfig;
import com.kenzie.appserver.repositories.TransactionRepository;
import com.kenzie.appserver.repositories.model.ProductRecord;
import com.kenzie.appserver.repositories.model.TransactionRecord;
import com.kenzie.appserver.service.model.Product;
import com.kenzie.appserver.service.model.Transaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TransactionServiceTest {
    private TransactionRepository transactionRepository;
    private DynamoDbConfig dynamoDbConfig;

    private DynamoDBMapper mapper;
    private String TRANSACTION_CUSTOMER_ID = "TransactionsByCustomerID";
    private TransactionService transactionService;
    private TransactionRecord transactionRecord;

    private Product product;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        transactionRepository = mock(TransactionRepository.class);
        dynamoDbConfig = mock(DynamoDbConfig.class);
        product = mock(Product.class);
        transactionRecord = mock(TransactionRecord.class);
        transactionService = new TransactionService(transactionRepository, dynamoDbConfig);


    }

    @Test
    void generateTransaction() {
    }

    @Test
    void findTransactionID() {

        List<Integer> itemsPurchased = Collections.singletonList(1);
        List<String> productIDS = new ArrayList<>();
        double totalSales = 12.99;
        Integer quantity = 1;


        TransactionRecord transaction1 = new TransactionRecord();
        transaction1.setDate(LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG, FormatStyle.MEDIUM)));
        transaction1.setCustomerID("bob");
        transaction1.setProductID(productIDS);
        transaction1.setQuantity(quantity);
        transaction1.setTotalSale(totalSales);
        transaction1.setTransactionID(randomUUID().toString());
        transaction1.setAmountPurchasedPerProduct(itemsPurchased);

        when(transactionRepository.findById(transaction1.getTransactionID())).thenReturn(Optional.of(transaction1));


        Transaction transaction = transactionService.findTransactionID(transaction1.getTransactionID());

        Assertions.assertNotNull(transaction);
        assertEquals(transaction1.getDate(), transaction.getDate(), "The Transaction Dates Match.");
        assertEquals(transaction1.getCustomerID(), transaction.getCustomerID(), "The Customer IDs match.");
        assertEquals(transaction1.getProductID(), transaction.getProductID(), "The product IDs match.");
        assertEquals(transaction1.getQuantity(), transaction.getQuantity(), "The product quantities match.");
        assertEquals(transaction1.getTotalSale(), transaction.getTotalSale(), "The sales match.");
        assertEquals(transaction1.getTransactionID(), transaction.getTransactionID(), "The transaction IDs match.");
        assertEquals(transaction1.getAmountPurchasedPerProduct(), transaction.getAmountPurchasedPerProduct(),
                "The Amounts per product match.");
    }

    @Test
    void getAllTransactions() {
        List<Integer> itemsPurchased1 = Collections.singletonList(1);
        List<String> productIDS1 = new ArrayList<>();
        double totalSales1 = 12.99;
        Integer quantity1 = 1;


        TransactionRecord transaction1 = new TransactionRecord();
        transaction1.setDate(LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG, FormatStyle.MEDIUM)));
        transaction1.setCustomerID("bob");
        transaction1.setProductID(productIDS1);
        transaction1.setQuantity(quantity1);
        transaction1.setTotalSale(totalSales1);
        transaction1.setTransactionID(randomUUID().toString());
        transaction1.setAmountPurchasedPerProduct(itemsPurchased1);

        List<Integer> itemsPurchased2 = Collections.singletonList(1);
        List<String> productIDS2 = new ArrayList<>();
        double totalSales2 = 12.99;
        Integer quantity2 = 1;


        TransactionRecord transaction2 = new TransactionRecord();
        transaction1.setDate(LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG, FormatStyle.MEDIUM)));
        transaction1.setCustomerID("Jerry");
        transaction1.setProductID(productIDS2);
        transaction1.setQuantity(quantity2);
        transaction1.setTotalSale(totalSales2);
        transaction1.setTransactionID(randomUUID().toString());
        transaction1.setAmountPurchasedPerProduct(itemsPurchased2);

        List<TransactionRecord> transactions =new ArrayList<>();
        transactions.add(transaction1);
        transactions.add(transaction2);

        when(transactionRepository.findAll()).thenReturn(transactions);
        List<Transaction> allTransactions = transactionService.getAllTransactions();

        Assertions.assertNotNull(allTransactions, "The Transactions are returned");
        assertEquals(2,transactions.size(), "There are 2 transactions");

        for (Transaction transaction: allTransactions)
            if (transaction.getTransactionID() == transaction1.getTransactionID()) {
                assertEquals(transaction1.getDate(), transaction.getDate(), "The Transaction Dates Match.");
                assertEquals(transaction1.getCustomerID(), transaction.getCustomerID(), "The Customer IDs match.");
                assertEquals(transaction1.getProductID(), transaction.getProductID(), "The product IDs match.");
                assertEquals(transaction1.getQuantity(), transaction.getQuantity(), "The product quantities match.");
                assertEquals(transaction1.getTotalSale(), transaction.getTotalSale(), "The sales match.");
                assertEquals(transaction1.getTransactionID(), transaction.getTransactionID(), "The transaction IDs match.");
                assertEquals(transaction1.getAmountPurchasedPerProduct(), transaction.getAmountPurchasedPerProduct(),
                        "The Amounts per product match.");
            }else     if (transaction.getTransactionID() == transaction2.getTransactionID()) {
                assertEquals(transaction2.getDate(), transaction.getDate(), "The Transaction Dates Match.");
                assertEquals(transaction2.getCustomerID(), transaction.getCustomerID(), "The Customer IDs match.");
                assertEquals(transaction2.getProductID(), transaction.getProductID(), "The product IDs match.");
                assertEquals(transaction2.getQuantity(), transaction.getQuantity(), "The product quantities match.");
                assertEquals(transaction2.getTotalSale(), transaction.getTotalSale(), "The sales match.");
                assertEquals(transaction2.getTransactionID(), transaction.getTransactionID(), "The transaction IDs match.");
                assertEquals(transaction2.getAmountPurchasedPerProduct(), transaction.getAmountPurchasedPerProduct(),
                        "The Amounts per product match.");
            }else
                Assertions.assertTrue(false, "transactions do not exits");


    }

    @Test
    void transactionByCustomerID() {

    }

    @Test
    void transactionByDate() {
    }

}