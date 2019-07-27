package afokeeva.app;

/**
 * Created by User on 18.08.2018.
 */

import  afokeeva.app.connectToDB.GetTablesValues;
import  afokeeva.app.tablesValues.NameGRWDishByTypeRecipe;
import  afokeeva.app.tablesValues.TypeRecipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class TestMain {

        public static void main(String[] args) {
            TestMain t = new TestMain();
            //t.createChngTechCard();
            //Получаем значения--->
            GetTablesValues getTablesValues = new GetTablesValues();

       //получить тип блюд
       TypeRecipe typeRecipe = new TypeRecipe();
       typeRecipe =   getTablesValues.getNameTypeRecipe();
       if(typeRecipe.getTypeRecipeMap() != null){
       Iterator it = typeRecipe.getTypeRecipeMap().entrySet().iterator();
        for (HashMap.Entry entry : typeRecipe.getTypeRecipeMap().entrySet()) {
    	   System.out.println("Key: " + entry.getKey() + " Value: "   + entry.getValue());
    	}
        }else{     System.out.println("typeRecipe = null " );  }

        // получить имя + вес блюда по типу блюда
	    ArrayList<NameGRWDishByTypeRecipe> arrayNameGRWDish = new ArrayList();
	    arrayNameGRWDish = getTablesValues.getNameAndGRWDishByTypeRecipe("1");
        if(arrayNameGRWDish != null){
            for (int i=0; i<arrayNameGRWDish.size(); i++){
                System.out.println(" NameGRWDishByTypeRecipe ID::NAME::GRW : "+arrayNameGRWDish.get(i).getIdDish() + " " + arrayNameGRWDish.get(i).name_dish + " "+ arrayNameGRWDish.get(i).grw_dish);
       	}
        }else{  System.out.println("NameGRWDishByTypeRecipe = null " );  }


            // получить информацию по рецепту
	 /*   ArrayList<RecipeInfoByIdDish> arr  = new ArrayList();
	      arr = getTablesValues.getRecipeInfoByIdDish("898");
       if(arr != null){
         for (int i=0; i<arr.size(); i++){
             System.out.println(" RecipeInfoByIdDish ID::NAME::GRW::Выход : "+arr.get(i).getIdProd() + " " + arr.get(i).getNameProd() + " "+ arr.get(i).getGrwProd() + " "+ arr.get(i).getGrwProdRecipe());}
       }else{  System.out.println("RecipeInfoByIdDish = null " );  }
       */

            // получить информацию по продуктам
       /*ArrayList<Products> arr = new ArrayList() ;
	     arr = getTablesValues.getPoductInfo();
      if(arr != null){
        for (int i=0; i<arr.size(); i++){
          System.out.println(" Products ID::NAME::GRW : "+arr.get(i).getIdProduct() + " " + arr.get(i).getNameProduct() + " "+ arr.get(i).getGrwProduct());}
      }else{  System.out.println("Products = null " );  }
      */

            // внести значения в таблицу типы блюд
            // res = getTablesValues.isInsertNameTypeRecipe("");
            //  System.out.println("Внесен тип блюд? " +res );
        }

      /*  public void createChngTechCard(){
            // форма Изменить техн карту
            JFrame jf = new JFrame();
            JPanel jp = new JPanel();
            GridBagLayout gb = new GridBagLayout();
            jf.add(jp);
            jp.setLayout(gb);

            GridBagConstraints c =  new GridBagConstraints();
            c.anchor = GridBagConstraints.NORTH;
            c.fill   = GridBagConstraints.NONE;
            c.gridheight = 1;
            c.gridwidth  = GridBagConstraints.REMAINDER;
            c.gridx = GridBagConstraints.RELATIVE;
            c.gridy = GridBagConstraints.RELATIVE;
            c.insets = new Insets(40, 0, 0, 0);
            c.ipadx = 0;
            c.ipady = 0;
            c.weightx = 0.0;
            c.weighty = 0.0;

            TextField tf = new TextField(30);
            gb.setConstraints(tf, c);
            //add(tf);
            }
*/
    }
