package  afokeeva.app.tablesValues;

/**
 * Created by User on 18.08.2018.
 */

import java.util.ArrayList;

public class NameGRWDishByTypeRecipe {
    // ИМЯ + ВЕС БРУТТО БЛЮДА ПО ТИПУ БЛЮДА

    ArrayList<NameGRWDishByTypeRecipe> arrayNameGRWDish;

    public String name_dish;
    public String grw_dish;
    public String idDish;

    public NameGRWDishByTypeRecipe(String name_dish, String grw_dish, String idDish) {
        this.grw_dish = grw_dish;
        this.name_dish = name_dish;
        this.idDish = idDish;
    }

    public ArrayList<NameGRWDishByTypeRecipe> getArrayNameGRWDish() {
        return arrayNameGRWDish;
    }

    public void setArrayNameGRWDish(ArrayList<NameGRWDishByTypeRecipe> arrayNameGRWDish) {
        this.arrayNameGRWDish = arrayNameGRWDish;
    }

    public String getIdDish() {
        return idDish;
    }

    public void setIdDish(String idDish) {
        this.idDish = idDish;
    }

    public String getName_dish() {
        return name_dish;
    }

    public void setName_dish(String name_dish) {
        this.name_dish = name_dish;
    }

    public String getGrw_dish() {
        return grw_dish;
    }

    public void setGrw_dish(String grw_dish) {
        this.grw_dish = grw_dish;
    }

}

