package com.kenzie.appserver.service.model;

public class Sales {
    private String month;
    private double totalSales;
    private double averageSalePrice;

    public Sales(String month, double totalSales, double averageSalePrice) {
        this.month = month;
        this.totalSales = totalSales;
        this.averageSalePrice = averageSalePrice;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public double getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(double totalSales) {
        this.totalSales = totalSales;
    }

    public double getAverageSalePrice() {
        return averageSalePrice;
    }

    public void setAverageSalePrice(double averageSalePrice) {
        this.averageSalePrice = averageSalePrice;
    }
}
