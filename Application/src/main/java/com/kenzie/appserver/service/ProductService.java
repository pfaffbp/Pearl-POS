package com.kenzie.appserver.service;

import com.kenzie.appserver.Exceptions.ProductNotFoundException;
import com.kenzie.appserver.controller.TransactionController;
import com.kenzie.appserver.repositories.TransactionRepository;
import com.kenzie.appserver.repositories.model.ProductRecord;
import com.kenzie.appserver.repositories.ProductRepository;
import com.kenzie.appserver.service.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import java.util.*;


@Service
public class ProductService {
    private ProductRepository productRepository;

    private TransactionService transactionService;

    private TransactionRepository transactionRepository;



    @Autowired
    public ProductService(ProductRepository productRepository,
                          TransactionRepository transactionRepository, TransactionService transactionService){
        this.productRepository = productRepository;
        this.transactionRepository = transactionRepository;
        this.transactionService = transactionService;
    }

    public List<Product> getAllProducts(){
        List<Product> allProducts = new ArrayList<Product>();
        productRepository.findAll().forEach(productRecord -> allProducts.add(productHelperMethod(productRecord)));
        return allProducts;
    }

    public Product addProduct(Product product){
        ProductRecord addNewProduct = productRecordHelperMethod(product);
        productRepository.save(addNewProduct);
        return product;
    }


    public Product findByProductID(String productID){
       Optional<ProductRecord> record = productRepository.findById(productID);

       if(record.isPresent()){
           ProductRecord productRecord = record.get();
           return recordToProductHelperMethod(productRecord);
       } else {
           return null;
       }
    }

    public void updateProduct(Product product){
        if(productRepository.existsById(product.getProductID())){
            ProductRecord productRecord = productRecordHelperMethod(product);
            productRepository.save(productRecord);
        }
    }

    public void deleteProduct(String productID){
        productRepository.deleteById(productID);
    }
    public List<Product> buyProducts(List<Product> product, List<Integer> itemsPurchased){
        Map<Product, Integer> productPurchasedMap = new HashMap<>();
        List<Product> productList = new ArrayList<>();
        List<Product> productResponse = new ArrayList<>();
        List<ProductRecord> productRecords = new ArrayList<>();

        if(product.size() == itemsPurchased.size()) {
            for(int i = 0; i < product.size(); i++){
                if(productRepository.existsById(product.get(i).getProductID()) == true){
                    productPurchasedMap.put(product.get(i), itemsPurchased.get(i));
                    productList.add(product.get(i));
                } else {
                    throw new ProductNotFoundException("This product does not exist: " + product.get(i).getProductID());
                }
            }
        }
        for(Map.Entry<Product, Integer> productIntegerMap : productPurchasedMap.entrySet()){
            if(productIntegerMap.getKey().getQuantity() >= productIntegerMap.getValue()){
                ProductRecord productRecord = new ProductRecord();
                productRecord.setProductID(productIntegerMap.getKey().getProductID());
                productRecord.setProductName(productIntegerMap.getKey().getProductName());
                productRecord.setPrice(productIntegerMap.getKey().getPrice());
                productRecord.setQuantity(productIntegerMap.getKey().getQuantity() - productIntegerMap.getValue());
                productRecord.setDescription(productIntegerMap.getKey().getDescription());
                productRecord.setCategory(productIntegerMap.getKey().getCategory());
                productRecords.add(productRecord);

                productResponse.add(recordToProductHelperMethod(productRecord));
            }
        }
        productRepository.saveAll(productRecords);
        transactionService.generateTransaction(productList, itemsPurchased);

        return productResponse;
    }


    public ProductRecord productRecordHelperMethod(Product product){
        ProductRecord createNewProduct = new ProductRecord();
        createNewProduct.setProductID(product.getProductID());
        createNewProduct.setProductName(product.getProductName());
        createNewProduct.setCategory(product.getCategory());
        createNewProduct.setPrice(product.getPrice());
        createNewProduct.setQuantity(product.getQuantity());
        createNewProduct.setDescription(product.getDescription());

        return createNewProduct;
    }

    public Product productHelperMethod(ProductRecord product){
        Product createNewProduct = new Product();
        createNewProduct.setProductName(product.getProductName());
        createNewProduct.setCategory(product.getCategory());
        createNewProduct.setPrice(product.getPrice());
        createNewProduct.setQuantity(product.getQuantity());
        createNewProduct.setDescription(product.getDescription());
        createNewProduct.setProductID(product.getProductID());

        return createNewProduct;
    }

    public Product recordToProductHelperMethod(ProductRecord product){
        Product createNewProduct = new Product();
        createNewProduct.setProductID(product.getProductID());
        createNewProduct.setProductName(product.getProductName());
        createNewProduct.setCategory(product.getCategory());
        createNewProduct.setPrice(product.getPrice());
        createNewProduct.setQuantity(product.getQuantity());
        createNewProduct.setDescription(product.getDescription());

        return createNewProduct;
    }


}
