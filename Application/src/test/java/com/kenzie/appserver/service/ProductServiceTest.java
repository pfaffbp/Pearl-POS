package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.ProductRepository;
import com.kenzie.appserver.repositories.TransactionRepository;
import com.kenzie.appserver.repositories.model.ProductRecord;
import com.kenzie.appserver.service.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private TransactionRepository transactionRepository;

    private ProductService productService;

    private Product product;

    private ProductRecord productRecord;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        productService = new ProductService(productRepository, transactionRepository, new TransactionService(transactionRepository));

        product = new Product();
        product.setProductID("1");
        product.setProductName("testProduct");
        product.setPrice(10.0);
        product.setQuantity(5);
        product.setDescription("This is a test product");
        product.setCategory("test");

        productRecord = new ProductRecord();
        productRecord.setProductID("1");
        productRecord.setProductName("testProduct");
        productRecord.setPrice(10.0);
        productRecord.setQuantity(5);
        productRecord.setDescription("This is a test product");
        productRecord.setCategory("test");
    }

    @Test
    public void testAddProduct() {
        ProductRecord productRecord = new ProductRecord();
        productRecord.setProductID(product.getProductID());
        productRecord.setProductName(product.getProductName());
        productRecord.setPrice(product.getPrice());
        productRecord.setQuantity(product.getQuantity());
        productRecord.setDescription(product.getDescription());
        productRecord.setCategory(product.getCategory());

        when(productRepository.save(productRecord)).thenReturn(productRecord);

        Product result = productService.addProduct(product);

        verify(productRepository, times(1)).save(productRecord);
        assertEquals(product.getProductID(), result.getProductID());
        assertEquals(product.getProductName(), result.getProductName());
        assertEquals(product.getPrice(), result.getPrice());
        assertEquals(product.getQuantity(), result.getQuantity());
        assertEquals(product.getDescription(), result.getDescription());
        assertEquals(product.getCategory(), result.getCategory());
    }


    @Test
    public void testFindByProductID() {
        when(productRepository.findById(product.getProductID())).thenReturn(Optional.of(productRecord));

        Product result = productService.findByProductID(product.getProductID());

        verify(productRepository, times(1)).findById(product.getProductID());
        assertEquals(product.getProductID(), result.getProductID());
        assertEquals(product.getProductName(), result.getProductName());
        assertEquals(product.getPrice(), result.getPrice());
        assertEquals(product.getQuantity(), result.getQuantity());
        assertEquals(product.getDescription(), result.getDescription());
        assertEquals(product.getCategory(), result.getCategory());
    }

    @Test
    void testUpdateProduct() {
        // Arrange
        Product product = new Product();
        product.setProductID("1");
        product.setProductName("testProduct");
        product.setPrice(10.0);
        product.setQuantity(5);
        product.setDescription("This is a test product");
        product.setCategory("test");

        ProductRecord productRecord = new ProductRecord();
        productRecord.setProductID(product.getProductID());
        productRecord.setProductName(product.getProductName());
        productRecord.setPrice(product.getPrice());
        productRecord.setQuantity(product.getQuantity());
        productRecord.setDescription(product.getDescription());
        productRecord.setCategory(product.getCategory());

        when(productRepository.findById(product.getProductID())).thenReturn(Optional.of(productRecord));
        when(productRepository.save(productRecord)).thenReturn(productRecord);

        // Act
        Product result = productService.updateProduct(product);

        // Assert
        verify(productRepository, times(1)).findById(product.getProductID());
        verify(productRepository, times(1)).save(productRecord);
        assertEquals(product.getProductID(), result.getProductID());
        assertEquals(product.getProductName(), result.getProductName());
        assertEquals(product.getPrice(), result.getPrice());
        assertEquals(product.getQuantity(), result.getQuantity());
        assertEquals(product.getDescription(), result.getDescription());
        assertEquals(product.getCategory(), result.getCategory());
    }


    @Test
    void testDeleteProduct() {
        // arrange
        String productId = "1";
        Product product = new Product();
        product.setProductID(productId);
        product.setProductName("testProduct");
        product.setPrice(10.0);
        product.setQuantity(5);
        product.setDescription("This is a test product");
        product.setCategory("test");

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        // act
        productService.deleteProduct(productId);
        // assert
        verify(productRepository, times(1)).deleteById(productId);
    }





    @Test
    public void testGetAllProducts() {
        List<ProductRecord> productRecordList = Collections.singletonList(productRecord);
        when(productRepository.findAll()).thenReturn(productRecordList);

        List<Product> result = productService.getAllProducts();

        verify(productRepository, times(1)).findAll();

        assertEquals(1, result.size());
        assertEquals(product.getProductID(), result.get(0).getProductID());
        assertEquals(product.getProductName(), result.get(0).getProductName());
        assertEquals(product.getPrice(), result.get(0).getPrice());
        assertEquals(product.getQuantity(), result.get(0).getQuantity());
        assertEquals(product.getDescription(), result.get(0).getDescription());
        assertEquals(product.getCategory(), result.get(0).getCategory());
    }



}