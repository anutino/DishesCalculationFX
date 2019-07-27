package afokeeva.app.connectToDB;

/**
 * Created by User on 18.08.2018.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

    public class ConnectToDB {
        // JDBC variables for opening and managing connection
        static Connection con = null;
        public static Statement stmt;
        public static ResultSet rs;


        public static Connection getCon() {
            return con;
        }
        public static void setCon(Connection con) {
            ConnectToDB.con = con;
        }
        public static Statement getStmt() {
            return stmt;
        }
        public static void setStmt(Statement stmt) {
            ConnectToDB.stmt = stmt;
        }

        public static ResultSet getRs() {
            return rs;
        }

        public static void setRs(ResultSet rs) {
            ConnectToDB.rs = rs;
        }

        // JDBC URL, username and password of MySQL server

        private static final String url = "jdbc:mysql://localhost:3306/mydb"; // mydb0809-full-1
        private static final String user = "root";
        private static final String password = "";

        public static Connection getDBConnection(){
            try{
                if(con==null){
                    Class.forName("com.mysql.jdbc.Driver");
                    con = (Connection) DriverManager.getConnection(url, user, password);
                }}catch (Exception e){
                System.out.println("Ошибка при создании соединения " );
                e.printStackTrace();
            }
            return con;
        }
        // ПОЛУЧАЕМ

        public ResultSet  getResultFromDB(String query){
            Connection con =  ConnectToDB.getDBConnection();
            try{
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
            } catch (SQLException sqlEx) {
                System.err.println("getResultFromDB SQLException: " + sqlEx);
            }  catch(Exception e) {
                System.out.println("getResultFromDB Exception " + e);
            }
            setRs(rs);
            setStmt(stmt);
            return rs;
        }

        // ВНОСИМ ЗНАЧЕНИЯ В БД
        public boolean isSentQuery(String query){
            boolean result = false;
            Connection con =  ConnectToDB.getDBConnection();
            try{
                stmt = con.createStatement();
                stmt.executeUpdate(query);
                result = true;
            } catch (SQLException sqlEx) {
                System.err.println("sentQuery SQLException: " + sqlEx);
            }  catch(Exception e) {
                System.out.println("sentQuery Exception" + e);
            }finally {
                try { stmt.close();} catch(SQLException se1) { System.err.println("sentQuery SQLException2: " + se1); }
                try { rs.close();  } catch(SQLException se2) { }
            }

            return result;
        }

        // проверяем есть ли продукт в БД
        public boolean isNameProductInDB(String query, String nameProduct){
            boolean hasProduct = false;
            Connection con =  ConnectToDB.getDBConnection();
            String nameProduct_2 = "";
            try{
                stmt = con.createStatement();
                stmt.executeUpdate(query);
                while (rs.next()){
                    nameProduct_2 = rs.getString("name_product");
                    if(nameProduct_2.equalsIgnoreCase(nameProduct)){
                        hasProduct= true;
                    }}
            } catch (SQLException sqlEx) {
                System.err.println("sentQuery SQLException: " + sqlEx);
            }  catch(Exception e) {
                System.out.println("sentQuery Exception" + e);
            }finally {
                try { stmt.close();} catch(SQLException se1) { System.err.println("sentQuery SQLException2: " + se1); }
                try { rs.close();  } catch(SQLException se2) {}
            }
            return hasProduct;
        }

    }

