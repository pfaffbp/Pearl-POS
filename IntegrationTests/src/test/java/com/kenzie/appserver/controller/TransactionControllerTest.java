//package com.kenzie.appserver.controller;
//
//import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.kenzie.appserver.IntegrationTest;
//import com.kenzie.appserver.service.ProductService;
//import com.kenzie.appserver.service.TransactionService;
//import com.kenzie.appserver.service.model.Product;
//import net.andreinc.mockneat.MockNeat;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//
//import static org.hamcrest.Matchers.is;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.time.format.FormatStyle;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.UUID;
//
//
//
//@IntegrationTest
//public class TransactionControllerTest {
//    @Autowired
//    private MockMvc mvc;
//
//    @Autowired
//    TransactionService transactionService;
//
//    @Autowired
//    ProductService productService;
//
//    private final MockNeat mockNeat = MockNeat.threadLocal();
//
//    private final ObjectMapper mapper = new ObjectMapper();
//
//
//
//    @Test
//    public void transaction_getByCustomerID_exists() throws Exception{
//        Product product = new Product();
//        product.setProductName("ToothPaste");
//        product.setPrice(9.99);
//        product.setQuantity(100);
//        product.setDescription("White teeth");
//        product.setCategory("Item");
//        product.setProductID(UUID.randomUUID().toString());
//        Product products = productService.addProduct(product);
//
//        String customerID = "TestCustomer";
//
//        List<Product> productList = new ArrayList<>();
//        productList.add(products);
//
//        List<Integer> itemsPurchased = new ArrayList<>();
//        itemsPurchased.add(3);
//
//        productService.buyProducts(productList, itemsPurchased);
//        String time = LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG, FormatStyle.MEDIUM));
//        System.out.println(time);
//
//        mvc.perform(get("/transaction/customer/{customerID}", customerID)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("date")
//                        .value(is(time)))
//                .andExpect(jsonPath("price")
//                        .value(is(products.getPrice())))
//                .andExpect(jsonPath("quantity")
//                        .value(is(products.getQuantity())))
//                .andExpect(jsonPath("description")
//                        .value(is(products.getDescription())))
//                .andExpect(jsonPath("category")
//                        .value(is(products.getCategory())))
//                .andExpect(status().isOk());
//    }
//}
