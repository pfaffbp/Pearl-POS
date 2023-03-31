package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.TransactionRepository;
import com.kenzie.appserver.repositories.model.TransactionRecord;
import com.kenzie.appserver.service.model.Product;
import com.kenzie.appserver.service.model.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TransactionServiceTest {

    private TransactionService transactionService;

    @Mock
    private TransactionRepository transactionRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        transactionService = new TransactionService(transactionRepository);
    }

    @Test
    public void testGenerateTransaction() {
        Transaction transaction = new Transaction();
        transaction.setTransactionID("1");
        transaction.setQuantity(2);
        transaction.setDate(LocalDateTime.now().toString());
        transaction.setProductID("1");
        transaction.setCustomerID("TestCustomer");
        transaction.setTotalSale(20.0);

        TransactionRecord expectedTransactionRecord = new TransactionRecord();
        expectedTransactionRecord.setTransactionID("1");
        expectedTransactionRecord.setQuantity(2);
        expectedTransactionRecord.setDate(LocalDateTime.now().toString());
        expectedTransactionRecord.setProductID("1");
        expectedTransactionRecord.setCustomerID("TestCustomer");
        expectedTransactionRecord.setTotalSale(20.0);

        ArgumentCaptor<TransactionRecord> captor = ArgumentCaptor.forClass(TransactionRecord.class);

        when(transactionRepository.save(captor.capture())).thenReturn(expectedTransactionRecord);

        TransactionRecord result = transactionService.generateTransaction(transaction);

        verify(transactionRepository, times(1)).save(captor.capture());

        assertEquals("TestCustomer", captor.getValue().getCustomerID());
        assertEquals("1", captor.getValue().getProductID());
        assertEquals(2, captor.getValue().getQuantity());
        assertEquals(20.0, captor.getValue().getTotalSale());
        assertEquals("1", captor.getValue().getTransactionID());

        assertEquals("TestCustomer", result.getCustomerID());
        assertEquals("1", result.getProductID());
        assertEquals(2, result.getQuantity());
        assertEquals(20.0, result.getTotalSale());
        assertEquals("1", result.getTransactionID());
    }






    @Test
    public void testFindTransactionByDate() {
        String date = LocalDateTime.now().toString();

        TransactionRecord transactionRecord = new TransactionRecord();
        transactionRecord.setTransactionID("1");
        transactionRecord.setQuantity(2);
        transactionRecord.setDate(date);
        transactionRecord.setProductID("1");
        transactionRecord.setCustomerID("TestCustomer");
        transactionRecord.setTotalSale(20.0);

        when(transactionRepository.findById(date)).thenReturn(Optional.of(transactionRecord));

        Transaction result = transactionService.findTransactionByDate(date);

        verify(transactionRepository, times(1)).findById(date);
        assertEquals(date, result.getDate());
        assertEquals("TestCustomer", result.getCustomerID());
        assertEquals("1", result.getProductID());
        assertEquals(2, result.getQuantity());
        assertEquals(20.0, result.getTotalSale());
        assertEquals("1", result.getTransactionID());
    }

}
