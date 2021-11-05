package userRegistration.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ConnectionUtill {
	private static final String dburl = "jdbc:derby://localhost:1527/Users;create=true";

	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("org.apache.derby.jdbc.ClientDriver");
			con = DriverManager.getConnection(dburl);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public static void close(Connection con) {
		if (null != con) {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws Exception {
		Connection con = ConnectionUtill.getConnection();
		System.out.println("Connection successs...");
		Statement stmt = con.createStatement();
		String Sql = null;
		Sql = "CREATE TABLE Users(" + "id INT NOT NULL ," + "Name varchar(225)," + "Email varchar(225),"
				+ "Phonenum varchar(220)," + "Country varchar(210))";
		stmt.execute(Sql);
		System.out.println("Table created....");
	}
}
