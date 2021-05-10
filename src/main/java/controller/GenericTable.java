package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class GenericTable<T> {
    private final Class<T> type;

    @SuppressWarnings("unchecked")
    public GenericTable(Class<T> type) {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * This is a generic method to generate the content of the table of different type of objects
     * @param tableView - the table on the user interface
     * @param listT - the list of the objects I want to display inton the table
     */
    public void generateTable(TableView tableView, List<T> listT) {
        tableView.getColumns().clear();
        tableView.getItems().clear();
        for (Field field : type.getDeclaredFields()) {
            TableColumn<T, String> tableColumn = new TableColumn<T, String>(field.getName());
            tableColumn.setCellValueFactory(new PropertyValueFactory<T, String>(field.getName()));
            tableView.getColumns().add(tableColumn);
        }

        ObservableList<T> observableList = FXCollections.observableArrayList(listT);
        tableView.setItems(observableList);
    }
}
