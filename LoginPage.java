package cgpa_gpa_calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage {
  //Initialization
    private JPanel panel;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private JPanel loginPanel;
    private JFrame frame;

    public LoginPage(JPanel mainPanel, CardLayout cardLayout) {
        this.mainPanel = mainPanel;
        this.cardLayout = cardLayout;
        createLoginPage();
    }

    private void createLoginPage() {
        loginPanel = new JPanel();
        loginPanel.add(new JLabel("Login Page"));
        loginPanel.setBackground(new Color(46, 57, 68)); // Dark background
        loginPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("Login to GradePoint Pro", JLabel.CENTER);
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 32));
        titleLabel.setForeground(new Color(211, 217, 212)); // Light gray text
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        loginPanel.add(titleLabel, gbc);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Verdana", Font.PLAIN, 18));
        usernameLabel.setForeground(Color.WHITE);
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        loginPanel.add(usernameLabel, gbc);

        JTextField usernameField = new JTextField(15);
        usernameField.setFont(new Font("Verdana", Font.PLAIN, 18));
        gbc.gridx = 1;
        loginPanel.add(usernameField, gbc);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Verdana", Font.PLAIN, 18));
        passwordLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        loginPanel.add(passwordLabel, gbc);

        JPasswordField passwordField = new JPasswordField(15);
        passwordField.setFont(new Font("Verdana", Font.PLAIN, 18));
        gbc.gridx = 1;
        loginPanel.add(passwordField, gbc);

        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Verdana", Font.BOLD, 18));
        loginButton.setBackground(new Color(33, 150, 83)); // Green button
        loginButton.setForeground(Color.WHITE);
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

         // Call the authenticateUser method from UserDAO
            boolean isAuthenticated = UserDAO.authenticateUser(username, password);

            if (isAuthenticated) {
                cardLayout.show(mainPanel, "Home"); // Navigate to home page
                System.out.println("Login successful!");
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid username or password!", "Login Failed", JOptionPane.ERROR_MESSAGE);
                System.out.println("Invalid username or password.");
            }
        });
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 3;
        loginPanel.add(loginButton, gbc);
    }
    // Define the getPanel method
    public JPanel getPanel() {
        return loginPanel;
    }
}
