package afokeeva.view.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import afokeeva.app.SelectedVariables;
import afokeeva.app.connectToDB.GetTablesValues;
import afokeeva.app.tablesValues.ProductTable;
import afokeeva.app.tablesValues.Products;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by User on 18.08.2018.
 */


    public class productReport implements Initializable {
        @FXML
        private TableColumn<ProductTable, String> productColumn;
        @FXML private TableColumn<ProductTable, String> usedColumn;
        @FXML private TableView<ProductTable> tableProduct;
        @FXML private Button deleteProductGRW;
        @FXML private Button loadGrwProduct;
        @FXML private Label labelResLoad;
        ObservableList<ProductTable> nameProduct = FXCollections.observableArrayList();
        SelectedVariables selectedVar = new SelectedVariables();
        GetTablesValues getTablesValues = new GetTablesValues();
        ArrayList<Products> arrProducts;


    @FXML
        public void initialize(URL location, ResourceBundle resources) {
            productColumn.setCellValueFactory(new PropertyValueFactory<ProductTable, String>("nameProduct"));
            usedColumn.setCellValueFactory(new PropertyValueFactory<ProductTable, String>("grwProduct"));
            arrProducts = new ArrayList<>();
            arrProducts = getTablesValues.getProductInfo();

            for (int i=0; i<arrProducts.size(); i++) {
                // System.out.println(" ArrayDishesCard.gets " + sentQuery.getArrayProducts().get(i).nameProduct);
                nameProduct.add(new ProductTable(arrProducts.get(i).idProduct,
                        arrProducts.get(i).nameProduct,
                        arrProducts.get(i).grwProduct ));}
            tableProduct.setItems(nameProduct);
        }

        public void loadGrwProduct(ActionEvent actionEvent) throws IOException {
            Date date = new Date();
            Calendar.getInstance().getTime();
            Locale local = new Locale("ru","RU");
            SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd-MM-yyyy'-T-'hh-mm-ss", local);
            String dateNow = formatForDateNow.format(date);
            String nameFile="products-"+dateNow+".txt";
            String charset = "UTF-8";
            // получаем разделитель пути в текущей операционной системе
            String fileSeparator = System.getProperty("file.separator");
            String absoluteFilePath = "D:" +fileSeparator + "КАЛЬКУЛЯЦИЯ_БЛЮД"+ fileSeparator +"ВЫГРУЗКА_ПРОДУКТОВ"  + fileSeparator + nameFile;
            File file = new File(absoluteFilePath);
            if(file.createNewFile()){
                System.out.println(absoluteFilePath + " Файл создан!");
                try(FileWriter writer = new FileWriter(file, false))
                {
                    // запись всей строки
                    String text = "ID :  ПРОДУКТ  :  ВЕС БРУТТО";
                    writer.write(text +"\n");
                    for (int i=0; i<arrProducts.size(); i++) {
                        text = arrProducts.get(i).idProduct;
                        writer.write(text + " : ");
                        text = arrProducts.get(i).nameProduct;
                        writer.write(text+ " : ");
                        text = arrProducts.get(i).grwProduct;
                        writer.write(text + "\n");
                    }
                    writer.flush();
                    labelResLoad.setText("успешно");
                }
                catch(IOException ex){
                    labelResLoad.setText("Не успешно");
                    System.out.println(ex.getMessage());
                }
            } else {
                labelResLoad.setText("Не успешно");}
          /*  System.out.println("Файл " + absoluteFilePath + " уже существует");
            //создаем файл только с указанием имени файла
            file = new File("file.txt");
            if(file.createNewFile()){
                System.out.println("file.txt файл создан в корневой директории проекта");
            }else System.out.println("file.txt файл уже существует в корневой директории проекта");

            //создаем файл с указанием относительного пути к файлу
            String relativePath = "tmp" + fileSeparator + "file.txt";
            file = new File(relativePath);
            if(file.createNewFile()){
                System.out.println(relativePath + " файл создан в корневой директории проекта");
            }else System.out.println("Файл " + relativePath + " уже существует в директории проекта");*/

       /* try
        {
            // открываем поток для записи
            PrintWriter bw = new PrintWriter(file.getAbsoluteFile());            // пишем данные
            bw.write("НЕКИЕ_СТРОКОВЫЕ_ДАННЫЕ");
            // закрываем поток
            bw.close();
        } catch (IOException e)
        {
            e.printStackTrace();

      /*  File file = new File(nameFile);
        File filePath = new File(absoluteFilePath  + nameFile);
        filePath.createNewFile();
        System.out.println("Файл " + absoluteFilePath + " уже существует");
        */

        }
        public void deleteProductGrw(ActionEvent actionEvent) {
        getTablesValues.isUpdateGRWProductsSetZero();
        }
    }

