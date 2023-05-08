package model;
import java.util.Comparator;
public class ComparatorNumberOfSales implements Comparator<Product>{


    @Override
    public int compare(Product o1, Product o2) {
        return ((Integer)(o1.getQuantityOfSales())).compareTo((Integer)(o2.getQuantityOfSales()));
    }
    
}
