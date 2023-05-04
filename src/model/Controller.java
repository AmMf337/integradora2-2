package model;

import java.util.ArrayList;

public class Controller {
    private ArrayList<Product> products;
    private ArrayList<Order> orders;

    public Controller() {
        products = new ArrayList<>();
        orders = new ArrayList<>();
    }

    public String addProduct(String name, String description, double price, int availableQuantity, int category,
            int quantityOfSales) {
        String msj = "product added";
        Product newProduct = new Product(name, description, price, availableQuantity, category, quantityOfSales);
        products.add(newProduct);
        return msj;
    }

    public String increaseProductQuantity(String name, int quantity) {
        String msj = "";
        int position = searchProductPosition(name);
        int newQuantity = 0;
        if (position == -1) {
            msj = "the product hasnt been found";
        } else {
            newQuantity = products.get(position).getAvailableQuantity() + quantity;
            products.get(position).setAvailableQuantity(newQuantity);
            msj = "the product quantity have been change";
        }
        return msj;
    }

    // search methods
    public int searchProductPosition(String name) {
        int position = -1;
        for (int i = 0; i < products.size(); i++) {
            if (name.equals(products.get(i).getName())) {
                position = i;
            }
        }
        return position;
    }
}