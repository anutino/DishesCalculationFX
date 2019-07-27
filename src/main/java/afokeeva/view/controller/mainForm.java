package  afokeeva.view.controller;

import afokeeva.app.SelectedVariables;
import afokeeva.app.connectToDB.GetTablesValues;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Statement;
import java.util.ResourceBundle;

/**
 * Created by User on 18.08.2018.
 */

    public class mainForm implements Initializable {
        private static Statement stmt;

        @FXML
        private TextField textFieldITypeDishes;
        @FXML
        private Label labelTypeDishes;
        GetTablesValues getTablesValues = new GetTablesValues();
        SelectedVariables selectedVar = new SelectedVariables();
        String path = "/fxmlFile/";



    @Override
        public void initialize(URL url, ResourceBundle rb) {
        }


        public void openTypeDish(ActionEvent actionEvent) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(path+"addTypeDish.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }



        public void openNameDish(ActionEvent actionEvent) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(path+"addNameDish.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        public void addTypeDishes(ActionEvent actionEvent) {
          /*  String nameType = textFieldITypeDishes.getText();
            System.out.println("nameType= " + nameType);
            if (!nameType.equals("")) {
                //формируем запрос forming query
                queries.addTypeDishes(nameType);
                getTablesValues
                System.out.println("query Type Dishes" + queries.getQuery());
                sentQuery.sentQuery(queries.getQuery());
                if (!sentQuery.isTroubleSentQuery()) {
                    labelTypeDishes.setText("ok");
                } else {
                    labelTypeDishes.setText("не ok");
                }
            } else {
                labelTypeDishes.setText("не ok");
            }*/
        }


        public void openSearhDish(ActionEvent actionEvent) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(path+"searchDishByTypeMultiply.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setTitle("Поиск блюда ");
                stage.setScene(new Scene(root1));
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void loadDeletProduct(ActionEvent actionEvent) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(path+"productReport.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setTitle("Выгрузка продуктов");
                stage.setScene(new Scene(root1));
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void chngDishCard(ActionEvent actionEvent) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(path+"searchDishByTypeToChng.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setTitle("Поиск блюда по типу");
                stage.setScene(new Scene(root1));
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


}

