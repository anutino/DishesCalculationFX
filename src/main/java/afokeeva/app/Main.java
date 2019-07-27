package afokeeva.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by User on 18.08.2018.
 */

    public class Main extends Application {
        @Override
        public void start(Stage primaryStage) throws Exception {
            Parent root = FXMLLoader.load(getClass().getResource("/fxmlFile/mainForm.fxml"));
            System.out.println("--"+ root);
            primaryStage.setTitle("Калькуляция блюд");
            primaryStage.setScene(new Scene(root, 550, 580));
            primaryStage.show();
        }
        public static void main(String[] args) {
            launch(args);
        }
    }

