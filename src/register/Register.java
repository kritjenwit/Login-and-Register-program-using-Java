package register;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import connection.ConnectionConfig;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Register {
    public JFXTextField tf_uname;
    public JFXTextField tf_pass;
    public JFXButton btn_confirm;
    public JFXButton btn_back;

    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public Register(){
        connection = ConnectionConfig.getConnection();
    }

    Stage dialogStage = new Stage();
    Scene scene;

    public void pressConfirm(ActionEvent event) {
        String uname = tf_uname.getText().toString();
        String pass = tf_pass.getText().toString();

        String sql = "insert into login(username,password) values(?,?)";

        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, uname);
            ps.setString(2, pass);
            int i = ps.executeUpdate();

            if ( i == 0 || uname.isEmpty() || pass.isEmpty()){
                infoBox("Cannot register",null,"Failed");
            }else{
                infoBox("Register Successful", null, "Success");
                Node node = (Node) event.getSource();
                tf_uname.setText("");
                tf_pass.setText("");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void pressBack(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        dialogStage = (Stage) node.getScene().getWindow();
        dialogStage.close();
        scene = new Scene(FXMLLoader.load(getClass().getResource("/sample/sample.fxml")));
        dialogStage.setScene(scene);
        dialogStage.show();
    }

    public static void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }
}
