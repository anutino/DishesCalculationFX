package  afokeeva.view.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import afokeeva.app.tablesValues.DishesInTable;
import afokeeva.app.SelectedVariables;
import afokeeva.app.connectToDB.GetTablesValues;
import afokeeva.app.tablesValues.RecipeInfoByIdDish;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ResourceBundle;

/**
 * Created by User on 18.08.2018.
 */

public class dishCardMultiply  implements Initializable {
    @FXML
    private TableColumn<DishesInTable, String> selectedColumn;
    @FXML private TableColumn<DishesInTable, String> productColumn;
    @FXML private TableColumn<DishesInTable, String> columnWeight;
    @FXML private TableView<DishesInTable> tableCard;
    @FXML private Label labelNameDish;
    @FXML private Label labelGrwDish;
     @FXML private TextField fieldMult;
    @FXML private Label sentInDBResult;


     ObservableList<DishesInTable> dishesInTable = FXCollections.observableArrayList();
     GetTablesValues getTablesValues = new GetTablesValues();
     SelectedVariables selectedVar = new SelectedVariables();

    @FXML public void  initialize(URL url, ResourceBundle rb) {
        labelNameDish.setText(selectedVar.getNameDish());
        labelGrwDish.setText(selectedVar.getGrwDish());
        selectedColumn.setCellValueFactory(new PropertyValueFactory<DishesInTable, String>("checkbox"));
        productColumn.setCellValueFactory(new PropertyValueFactory<DishesInTable, String>("nameProduct"));
         columnWeight.setCellValueFactory(new PropertyValueFactory<DishesInTable, String>("grwProductRecipe"));

        // получить информацию по рецепту
        ArrayList<RecipeInfoByIdDish> arr = new ArrayList();
         arr = getTablesValues.getRecipeInfoByIdDish(selectedVar.getIdDish());
        if (arr != null) {
            for (int i = 0; i < arr.size(); i++) {
                 System.out.println(" RecipeInfoByIdDish ID::NAME::GRW::Выход : " + arr.get(i).getIdProd() + " " + arr.get(i).getNameProd() + " " + arr.get(i).getGrwProd() + " " + arr.get(i).getGrwProdRecipe());
                dishesInTable.add(new DishesInTable(arr.get(i).getNameProd(),
                        arr.get(i).getGrwProd(),
                        arr.get(i).getIdProd(),
                        arr.get(i).getGrwProdRecipe()));
            }}else {
            System.out.println("RecipeInfoByIdDish = null ");
        }
        tableCard.setItems(dishesInTable);
        ObservableList<DishesInTable> choosen = FXCollections.observableArrayList();
        for(DishesInTable bean : dishesInTable){
            bean.getCheckbox().setSelected(true);

            }
    }

    HashMap mapSelectedProduct = new HashMap();

    public void multiplyAndSentToDB(ActionEvent actionEvent) {      // listener for checkBox
        ObservableList<DishesInTable> choosen = FXCollections.observableArrayList();
        for(DishesInTable bean : dishesInTable){
               if(bean.getCheckbox().isSelected()){
                if(bean.getIdProduct() != null){
                    choosen.add(bean);
                    System.out.println("Выбран продукт: " + bean.getNameProduct());
                    System.out.println("ID product : " + bean.getIdProduct());
                    System.out.println("grw of Product : " + bean.getGrwProductRecipe());
                    mapSelectedProduct.put(bean.getIdProduct(), bean.grwProductRecipe);
                }
            }
        }
        sentProductInfoToBD();
    }
    public void sentProductInfoToBD(){
        if(!fieldMult.getText().contains("-")){
            String newDoubleMult = fieldMult.getText();
            newDoubleMult = newDoubleMult.replace(",", ".");
        double multOnThisVal = Double.parseDouble(newDoubleMult);
        String newValue="";
        double newValueDouble;
        boolean res = false;
         Iterator it = mapSelectedProduct.entrySet().iterator();
        while (it.hasNext()){
            HashMap.Entry pair = (HashMap.Entry) it.next();
            System.out.println("Found ID product: " + pair.getKey() + " = grw of Product:  " + pair.getValue());
            double pairDobleGRW = Double.parseDouble(pair.getValue().toString());
            newValueDouble = multOnThisVal * pairDobleGRW;
            System.out.println("newValueDouble = " +newValueDouble);
            newValue = String.valueOf(newValueDouble);
            System.out.println("newValue string = " +newValue);
            res =  getTablesValues.isUpdateGRWProductByIdProduct(newValue,pair.getKey().toString());
        }
        if (res) {
            sentInDBResult.setText("Успешно");
        }else{
            sentInDBResult.setText("Не успешно");
        }
    }else{
            sentInDBResult.setText("Не успешно");
        }}


}

