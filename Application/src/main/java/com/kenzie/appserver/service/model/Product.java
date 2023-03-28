package com.kenzie.appserver.service.model;

import java.util.Objects;
import java.util.UUID;

public class Product {
    private String productName;

    private Double price;

    private Integer quantity;

    private String description;

    private String category;

    private String ProductID;


    public Product(){
        this.ProductID = UUID.randomUUID().toString();
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
            this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String productID) {
        this.ProductID = productID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return productName.equals(product.productName) && ProductID.equals(product.ProductID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productName, ProductID);
    }
}
