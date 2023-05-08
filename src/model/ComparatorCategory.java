package model;
import java.util.Comparator;
public class ComparatorCategory implements Comparator<Product>{

    @Override
    public int compare(Product o1, Product o2) {
        return o1.getCategory().toString().compareTo(o2.getCategory().toString());
    }
    
}
