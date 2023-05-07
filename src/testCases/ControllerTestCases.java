package testCases;
import model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import  java.util.ArrayList;
public class ControllerTestCases {
    @Test
    public void testAddProductWithValidInputs(){
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
    public  void testIncreaseQuantityProductExist(){
        Controller controller = new Controller();
        controller.addProduct("Product 1", "This is product 1", 10.0, 5, 1, 0);
        String result = controller.increaseProductQuantity("Product 1", 4);
        Assertions.assertEquals("the product quantity have been change", result);
    }
    @Test
    public  void  testIncreaseQuantityProductNotExist(){
        Controller controller = new Controller();
        String result = controller.increaseProductQuantity("Product 1", 4);
        Assertions.assertEquals("the product hasn't been found", result);
    }


}
