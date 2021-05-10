package controller;

import javafx.scene.control.TableView;
import model.Product;

import java.util.List;

public class ProductReflectionTable extends GenericTable<Product>{

    /**
     * This is the constructor, in order to know which type of class will be used for the operations made in
     * the generic class
     * @param type - Product class type
     */
    public ProductReflectionTable(Class<Product> type) {
        super(type);
    }

    /**
     * This method generates the table with all the products from the database
     * @param tableView - the table on the user interface
     * @param productList - all the product from the database
     */
    public void generateTable(TableView tableView, List<Product> productList) {
        super.generateTable(tableView, productList);
    }
}
