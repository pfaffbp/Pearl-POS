package com.kenzie.appserver.controller;

import com.kenzie.appserver.IntegrationTest;
import com.kenzie.appserver.service.ProductService;
import com.kenzie.appserver.service.TransactionService;
import com.kenzie.appserver.service.model.Product;
import net.andreinc.mockneat.MockNeat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
    public void product_getByID_exists() throws Exception {
        Product product = new Product();
        product.setProductName("ToothPaste");
        product.setPrice(9.99);
        product.setQuantity(100);
        product.setDescription("White teeth");
        product.setCategory("Item");
        product.setProductID(UUID.randomUUID().toString());
        Product products = productService.addProduct(product);

//        mvc.perform(get("/products/{productID}", products.getProductID())
//                .accept(MediaType.APPLICATION_JSON))
//                    .andExpect(jsonPath("productName"))
//                        .value(is(products.getProductName()))
//                .
        mvc.perform(get("/products/{productID}", products.getProductID())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("productName")
                        .value(is(products.getProductName())))
                .andExpect(jsonPath("price")
                        .value(is(products.getPrice())))
                .andExpect(jsonPath("quantity")
                        .value(is(products.getQuantity())))
                .andExpect(jsonPath("description")
                        .value(is(products.getDescription())))
                .andExpect(jsonPath("category")
                        .value(is(products.getCategory())))
                .andExpect(status().isOk());

    }

}
