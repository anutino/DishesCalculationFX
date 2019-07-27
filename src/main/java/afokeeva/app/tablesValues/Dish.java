package  afokeeva.app.tablesValues;

/**
 * Created by User on 18.08.2018.
 */
public class Dish {
    private String name;
    private String idDish;
    private String grwDish;


    public Dish(String name,String idDish, String grwDish) {
        this.name = name;
        this.idDish = idDish;
        this.grwDish = grwDish;

    }

    public String getIdDish() {
        return idDish;
    }

    public void setIdDish(String idDish) {
        this.idDish = idDish;
    }

    public String getGrwDish() {
        return grwDish;
    }

    public void setGrwDish(String grwDish) {
        this.grwDish = grwDish;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
