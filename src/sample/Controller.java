package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import connection.ConnectionConfig;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class Controller {
    public JFXButton btn_login;
    public JFXButton btn_reg;
    public AnchorPane anchor2;
    public JFXTextField tf_user;
    public JFXPasswordField pass_field;
    public Label check;

    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    Stage dialogStage = new Stage();
    Scene scene;

    public Controller() {
        connection = ConnectionConfig.getConnection();
    }

    public void pressLogin(ActionEvent event) {
        String uname = tf_user.getText().toString();
        String pass = pass_field.getText().toString();

        String sql = "select * from login where username = ? and password = ?";

        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, uname);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            if (!rs.next()) {
                infoBox("Please Enter correct Username and Password", null, "Failed");
            } else {
                infoBox("Login Successful", null, "Success");
                Node node = (Node) event.getSource();
                dialogStage = (Stage) node.getScene().getWindow();
                dialogStage.close();
                scene = new Scene(FXMLLoader.load(getClass().getResource("/menu/menu.fxml")));
                dialogStage.setScene(scene);
                dialogStage.show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    public void pressRes(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        dialogStage = (Stage) node.getScene().getWindow();
        dialogStage.close();
        scene = new Scene(FXMLLoader.load(getClass().getResource("/register/register.fxml")));
        dialogStage.setScene(scene);
        dialogStage.show();
    }
}
