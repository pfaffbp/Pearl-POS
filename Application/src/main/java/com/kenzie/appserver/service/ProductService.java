package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.model.ProductRecord;
import com.kenzie.appserver.repositories.ProductRepository;
import com.kenzie.appserver.service.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
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
           return productHelperMethod(productRecord);
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
        createNewProduct.setProductID(product.getProductID());
        createNewProduct.setProductName(product.getProductName());
        createNewProduct.setCategory(product.getCategory());
        createNewProduct.setPrice(product.getPrice());
        createNewProduct.setQuantity(product.getQuantity());
        createNewProduct.setDescription(product.getDescription());

        return createNewProduct;
    }

}
