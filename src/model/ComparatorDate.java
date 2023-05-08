package model;
import java.util.Comparator;
public class ComparatorDate implements Comparator<Order>{

    @Override
    public int compare(Order o1, Order o2) {
       return o1.getDateOfPurchase().compareTo(o2.getDateOfPurchase());
    }
    
}
