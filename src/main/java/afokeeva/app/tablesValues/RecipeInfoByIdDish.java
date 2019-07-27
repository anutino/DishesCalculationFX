package afokeeva.app.tablesValues;

/**
 * Created by User on 18.08.2018.
 */
import java.util.ArrayList;

    public class RecipeInfoByIdDish {

        // ID продукта + Имя продукта + вес продукта + выход блюда = рецепт

        ArrayList<RecipeInfoByIdDish> arrayRecipeInfo;

        String idProd;
        String nameProd;
        double grwProd;
        double grwProdRecipe;
        public RecipeInfoByIdDish(String idProd, String nameProd, double grwProd, double grwProdRecipe) {
            super();
            this.idProd = idProd;
            this.nameProd = nameProd;
            this.grwProd = grwProd;
            this.grwProdRecipe = grwProdRecipe;
        }


        public ArrayList<RecipeInfoByIdDish> getArrayRecipeInfo() {
            return arrayRecipeInfo;
        }


        public void setArrayRecipeInfo(ArrayList<RecipeInfoByIdDish> arrayRecipeInfo) {
            this.arrayRecipeInfo = arrayRecipeInfo;
        }


        public String getIdProd() {
            return idProd;
        }
        public void setIdProd(String idProd) {
            this.idProd = idProd;
        }
        public String getNameProd() {
            return nameProd;
        }
        public void setNameProd(String nameProd) {
            this.nameProd = nameProd;
        }
        public double getGrwProd() {
            return grwProd;
        }
        public void setGrwProd(double grwProd) {
            this.grwProd = grwProd;
        }

        public double getGrwProdRecipe() {
            return grwProdRecipe;
        }

        public void setGrwProdRecipe(double grwProdRecipe) {
            this.grwProdRecipe = grwProdRecipe;
        }



    }

