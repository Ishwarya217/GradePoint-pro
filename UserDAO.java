package cgpa_gpa_calculator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
//Inserting the login credentials such as username and password
    public static void insertUser(String username, String password) {
        String query = "INSERT INTO users (username, password) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
//Authentication process
    public static boolean authenticateUser(String username, String password) {
    	String query = "SELECT * FROM users WHERE LOWER(username) = LOWER(?) AND password = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            // Debugging output
            System.out.println("Attempting login with Username: " + username + " and Password: " + password);

            return rs.next(); // Returns true if a match is found
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // If an exception occurs or no matching record found
    }
//for viewing the data (login credentials) stored in the database        
    public static void viewUsers() {
        String query = "SELECT * FROM users"; // Adjust if you want specific columns
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
             
            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password"); // Only if you want to display it
                
                System.out.print("Username: " + username + "\t,Password: " + password);
                System.out.println();
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
