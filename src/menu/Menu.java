package menu;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class Menu {
    public JFXButton btn_logout;

    Stage dialogStage = new Stage();
    Scene scene;

    public void pressLogout(ActionEvent event) throws IOException {
        infoBox("Logout Successful", null, "Success");
        Node node = (Node) event.getSource();
        dialogStage = (Stage) node.getScene().getWindow();
        dialogStage.close();
        scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/sample.fxml")));
        dialogStage.setScene(scene);
        dialogStage.show();
    }

    public static void infoBox(String infoMessage,String headerText,String title){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }
}
