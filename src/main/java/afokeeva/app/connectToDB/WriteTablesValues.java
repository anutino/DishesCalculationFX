package afokeeva.app.connectToDB;

/**
 * Created by User on 18.08.2018.
 */
public class WriteTablesValues {
    ConnectToDB con = new ConnectToDB();

    public boolean writeTypeRecipe(String grwProduct, String idProduct ){
        boolean result = false;
        String query="UPDATE `products` SET grw_product = grw_product+'"+grwProduct+"' WHERE  id_product= " +idProduct;
        // result = con.sentQuery(query);
        return result;
    }
}
