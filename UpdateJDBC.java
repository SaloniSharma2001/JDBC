//Singleton class and Factory Method

public class UpdateJDBC {
    public static void main(String[] args) {
        try {
            Connection con = ConnectionProvider.getConnection();
            String q = "update table1 set tName=? , tCity=? where tId=?;";
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter new name : ");
            String name = br.readLine();
            System.out.println("Enter new city name : ");
            String city = br.readLine();
            System.out.println("Enter the student id : ");
            int id = Integer.parseInt(br.readLine());
            //What prepareStatement does is, it puts value of name, city and student id in the place of question marks
            PreparedStatement pstmt = con.prepareStatement(q);
            pstmt.setString(1, name);
            pstmt.setString(2, city);
            pstmt.setInt(3, id);

            pstmt.executeUpdate();
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