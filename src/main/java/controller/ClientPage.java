package controller;

import connection.ConnectionFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Client;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ClientPage extends Controller {
    @FXML
    TableView<Client> clientTable;
    @FXML
    TextField firstName;
    @FXML
    TextField lastName;
    @FXML
    TextField email;
    @FXML
    TextField address;
    @FXML
    TextField age;
    private Client selectedClient = null;
    private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());

    /**
     * The function to display all the clients into the table (for the button "View")
     */
    @FXML
    public void displayClients() {
        List<Client> clientList = clientBLL.findAll();
        clientReflectionTable.generateTable(clientTable, clientList);
    }

    /**
     * Insert the new client with all the data introduced by the user (For the "Add" button)
     */
    @FXML
    public void insert() {
        if (checkInput()) {
            int ageInteger = Integer.parseInt(age.getText());
            Client client = new Client(0, firstName.getText(), lastName.getText(), email.getText(),
                    address.getText(), ageInteger);
            try {
                clientBLL.insertClient(client);
            } catch (Exception e) {
                showAlert(Alert.AlertType.ERROR, e.getMessage(), e.getMessage());
                LOGGER.log(Level.INFO, e.getMessage());
            }
        }
    }

    /**
     * This method edit for an existing client which is selected from the table (For the button "Edit")
     */
    @FXML
    public void edit() {
        if (selectedClient != null) {
            if (checkInput()) {
                int ageInteger = Integer.parseInt(age.getText());
                Client client = new Client(selectedClient.getId(), firstName.getText(), lastName.getText(), email.getText(),
                        address.getText(), ageInteger);
                try {
                    clientBLL.editClient(client);
                } catch (Exception e) {
                    showAlert(Alert.AlertType.ERROR, e.getMessage(), e.getMessage());
                    LOGGER.log(Level.INFO, e.getMessage());
                }
            }
        } else {
            showAlert(Alert.AlertType.WARNING, "No client was selected", "Please select a client from the table in order to edit it");
        }
    }

    /**
     * This method deletes the client selected from the table (For the "Delet" button)
     */
    @FXML
    public void delete() {
        if (selectedClient != null) {
            try {
                clientBLL.deleteClient(selectedClient.getId());
            } catch (Exception e) {
                showAlert(Alert.AlertType.ERROR, e.getMessage(), e.getMessage());
                LOGGER.log(Level.WARNING, e.getMessage());
            }
        } else {
            showAlert(Alert.AlertType.WARNING, "No client was selected", "Please select a client from the table in order to delete it");

        }
    }

    /**
     * Setting the text fields for the selected client
     */
    @FXML
    public void setFields() {
        selectedClient = clientTable.getSelectionModel().getSelectedItem();
        firstName.setText(selectedClient.getFirstName());
        lastName.setText(selectedClient.getLastName());
        email.setText(selectedClient.getEmail());
        address.setText(selectedClient.getAddress());
        age.setText(String.valueOf(selectedClient.getAge()));
    }

    /**
     * Validate the input data introduced by the user
     * @return - true, in case the data is introduced correctly, false, otherwise
     */
    public boolean checkInput() {
        try {
            if (!firstName.getText().isEmpty() && !lastName.getText().isEmpty() && !email.getText().isEmpty()
                    && !address.getText().isEmpty()) {
                int ageInteger = Integer.parseInt(age.getText());
                return true;
            } else {
                showAlert(Alert.AlertType.INFORMATION, "Some input is missing", "Please fill in all the fields");
                return false;
            }
        } catch (NumberFormatException numberFormatException) {
            showAlert(Alert.AlertType.INFORMATION, "Age is not ok!", "Age is not an integer");
            return false;
        }
    }

    /**
     * Going back to the main menu (For the "Back" button)
     * @param event - mouse event
     */
    @FXML
    private void clientBackButton(ActionEvent event) {
        try {
            changeScene(event, "/OrderManagementView.fxml");
        } catch (Exception e) {
            System.out.println("Can't load the OrderManagementView page. Sorry!");
        }
    }
}
