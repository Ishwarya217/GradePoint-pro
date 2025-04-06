package cgpa_gpa_calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import cgpa_gpa_calculator.CardPanelCGPA;


public class CgpaCalculator {
    private JPanel cgpaWrapper;
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
        // OUTER PANEL that holds the scroll view
        cgpaWrapper = new JPanel(new BorderLayout());
        cgpaWrapper.setBackground(new Color(244, 246, 247));

        // INNER content panel for scroll
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBackground(new Color(244, 246, 247));
        GridBagConstraints rootGbc = new GridBagConstraints();
        rootGbc.insets = new Insets(30, 30, 30, 30);

        // CardPanel for CGPA Calculator UI
        CardPanelCGPA cardPanel = new CardPanelCGPA(30);
        cardPanel.setPreferredSize(new Dimension(580, 800));
        cardPanel.setBackground(Color.WHITE);
        cardPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font titleFont = new Font("Segoe UI", Font.BOLD, 34);
        Font labelFont = new Font("Segoe UI", Font.BOLD, 20);
        Font fieldFont = new Font("Segoe UI", Font.PLAIN, 20);
        Font buttonFont = new Font("Segoe UI", Font.BOLD, 20);

        JLabel titleLabel = new JLabel("CGPA Calculator", JLabel.CENTER);
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(new Color(31, 40, 51));
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        cardPanel.add(titleLabel, gbc);

        JLabel instructionLabel = new JLabel("Enter Your GPA for Each Semester");
        instructionLabel.setFont(labelFont);
        instructionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 1;
        cardPanel.add(instructionLabel, gbc);

        gpaFields = new JTextField[8];

        for (int i = 0; i < gpaFields.length; i++) {
            JLabel gpaLabel = new JLabel("Sem " + (i + 1) + ":");
            gpaLabel.setFont(labelFont);
            gbc.gridwidth = 1;
            gbc.gridx = 0;
            gbc.gridy = i + 2;
            cardPanel.add(gpaLabel, gbc);

            gpaFields[i] = new JTextField(10);
            gpaFields[i].setFont(fieldFont);
            gbc.gridx = 1;
            cardPanel.add(gpaFields[i], gbc);
        }

        JButton calculateButton = new JButton("Calculate CGPA");
        calculateButton.setFont(buttonFont);
        calculateButton.setBackground(new Color(46, 204, 113));
        calculateButton.setForeground(Color.WHITE);
        calculateButton.setFocusPainted(false);
        calculateButton.addActionListener(new CalculateCgpaListener());
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = gpaFields.length + 2;
        cardPanel.add(calculateButton, gbc);

        JButton resetButton = new JButton("Reset");
        resetButton.setFont(buttonFont);
        resetButton.setBackground(new Color(231, 76, 60));
        resetButton.setForeground(Color.WHITE);
        resetButton.setFocusPainted(false);
        resetButton.addActionListener(e -> resetFields());
        gbc.gridy = gpaFields.length + 3;
        cardPanel.add(resetButton, gbc);

        JButton backButton = new JButton("Go Back");
        backButton.setFont(buttonFont);
        backButton.setBackground(new Color(52, 152, 219));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Home"));
        gbc.gridy = gpaFields.length + 4;
        cardPanel.add(backButton, gbc);

        cgpaLabel = new JLabel("CGPA: ");
        cgpaLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        gbc.gridy = gpaFields.length + 5;
        cardPanel.add(cgpaLabel, gbc);

        contentPanel.add(cardPanel, rootGbc);

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setBorder(null);
        scrollPane.setOpaque(false);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.getViewport().setOpaque(false);

        cgpaWrapper.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(cgpaWrapper, "CGPA Calculator");
    }

    private void resetFields() {
        for (JTextField field : gpaFields) {
            field.setText("");
        }
        cgpaLabel.setText("CGPA: ");
    }

    private class CalculateCgpaListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            double totalGpa = 0.0;
            int count = 0;

            for (JTextField field : gpaFields) {
                String text = field.getText().trim();
                if (!text.isEmpty()) {
                    try {
                        totalGpa += Double.parseDouble(text);
                        count++;
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(
                                cgpaWrapper,
                                "Please enter valid numeric values.",
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
        return cgpaWrapper;
    }
}
