package dao;

import model.Orders;

import java.util.List;

public class OrderDAO extends GenericDAO<Orders>{

    /**
     * This is the constructor, in order to know which type of class will be used for the operations made in
     * the generic class
     * @param type - Orders class type
     */
    public OrderDAO(Class<Orders> type) {
        super(type);
    }

    /**
     * This methods displays all the orders from the database table
     * @return - a list of all the orders from the database
     */
    public List<Orders> findAll(){
        return super.findAll();
    }

    /**
     * This method adds an order to the database
     * @param orders - the order we want to introduce intro the table
     * @return - an integer which is -1 if there is an exception
     */
    public int insert(Orders orders){
        return super.insert(orders);
    }
}
