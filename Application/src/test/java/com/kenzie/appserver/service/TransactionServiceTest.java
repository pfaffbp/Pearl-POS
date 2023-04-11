
package com.kenzie.appserver.service;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedList;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.QueryResult;
import com.kenzie.appserver.repositories.TransactionRepository;
import com.kenzie.appserver.repositories.model.TransactionRecord;
import com.kenzie.appserver.service.model.Product;
import com.kenzie.appserver.service.model.Transaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.stubbing.defaultanswers.ForwardsInvocations;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;

import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TransactionServiceTest {
    private TransactionRepository transactionRepository;
    private TransactionService transactionService;
    private TransactionRecord transactionRecord;

    private DynamoDBMapper mapper;

    private Product product;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mapper = mock(DynamoDBMapper.class);
        transactionRepository = mock(TransactionRepository.class);
        product = mock(Product.class);
        transactionRecord = mock(TransactionRecord.class);
        transactionService = new TransactionService(transactionRepository, mapper);
    }
//todo
//    @Test
//    public void testGenerateTransaction() {
//        Transaction transaction = new Transaction();
//        transaction.setTransactionID("1");
//        transaction.setQuantity(2);
//        transaction.setDate(LocalDateTime.now().toString());
//        transaction.setProductID("1");
//        transaction.setCustomerID("TestCustomer");
//        transaction.setTotalSale(20.0);
//
//        TransactionRecord expectedTransactionRecord = new TransactionRecord();
//        expectedTransactionRecord.setTransactionID("1");
//        expectedTransactionRecord.setQuantity(2);
//        expectedTransactionRecord.setDate(LocalDateTime.now().toString());
//        expectedTransactionRecord.setProductID("1");
//        expectedTransactionRecord.setCustomerID("TestCustomer");
//        expectedTransactionRecord.setTotalSale(20.0);
//
//        ArgumentCaptor<TransactionRecord> captor = ArgumentCaptor.forClass(TransactionRecord.class);
//
//        when(transactionRepository.save(captor.capture())).thenReturn(expectedTransactionRecord);
//
//        TransactionRecord result = transactionService.generateTransaction(transaction);
//
//        verify(transactionRepository, times(1)).save(captor.capture());
//
//        assertEquals("TestCustomer", captor.getValue().getCustomerID());
//        assertEquals("1", captor.getValue().getProductID());
//        assertEquals(2, captor.getValue().getQuantity());
//        assertEquals(20.0, captor.getValue().getTotalSale());
//        assertEquals("1", captor.getValue().getTransactionID());
//
//        assertEquals("TestCustomer", result.getCustomerID());
//        assertEquals("1", result.getProductID());
//        assertEquals(2, result.getQuantity());
//        assertEquals(20.0, result.getTotalSale());
//        assertEquals("1", result.getTransactionID());
//    }

    @Test
    public void generateTransaction(){
        Product product = new Product();
        product.setProductName("Doritos");
        product.setQuantity(30);
        product.setPrice(7.99);
        product.setCategory("Food");
        product.setProductID(randomUUID().toString());
        product.setDescription("Nacho your Business");

        List<Product> productList = new ArrayList<>();
        productList.add(product);

        Integer quant = 3;
        List<Integer> itemsPurchased = new ArrayList<>();
        itemsPurchased.add(quant);

        TransactionRecord transactionRecord1 =  transactionService.generateTransaction(productList, itemsPurchased, "Test");

        assertEquals("TestCustomer", transactionRecord1.getCustomerID());
        assertEquals(product.getProductID(), transactionRecord1.getProductID().get(0));
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
    void findTransactionByID_isNull_Test() {
        TransactionRecord transactionNull = new TransactionRecord();
        transactionNull.setTransactionID(null);

        when(transactionRepository.findById(transactionNull.getTransactionID())).thenReturn(Optional.empty());
        Transaction response = transactionService.findTransactionID(transactionNull.getTransactionID());

        assertNull(response);
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

        List<TransactionRecord> transactions = new ArrayList<>();
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
        TransactionRecord transaction1 = new TransactionRecord();
        transaction1.setDate(LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG, FormatStyle.MEDIUM)));
        transaction1.setCustomerID("Jerry");
        transaction1.setProductID(Collections.singletonList(randomUUID().toString()));
        transaction1.setQuantity(22);
        transaction1.setTotalSale(100.00);
        transaction1.setTransactionID(randomUUID().toString());
        transaction1.setAmountPurchasedPerProduct(Collections.singletonList(11));

        List<TransactionRecord> list = new ArrayList<>();
        list.add(transaction1);

        when(mapper.query(eq(TransactionRecord.class), any(DynamoDBQueryExpression.class)))
                .thenReturn(mock(PaginatedQueryList.class, withSettings().defaultAnswer(new ForwardsInvocations(list))));

        List<TransactionRecord> transactionRecords = transactionService.transactionByCustomerID(transaction1.getCustomerID());
        assertEquals(transactionRecords.get(0).getCustomerID(), transaction1.getCustomerID());

    }
//todo
//    @Test
//    public void testFindTransactionByDate() {
//        String date = LocalDateTime.now().toString();
//
//        TransactionRecord transactionRecord = new TransactionRecord();
//        transactionRecord.setTransactionID("1");
//        transactionRecord.setQuantity(2);
//        transactionRecord.setDate(date);
//        transactionRecord.setProductID("1");
//        transactionRecord.setCustomerID("TestCustomer");
//        transactionRecord.setTotalSale(20.0);
//
//        when(transactionRepository.findById(date)).thenReturn(Optional.of(transactionRecord));
//
//        Transaction result = transactionService.findTransactionByDate(date);
//
//        verify(transactionRepository, times(1)).findById(date);
//        assertEquals(date, result.getDate());
//        assertEquals("TestCustomer", result.getCustomerID());
//        assertEquals("1", result.getProductID());
//        assertEquals(2, result.getQuantity());
//        assertEquals(20.0, result.getTotalSale());
//        assertEquals("1", result.getTransactionID());
//    }

}