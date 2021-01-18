package annotations.database;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Job01_InteractionWithDatabase {
    private static String DBDRIVER = "com.mysql.cj.jdbc.Driver";
    private static String DBURL = "jdbc:mysql://localhost:3306/test?useSSL=true&serverTimezone=UTC";
    private static String DBUSER = "root";
    private static String DBPASSWORD = "mysqladmin";
   
	public static void main(String[] args) throws Exception {
		Connection conn = null;
		Statement stem = null;
		ResultSet rs = null;
		try {
		    Class.forName(DBDRIVER);
		    conn = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD);
		    stem = conn.createStatement();
		    String sql = getSQLCommand(args);
		    System.out.println(
		    		"******* 用于操作数据库的SQL语句为: *******\n" + sql);
		    stem.executeUpdate(sql);
		    sql = "insert into member value('steven', 'ye', 38, '001');";
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
	public static String getSQLCommand(String[] args) throws Exception {
		if(args.length < 1) {
			System.out.println("Arguments: annotated classes.");
			System.exit(0);
		}
		String sql = "";
		for(String className : args) {
			Class<?> cl = Class.forName(className);
			DBTable dbTable = cl.getAnnotation(DBTable.class);
            CommandType commaType = cl.getAnnotation(CommandType.class);
			if(dbTable == null) {
				System.out.println("No DBTable annotation in class " + className);
				continue;
			}
			String tableName = dbTable.name();
			String typeName = commaType.name();
			// If the name if empty, use the Class name:
			if(tableName.length() < 1)
				tableName = cl.getName().toUpperCase();
			if(typeName.length() < 1)
				typeName = args[1].split(" ")[0].toUpperCase();
			List<String> columnDefs = new ArrayList<String>();
			StringBuilder createCommand = new StringBuilder(" " + tableName + "(");
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
					// Use field name if name not specified.
					if(sString.name().length() < 1)
						columnName = field.getName().toUpperCase();
					else
						columnName = sString.name();
					columnDefs.add(columnName + " VARCHAR(" + sString.value() + ")" +
						getConstraints(sString.constraints()));
				}
				for(String columnDef : columnDefs)
					createCommand.append("\n  " + columnDef + ",");
				// Remove trailing comma
				sql = createCommand.substring(0, createCommand.length() - 1) + ");";
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
