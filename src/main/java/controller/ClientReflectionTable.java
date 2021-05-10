package controller;

import javafx.scene.control.TableView;
import model.Client;

import java.util.List;

public class ClientReflectionTable extends GenericTable<Client> {

    /**
     * This is the constructor, in order to know which type of class will be used for the operations made in
     * the generic class
     * @param type - Client class type
     */
    public ClientReflectionTable(Class<Client> type) {
        super(type);
    }

    /**
     * This method generates the table with all the clients from the database
     * @param tableView - the table on the user interface
     * @param clientList - all the clients that are in the database
     */
    public void generateTable(TableView tableView, List<Client> clientList) {
        super.generateTable(tableView, clientList);
    }
}
