package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.ProductRepository;
import com.kenzie.appserver.repositories.TransactionRepository;
import com.kenzie.appserver.service.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.enterprise.inject.New;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;
    @Mock
    private TransactionService transactionService;
    @Mock
    private TransactionRepository transactionRepository;

    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        productService = new ProductService(productRepository,transactionRepository, transactionService);
    }


    @Test
    public void Test(){
        Product product = new Product();
        product.setProductName("ToothPaste");
        product.setPrice(9.99);
        product.setQuantity(100);

        Product product2 = new Product();
        product2.setProductName("IPhone");
        product2.setPrice(999.99);
        product2.setQuantity(100);


    }
}
