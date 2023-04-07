package com.kenzie.appserver.service;
<<<<<<< HEAD

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
import com.kenzie.appserver.controller.model.ProductModels.ProductResponse;
=======
>>>>>>> 05a2520 (preptomerge)
=======
>>>>>>> 78e3b20 (login and create user all test passing for service and controller)
=======
import com.kenzie.appserver.controller.model.ProductModels.ProductResponse;
>>>>>>> eb90ed7 (Revert "login and create user all test passing for service and controller")
import com.kenzie.appserver.repositories.ProductRepository;
import com.kenzie.appserver.repositories.TransactionRepository;
import com.kenzie.appserver.repositories.model.ProductRecord;
import com.kenzie.appserver.service.model.Product;
<<<<<<< HEAD
import org.junit.jupiter.api.Assertions;
=======
>>>>>>> 05a2520 (preptomerge)
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.UUID.randomUUID;
<<<<<<< HEAD
<<<<<<< HEAD
import static org.junit.jupiter.api.Assertions.*;
=======
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
>>>>>>> 05a2520 (preptomerge)
=======
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
>>>>>>> 78e3b20 (login and create user all test passing for service and controller)
=======
import static org.junit.jupiter.api.Assertions.*;
>>>>>>> eb90ed7 (Revert "login and create user all test passing for service and controller")
import static org.mockito.Mockito.*;

public class ProductServiceTest {

<<<<<<< HEAD
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
<<<<<<< HEAD
=======
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
=======
    @Mock
    private ProductRepository productRepository;

>>>>>>> 05a2520 (preptomerge)
    @Mock
    private TransactionRepository transactionRepository;

    private ProductService productService;

<<<<<<< HEAD
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        productService = new ProductService(productRepository,transactionRepository, transactionService);
>>>>>>> d60c250 (Made purchaseProducts accept multiple product id, and made Transaction service accept multiple productIDS)
=======
>>>>>>> aa4f162 (merging new transations with maps to uses if needed)
=======
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
>>>>>>> 05a2520 (preptomerge)
    }


    @Test
<<<<<<< HEAD
<<<<<<< HEAD
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

    @Test
    void buyProductsTest() {



=======
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
<<<<<<< HEAD
>>>>>>> d60c250 (Made purchaseProducts accept multiple product id, and made Transaction service accept multiple productIDS)
=======
>>>>>>> aa4f162 (merging new transations with maps to uses if needed)
}
=======
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
>>>>>>> 05a2520 (preptomerge)
