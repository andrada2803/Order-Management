package bll;

import dao.OrderDAO;
import model.Orders;

import java.util.List;


public class OrderBLL {
    OrderDAO orderDAO;

    /**
     * The constructor of the class
     */
    public OrderBLL() {
        orderDAO = new OrderDAO(Orders.class);
    }

    /**
     * Displays all the orders
     * @return - all the orders from the database
     */
    public List<Orders> findAll()
    {
        return orderDAO.findAll();
    }

    /**
     *
     * @param orders - the order which we want to add to the database
     * @return - an integer which is -1 if it happens to be an exception
     */
    public int insertOrder(Orders orders) {
        return orderDAO.insert(orders);
    }
}
