package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root, 400, 252));
        primaryStage.show();

        primaryStage.setResizable(false);

        Image icon = new Image("file:icon.png");
        primaryStage.getIcons().add(icon);

    }


    public static void main(String[] args) {
        launch(args);
    }
}
