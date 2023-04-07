package com.kenzie.appserver.service;
import com.kenzie.appserver.controller.model.ProductModels.ProductResponse;
import com.kenzie.appserver.repositories.ProductRepository;
import com.kenzie.appserver.repositories.TransactionRepository;
import com.kenzie.appserver.repositories.model.ProductRecord;
import com.kenzie.appserver.service.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    private ProductRepository productRepository;
    private TransactionService transactionService;
    private TransactionRepository transactionRepository;
    private ProductService productService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        productRepository = mock(ProductRepository.class);
        transactionService = mock((TransactionService.class));
        transactionRepository = mock(TransactionRepository.class);
        productService = new ProductService(productRepository, transactionRepository, transactionService);
    }

    @Test
    void getAllProducts_Test() {
        ProductRecord product1 = new ProductRecord();
        product1.setProductID(randomUUID().toString());
        product1.setProductName("Frozen Burrito");
        product1.setCategory("Food");
        product1.setPrice(12.99);
        product1.setQuantity(12);
        product1.setDescription("Beef and cheese");

        ProductRecord product2 = new ProductRecord();
        product2.setProductID(randomUUID().toString());
        product2.setProductName("Star Wars T-Shirt");
        product2.setCategory("Clothing");
        product2.setPrice(21.50);
        product2.setQuantity(20);
        product2.setDescription("Hano Solo with Luke");

        List<ProductRecord> products = new ArrayList<>();

        products.add(product1);
        products.add(product2);

        when(productRepository.findAll()).thenReturn(products);

        List<Product> allProducts = productService.getAllProducts();

        Assertions.assertNotNull(allProducts, "The products are returned");
        assertEquals(2, products.size(), "There are 2 products");

        for (Product product : allProducts) {
            if (product.getProductID() == product1.getProductID()) {
                assertEquals(product1.getProductName(), product.getProductName(), "The product names match.");
                assertEquals(product1.getCategory(), product.getCategory(), "The product categories match.");
                assertEquals(product1.getPrice(), product.getPrice(), "The product prices match.");
                assertEquals(product1.getQuantity(), product.getQuantity(), "The product quantities match.");
                assertEquals(product1.getDescription(), product.getDescription(), "The product descriptions match");
            } else if (product.getProductID() == product2.getProductID()) {
                assertEquals(product2.getProductName(), product.getProductName(), "The product names match.");
                assertEquals(product2.getCategory(), product.getCategory(), "The product categories match.");
                assertEquals(product2.getPrice(), product.getPrice(), "The product prices match.");
                assertEquals(product2.getQuantity(), product.getQuantity(), "The product quantities match.");
                assertEquals(product2.getDescription(), product.getDescription(), "The product descriptions match");

            } else
                Assertions.assertTrue(false, "product does not exist");
        }

    }

    //todo
    @Test
    void addProduct_Test() {
        String productId = randomUUID().toString();
        Product product1 = new Product();
        product1.setProductID(productId);
        product1.setProductName("Frozen Burrito");
        product1.setCategory("Food");
        product1.setPrice(12.99);
        product1.setQuantity(12);
        product1.setDescription("Beef and cheese");

        productService.addProduct(product1);

        ArgumentCaptor<ProductRecord> returnCaptor = ArgumentCaptor.forClass(ProductRecord.class);

        verify(productRepository).save(returnCaptor.capture());

        ProductRecord updatedProduct = returnCaptor.getValue();

        Assertions.assertNotNull(updatedProduct, "The product is returned.");
        assertEquals(updatedProduct.getProductID(), product1.getProductID(), "The Product Id match.");
        assertEquals(updatedProduct.getProductName(), product1.getProductName(), "The product names match.");
        assertEquals(updatedProduct.getCategory(), product1.getCategory(), "The product categories match.");
        assertEquals(updatedProduct.getPrice(), product1.getPrice(), "The product prices match.");
        assertEquals(updatedProduct.getQuantity(), product1.getQuantity(), "The product quantities match.");
        assertEquals(updatedProduct.getDescription(), product1.getDescription(), "The product descriptions match.");

    }

    @Test
    void findProductsByID_Test() {
        ProductRecord product1 = new ProductRecord();
        product1.setProductID(randomUUID().toString());
        product1.setProductName("Frozen Burrito");
        product1.setCategory("Food");
        product1.setPrice(12.99);
        product1.setQuantity(12);
        product1.setDescription("Beef and cheese");

        when(productRepository.findById(product1.getProductID())).thenReturn(Optional.of(product1));

        Product product = productService.findByProductID(product1.getProductID());

        Assertions.assertNotNull(product);
        assertEquals(product1.getProductID(), product.getProductID(), "The Product Id match.");
        assertEquals(product1.getProductName(), product.getProductName(), "The product names match.");
        assertEquals(product1.getCategory(), product.getCategory(), "The product categories match.");
        assertEquals(product1.getPrice(), product.getPrice(), "The product prices match.");
        assertEquals(product1.getQuantity(), product.getQuantity(), "The product quantities match.");
        assertEquals(product1.getDescription(), product.getDescription(), "The product descriptions match.");


    }

    @Test
    void findProductsByID_isNull_Test() {
        ProductRecord productNull = new ProductRecord();
        productNull.setProductID(null);

        when(productRepository.findById(productNull.getProductID())).thenReturn(Optional.empty());
        Product response = productService.findByProductID(productNull.getProductID());

        assertNull(response);
    }


    @Test
    void updateProduct_Test() {
        String productId = randomUUID().toString();
        Product product1 = new Product();
        product1.setProductID(productId);
        product1.setProductName("Frozen Burrito");
        product1.setCategory("Food");
        product1.setPrice(12.99);
        product1.setQuantity(12);
        product1.setDescription("Beef and cheese");

        Product updateproduct1 = new Product();
        updateproduct1.setProductID(productId);
        updateproduct1.setProductName("Frozen Chili Burrito");//trying to update name
        updateproduct1.setCategory("Food");
        updateproduct1.setPrice(15.99);//tyring to update price
        updateproduct1.setQuantity(12);
        updateproduct1.setDescription("Beef and chili cheese");//trying tu update description

        when(productRepository.existsById(product1.getProductID())).thenReturn(true);
        productService.updateProduct(updateproduct1);

        ArgumentCaptor<ProductRecord> returnCaptor = ArgumentCaptor.forClass(ProductRecord.class);

        verify(productRepository, times(1)).existsById(productId);
        verify(productRepository).save(returnCaptor.capture());

        ProductRecord updatedProduct = returnCaptor.getValue();

        Assertions.assertNotNull(updatedProduct, "The product is returned.");
        assertEquals(updatedProduct.getProductID(), updateproduct1.getProductID(), "The Product Id match.");
        assertEquals(updatedProduct.getProductName(), updateproduct1.getProductName(), "The product names match.");
        assertEquals(updatedProduct.getCategory(), updateproduct1.getCategory(), "The product categories match.");
        assertEquals(updatedProduct.getPrice(), updateproduct1.getPrice(), "The product prices match.");
        assertEquals(updatedProduct.getQuantity(), updateproduct1.getQuantity(), "The product quantities match.");
        assertEquals(updatedProduct.getDescription(), updateproduct1.getDescription(), "The product descriptions match.");
    }

    @Test
    void updateProduct_noProduct_Test() {
        String productId = randomUUID().toString();
        Product product1 = new Product();
        product1.setProductID(productId);
        product1.setProductName("Frozen Burrito");
        product1.setCategory("Food");
        product1.setPrice(12.99);
        product1.setQuantity(12);
        product1.setDescription("Beef and cheese");

        Product updateproduct1 = new Product();
        updateproduct1.setProductID(productId);
        updateproduct1.setProductName("Frozen Chili Burrito");//trying to update name
        updateproduct1.setCategory("Food");
        updateproduct1.setPrice(15.99);//tyring to update price
        updateproduct1.setQuantity(12);
        updateproduct1.setDescription("Beef and chili cheese");//trying tu update description

        when(productRepository.existsById(product1.getProductID())).thenReturn(false);
        productService.updateProduct(updateproduct1);

        verify(productRepository, times(1)).existsById(productId);
        verifyNoMoreInteractions(productRepository);


    }

    @Test
    void deleteProduct_Test() {
        ProductRecord product1 = new ProductRecord();
        product1.setProductID(randomUUID().toString());
        product1.setProductName("Frozen Burrito");
        product1.setCategory("Food");
        product1.setPrice(12.99);
        product1.setQuantity(12);
        product1.setDescription("Beef and cheese");

        productService.deleteProduct(product1.getProductID());
        verify(productRepository).deleteById(product1.getProductID());
    }


    //todo
    @Test
    void buyProductsTest() {


    }
}