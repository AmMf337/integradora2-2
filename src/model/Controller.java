package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat; 
public class Controller {
    private ArrayList<Product> products;
    private ArrayList<Order> orders;

    public Controller() {
        products = new ArrayList<>();
        orders = new ArrayList<>();
    }
    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public String addProduct(String name, String description, double price, int availableQuantity, int category,
            int quantityOfSales) {
        String msj = "";
        int position = searchProductPosition(name);
        if(position == -1){
            Product newProduct = new Product(name, description, price, availableQuantity, category, quantityOfSales);
            products.add(newProduct);
            msj = "product added";
        }else{
            msj = "Product name already exists";
        }

        return msj;
    }

    public String increaseProductQuantity(String name, int quantity) {
        String msj = "";
        int position = searchProductPosition(name);
        int newQuantity = 0;
        if (position == -1) {
            msj = "the product hasn't been found";
        } else {
            newQuantity = products.get(position).getAvailableQuantity() + quantity;
            products.get(position).setAvailableQuantity(newQuantity);
            msj = "the product quantity have been change";
        }
        return msj;
    }

    public String addOrder(String buyerName, ArrayList<String> productList, ArrayList<Integer> quantityList,
            double totalPrice) {
        String msj = "order complete";
        Date dateOfPurchasDate = new Date();
        Order newOrder = new Order(buyerName, productList, quantityList, totalPrice,dateOfPurchasDate);
        orders.add(newOrder);
        return msj;

    }

    public void setNewQuantityProduct(String name, int quantityOrder) {
        int position = searchProductPosition(name);
        int quantityAvalible = searchProductQuantity(name);
        int result = quantityAvalible - quantityOrder;
        products.get(position).setAvailableQuantity(result);
        products.get(position).setQuantityOfSales(quantityOrder);
    }

