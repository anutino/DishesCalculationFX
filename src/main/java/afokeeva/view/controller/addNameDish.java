package  afokeeva.view.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import afokeeva.app.connectToDB.GetTablesValues;
import afokeeva.app.tablesValues.TypeRecipe;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ResourceBundle;

/**
 * Created by User on 18.08.2018.
 */

    public class addNameDish  implements Initializable {
        @FXML
        private ChoiceBox<String> choiceBoxDishes;
        @FXML
        private Label lblTypeDish;
        @FXML
        private TextField fldNameDish;
        @FXML
        private TextField fldGRW;
        @FXML
        private Label labelOkNoDihes;

       TypeRecipe typeRecipe;
       GetTablesValues getTablesValues = new GetTablesValues();

    @Override
        public void initialize(URL url, ResourceBundle rb) {
            // получить  тип блюд
            typeRecipe = new TypeRecipe();
            typeRecipe = getTablesValues.getNameTypeRecipe();
            ArrayList<String> myArrayList = new ArrayList<String>();
            myArrayList = typeRecipe.getTypeRecipeArr();

            ObservableList<String> choiceList = FXCollections.observableArrayList(myArrayList);
            choiceBoxDishes.setItems(choiceList);
            Iterator it = typeRecipe.getTypeRecipeMap().entrySet().iterator();
            choiceBoxDishes.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> ov, String old_val, String new_val) {
                lblTypeDish.setText(new_val.toString());
            }});
    }

        public void addNameDishes(ActionEvent actionEvent) {
            System.out.println("nameDishes_  = " + fldNameDish.getText());
            System.out.println("grwDishes  = " + fldGRW.getText());
            System.out.println("lblType  = " + lblTypeDish.getText());

            typeRecipe = new TypeRecipe();
            typeRecipe = getTablesValues.getNameTypeRecipe();
            Iterator it = typeRecipe.getTypeRecipeMap().entrySet().iterator();
            String idTypeRecipe ="";
             while (it.hasNext()) {
                  HashMap.Entry pair = (HashMap.Entry) it.next();
                if (lblTypeDish.getText().equals(pair.getValue().toString())) {
                    System.out.println("Found2: " + pair.getKey() + " = " + pair.getValue());
                    idTypeRecipe= pair.getKey().toString();
                }
                it.remove(); // дабы не было ConcurrentModificationException
            }
            if(idTypeRecipe.equals("")|| fldNameDish.getText().equals("") || fldGRW.getText().equals("")
                    || lblTypeDish.getText().equals("") || lblTypeDish.getText().equals("Тип блюда")){
                    labelOkNoDihes.setText("не ok");
                    System.out.println("somthing is empty ");
            }else {
                 getTablesValues.getNameAndGRWDishByTypeRecipe(idTypeRecipe);
                 labelOkNoDihes.setText("ok");
                 System.out.println("add ");
            }}

    public void addAllProduct(ActionEvent actionEvent) {
    }
}

