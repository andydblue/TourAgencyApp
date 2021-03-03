package business.implementations;

import business.interfaces.OrderInterface;
import dao.implementationsMySQL.OrderDaoImplMySQL;
import entities.Order;

public class OrderImpl implements OrderInterface {

    OrderDaoImplMySQL orderDaoImplMySQL = new OrderDaoImplMySQL();

    @Override
    public Order getOrderByNumber(long number) {
        return orderDaoImplMySQL.getOrderByOrderNumber(number);
    }

    @Override
    public boolean removeOrderByNumber(long number) { return orderDaoImplMySQL.removeOrderByOrderNumber(number); }

    @Override
    public void addNewOrder(Order order) {
        orderDaoImplMySQL.create(order);
    }
}
