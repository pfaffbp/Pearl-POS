<<<<<<< HEAD

<<<<<<< HEAD
public class Sales {

    private String id;
    private String name;
    private Double price;
    private Integer quantity;
    private String date;

    public Sales(String id, String name, Double price, Integer quantity, String date) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getDate() {
        return date;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
*/
=======
=======
>>>>>>> 8557766 (merging with main 4/1 6:35)
//package com.kenzie.appserver.service.model;
//
//public class Sales {
//
//    private String id;
//    private String name;
//    private Double price;
//    private Integer quantity;
//    private String date;
//
//    public Sales() {
//    }
//
//    public Sales(String id, String name, Double price, Integer quantity, String date) {
//        this.id = id;
//        this.name = name;
//        this.price = price;
//        this.quantity = quantity;
//        this.date = date;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public Double getPrice() {
//        return price;
//    }
//
//    public Integer getQuantity() {
//        return quantity;
//    }
//
//    public String getDate() {
//        return date;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setPrice(Double price) {
//        this.price = price;
//    }
//
//    public void setQuantity(Integer quantity) {
//        this.quantity = quantity;
//    }
//
//    public void setDate(String date) {
//        this.date = date;
//    }
//
//}
<<<<<<< HEAD
>>>>>>> d60c250 (Made purchaseProducts accept multiple product id, and made Transaction service accept multiple productIDS)
=======

>>>>>>> 8557766 (merging with main 4/1 6:35)
=======
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
>>>>>>> 05a2520 (preptomerge)
