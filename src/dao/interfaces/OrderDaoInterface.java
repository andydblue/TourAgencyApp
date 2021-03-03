package dao.interfaces;

import entities.Country;
import entities.Excursion;
import entities.Hotel;
import entities.Order;

import java.util.List;

public interface OrderDaoInterface extends DaoCrud<Order, Integer> {
    Order getOrderByOrderNumber(long number);
    boolean removeOrderByOrderNumber(long number);
    Country getCountryByName(String name);
    Hotel getHotelByName(String name);
    Excursion getExcursionByName(String name);
}
