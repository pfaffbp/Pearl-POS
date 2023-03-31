package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.TransactionRepository;
import com.kenzie.appserver.repositories.model.TransactionRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class SalesServiceTest {

    private SalesService salesService;

    @Mock
    private TransactionRepository transactionRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        salesService = new SalesService(transactionRepository);
    }

    @Test
    void testGetMonthlySales() {
        // Mock the repository response
        TransactionRecord record1 = new TransactionRecord();
        record1.setDate("2022-01-01T00:00:00");
        record1.setTotalSale(100.0);
        TransactionRecord record2 = new TransactionRecord();
        record2.setDate("2022-01-15T00:00:00");
        record2.setTotalSale(200.0);
        when(transactionRepository.findAll()).thenReturn(Arrays.asList(record1, record2));

        // Expected result
        Map<String, Object> month1 = new TreeMap<>();
        month1.put("month", "2022-01");
        month1.put("totalSales", 300.0);
        month1.put("averageSalePrice", 150.0);
        List<Map<String, Object>> expected = new ArrayList<>();
        expected.add(month1);

        // Test the method
        List<Map<String, Object>> result = salesService.getMonthlySales();
        assertEquals(expected, result);
    }

    @Test
    void testGetDailySales() {
        // Mock the repository response
        TransactionRecord record1 = new TransactionRecord();
        record1.setDate("2022-01-01T00:00:00");
        record1.setTotalSale(100.0);
        TransactionRecord record2 = new TransactionRecord();
        record2.setDate("2022-01-01T10:00:00");
        record2.setTotalSale(200.0);
        when(transactionRepository.findAll()).thenReturn(Arrays.asList(record1, record2));

        // Expected result
        Map<String, Object> day1 = new TreeMap<>();
        day1.put("day", "2022-01-01");
        day1.put("totalSales", 300.0);
        day1.put("averageSalePrice", 150.0);
        List<Map<String, Object>> expected = new ArrayList<>();
        expected.add(day1);

        // Test the method
        List<Map<String, Object>> result = salesService.getDailySales();
        assertEquals(expected, result);
    }

    @Test
    void testGetHourlySales() {
        // Mock the repository response
        TransactionRecord record1 = new TransactionRecord();
        record1.setDate("2022-01-01T00:00:00");
        record1.setTotalSale(100.0);
        TransactionRecord record2 = new TransactionRecord();
        record2.setDate("2022-01-01T00:30:00");
        record2.setTotalSale(200.0);
        when(transactionRepository.findAll()).thenReturn(Arrays.asList(record1, record2));

        // Expected result
        Map<String, Object> hour1 = new TreeMap<>();
        hour1.put("hour", "2022-01-01T00:00:00");
        hour1.put("totalSales", 300.0);
        hour1.put("averageSalePrice", 150.0);
        List<Map<String, Object>> expected = new ArrayList<>();
        expected.add(hour1);

        // Test the method
        List<Map<String, Object>> result = salesService.getHourlySales();
        assertEquals(expected, result);
    }

    @Test
    void testGetTopSellingProducts() {
        // Mock the repository response
        TransactionRecord record1 = new TransactionRecord();
        record1.setDate("2022-01-01T00:00:00");
        record1.setTotalSale(100.0);
        record1.setProductID("Product 1");
        TransactionRecord record2 = new TransactionRecord();
        record2.setDate("2022-01-01T00:30:00");
        record2.setTotalSale(200.0);
        record2.setProductID("Product 2");
        when(transactionRepository.findAll()).thenReturn(Arrays.asList(record1, record2));

        // Expected result
        List<Map<String, Object>> expected = new ArrayList<>();
        Map<String, Object> product1 = new TreeMap<>();
        product1.put("product", "Product 1");
        product1.put("totalSales", 100.0);
        expected.add(product1);
        Map<String, Object> product2 = new TreeMap<>();
        product2.put("product", "Product 2");
        product2.put("totalSales", 200.0);
        expected.add(product2);
        expected.sort(Comparator.comparing(m -> m.get("product").toString()));

        // Test the method
        List<Map<String, Object>> result = salesService.getTopSellingProducts();
        result.sort(Comparator.comparing(m -> m.get("product").toString()));
        assertEquals(expected, result);
    }



}