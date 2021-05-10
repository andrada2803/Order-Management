package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MainMenuPage extends Controller{
    /**
     * This method changes the scene to the Client's page
     * @param event - mouse event
     */
    @FXML
    private void clientButton(ActionEvent event) {
        try {
            changeScene(event, "/Client.fxml");
        } catch (Exception e) {
            System.out.println("Can't load the Client page. Sorry!");
        }
    }

    /**
     * This method changes the scene to the Product's page
     * @param event - mouse event
     */
    @FXML
    private void productButton(ActionEvent event) {
        try {
            changeScene(event, "/Product.fxml");
        } catch (Exception e) {
            System.out.println("Can't load the Product page. Sorry!");
        }
    }

    /**
     * This method changes the scene to the Order's page
     * @param event - mouse event
     */
    @FXML
    private void orderButton(ActionEvent event) {
        try {
            changeScene(event, "/Order.fxml");
        } catch (Exception e) {
            System.out.println("Can't load the Order page. Sorry!");
        }
    }
}
