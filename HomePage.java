package cgpa_gpa_calculator;

import javax.swing.*;
import java.awt.*;

public class HomePage {
    private JPanel homePanel; // Replaced 'panel' with 'homePanel'
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public HomePage(JPanel mainPanel, CardLayout cardLayout) {
        this.mainPanel = mainPanel;
        this.cardLayout = cardLayout;
        createHomePage(); // Initializes 'homePanel'
    }

    private void createHomePage() {
        homePanel = new JPanel();
        homePanel.setBackground(new Color(18, 78, 102)); // Light blue color
        homePanel.setLayout(new BorderLayout());

        // Welcome Label in the center
        JLabel welcomeLabel = new JLabel("Hey All! Welcome to GradePoint Pro...", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Verdana", Font.BOLD, 44));
        welcomeLabel.setForeground(new Color(255, 255, 255)); // RGB for #d3d9d4
        homePanel.add(welcomeLabel, BorderLayout.CENTER);
        
        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        separator.setForeground(Color.WHITE);  // Set line color to white
        separator.setPreferredSize(new Dimension(1000, 2)); // width and thickness


        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 30)); // Centered layout with spacing
        buttonPanel.setOpaque(false);     
        
        Dimension buttonSize = new Dimension(250, 60); // Width, Height
        
        // GPA Calculator button
        JButton proceedButton = new JButton("GPA Calculator");
        proceedButton.setFont(new Font("Verdana", Font.BOLD, 24));
        proceedButton.setForeground(new Color(211, 217, 212)); // RGB for #d3d9d4
        proceedButton.setBackground(new Color(33, 42, 49)); // RGB for #212A31
        proceedButton.setFocusPainted(false);
        proceedButton.setPreferredSize(buttonSize); //  Make button bigger
        proceedButton.addActionListener(e -> cardLayout.show(mainPanel, "GPA Calculator"));
        buttonPanel.add(proceedButton);

        // CGPA Calculator button
        JButton cgpaButton = new JButton("CGPA Calculator");
        cgpaButton.setFont(new Font("Verdana", Font.BOLD, 24));
        cgpaButton.setForeground(new Color(211, 217, 212)); // RGB for #d3d9d4
        cgpaButton.setBackground(new Color(46, 57, 68)); // RGB for #2e3944
        cgpaButton.setFocusPainted(false);
        cgpaButton.setPreferredSize(buttonSize);
        cgpaButton.addActionListener(e -> cardLayout.show(mainPanel, "CGPA Calculator"));
        buttonPanel.add(cgpaButton);

        // Add the button panel below the welcome label
        homePanel.add(buttonPanel, BorderLayout.SOUTH);
    }

    // Corrected getPanel() method
    public JPanel getPanel() {
        return homePanel; // Return 'homePanel'
    }
}
