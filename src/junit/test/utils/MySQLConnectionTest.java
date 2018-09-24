package junit.test.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.junit.Ignore;
import org.junit.Test;

public class MySQLConnectionTest {
	
	@Ignore
	public void testGetProperties() throws IOException{
		String path = MySQLConnectionTest.class.getClassLoader().getResource("db.properties").getPath();
		FileInputStream in = new FileInputStream(path);
		Properties properties = new Properties();
		properties.load(in);
		System.out.println(properties.getProperty("jdbc.user"));
		
	}
	
	@Test
	public void testConnectionMySQL() throws IOException{
		String path = MySQLConnectionTest.class.getClassLoader().getResource("db.properties").getPath();
		FileInputStream in = new FileInputStream(path);
		Properties properties = new Properties();
		properties.load(in);
		
		String user = properties.getProperty("jdbc.user");
		String password = properties.getProperty("jdbc.password");
		String driverClass = properties.getProperty("jdbc.driverClass");
		String url = properties.getProperty("jdbc.jdbcUrl");
		ResultSet rs = null;
		String sql = "SELECT * FROM administrator";
		
		try {
			Class.forName(driverClass);
			Connection connection = DriverManager.getConnection(url, user, password);
			Statement statement = connection.createStatement();
			rs = statement.executeQuery(sql);
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			while(rs.next()){
				System.out.println(rs.getInt(1) + ";" + rs.getString(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