    public double calculateTotalPriceByProduct(String name, int quantity) {
        int position = searchProductPosition(name);
        double totalPrice = products.get(position).getPrice() * quantity;
        return totalPrice;
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

    public int searchProductQuantity(String name) {
        int position = searchProductPosition(name);
        if(position ==-1){
            return position;
        }else {
            return products.get(position).getAvailableQuantity();
        }
    }
    public String searchByProductPrice(double goal){
        ArrayList<Product> productsClone = new ArrayList<>(products);
        return searchByProductPrice(goal,productsClone,"");
    }
    public String searchByProductPrice(double goal,ArrayList<Product> products2,String msj){
        products2.sort(new ComparatorPrice());
        int left = 0;
        int right = products2.size() - 1;

        while(left <= right){

            int mid = (right + left)/2;

            if(goal < products2.get(mid).getPrice()){
                right = mid - 1;
            }
            else if(goal > products2.get(mid).getPrice()){
                left = mid + 1;
            } else {
                msj += products2.get(mid).toString()+"\n";
                
                products2.remove(mid);
                return searchByProductPrice(goal,products2,msj);
            }
        }
        if(msj.equals("")){
            msj = "product not found"; 
        }
       return msj;
    }
    public String searchProductByName(String goal){
        ArrayList<Product> productsClone = new ArrayList<>(products);
        return searchProductByName(goal,productsClone,"");
    }
    public String searchProductByName(String goal,ArrayList<Product> products2,String msj){
        products2.sort(new ComparatorName());
        int left = 0;
        int right = products2.size() - 1;

        while(left <= right){

            int mid = (right + left)/2;

            if(goal.compareTo(products2.get(mid).getName())<0){
                right = mid - 1;
            }
            else if(goal.compareTo(products2.get(mid).getName())>0){
                left = mid + 1;
            } else {
                msj += products2.get(mid).toString()+"\n";
                
                products2.remove(mid);
                return searchProductByName(goal,products2,msj);
            }
        }
        if(msj.equals("")){
            msj = "product not found"; 
        }
       return msj;
    }
    public String searchProductByCategory(Category goal){
        ArrayList<Product> productsClone = new ArrayList<>(products);
        return searchProductByCategory(goal,productsClone,"");
    }
    public String searchProductByCategory(Category goal,ArrayList<Product> products2,String msj){
        products2.sort(new ComparatorCategory());
        int left = 0;
        int right = products2.size() - 1;

        while(left <= right){

            int mid = (right + left)/2;

            if(goal.toString().compareTo(products2.get(mid).getCategory().toString())<0){
                right = mid - 1;
            }
            else if(goal.toString().compareTo(products2.get(mid).getCategory().toString())>0){
                left = mid + 1;
            } else {
                msj += products2.get(mid).toString()+"\n";
                products2.remove(mid);
                return searchProductByCategory(goal,products2,msj);
            }
        }
        if(msj.equals("")){
            msj = "product not found"; 
        }
       return msj;
    }
    public String searchProductByNumberOfSales(int goal){
        ArrayList<Product> productsClone = new ArrayList<>(products);
        return searchProductByNumberOfSales((Integer)goal,productsClone,"");
    }
    public String searchProductByNumberOfSales(Integer goal,ArrayList<Product> products2,String msj){
        products2.sort(new ComparatorNumberOfSales());
        int left = 0;
        int right = products2.size() - 1;

        while(left <= right){

            int mid = (right + left)/2;

            if(goal.compareTo(products2.get(mid).getQuantityOfSales())<0){
                right = mid - 1;
            }
            else if(goal.compareTo(products2.get(mid).getQuantityOfSales())>0){
                left = mid + 1;
            } else {
                msj += products2.get(mid).toString()+"\n";
                products2.remove(mid);
                return searchProductByNumberOfSales(goal,products2,msj);
            }
        }
        if(msj.equals("")){
            msj = "product not found"; 
        }
       return msj;
    }
    public ArrayList<Product> searchPriceRange(double minGoal,double maxGoal){
        ArrayList<Product> productsClone = new ArrayList<>(products);
        ArrayList<Product> result = new ArrayList<>();
        return searchPriceRange(minGoal,maxGoal,productsClone,result);
    }
    public ArrayList<Product> searchPriceRange(double minGoal,double maxGoal,ArrayList<Product> products2,ArrayList<Product> result){
        products2.sort(new ComparatorPrice());
        int left = 0;
        int right = products2.size() - 1;
        
        while(left <= right){

            int mid = (right + left)/2;

            if(minGoal>products2.get(mid).getPrice()){
                right = mid - 1;
            }
            else if(maxGoal<products2.get(mid).getPrice()){
                left = mid + 1;
            } else if(minGoal<=products2.get(mid).getPrice() && products2.get(mid).getPrice()<=maxGoal){
                result.add(products2.get(mid));
                products2.remove(mid);
                return searchPriceRange(minGoal, maxGoal, products2,result);
            }
        }
        
       return result;
    }
    public  ArrayList<Product> searchNumberOfSalesRange(int minGoal,int maxGoal){
        ArrayList<Product> productsClone = new ArrayList<>(products);
        ArrayList<Product> result = new ArrayList<>();
        return searchNumberOfSalesRange(minGoal,maxGoal,productsClone,result);
    }
    public  ArrayList<Product> searchNumberOfSalesRange(int minGoal,int maxGoal,ArrayList<Product> products2,ArrayList<Product> result){
        products2.sort(new ComparatorPrice());
        int left = 0;
        int right = products2.size() - 1;
        
        while(left <= right){

            int mid = (right + left)/2;

            if(minGoal>products2.get(mid).getQuantityOfSales()){
                right = mid - 1;
            }
            else if(maxGoal<products2.get(mid).getQuantityOfSales()){
                left = mid + 1;
            } else if(minGoal<=products2.get(mid).getQuantityOfSales() && products2.get(mid).getQuantityOfSales()<=maxGoal){
                result.add(products2.get(mid));
                products2.remove(mid);
                return searchPriceRange(minGoal, maxGoal, products2,result);
            }
        }
        
       return result;
    }
    public  ArrayList<Product> searchAlphabeticRange(String minGoal,String maxGoal){
        ArrayList<Product> productsClone = new ArrayList<>(products);
        ArrayList<Product> result = new ArrayList<>();
        return searchAlphabeticRange(minGoal,maxGoal,productsClone,result);
    }
    public  ArrayList<Product> searchAlphabeticRange(String minGoal,String maxGoal,ArrayList<Product> products2,ArrayList<Product> result){
        products2.sort(new ComparatorPrice());
        int left = 0;
        int right = products2.size() - 1;
        
        while(left <= right){

            int mid = (right + left)/2;

            if(minGoal.compareTo(products2.get(mid).getName())>0){
                right = mid - 1;
            }
            else if(maxGoal.compareTo(products2.get(mid).getName())<0){
                left = mid + 1;
            } else if(minGoal.compareTo(products2.get(mid).getName())<=0 && maxGoal.compareTo(products2.get(mid).getName())>=0){
                result.add(products2.get(mid));
                products2.remove(mid);
                return searchAlphabeticRange(minGoal, maxGoal, products2,result);
            }
        }
        
       return result;
    }
    public String searchOrderByBuyerName(String buyerName){
        ArrayList<Order> ordersClone = new ArrayList<>(orders);
        return searchOrderByBuyerName(buyerName,ordersClone,"");
    }
    public String searchOrderByBuyerName(String goal,ArrayList<Order> order2,String msj){
        order2.sort(new ComparatorBuyerName());
        int left = 0;
        int right = order2.size() - 1;

        while(left <= right){

            int mid = (right + left)/2;

            if(goal.compareTo(order2.get(mid).getBuyerName())<0){
                right = mid - 1;
            }
            else if(goal.compareTo(order2.get(mid).getBuyerName())>0){
                left = mid + 1;
            } else {
                msj += order2.get(mid).toString()+"\n";
                order2.remove(mid);
                return searchOrderByBuyerName(goal,order2,msj);
            }
        }
        if(msj.equals("")){
            msj = "product not found"; 
        }
       return msj;
    }
    public String searchOrderByTotalPrice(double totalPrice){
        ArrayList<Order> ordersClone = new ArrayList<>(orders);
        return searchOrderByTotalPrice(totalPrice,ordersClone,"");
    }
    public String searchOrderByTotalPrice(double totalPrice,ArrayList<Order>order2,String msj){
        order2.sort(new ComparatorTotalPrice());
        int left = 0;
        int right = order2.size() - 1;

        while(left <= right){

            int mid = (right + left)/2;

            if(totalPrice<order2.get(mid).getTotalPrice()){
                right = mid - 1;
            }
            else if(totalPrice>order2.get(mid).getTotalPrice()){
                left = mid + 1;
            } else {
                msj += order2.get(mid).toString()+"\n";
                order2.remove(mid);
                return searchOrderByTotalPrice(totalPrice,order2,msj);
            }
        }
        if(msj.equals("")){
            msj = "product not found"; 
        }
       return msj;
    }
    public String searchOrderByDate(String date) throws ParseException{
        SimpleDateFormat formatter6=new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        ArrayList<Order> ordersClone = new ArrayList<>(orders);
        Date goal = null;
        try{
            goal = formatter6.parse(date);
        }catch(ParseException e){
            return "wrong format";
        }
        
        return searchOrderByDate(goal, ordersClone, ""); 
    }
    public String searchOrderByDate(Date date,ArrayList<Order> order2,String msj){
        order2.sort(new ComparatorDate());
        int left = 0;
        int right = order2.size() - 1;

        while(left <= right){

            int mid = (right + left)/2;

            if(date.compareTo(order2.get(mid).getDateOfPurchase())<0){
                right = mid - 1;
            }
            else if(date.compareTo(order2.get(mid).getDateOfPurchase())>0){
                left = mid + 1;
            } else {
                msj += order2.get(mid).toString()+"\n";
                order2.remove(mid);
                return searchOrderByDate(date,order2,msj);
            }
        }
        if(msj.equals("")){
            msj = "product not found"; 
        }
       return msj;
    }
    public ArrayList<Order> searchBuyerNameRange(String minGoal,String maxGoal){
        ArrayList<Order> ordersClone = new ArrayList<>(orders);
        ArrayList<Order> result = new ArrayList<>();
        return  searchBuyerNameRange(minGoal,maxGoal,ordersClone,result);
    }
    public ArrayList<Order> searchBuyerNameRange(String minGoal,String maxGoal,ArrayList<Order> order2,ArrayList<Order> result){
        order2.sort(new ComparatorBuyerName());
        int left = 0;
        int right = order2.size() - 1;

        while(left <= right){

            int mid = (right + left)/2;

            if(minGoal.compareTo(order2.get(mid).getBuyerName())>0){
                right = mid - 1;
            }
            else if(maxGoal.compareTo(order2.get(mid).getBuyerName())<0){
                left = mid + 1;
            } else if(minGoal.compareTo(order2.get(mid).getBuyerName())<=0 && maxGoal.compareTo(order2.get(mid).getBuyerName())>=0){
                result.add(order2.get(mid));
                order2.remove(mid);
                return searchBuyerNameRange(minGoal,maxGoal,order2,result);
            }
        }
       return result;
    }
    public ArrayList<Order> searchOrderByTotalPriceRange(double minGoal,double maxGoal){
        ArrayList<Order> ordersClone = new ArrayList<>(orders);
        ArrayList<Order> result = new ArrayList<>();
        return searchOrderByTotalPriceRange(minGoal,maxGoal,ordersClone,result);
    }
    public ArrayList<Order> searchOrderByTotalPriceRange(double minGoal,double maxGoal,ArrayList<Order>order2,ArrayList<Order>result){
        order2.sort(new ComparatorTotalPrice());
        int left = 0;
        int right = order2.size() - 1;

        while(left <= right){

            int mid = (right + left)/2;

            if(minGoal>order2.get(mid).getTotalPrice()){
                right = mid - 1;
            }
            else if(maxGoal<order2.get(mid).getTotalPrice()){
                left = mid + 1;
            } else if(minGoal<=order2.get(mid).getTotalPrice() && order2.get(mid).getTotalPrice()<=maxGoal){
                result.add(order2.get(mid));
                order2.remove(mid);
                return searchOrderByTotalPriceRange(minGoal,maxGoal,order2,result);
            }
        }
       return result;
    }
    public String ascendantOrderForOrders(ArrayList<Order> result,int option){
        String msj = ""; 
        switch(option){
            case 1:
                result.sort(new ComparatorBuyerName());
                for(int i = result.size()-1;i>=0;i--){
                    msj += result.get(i).toString()+"\n";
                }
                return msj;
            case 2:
                result.sort(new ComparatorTotalPrice());
                for(int i = 0;i<result.size();i++){
                    msj += result.get(i).toString()+"\n";
                }
                return msj;
            case 3:
                result.sort(new ComparatorDate());
                for(int i = 0;i<result.size();i++){
                    msj += result.get(i).toString()+"\n";
                }
                return msj;
            default:
                    return "Invalid option";
        }
    }
    public String descendantOrderForOrders(ArrayList<Order> result,int option){
        String msj = ""; 
        switch(option){
            case 1:
                result.sort(new ComparatorBuyerName());
                for(int i = 0;i<result.size();i++){
                    msj += result.get(i).toString()+"\n";
                }
                return msj;
            case 2:
                result.sort(new ComparatorTotalPrice());
                for(int i = result.size()-1;i>=0;i--){
                    msj += result.get(i).toString()+"\n";
                }
                return msj;
            case 3:
                result.sort(new ComparatorDate());
                for(int i = result.size()-1;i>=0;i--){
                    msj += result.get(i).toString()+"\n";
                }
                return msj;
            default:
                    return "Invalid option";
        }
    }
    public String ascendantOrder(ArrayList<Product> result,int option){
        String msj = ""; 
        switch(option){
            case 1:
                result.sort(new ComparatorName());
                for(int i = result.size()-1;i>=0;i--){
                    msj += result.get(i).toString();
                }
                return msj;
            case 2:
                result.sort(new ComparatorCategory());
                for(int i = 0;i<result.size();i++){
                    msj += result.get(i).toString();
                }
                return msj;
            case 3:
                result.sort(new ComparatorPrice());
                for(int i = 0;i<result.size();i++){
                    msj += result.get(i).toString();
                }
                return msj;
            case 4:
                result.sort(new ComparatorNumberOfSales());
                    for(int i = 0;i<result.size();i++){
                        msj += result.get(i).toString();
                    }
                    return msj;
            
            default:
                    return "Invalid option";
        }
    }

    public String descendantOrder(ArrayList<Product> result,int option){
        String msj = ""; 
        switch(option){
            case 1:
                result.sort(new ComparatorName());
                for(int i = 0;i<result.size();i++){
                    msj += result.get(i).toString();
                }
                return msj;
            case 2:
                result.sort(new ComparatorCategory());
                for(int i = result.size()-1;i>=0;i--){
                    msj += result.get(i).toString();
                }
                return msj;
            case 3:
                result.sort(new ComparatorPrice());
                for(int i = result.size()-1;i>=0;i--){
                    msj += result.get(i).toString();
                }
                return msj;
            case 4:
                result.sort(new ComparatorNumberOfSales());
                    for(int i = result.size()-1;i>=0;i--){
                        msj += result.get(i).toString();
                    }
                    return msj;
            
            default:
                    return "Invalid option";
            }
        }
    public Category getCategory(int category){
        switch (category) {
            case 1:
                return Category.BOOKS;
            case 2:
                return Category.ELETRONICS;
            case 3:
                return Category.CLOTHESANDACCESORIES;
            case 4:
                return Category.FOODANDDRINKS;
            case 5:
                return Category.STATIONERS;
            case 6:
                return Category.SPORTS;
            case 7:
                return Category.TOYSANDGAMES;
            case 8:
                return Category.BEAUTYANDPERSONALCARE;
        }
        return Category.BOOKS;
    }
}