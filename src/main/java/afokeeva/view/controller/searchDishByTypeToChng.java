package afokeeva.view.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import afokeeva.app.SelectedVariables;
import afokeeva.app.connectToDB.GetTablesValues;
import afokeeva.app.tablesValues.Dish;
import afokeeva.app.tablesValues.NameGRWDishByTypeRecipe;
import afokeeva.app.tablesValues.TypeRecipe;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ResourceBundle;

/**
 * Created by User on 22.05.2018.
 */

//TextField
public class searchDishByTypeToChng  implements Initializable {
    @FXML private TableColumn<Dish, String> nameColumn;
    @FXML private TableColumn<Dish, String> grwColumn;
    @FXML private TableView<Dish> nameTableView;
    @FXML private ChoiceBox<String> choiceTypeDishes;
    ObservableList<Dish> nameDishes = FXCollections.observableArrayList();
    GetTablesValues getTablesValues = new GetTablesValues();
    SelectedVariables selectedVar = new SelectedVariables();
    TypeRecipe typeRecipe;

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        // получить  тип блюд
       typeRecipe = new TypeRecipe();
       typeRecipe = getTablesValues.getNameTypeRecipe();
       ArrayList<String> myArrayList = new ArrayList<String>();
       myArrayList = typeRecipe.getTypeRecipeArr();
        ObservableList<String> choiceList = FXCollections.observableArrayList(myArrayList);
        choiceTypeDishes.setItems(choiceList);
        choiceTypeDishes.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> ov, String old_val, String new_val) {
                System.out.println("choice  : " + new_val);
                selectedVar.setNameTypeDish(new_val);
            }
        });
        nameColumn.setCellValueFactory(new PropertyValueFactory<Dish, String>("name")); // писать как в классе DishesName!   // ??
        grwColumn.setCellValueFactory(new PropertyValueFactory<Dish, String>("grwDish")); // писать как в классе DishesName!   // ??
    }

    public void findNameDishes(ActionEvent actionEvent) {
        System.out.println("выбрали  type   : " + selectedVar.getNameTypeDish());// mainVariables.getTypeDishes());
        nameTableView.getItems().clear();
        String idTypeRecipe ="";
        HashMap typeRecipeMap = new HashMap();
        typeRecipeMap = typeRecipe.getTypeRecipeMap();
        Iterator it = typeRecipeMap.entrySet().iterator();
        if(selectedVar.getNameTypeDish().equals("")){
            System.out.println("type is empty");
        }else {
            while (it.hasNext()) {
                HashMap.Entry pair = (HashMap.Entry) it.next();
                if (selectedVar.getNameTypeDish().equals(pair.getValue().toString())) {
                     System.out.println("Found: " + pair.getKey() + " = " + pair.getValue());
                    idTypeRecipe= pair.getKey().toString();
                }   //it.remove(); // дабы не было ConcurrentModificationException
        }}
        if(idTypeRecipe.equals("")){
            System.out.println("idType empty ");
        }else {
            // получаем из БД имя + вес + ИД блюда по типу
 	    ArrayList<NameGRWDishByTypeRecipe> arrayNameGRWDish = new ArrayList();
	    arrayNameGRWDish = getTablesValues.getNameAndGRWDishByTypeRecipe(idTypeRecipe);
        if(arrayNameGRWDish != null){
            for (int i=0; i<arrayNameGRWDish.size(); i++){
               // System.out.println(" NameGRWDishByTypeRecipe ID::NAME::GRW : "+arrayNameGRWDish.get(i).getIdDish() + " " + arrayNameGRWDish.get(i).name_dish + " "+ arrayNameGRWDish.get(i).grw_dish);
                nameDishes.add(new Dish(arrayNameGRWDish.get(i).name_dish,
                        arrayNameGRWDish.get(i).getIdDish(),
                        arrayNameGRWDish.get(i).grw_dish));
       	}
        }else{  System.out.println("NameGRWDishByTypeRecipe = null " );  }
            nameTableView.setItems(nameDishes);
        }
        listenerType();
    }

    private void listenerType() {
        nameTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                //Check whether item is selected and set value of selected item to Label
                if(nameTableView.getSelectionModel().getSelectedItem() != null)
                {
                    TableView.TableViewSelectionModel selectionModel = nameTableView.getSelectionModel();
                    ObservableList selectedCells = selectionModel.getSelectedCells();
                    TablePosition tablePosition = (TablePosition) selectedCells.get(0);
                    Object val = tablePosition.getTableColumn().getCellData(newValue);
                    System.out.println("Selected Value " + val);
                }
            }
        });

        nameTableView.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                    System.out.println("= "+nameTableView.getSelectionModel().getSelectedItem().getName());
                    //чтобы передать в след окно
                    String nameDish = nameTableView.getSelectionModel().getSelectedItem().getName();
                    String idDish = nameTableView.getSelectionModel().getSelectedItem().getIdDish();
                    String grwDish = nameTableView.getSelectionModel().getSelectedItem().getGrwDish();
                    System.out.println("name="+ nameDish + " grw_dish=" +grwDish + " id=" + idDish);
                    selectedVar.setNameDish(nameDish);
                    selectedVar.setGrwDish(grwDish);
                    selectedVar.setIdDish(idDish);
                    //open new window
                    openChosenNameDishes();
            }}
        });
    }


    public void openChosenNameDishes() {
        try {
            URL url = new File("src/main/resources/fxmlFile/chngDishCard.fxml").toURL();
            Parent root1 = FXMLLoader.load(url);
           // FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../../../resources/fxmlFile/chngDishCard.fxml"));
           // Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Карта блюда (изменить) ");
            stage.setScene(new Scene(root1));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}