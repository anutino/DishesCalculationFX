package  afokeeva.app.connectToDB;

/**
 * Created by User on 18.08.2018.
 */
import  afokeeva.app.tablesValues.NameGRWDishByTypeRecipe;
import  afokeeva.app.tablesValues.Products;
import  afokeeva.app.tablesValues.RecipeInfoByIdDish;
import  afokeeva.app.tablesValues.TypeRecipe;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class GetTablesValues {
    public ResultSet rs;
    ConnectToDB conDB = new ConnectToDB(); // для 2х методов

    /*
     * получить всю таблицу с типами блюд (рецептов) SELECT
     */
    public TypeRecipe getNameTypeRecipe(){
        TypeRecipe t = null;
        HashMap<Integer, String> tMap = new HashMap<Integer, String>();
        ArrayList<String> arr = new ArrayList<>();
        String query = "SELECT * FROM typerecipe";
        try {
            rs = conDB.getResultFromDB(query);
            t = new TypeRecipe();
            while (rs.next()){
                int id;
                String nameType;
                id = rs.getInt("id_typeRecipe");
                nameType = rs.getString("name_type");
                tMap.put(id, nameType);
                arr.add(nameType);
            }
            t.setTypeRecipe(tMap);
            t.setTypeRecipeArr(arr);
        } catch (SQLException e) {System.out.println("SQLException formationTypeDishes " + e);}
        finally {
            try { conDB.getStmt().close(); } catch(SQLException se1) { System.err.println("sentQuery SQLException2: " + se1);}
            try { conDB.getRs().close();   } catch(SQLException se2) { System.err.println("sentQuery SQLException3: " + se2);}
        }
        return t;
    }


    /*
     * получить всю таблицу с продуктами  SELECT
     */
    public ArrayList<Products> getProductInfo(){
        ArrayList<Products> arr = new ArrayList<>();
        String query ="SELECT * FROM products";
        try {
            rs = conDB.getResultFromDB(query);
            arr = new ArrayList<>();
            while (rs.next()){
                //int id;
                String id, nameProduct, grwProduct;
                id = rs.getString("id_product");
                nameProduct = rs.getString("name_product");
                grwProduct = rs.getString("grw_product");
                arr.add(new Products(id, nameProduct, grwProduct));

            }

        } catch (SQLException e) {System.out.println("SQLException formationTypeDishes " + e);}
        finally {
            try { conDB.getStmt().close(); } catch(SQLException se1) { System.err.println("sentQuery SQLException2: " + se1);}
            try { conDB.getRs().close();   } catch(SQLException se2) { System.err.println("sentQuery SQLException3: " + se2);}
        }
        return arr;
    }



    /*
     * получить тип блюда + имя блюда + вес брутто по типю блюда   JOIN
     */
    public ArrayList<NameGRWDishByTypeRecipe> getNameAndGRWDishByTypeRecipe(String idTypeRecipe ){
        ArrayList<NameGRWDishByTypeRecipe> arr = new ArrayList();
        String query = "SELECT d.name_dish , d.grw_dish, d.id_dish\n" +
                "FROM dish d\n" +
                "INNER JOIN typerecipe t ON t.id_typeRecipe = d.id_type\n" +
                "AND t.id_typeRecipe = "+idTypeRecipe+"\n";
        try {
            rs = conDB.getResultFromDB(query);
            while (rs.next()){
                String name_dish, grw_dish, id;
                id = rs.getString("id_dish");
                name_dish = rs.getString("name_dish");
                grw_dish = rs.getString("grw_dish");
                arr.add(new NameGRWDishByTypeRecipe(name_dish, grw_dish, id));
            }
        } catch (SQLException e) {System.out.println("SQLException formationTypeDishes " + e);}
        finally {
            try { conDB.getStmt().close(); } catch(SQLException se1) { System.err.println("sentQuery SQLException2: " + se1);}
            try { conDB.getRs().close();   } catch(SQLException se2) { System.err.println("sentQuery SQLException3: " + se2);}
        }
        return arr;
    }


	/*
	 * получить ИД и имя блюда + ИД, имя, вес продуктов  + выход блюда   JOIN
	 */

    public ArrayList<RecipeInfoByIdDish> getRecipeInfoByIdDish(String idDish){
        ArrayList<RecipeInfoByIdDish> arr = new ArrayList();
        String query="SELECT d.id_dish, d.name_dish, products.id_product, products.name_product, products.grw_product, recipe.grwProductRecipe\n" +
                "FROM `dish` d\n" +
                "JOIN recipe ON recipe.id_dish = d.id_dish\n" +
                "AND d.id_dish = " +idDish +"\n" +
                "JOIN products ON products.id_product = recipe.id_product\n";
        try {
            rs = conDB.getResultFromDB(query);
            while (rs.next()){
                String id, nameProd;
                id = rs.getString("id_product");
                nameProd = rs.getString("name_product");
                double grwProd = rs.getDouble("grw_product");
                double grwProductRecipe = rs.getDouble("grwProductRecipe");
                arr.add(new RecipeInfoByIdDish(id, nameProd, grwProd, grwProductRecipe));
            }
        } catch (SQLException e) {System.out.println("SQLException formationTypeDishes " + e);}
        finally {
            try { conDB.getStmt().close(); } catch(SQLException se1) { System.err.println("sentQuery SQLException2: " + se1);}
            try { conDB.getRs().close();   } catch(SQLException se2) { System.err.println("sentQuery SQLException3: " + se2);}
        }
        return arr;
    }

	/*
	 * внести значения  в таблицу ТИП БЛЮД   INSERT
	 */

    public boolean isInsertNameTypeRecipe(String nameTyoe){
        boolean result = false;
        String query = "INSERT INTO typerecipe (name_type) VALUES ('"+ nameTyoe +"')";
        result = conDB.isSentQuery(query);
        return result;
    }


    /*
     * внести значения  в таблицу ПРОДУКТЫ   INSERT
     */
    public boolean isInsertNameProduct(String nameProduct ){
        boolean result = false;
        String  query = "INSERT INTO `products`( `name_product`, `grw_product`)" +
                " VALUES ('" + nameProduct + "',0)";
        result = conDB.isSentQuery(query);
        return result;
    }
    /*
     *   получить продукт из таблицы ПРОДУКТЫ    SELECT
   */
    /*public boolean isNameProductInDB(String nameProduct ) {
        boolean result = false;
        String query = "SELECT name_product FROM products WHERE name_product= '" + nameProduct + "'";
        result = conDB.isNameProductInDB(query, nameProduct);
        return result;
    }*/

    /*
    *   в таблицу рецепты внести значения    INSERT
  */
    public boolean insertRecipe(String id_dish,String id_product,String productGrw) {
        boolean result = false;
        String query = "INSERT INTO recipe (id_dish, id_product, grwProductRecipe)" +
                      " VALUES ('" + id_dish + "','" + id_product + "', " + productGrw + ")";
        result = conDB.isSentQuery(query);
        return result;
    }

    /*
        * внести значения  в таблицу Продукты  INSERT
        */
    public boolean isInsertNameTypeRecipe(String nameDish, String grwDish, String idType ){
        boolean result = false;
        String query = "INSERT INTO dish (name_dish, grw_dish, id_type) VALUES ('"+ nameDish +"', '"+ grwDish +"','"+ idType +"',)";
        result = conDB.isSentQuery(query);
        return result;
    }

    /*
     * обновить значения вес брутто  в таблице БЛЮДА   UPDATE
     */
    public boolean isUpdateGRWProductByIdProduct(String grwProduct, String idProduct){
        boolean result = false;
        String  query="UPDATE products SET grw_product = grw_product+'"+grwProduct+"' WHERE  id_product= " +idProduct;
        result = conDB.isSentQuery(query);
        return result;
    }

    /*
     * обновить значения вес продукты в общем таблицеРецепты по ИД блюду  в таблице БЛЮДА   UPDATE
     */
    public boolean isUpdateGRWProductRecipeByIdDish(String grwProductRecipe, String name_product, String idDish ){
        boolean result = false;
        String query="UPDATE recipe, products\n" +
                "SET recipe.grwProductRecipe="+grwProductRecipe+"\n" +
                "products.name_product='"+name_product+"'\n" +
                "WHERE recipe.id_dish="+idDish;
        result = conDB.isSentQuery(query);
        return result;
    }

    /*
     * установить в таблицу продукты вес продукта= 0  UPDATE
     */
    public boolean isUpdateGRWProductsSetZero(){
        boolean result = false;
        String query="UPDATE products SET grw_product = 0";
        result = conDB.isSentQuery(query);
        return result;
    }





}


