import java.sql.*;

class JdbcConnection{
    public static void main(String args[]){
        try{
            //Fully qualified name to identify the DB driver from the package for different DB we have different drivers
            String databaseDriver = "com.mysql.jdbc.Driver";
            //loading the driver
            Class.forName(databaseDriver);
            //DataBase URL structure jdbc:engineName://host:port/dbName
            String.url="jdbc:mysql://localhost:3306/saloni";
            String username="root";
            String password="root";

            Connection con = DriverManger.getConnection(url, username, password);
            if(con.isclosed()){
                System.out.println("Connection is closed")
            }else{
                System.out.println("Connection created...");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}