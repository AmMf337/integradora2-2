package model;

import java.util.Comparator;

public class ComparatorBuyerName implements Comparator<Order>{

    @Override
    public int compare(Order o1, Order o2) {
      return o1.getBuyerName().compareTo(o2.getBuyerName());
    }
    
}
