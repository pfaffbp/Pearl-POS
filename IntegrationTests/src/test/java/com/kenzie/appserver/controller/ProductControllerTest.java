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

import java.util.ArrayList;
import java.util.List;

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
        product.setProductName("ToothPaste");
        product.setPrice(9.99);
        product.setQuantity(100);

        Product product2 = new Product();
        product2.setProductName("IPhone");
        product2.setPrice(999.99);
        product2.setQuantity(100);

        Product product3 = new Product();
        product2.setProductName("Doritos");
        product3.setPrice(7.99);
        product3.setQuantity(100);

        productService.addProduct(product);
        productService.addProduct(product2);
        productService.addProduct(product3);

        List<Product> productList = new ArrayList<>();
        productList.add(product);
        productList.add(product2);
        productList.add(product3);

        List<Integer> itemsPurchased = new ArrayList<>();
        itemsPurchased.add(5);
        itemsPurchased.add(10);
        itemsPurchased.add(15);

        List<Integer> itemsPurchased2 = new ArrayList<>();
        itemsPurchased2.add(10);
        itemsPurchased2.add(5);
        itemsPurchased2.add(12);

        List<Product> products = productService.buyProducts(productList, itemsPurchased);
        List<Product> products2 = productService.buyProducts(productList, itemsPurchased2);

        String hi = "";

    }

}
