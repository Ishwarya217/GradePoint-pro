package cgpa_gpa_calculator;  //package name
import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	 private static final String DB_URL = "jdbc:mysql://localhost:3306/crudproject";      //Enter your database_name
	    private static final String USER = "root";  //usename
	    private static final String PASSWORD = "root"; //password

	    public static Connection getConnection() throws SQLException {
	        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
	    }
	    }


