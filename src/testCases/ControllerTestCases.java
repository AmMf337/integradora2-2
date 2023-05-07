package testCases;
import model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import  java.util.ArrayList;
public class ControllerTestCases {
    @Test
    public void testAddProductWithValidInputs() {
        Controller controller = new Controller();
        String result = controller.addProduct("Product 1", "This is product 1", 10.0, 5, 1, 0);
        Assertions.assertEquals("product added", result);
    }

    @Test
    public void testAddProductDuplicateName() {
        Controller controller = new Controller();
        controller.addProduct("Product 1", "This is product 1", 10.0, 5, 1, 0);
        String result = controller.addProduct("Product 1", "This is another product 1", 20.0, 10, 2, 0);
        Assertions.assertEquals("Product name already exists", result);
    }

    @Test
    public void testIncreaseQuantityProductExist() {
        Controller controller = new Controller();
        controller.addProduct("Product 1", "This is product 1", 10.0, 5, 1, 0);
        String result = controller.increaseProductQuantity("Product 1", 4);
        Assertions.assertEquals("the product quantity have been change", result);
    }

    @Test
    public void testIncreaseQuantityProductNotExist() {
        Controller controller = new Controller();
        String result = controller.increaseProductQuantity("Product 1", 4);
        Assertions.assertEquals("the product hasn't been found", result);
    }

    @Test
    public void testAddOrder() {
        Controller controller = new Controller();
        controller.addProduct("Product 1", "This is product 1", 300.0, 5, 1, 0);
        ArrayList<String> nameProducts = new ArrayList<>();
        ArrayList<Integer> quantityProducts = new ArrayList<>();
        nameProducts.add("Product 1");
        quantityProducts.add(1);
        String result = controller.addOrder("name 1", nameProducts, quantityProducts, 300);
        Assertions.assertEquals("order complete", result);
    }

    @Test
    public void testCalculateTotalPrice() {
        Controller controller = new Controller();
        controller.addProduct("Product 1", "This is product 1", 300.0, 5, 1, 0);
        ArrayList<String> nameProducts = new ArrayList<>();
        ArrayList<Integer> quantityProducts = new ArrayList<>();
        nameProducts.add("Product 1");
        quantityProducts.add(1);
        double result = controller.calculateTotalPriceByProduct(nameProducts.get(0), quantityProducts.get(0));
        Assertions.assertEquals(300, result);
    }

    @Test
    public void testSearchProductPosition() {
        Controller controller = new Controller();
        controller.addProduct("Product 1", "This is product 1", 300.0, 5, 1, 0);
        String name = "Product 1";
        int result = controller.searchProductPosition(name);
        Assertions.assertEquals(0, result);
    }

    @Test
    public void testSearchProductPositionNotExist() {
        Controller controller = new Controller();
        String name = "Product 1";
        int result = controller.searchProductPosition(name);
        Assertions.assertEquals(-1, result);
    }


    @Test
    public void testSearchProductQuantity() {
        Controller controller = new Controller();
        controller.addProduct("Product 1", "This is product 1", 300.0, 5, 1, 0);
        String name = "Product 1";
        int result = controller.searchProductQuantity(name);
        Assertions.assertEquals(5, result);
    }
    @Test
    public void testSearchProductQuantityNotExist() {
        Controller controller = new Controller();
        String name = "Product 1";
        int result = controller.searchProductQuantity(name);
        Assertions.assertEquals(-1, result);
    }


    @Test
    public void testSearchProductByPrice() {
        Controller controller = new Controller();
        controller.addProduct("Product 1", "This is product 1", 300.0, 5, 1, 0);
        double price = 300;
        String result = controller.searchByProductPrice(price);
        Assertions.assertEquals("Name: "+"Product 1"+"\n"+
                "Category: "+"BOOKS"+"\n"+
                "Available Quantities: "+5+"\n"+
                "Price: "+300.0+"\n"+
                "Description: "+"This is product 1"+"\n"+
                "Qunatities of sales: "+0+"\n"+"\n", result);
    }

    @Test
    public void testSearchProductByPriceNotExist() {
        Controller controller = new Controller();
        double price = 300;
        String result = controller.searchByProductPrice(price);
        Assertions.assertEquals("product not found", result);
    }
    @Test
    public void testSearchProductByName() {
        Controller controller = new Controller();
        controller.addProduct("Product 1", "This is product 1", 300.0, 5, 1, 0);
        String name = "Product 1";
        String result = controller.searchProductByName(name);
        Assertions.assertEquals("Name: "+"Product 1"+"\n"+
                "Category: "+"BOOKS"+"\n"+
                "Available Quantities: "+5+"\n"+
                "Price: "+300.0+"\n"+
                "Description: "+"This is product 1"+"\n"+
                "Qunatities of sales: "+0+"\n"+"\n", result);
    }

    @Test
    public void testSearchProductByNameNotExist() {
        Controller controller = new Controller();
        String name = "Product 1";
        String result = controller.searchProductByName(name);
        Assertions.assertEquals("product not found", result);
    }

    @Test
    public void testSearchProductByCategory() {
        Controller controller = new Controller();
        controller.addProduct("Product 1", "This is product 1", 300.0, 5, 1, 0);
        Category category = Category.BOOKS;
        String result = controller.searchProductByCategory(category);
        Assertions.assertEquals("Name: "+"Product 1"+"\n"+
                "Category: "+"BOOKS"+"\n"+
                "Available Quantities: "+5+"\n"+
                "Price: "+300.0+"\n"+
                "Description: "+"This is product 1"+"\n"+
                "Qunatities of sales: "+0+"\n"+"\n", result);
    }

    @Test
    public void testSearchProductByCategoryNotExist() {
        Controller controller = new Controller();
        Category category = Category.BOOKS;
        String result = controller.searchProductByCategory(category);
        Assertions.assertEquals("product not found", result);
    }

    @Test
    public void testSearchProductByNumberOfSales() {
        Controller controller = new Controller();
        controller.addProduct("Product 1", "This is product 1", 300.0, 5, 1, 0);
        int quantity = 0;
        String result = controller.searchProductByNumberOfSales(quantity);
        Assertions.assertEquals("Name: "+"Product 1"+"\n"+
                "Category: "+"BOOKS"+"\n"+
                "Available Quantities: "+5+"\n"+
                "Price: "+300.0+"\n"+
                "Description: "+"This is product 1"+"\n"+
                "Qunatities of sales: "+0+"\n"+"\n", result);
    }

    @Test
    public void testSearchProductByNumberOfSalesNotExist() {
        Controller controller = new Controller();
        int quantity = 0;
        String result = controller.searchProductByNumberOfSales(quantity);
        Assertions.assertEquals("product not found", result);
    }










}