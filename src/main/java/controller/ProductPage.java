package controller;

import connection.ConnectionFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Product;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductPage extends Controller {
    @FXML
    TableView<Product> productTable;
    @FXML
    TextField name;
    @FXML
    TextField quantity;
    private Product selectedProduct;
    private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());

    /**
     * The function to display all the product into the table (for the button "View")
     */
    @FXML
    public void displayProduct() {
        List<Product> productList = productBLL.findAll();
        productReflectionTable.generateTable(productTable, productList);
    }

    /**
     * Verify the data introduced by the user, then the product will be introduced intro the database (For the button "Add")
     */
    @FXML
    public void insert() {
        if (checkInput()) {
            int quantityInteger = Integer.parseInt(quantity.getText());
            Product product = new Product(0, name.getText(), quantityInteger);
            try {
                productBLL.insertProduct(product);
            } catch (Exception e) {
                showAlert(Alert.AlertType.ERROR, e.getMessage(), e.getMessage());
                LOGGER.log(Level.INFO, e.getMessage());
            }
        }
    }

    /**
     * This method edit for an existing product which is selected from the table (For the button "Edit")
     */
    @FXML
    public void edit() {
        if (selectedProduct != null) {
            if (checkInput()) {
                int quantityInteger = Integer.parseInt(quantity.getText());
                Product product = new Product(selectedProduct.getId(), name.getText(), quantityInteger);
                try {
                    productBLL.editProduct(product);
                } catch (Exception e) {
                    showAlert(Alert.AlertType.ERROR, e.getMessage(), e.getMessage());
                    LOGGER.log(Level.INFO, e.getMessage());
                }
            }
        } else {
            showAlert(Alert.AlertType.WARNING, "No product was selected", "Please select a product from the table in order to edit it");
        }
    }

    /**
     * This method deletes the product selected from the table (For the "Delete" button)
     */
    @FXML
    public void delete() {
        if (selectedProduct != null) {
            try {
                productBLL.deleteProduct(selectedProduct.getId());
            } catch (Exception e) {
                showAlert(Alert.AlertType.ERROR, e.getMessage(), e.getMessage());
                LOGGER.log(Level.WARNING, e.getMessage());
            }
        } else {
            showAlert(Alert.AlertType.WARNING, "No product was selected", "Please select a product from the table in order to delete it");
        }
    }

    /**
     * Setting the text fields for the selected product
     */
    @FXML
    public void setFields() {
        selectedProduct = productTable.getSelectionModel().getSelectedItem();
        name.setText(selectedProduct.getName());
        quantity.setText(String.valueOf(selectedProduct.getQuantity()));
    }

    /**
     * Validate the input data introduced by the user
     * @return - true, in case the data is introduced correctly, false, otherwise
     */
    public boolean checkInput() {
        try {
            if (!name.getText().isEmpty() && !quantity.getText().isEmpty()) {
                int quantityInteger = Integer.parseInt(quantity.getText());
                return true;
            } else {
                showAlert(Alert.AlertType.INFORMATION, "Some input is missing", "Please fill in all the fields");
                return false;
            }
        } catch (NumberFormatException numberFormatException) {
            //show alert
            showAlert(Alert.AlertType.INFORMATION, "Quantity is not ok!", "Quantity is not an integer");
            return false;
        }
    }

    /**
     * Going back to the main menu (For the "Back" button)
     * @param event - mouse event
     */
    @FXML
    private void productBackButton(ActionEvent event) {
        try {
            changeScene(event, "/OrderManagementView.fxml");
        } catch (Exception e) {
            System.out.println("Can't load the OrderManagementView page. Sorry!");
        }
    }
}
