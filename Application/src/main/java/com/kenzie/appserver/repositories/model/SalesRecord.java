package com.kenzie.appserver.repositories.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Objects;

@DynamoDBTable(tableName = "Sales")
public class SalesRecord {

    private String id;
    private String productId;
    private String customerId;
    private int quantity;
    private long totalSale;
    private String transactionId;

    @DynamoDBHashKey(attributeName = "Id")
    public String getId() {
        return id;
    }

    @DynamoDBAttribute(attributeName = "ProductId")
    public String getProductId() {
        return productId;
    }

    @DynamoDBAttribute(attributeName = "CustomerId")
    public String getCustomerId() {
        return customerId;
    }

    @DynamoDBAttribute(attributeName = "Quantity")
    public int getQuantity() {
        return quantity;
    }

    @DynamoDBAttribute(attributeName = "TotalSale")
    public long getTotalSale() {
        return totalSale;
    }

    @DynamoDBAttribute(attributeName = "TransactionId")
    public String getTransactionId() {
        return transactionId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setTotalSale(long totalSale) {
        this.totalSale = totalSale;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SalesRecord salesRecord = (SalesRecord) o;
        return Objects.equals(id, salesRecord.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
