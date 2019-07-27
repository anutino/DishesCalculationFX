package  afokeeva.app.tablesValues;

import javafx.beans.property.BooleanProperty;
import javafx.scene.control.CheckBox;

/**
 * Created by User on 19.08.2018.
 */
public class DishesInTable {
    public String nameProduct;
    public double grwProduct;
    public String idProduct;
    public double grwProductRecipe;
    public CheckBox checkbox ;
    public BooleanProperty checked;
    public CheckBox select;


    public DishesInTable(String nameProduct, double grwProduct, String idProduct, double grwProductRecipe ) {
        this.nameProduct = nameProduct;
        this.grwProduct = grwProduct;
        this.idProduct = idProduct;
        this.grwProductRecipe = grwProductRecipe;
        this.select = new CheckBox();
        this.checkbox =  new CheckBox();
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public double getGrwProduct() {
        return grwProduct;
    }

    public void setGrwProduct(double grwProduct) {
        this.grwProduct = grwProduct;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public double getGrwProductRecipe() {
        return grwProductRecipe;
    }

    public void setGrwProductRecipe(double grwProductRecipe) {
        this.grwProductRecipe = grwProductRecipe;
    }

    public CheckBox getCheckbox() {
        return checkbox;
    }

    public void setCheckbox() {
        this.checkbox.setSelected(true);// = checkbox;
    }

    public boolean isChecked() {
        return checked.get();
    }

    public BooleanProperty checkedProperty() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked.set(checked);
    }
}
