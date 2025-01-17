package cgpa_gpa_calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CgpaCalculator {
    private JPanel cgpaPanel;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private JLabel cgpaLabel;
    private JTextField[] gpaFields;

    public CgpaCalculator(JPanel mainPanel, CardLayout cardLayout) {
        this.mainPanel = mainPanel;
        this.cardLayout = cardLayout;
        createCgpaPage();
    }

    private void createCgpaPage() {
        cgpaPanel = new JPanel();
        cgpaPanel.setBackground(new Color(214, 174, 123)); // Light orange color
        cgpaPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("CGPA Calculator", JLabel.CENTER);
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 38));
        titleLabel.setForeground(new Color(18, 78, 102));
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        cgpaPanel.add(titleLabel, gbc);

        JLabel instructionLabel = new JLabel("Enter Your GPA for Each Semester ");
        instructionLabel.setFont(new Font("Verdana", Font.BOLD, 20));
        gbc.gridy = 1;
        cgpaPanel.add(instructionLabel, gbc);

        gpaFields = new JTextField[8]; // Eight GPA inputs for example

        for (int i = 0; i < gpaFields.length; i++) {
            JLabel gpaLabel = new JLabel("Sem " + (i + 1) + ":");
            gpaLabel.setFont(new Font("Verdana", Font.PLAIN, 18));
            gbc.gridx = 0;
            gbc.gridy = i + 2;
            cgpaPanel.add(gpaLabel, gbc);

            gpaFields[i] = new JTextField(10);
            gpaFields[i].setFont(new Font("Verdana", Font.PLAIN, 18));
            gbc.gridx = 1;
            cgpaPanel.add(gpaFields[i], gbc);
        }

        JButton calculateCgpaButton = new JButton("Calculate CGPA");
        calculateCgpaButton.setFont(new Font("Verdana", Font.BOLD, 22));
        calculateCgpaButton.setForeground(Color.WHITE);
        calculateCgpaButton.setBackground(Color.GREEN);
        calculateCgpaButton.addActionListener(new CalculateCgpaListener());
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = gpaFields.length + 2;
        cgpaPanel.add(calculateCgpaButton, gbc);

        // Go Back Button
        JButton goBackButton = new JButton("Go Back");
        goBackButton.setFont(new Font("Verdana", Font.BOLD, 18));
        goBackButton.setForeground(Color.WHITE);
        goBackButton.setBackground(new Color(0, 0, 255));
        goBackButton.addActionListener(e -> cardLayout.show(mainPanel, "Home"));
        gbc.gridy = gpaFields.length + 3;
        cgpaPanel.add(goBackButton, gbc);

        cgpaLabel = new JLabel("CGPA: ");
        cgpaLabel.setFont(new Font("Verdana", Font.BOLD, 22));
        gbc.gridy = gpaFields.length + 4;
        cgpaPanel.add(cgpaLabel, gbc);
    }

    // Inner class for CGPA calculation
    private class CalculateCgpaListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            double totalGpa = 0.0;
            int count = 0;

            for (JTextField gpaField : gpaFields) {
                String text = gpaField.getText().trim();
                if (!text.isEmpty()) {
                    try {
                        totalGpa += Double.parseDouble(text);
                        count++;
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(
                                cgpaPanel,
                                "Please enter valid numeric values for all GPA fields.",
                                "Input Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                        return;
                    }
                }
            }

            double cgpa = count > 0 ? totalGpa / count : 0.0;
            cgpaLabel.setText("CGPA: " + String.format("%.2f", cgpa));
        }
    }

    public JPanel getPanel() {
        return cgpaPanel;
    }
}
