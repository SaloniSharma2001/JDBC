//Create table images(id int primary key auto_increment, pic blob);
//Image will stored in binary large object

//In MySql, blob type can contain data of max size upto 65 kb if we try to store data larger than max blob size, it will give us an error as data too large for this column.

import java.sql.*;
import java.io.*;

class ImageSave {
	try

    {
        Class.forname("com.mysql.jdbc.Driver");
        Connection con = DriverManger.getConnection("jdbc:mysql://localhost:3306/youtube", "root", "rootpwd");
        String q = "insert into images(pic) values(?)";
        PreparedStatement pstmt = con.prepareStatement(q);
        FileInputStream fis = new FileInputStream("FileAbsolutePath");
        pstmt.setBinaryStream(1, fis, fis.available());
        pstmt.executeUpdate();
        System.out.println("Done.....");
    }catch(
    Exception e)

    {
        System.out.println("Error !!");
    }
}
