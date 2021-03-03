package business.interfaces;

import entities.Country;
import entities.Excursion;
import entities.Hotel;
import entities.Order;

public interface OrderInterface {
    Order getOrderByNumber(long number);
    boolean removeOrderByNumber(long number);
    void addNewOrder(Order order);
}
