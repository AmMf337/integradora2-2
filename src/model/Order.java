package model;

import java.util.ArrayList;
import java.util.Date;

public class Order {
    private String buyerName;
    private ArrayList<String> productList;
    private ArrayList<Integer> quantityList;
    private double totalPrice;
    private Date dateOfPurchase;

    public Order(String buyerName, ArrayList<String> productList, ArrayList<Integer> quantityList, double totalPrice,Date dateOfPurchase) {
        this.buyerName = buyerName;
        this.productList = productList;
        this.quantityList = quantityList;
        this.totalPrice = totalPrice;
        this.dateOfPurchase = dateOfPurchase;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public ArrayList<String> getProductList() {
        return productList;
    }

    public void setProductList(ArrayList<String> productList) {
        this.productList = productList;
    }

    public ArrayList<Integer> getQuantityList() {
        return quantityList;
    }

    public void setQuantityList(ArrayList<Integer> quantityList) {
        this.quantityList = quantityList;
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
    @Override
    public String toString(){
        String msj = "Buyer name: "+this.buyerName+"\n";
        for (int i = 0;i<productList.size();i++) {
            msj += "Product name: "+productList.get(i)+" Product Quantitie: "+quantityList.get(i)+"\n";
        }
        msj += "Total price: "+totalPrice+"\n";
        msj += "Date of purchase: "+dateOfPurchase.toString();
        return msj;
    }
}
