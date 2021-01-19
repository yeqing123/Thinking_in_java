package annotations.database;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.*;
import java.io.*;

public class Job01_InteractionWithDatabase {
    private static String DBDRIVER = "com.mysql.cj.jdbc.Driver";
    private static String DBURL = "jdbc:mysql://localhost:3306/test?useSSL=true&serverTimezone=UTC";
    private static String DBUSER = "root";
    private static String DBPASSWORD = "mysqladmin";
   
	public static void main(String[] args) throws Exception {
		Connection conn = null;
		Statement stem = null;
		ResultSet rs = null;
		if(args.length < 1) {
			System.out.println("arguments: A file with database operation infomation.");
			System.exit(0);
		}
		try {
			Class.forName(DBDRIVER);
			conn = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD);
			stem = conn.createStatement();
			BufferedReader read = new BufferedReader(
					new InputStreamReader(new FileInputStream(args[0])));
			String info = null, sql = null;
			while((info = read.readLine()) != null) {
				sql = getSQLCommand(info);
			}
		    System.out.println(
		    		"******* 用于操作数据库的SQL语句为: *******\n" + sql);
		    stem.executeUpdate(sql);
		    rs = stem.executeQuery("select * from member");
		    System.out.println("\n****** 打印member表： ******");
		    while(rs.next()) {
		    	String firstName = rs.getString("firstName");
		    	String lastName = rs.getString("lastName");
		    	int age = rs.getInt("age");
		    	String handle = rs.getString("handle");
		    	System.out.println("姓名：" + firstName + " " + lastName + ", 年龄：" + age + ", handle: " + handle);
		    }
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
			    if(conn != null) {
				    conn.close();
			    }
			    if(stem != null) {
			    	stem.close();
			    }
			    if(rs != null) {
			    	rs.close();
			    }
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
        
	}
	public static String getSQLCommand(String fileName) throws Exception {
		StringBuilder sql = new StringBuilder();
		Class<?> cl = Class.forName(fileName);
		DBTable dbTable = cl.getAnnotation(DBTable.class);
		if(dbTable == null) {
			System.out.println("在给定的类" + cl.getName() + "中没有DBTable注释！");
			System.exit(0);
		}
		String tableName = dbTable.name();
		// 如果name()为空，则用Class的名称
		if(tableName.length() < 1)
			tableName = cl.getName().toUpperCase();
		
			List<String> columnDefs = new ArrayList<String>();
			for(Field field : cl.getDeclaredFields()) {
				String columnName = null;
				Annotation[] anns = field.getDeclaredAnnotations();
				if(anns.length < 1)
					continue;    // Not a db table column
				if(anns[0] instanceof SQLInteger) {
					SQLInteger sInt = (SQLInteger) anns[0];
					// Use field name if name not specified
					if(sInt.name().length() < 1)
						columnName = field.getName().toUpperCase();
					else
						columnName = sInt.name();
					columnDefs.add(columnName + " INT" + getConstraints(sInt.constraints()));
				}
				if(anns[0] instanceof SQLString) {
					SQLString sString = (SQLString) anns[0];
					// 如果没有指定名称，则用域名
					if(sString.name().length() < 1)
						columnName = field.getName().toUpperCase();
					else
						columnName = sString.name();
					columnDefs.add(columnName + " VARCHAR(" + sString.value() + ")" +
							getConstraints(sString.constraints()));
				}
			}
			for(String columnDef : columnDefs)
				sql.append("\n  " + columnDef + ",");
			// 删除最后多余的逗号
			return sql.substring(0, sql.length() - 1) + ");";
		} else if("insert".equalsIgnoreCase(items[0])) {
		    
		}
		
		}
		return sql;
    }
	private static String getConstraints(Constraints con) {
    	String constraints = "";
    	if(!con.allowNull())
    		constraints += " NOT NULL";
    	if(con.primaryKey())
    		constraints += " PRIMARY KEY";
    	if(con.unique())
    		constraints += " UNIQUE";
    	return constraints;
    }
}
