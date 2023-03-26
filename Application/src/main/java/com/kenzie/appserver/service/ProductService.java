package com.kenzie.appserver.service;

import com.kenzie.appserver.controller.TransactionController;
import com.kenzie.appserver.repositories.TransactionRepository;
import com.kenzie.appserver.repositories.model.ProductRecord;
import com.kenzie.appserver.repositories.ProductRepository;
import com.kenzie.appserver.service.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        if(productRepository.existsById(product.getProductID()) == true){
            ProductRecord productRecord = productRecordHelperMethod(product);
            productRepository.save(productRecord);
        };
    }

    public void deleteProduct(String productID){
        productRepository.deleteById(productID);
    }


    public Product buyProducts(Product product, int itemsPurchased){
        if(productRepository.existsById(product.getProductID()) == true){
            if(product.getQuantity() >= itemsPurchased){
                ProductRecord productRecord = new ProductRecord();
                productRecord.setProductID(product.getProductID());
                productRecord.setProductName(product.getProductName());
                productRecord.setPrice(product.getPrice());
                productRecord.setQuantity(product.getQuantity() - itemsPurchased);
                productRecord.setDescription(product.getDescription());
                productRecord.setCategory(product.getCategory());

                productRepository.save(productRecord);
                transactionService.generateTransaction(product, itemsPurchased);

                return recordToProductHelperMethod(productRecord);
            }
        }
        return null;
    }


//    public void buyMultipleProducts(Map<Product, Integer> purchaseMap){
//        purchaseMap = new HashMap<>();
//        List<ProductRecord> records = new ArrayList<>();
//        String currentProductID;
//        Integer currentItemsPurchasedForID;
//
//        for(Map.Entry<Product, Integer> products: purchaseMap.entrySet()){
//             currentProductID = products.getKey().getProductID();
//             currentItemsPurchasedForID = products.getValue();
//
//            if(currentProductID != null){
//                if(productRepository.existsById(currentProductID) == true){
//                    ProductRecord  productRecord = new ProductRecord();
//                    productRecord.setProductID(currentProductID);
//                    productRecord.setProductName(products.getKey().getProductName());
//                    productRecord.setPrice(products.getKey().getPrice());
//                    productRecord.setQuantity(products.getKey().getQuantity() - currentItemsPurchasedForID);
//                    productRecord.setCategory(products.getKey().getCategory());
//                    productRecord.setDescription(products.getKey().getDescription());
//
//                    records.add(productRecord);
//                }
//            }
//        }
//        productRepository.saveAll(records);
//
//    }


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
