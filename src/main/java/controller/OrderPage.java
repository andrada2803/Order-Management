package controller;

import connection.ConnectionFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Client;
import model.Orders;
import model.Product;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderPage extends Controller {
    @FXML
    TableView orderTable;
    @FXML
    TextField clientId;
    @FXML
    TextField productId;
    @FXML
    TextField quantity;
    private Client selectedClient;
    private Product selectedProduct;
    private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());


    /**
     * The function to display all the orders into the table (for the button "View")
     */
    @FXML
    public void displayOrders() {
        List<Orders> ordersList = orderBLL.findAll();
        orderReflectionTable.generateTable(orderTable, ordersList);
    }

    /**
     * The function to display all the clients into the table (for the button "Clients")
     */
    @FXML
    public void displayClients() {
        List<Client> clientList = clientBLL.findAll();
        clientReflectionTable.generateTable(orderTable, clientList);
    }

    /**
     * The function to display all the product into the table (for the button "Products")
     */
    @FXML
    public void displayProducts() {
        List<Product> productList = productBLL.findAll();
        productReflectionTable.generateTable(orderTable, productList);
    }

    /**
     * Verify if the user selected a client and a product, then the quantity then it will add to the database table of the
     * Orders table, and from the quantity of the product we subtract the quantity selected by the user (For the "Add" button)
     */
    @FXML
    public void insert() {
        if (selectedClient == null) {
            showAlert(Alert.AlertType.INFORMATION, "No client was selected.", "Please select a client in order to create an order");
        } else if (selectedProduct == null) {
            showAlert(Alert.AlertType.INFORMATION, "No product was selected.", "Please select a product in order to create an order");
        } else {
            try {
                int quantityInteger = Integer.parseInt(quantity.getText());
                if (quantityInteger > selectedProduct.getQuantity()) {
                    showAlert(Alert.AlertType.WARNING, "UNDER STOCK", "The quantity you requested exceeds the quantity in the stock");
                } else {
                    Orders order = new Orders(0, selectedClient.getId(), selectedProduct.getId(), quantityInteger);
                    selectedProduct.setQuantity(selectedProduct.getQuantity() - quantityInteger);
                    try {
                        productBLL.editProduct(selectedProduct);
                        orderBLL.insertOrder(order);
                    } catch (Exception exception) {
                        showAlert(Alert.AlertType.ERROR, exception.getMessage(), exception.getMessage());
                        LOGGER.log(Level.WARNING, exception.getMessage());
                    }
                }
            } catch (NumberFormatException numberFormatException) {
                showAlert(Alert.AlertType.WARNING, "Quantity is not an integer", "The quantity should be an integer");
            }
        }
    }

    /**
     * Setting the text fields for the selected client
     */
    @FXML
    public void setFields() {
        Object object = orderTable.getSelectionModel().getSelectedItem();
        if (object instanceof Client) {
            selectedClient = (Client) object;
            clientId.setText(((Client) object).getFirstName() + " " + ((Client) object).getLastName());
        } else if (object instanceof Product) {
            selectedProduct = (Product) object;
            productId.setText(((Product) object).getName());
        }
    }

    /**
     * Displaying txt files with the information of each class for every order
     * @throws FileNotFoundException - the exception for the .txt file
     */
    @FXML
    public void displayBills() throws FileNotFoundException {
        int id = 0;
        for (Orders orders : orderBLL.findAll()) {
            id++;
            PrintWriter outFile = new PrintWriter("Order_" + id + ".txt");
            outFile.println(clientBLL.findClientById(orders.getClientId()).toString());
            outFile.println(productBLL.findProductById(orders.getProductId()).toString());
            outFile.println(orders.toString());
            outFile.close();
        }
    }

    /**
     * Going back to the main menu (For the "Back" button)
     * @param event - mouse event
     */
    @FXML
    private void orderBackButton(ActionEvent event) {
        try {
            changeScene(event, "/OrderManagementView.fxml");
        } catch (Exception e) {
            System.out.println("Can't load the OrderManagementView page. Sorry!");
        }
    }
}
