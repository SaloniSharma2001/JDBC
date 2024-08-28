// We have prepared a statement that is used for dynamic and parameterized queries. When our query gets a parameter at run time it is known as a dynamic query.

import java.sql.*;

class PreparedStatement {
    try{
        Class.forName(databaseDriver);
        //DataBase URL structure jdbc:engineName://host:port/dbName
        String.url = "jdbc:mysql://localhost:3306/saloni";
        String username = "root";
        String password = "root";
        Connection con = DriverManger.getConnection(URL, username, password);
        //Create a query
        String q = "insert into table1(tName,tCity) values (?,?)";
        //get the prepared statement object
        PreparedStatement pstmt = con.prepareStatement(q);
        //set the values to the query
        pstmt.setString(1, "Saloni");
        pstmt.setString(2, "Kolkata");
        //Since we are don't want any result set hence we shall use executeUpdate instead of execute itself
        pstmt.executeUpdate();
        //We cannot give q as a parameter to the execute statement here as it will compile the q again but in an incomplete way without the value being set.
        System.out.println("Inserted");
        con.close();
    }catch(Exception e){
        e.printStackTrace();
    }
}
