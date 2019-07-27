package  afokeeva.view.controller;

import afokeeva.app.SelectedVariables;
import afokeeva.app.connectToDB.GetTablesValues;
import afokeeva.app.tablesValues.DishesInTable;
import afokeeva.app.tablesValues.Products;
import afokeeva.app.tablesValues.RecipeInfoByIdDish;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ResourceBundle;

/**
 * Created by User on 18.08.2018.
 */

    public class chngDishCard  implements Initializable {
        @FXML
        private TableColumn<DishesInTable, String> choiseColumnChange;
        @FXML
        private TableColumn<DishesInTable, String> productColumnChange;
        @FXML
        private TableColumn<DishesInTable, String> columnWeightChange;
        @FXML
        private TableView<DishesInTable> tableCardChange;
        @FXML
        private Label labelNameDishChange;
        @FXML
        private Label labelGrwDishChange;
        @FXML private TextField txtNameProd;
        @FXML private TextField txtGRWProd;
        @FXML private Label lblOkNo;

        CheckBox checkBox = new CheckBox();
   //     SentQuery sentQuery = new SentQuery();
       // Queries queries = new Queries();
        ObservableList<DishesInTable> dishesInTable = FXCollections.observableArrayList();
        SelectedVariables selectedVar = new SelectedVariables();
        GetTablesValues getTablesValues = new GetTablesValues();

    @FXML
        public void initialize(URL url, ResourceBundle rb) {
        labelNameDishChange.setText(selectedVar.getNameDish());
        labelGrwDishChange.setText(selectedVar.getGrwDish());

        tableCardChange.setEditable(true);
        productColumnChange.setEditable(true);
        productColumnChange.setCellFactory(TextFieldTableCell.forTableColumn());
        productColumnChange.setOnEditCommit(
                (TableColumn.CellEditEvent<DishesInTable, String> tableCardChange) ->
                        (tableCardChange.getTableView().getItems().get(
                                tableCardChange.getTablePosition().getRow())
                        ).setNameProduct(tableCardChange.getNewValue())
        );
        productColumnChange.setEditable(true);
        productColumnChange.setCellValueFactory(new PropertyValueFactory<DishesInTable, String>("nameProduct"));
        // choiseColumnChange.setCellValueFactory(new PropertyValueFactory<DishesCardInTable, String>("checkbox"));
        columnWeightChange.setCellValueFactory(new PropertyValueFactory<DishesInTable, String>("grwDish"));


        // получить информацию по рецепту
        ArrayList<RecipeInfoByIdDish> arr = new ArrayList();
         arr = getTablesValues.getRecipeInfoByIdDish(selectedVar.getIdDish());
        if (arr != null) {
            for (int i = 0; i < arr.size(); i++) {
                // System.out.println(" RecipeInfoByIdDish ID::NAME::GRW::Выход : " + arr.get(i).getIdProd() + " " + arr.get(i).getNameProd() + " " + arr.get(i).getGrwProd() + " " + arr.get(i).getGrwProdRecipe());
                dishesInTable.add(new DishesInTable(arr.get(i).getNameProd(),
                        arr.get(i).getGrwProd(),
                        arr.get(i).getIdProd(),
                        arr.get(i).getGrwProdRecipe()));
            }}else {
            System.out.println("RecipeInfoByIdDish = null ");
        }
            tableCardChange.setItems(dishesInTable);
            tableCardChange.setEditable(true);
        }

        HashMap mapSelectedProduct = new HashMap();
        @FXML
        public void changeAndSentToDB(ActionEvent actionEvent) { // все изменяет
            String nameProduct =  txtNameProd.getText();
             if(!txtGRWProd.getText().contains("-") || nameProduct.equals("")) {
                 String grwProd = txtGRWProd.getText();
                 grwProd = grwProd.replace(",", ".");
                 String id_product = "";

                 boolean isFindProd = false;
                 // получить имена продуктов из БД и сравнить с введеными
                 Products products = new Products();
                 ArrayList<Products> arrProduct = new ArrayList<>();
                 arrProduct = getTablesValues.getProductInfo();
                 HashMap<String,String> nameProductMap = new HashMap<String,String>();
                 for ( int i = 0; i< arrProduct.size(); i++){
                     nameProductMap.put(arrProduct.get(i).idProduct, arrProduct.get(i).nameProduct);
                 }
                 Iterator it = nameProductMap.entrySet().iterator();
                 while (it.hasNext()) {
                     HashMap.Entry pair = (HashMap.Entry) it.next();
                     if (pair.getValue().toString().equalsIgnoreCase(nameProduct)) {
                         id_product = pair.getKey().toString();
                         System.out.println("Найден продукт в БАЗЕ: " + id_product);
                         isFindProd=true;
                     }
                 }

                  // передать инфо в БД
                 boolean res = false;
                 boolean insertProduct = false;
                 if(isFindProd){
                     System.out.println("Сразу заносим в БД");
                     res = getTablesValues.insertRecipe(selectedVar.getIdDish(), id_product, grwProd);
                 }else { // создать продукт и получить его ИД
                     System.out.println(" создаем продукт "+ nameProduct);
                     insertProduct = getTablesValues.isInsertNameProduct(nameProduct);

                     if(insertProduct){ // если успешно внесен продукт, то получеаем его ИД
                         ArrayList<Products> arrProduct1 = new ArrayList<>();
                         arrProduct1 = getTablesValues.getProductInfo();
                         for ( int i = 0; i< arrProduct1.size(); i++){
                             nameProductMap.put(arrProduct1.get(i).idProduct, arrProduct1.get(i).nameProduct);
                         }
                         Iterator it2 = nameProductMap.entrySet().iterator();
                         while (it2.hasNext()) {
                             HashMap.Entry pair = (HashMap.Entry) it2.next();
                             if (pair.getValue().toString().equalsIgnoreCase(nameProduct)) {
                                 id_product = pair.getKey().toString();
                                 System.out.println("id_product "+ id_product);
                             }
                         }
                         System.out.println(" Заносим в БД");
                         System.out.println("что заносим?  "+ selectedVar.getIdDish() + "- " + id_product+ " - " +grwProd);
                         res = getTablesValues.insertRecipe(selectedVar.getIdDish(), id_product, grwProd);
                     }
                 }
                 if(res){
                     lblOkNo.setText("УСПЕШНО");
                 }else{
                     lblOkNo.setText("НЕ успешно");
                 }
             }
             else{
                 lblOkNo.setText("ВВЕДЕН НЕ ВЕРНЫЙ ФОРМАТ");
             }

          /*  ObservableList<DishesInTable> choosen = FXCollections.observableArrayList();
            for (DishesInTable bean : dishesInTable) {
                //  if (bean.getCheckbox().isSelected()) {
                //if (!bean.getNameProduct()){
                if (bean.getIdProduct() != null) {
                    // System.out.println("grw of Product : " + bean.getNameProduct());
                    mapSelectedProduct.put(bean.getGrwProductRecipe(), bean.nameProduct);
                }
                sentProductInfoToBD();
            }*/
        }
        public void sentProductInfoToBD() {
             String idDishStr = String.valueOf(selectedVar.getIdDish());
            Iterator it = mapSelectedProduct.entrySet().iterator();
            while (it.hasNext()) {
                HashMap.Entry pair = (HashMap.Entry) it.next();
               // System.out.println("Found ID product: " + pair.getKey() + " = grw of Product:  " + pair.getValue());
                System.out.println("---- "+ pair.getValue().toString()+ pair.getKey().toString() + idDishStr);
                //getTablesValues.isUpdateGRWProductRecipeByIdDish(pair.getValue().toString(), pair.getKey().toString(), idDishStr);

             }

        }

    public void updateTable(ActionEvent actionEvent) {
        dishesInTable.clear();
        // получить информацию по рецепту
        ArrayList<RecipeInfoByIdDish> arr = new ArrayList();
        arr = getTablesValues.getRecipeInfoByIdDish(selectedVar.getIdDish());
        if (arr != null) {
            for (int i = 0; i < arr.size(); i++) {
                // System.out.println(" RecipeInfoByIdDish ID::NAME::GRW::Выход : " + arr.get(i).getIdProd() + " " + arr.get(i).getNameProd() + " " + arr.get(i).getGrwProd() + " " + arr.get(i).getGrwProdRecipe());
                dishesInTable.add(new DishesInTable(arr.get(i).getNameProd(),
                        arr.get(i).getGrwProd(),
                        arr.get(i).getIdProd(),
                        arr.get(i).getGrwProdRecipe()));
            }}else {
            System.out.println("RecipeInfoByIdDish = null ");
        }
        tableCardChange.setItems(dishesInTable);
        tableCardChange.setEditable(true);

}
}










