package afokeeva.app.tablesValues;

/**
 * Created by User on 18.08.2018.
 */
import java.util.ArrayList;
import java.util.HashMap;

public class TypeRecipe {
    // ТАБЛИЦА ТИПЫ БЛЮД (РЕЦЕПТОВ)
    ArrayList<String> typeRecipeArr;
    HashMap<Integer, String> typeRecipe;

    public HashMap<Integer, String> getTypeRecipeMap() {
        return typeRecipe;
    }

    public void setTypeRecipe(HashMap<Integer, String> typeRecipe) {
        this.typeRecipe = typeRecipe;
    }


    public ArrayList<String> getTypeRecipeArr() {
        return typeRecipeArr;
    }

    public void setTypeRecipeArr(ArrayList<String> typeRecipeArr) {
        this.typeRecipeArr = typeRecipeArr;
    }
}
