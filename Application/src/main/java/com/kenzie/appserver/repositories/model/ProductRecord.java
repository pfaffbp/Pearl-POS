package com.kenzie.appserver.repositories.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.kenzie.appserver.service.model.Product;
import org.springframework.data.annotation.Id;

import java.util.Objects;

@DynamoDBTable(tableName = "Products")
public class ProductRecord {
    @Id
    @DynamoDBHashKey(attributeName = "productID")
    private String productID;

    @DynamoDBAttribute(attributeName = "productName")
    private String productName;

    @DynamoDBAttribute(attributeName = "price")
    private Double price;

    @DynamoDBAttribute(attributeName = "quantity")
    private Integer quantity;

    @DynamoDBAttribute(attributeName = "description")
    private String description;

    @DynamoDBAttribute(attributeName = "category")
    private String category;


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
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return productName.equals(product.getProductName()) && productID.equals(product.getProductID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(productName, productID);
    }
}
