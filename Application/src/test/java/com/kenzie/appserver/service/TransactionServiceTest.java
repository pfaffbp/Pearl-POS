//package com.kenzie.appserver.service;
//
//import com.kenzie.appserver.repositories.TransactionRepository;
//import com.kenzie.appserver.repositories.model.TransactionRecord;
//import com.kenzie.appserver.service.model.Transaction;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.time.LocalDateTime;
//import java.util.Collections;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//public class TransactionServiceTest {
//
//    private TransactionService transactionService;
//
//    @Mock
//    private TransactionRepository transactionRepository;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//        transactionService = new TransactionService(transactionRepository);
//    }
//
//    @Test
//    public void testGenerateTransaction() {
//        Transaction transaction = createTransaction();
//
//        TransactionRecord expectedRecord = createTransactionRecord(transaction);
//        when(transactionRepository.save(any())).thenReturn(expectedRecord);
//
//        TransactionRecord actualRecord = transactionService.generateTransaction(transaction);
//
//        assertEquals(expectedRecord, actualRecord);
//        verify(transactionRepository).save(expectedRecord);
//    }
//
//    @Test
//    public void testFindTransactionByDate() {
//        String date = LocalDateTime.now().toString();
//
//        TransactionRecord expectedRecord = createTransactionRecord(createTransaction());
//        when(transactionRepository.findById(date)).thenReturn(Optional.of(expectedRecord));
//
//        Transaction actualTransaction = transactionService.findTransactionByDate(date);
//
//        assertEquals(createTransaction(), actualTransaction);
//        verify(transactionRepository).findById(date);
//    }
//
//    private Transaction createTransaction() {
//        Transaction transaction = new Transaction();
//        transaction.setTransactionID("1");
//        transaction.setQuantity(2);
//        transaction.setDate(LocalDateTime.now().toString());
//        transaction.setProductID(Collections.singletonList("1"));
//        transaction.setCustomerID("TestCustomer");
//        transaction.setTotalSale(20.0);
//        return transaction;
//    }
//
//    private TransactionRecord createTransactionRecord(Transaction transaction) {
//        TransactionRecord record = new TransactionRecord();
//        record.setTransactionID(transaction.getTransactionID());
//        record.setQuantity(transaction.getQuantity());
//        record.setDate(transaction.getDate());
//        record.setProductID(transaction.getProductID());
//        record.setCustomerID(transaction.getCustomerID());
//        record.setTotalSale(transaction.getTotalSale());
//        return record;
//    }
//
//}
