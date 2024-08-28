import.java.sql.connection;

public class SelectJDBC {
    public static void main(String[] args) {
        try {
            Connection con = ConnectionProvider.getConnection();
            String query = "SELECT * FROM table1";
            Statement pstmt = con.createStatement(q);
            ResultSet set = stmt.executeQuery(query);
            //ResultSet saves data in row and column where it's pointer points to first row by default.
            while(set.next()){
                //here set.next() returns true or false so, if set contains more rows then it return true else it returns false
                //1 and 2 are the column number depending on the type of column
                int id = set.getInt(1);
                String name = set.getString(2);
                String city = setString(3);
            }
            System.out.println("Id: " + id + ", Name: " + name + ", City: " + city);
            System.out.println("Done........");
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

public class ConnectionProvider {
    private static Connection con;

    public static Connection getConnection() {
        try {
            if (con == null) {
                Class.forname("com.mysql.jdbc.Driver");
                con = DriverManger.getConnection("jdbc:mysql://localhost:3306/youtube", "root", "rootpwd");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}