package model;

import java.util.Date;

public class Order {
    private String buyerName;
    private String[] productList;
    private double totalPrice;
    private Date dateOfPurchase;

    public Order(String buyerName, String[] productList, double totalPrice, Date dateOfPurchase) {
        this.buyerName = buyerName;
        this.productList = productList;
        this.totalPrice = totalPrice;
        this.dateOfPurchase = dateOfPurchase;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String[] getProductList() {
        return productList;
    }

    public void setProductList(String[] productList) {
        this.productList = productList;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(Date dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }
}
