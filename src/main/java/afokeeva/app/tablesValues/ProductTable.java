package afokeeva.app.tablesValues;

/**
 * Created by User on 09.09.2018.
 */
public class ProductTable {

        public String nameProduct;
        public String grwProduct;
        public String idProduct;
        public ProductTable(String idProduct, String nameProduct, String grwProduct){
            this.idProduct= idProduct;
            this.nameProduct=nameProduct;
            this.grwProduct=grwProduct;
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


