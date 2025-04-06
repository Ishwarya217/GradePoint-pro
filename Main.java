package cgpa_gpa_calculator;

import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.*;
import java.awt.*;

public class Main {

    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public static void main(String[] args) {
        // Test database connection
        try (Connection connection = DatabaseConnection.getConnection()) {
            if (connection != null) {
                System.out.println("Database connected successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Failed to connect to the database.");
            e.printStackTrace();
        }        
        // Launch GUI
        SwingUtilities.invokeLater(Main::new);
    }

    public Main() {
        frame = new JFrame("GradePoint Pro");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);

        // Initialize the main panel and card layout (use class fields)
        mainPanel = new JPanel(); // This uses the class-level mainPanel
        cardLayout = new CardLayout(); // This uses the class-level cardLayout
        mainPanel.setLayout(cardLayout);

        // Create and add pages to CardLayout
        createLoginPage();
        createHomePage();
        createCalculatePage();
        createCgpaPage();

        frame.add(mainPanel);
        frame.setVisible(true);

        // Show the login page by default
        cardLayout.show(mainPanel, "Login");
    }


    // Methods for creating pages
    private void createLoginPage() {
        LoginPage loginPage = new LoginPage(mainPanel, cardLayout);
        mainPanel.add(loginPage.getPanel(), "Login");
    }

    private void createHomePage() {
        HomePage homePage = new HomePage(mainPanel, cardLayout);
        mainPanel.add(homePage.getPanel(), "Home");
    }

    private void createCalculatePage() {
        GpaCalculator gpaCalculator = new GpaCalculator(mainPanel, cardLayout);
        mainPanel.add(gpaCalculator.getPanel(), "GPA Calculator");
    }

    private void createCgpaPage() {
        CgpaCalculator cgpaCalculator = new CgpaCalculator(mainPanel, cardLayout);
        mainPanel.add(cgpaCalculator.getPanel(), "CGPA Calculator");
    }
}
