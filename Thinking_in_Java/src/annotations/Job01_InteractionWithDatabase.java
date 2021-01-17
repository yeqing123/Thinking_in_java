package annotations;
import java.sql.*;

public class Job01_InteractionWithDatabase {
    private static String DBDRIVER = "com.mysql.cj.jdbc.Driver";
    private static String DBURL = "jdbc:mysql://localhost:3306/mldn?useSSL=true&serverTimezone=UTC";
    private static String DBUSER = "root";
    private static String DBPASSWORD = "mysqladmin";
    
	public static void main(String[] args) throws Exception {
		Class.forName(DBDRIVER);
		Connection conn = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD);
        System.out.println(conn);
        
	}

}
