import java.sql.*;

class InsertJdbc {
    public static void main(String args[]) {

        try {
            Class.forName(databaseDriver);
            //DataBase URL structure jdbc:engineName://host:port/dbName
            String.url = "jdbc:mysql://localhost:3306/saloni";
            String username = "root";
            String password = "root";
            //Creating a connection
            Connection con = DriverManger.getConnection(url, username, password);

            //create a query
            String q = "create table table1(tId int(20) primary key aut_increment, tName varchar(200) not null, tCity varchar(400))";

            //create a statement:
            Statetment stmt = con.createStatement();
            stmt.executeUpdate(q);
            System.out.println("Table created in database..");
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}