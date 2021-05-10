import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class OrderManagement extends Application {
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/OrderManagementView.fxml"));
        Parent root = myLoader.load();
        primaryStage.setTitle("Order Management");
        Scene scene = new Scene(root, 790, 625);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
