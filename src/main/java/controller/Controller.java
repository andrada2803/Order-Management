package controller;

import bll.ClientBLL;
import bll.OrderBLL;
import bll.ProductBLL;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import model.Client;
import model.Orders;
import model.Product;

import java.io.IOException;

public class Controller {

    public OrderReflectionTable orderReflectionTable = null;
    public ClientReflectionTable clientReflectionTable = null;
    public ProductReflectionTable productReflectionTable = null;
    public OrderBLL orderBLL = null;
    public ClientBLL clientBLL = null;
    public ProductBLL productBLL = null;

    /**
     * The initialization method for the attributes
     */
    public void initialize() {
        if (clientReflectionTable == null)
            clientReflectionTable = new ClientReflectionTable(Client.class);
        if (productReflectionTable == null)
            productReflectionTable = new ProductReflectionTable(Product.class);
        if (orderReflectionTable == null)
            orderReflectionTable = new OrderReflectionTable(Orders.class);
        if (clientBLL == null)
            clientBLL = new ClientBLL();
        if (productBLL == null)
            productBLL = new ProductBLL();
        if (orderBLL == null)
            orderBLL = new OrderBLL();

    }

    /**
     * This method changes the window when pressin the buttons "Client", "Product" or "Order" on the main menu page and
     * goes to one of the windows for the client, product or orders. Also I used for the back button which goes back to the
     * main menu page
     * @param event - the event, in our case a mouse event
     * @param fxmlFilePath - the path to the window we want to change the schene
     * @throws IOException - the exception
     */
    public void changeScene(ActionEvent event, String fxmlFilePath) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource(fxmlFilePath));
        Scene scene = new Scene(parent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    /**
     * This method computes an allert, which I used in case of something is not how it shoul be, for example wrong input
     * introduced by the user
     * @param alertType - the type of the alert
     * @param header - the header of the alert
     * @param content - the content of the alert
     */
    public void showAlert(Alert.AlertType alertType, String header, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(header);
        alert.setHeaderText(header);
        alert.setContentText(content);

        alert.showAndWait();
    }
}
