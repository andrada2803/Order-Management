package controller;

import javafx.scene.control.TableView;
import model.Orders;

import java.util.List;

public class OrderReflectionTable extends GenericTable<Orders>{
    /**
     * This is the constructor, in order to know which type of class will be used for the operations made in
     * the generic class
     * @param type - Order class type
     */
    public OrderReflectionTable(Class<Orders> type) {
        super(type);
    }

    /**
     * This method generates the table with all the orders from the database
     * @param tableView - the table on the user interface
     * @param ordersList - all the orders from the database
     */
    public void generateTable(TableView tableView, List<Orders> ordersList) {
        super.generateTable(tableView, ordersList);
    }
}
