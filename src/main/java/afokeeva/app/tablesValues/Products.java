package  afokeeva.app.tablesValues;

/**
 * Created by User on 18.08.2018.
 */
import java.util.ArrayList;

    public class Products {
        // ТАБЛИЦА ПРОДУКТЫ
        public String  idProduct;
        public String nameProduct;
        public String grwProduct;

        ArrayList<Products> arrayProducts;


        public Products(String idProduct, String nameProduct, String grwProduct) {
            super();
            this.idProduct = idProduct;
            this.nameProduct = nameProduct;
            this.grwProduct = grwProduct;
        }

        public Products() {
        }

        public ArrayList<Products> getArrayProducts() {
            return arrayProducts;
        }
        public void setArrayProducts(ArrayList<Products> arrayProducts) {
            this.arrayProducts = arrayProducts;
        }
        public String getIdProduct() {
            return idProduct;
        }
        public void setIdProduct(String idProduct) {
            this.idProduct = idProduct;
        }
        public String getNameProduct() {
            return nameProduct;
        }
        public void setNameProduct(String nameProduct) {
            this.nameProduct = nameProduct;
        }
        public String getGrwProduct() {
            return grwProduct;
        }
        public void setGrwProduct(String grwProduct) {
            this.grwProduct = grwProduct;
        }



    }

