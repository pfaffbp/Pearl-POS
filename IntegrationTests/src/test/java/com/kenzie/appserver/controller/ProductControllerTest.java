package com.kenzie.appserver.controller;

import com.kenzie.appserver.IntegrationTest;
import com.kenzie.appserver.service.ProductService;
import com.kenzie.appserver.service.TransactionService;
import com.kenzie.appserver.service.model.Product;
import net.andreinc.mockneat.MockNeat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

@IntegrationTest
public class ProductControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    ProductService productService;

    @Autowired
    TransactionService transactionService;

    private final MockNeat mockNeat = MockNeat.threadLocal();

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void Test(){
        Product product = new Product();
        product.setQuantity(100);
        product.setPrice(8.99);

        Product product2 = productService.addProduct(product);
        productService.buyProducts(product, 2);

    }

}
