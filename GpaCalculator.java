package cgpa_gpa_calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GpaCalculator {
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private JPanel calculatePanel;
    private JComboBox<String>[] gradeFields;
    private JComboBox<Integer>[] creditFields;
    private JLabel totalCreditsLabel, gpaLabel;

    public GpaCalculator(JPanel mainPanel, CardLayout cardLayout) {
        this.mainPanel = mainPanel;
        this.cardLayout = cardLayout;
        createCalculatePage();
    }

    private void createCalculatePage() {
        calculatePanel = new JPanel(new GridBagLayout());
        calculatePanel.setBackground(new Color(244, 246, 247));
        GridBagConstraints rootGbc = new GridBagConstraints();
        rootGbc.insets = new Insets(40, 40, 40, 40);

        // Rounded panel (like a vertical card)
        CardPanel cardPanel = new CardPanel(30);
        cardPanel.setLayout(new GridBagLayout());
        cardPanel.setPreferredSize(new Dimension(580, 780));
        cardPanel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font titleFont = new Font("Segoe UI", Font.BOLD, 32);
        Font labelFont = new Font("Segoe UI", Font.BOLD, 18);
        Font fieldFont = new Font("Segoe UI", Font.PLAIN, 16);
        Font buttonFont = new Font("Segoe UI", Font.BOLD, 18);

        JLabel titleLabel = new JLabel("GPA Calculator", JLabel.CENTER);
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(new Color(31, 40, 51));
        gbc.gridwidth = 4;
        gbc.gridx = 0;
        gbc.gridy = 0;
        cardPanel.add(titleLabel, gbc);

        String[] subjects = {"Subject 1", "Subject 2", "Subject 3", "Subject 4", "Subject 5", "Subject 6"};
        gradeFields = new JComboBox[6];
        creditFields = new JComboBox[6];
        String[] gradeOptions = {"O", "A+", "A", "B+", "B", "C"};

        for (int i = 0; i < subjects.length; i++) {
            JLabel subjectLabel = new JLabel(subjects[i] + ":");
            subjectLabel.setFont(labelFont);
            gbc.gridwidth = 1;
            gbc.gridx = 0;
            gbc.gridy = i + 1;
            cardPanel.add(subjectLabel, gbc);

            gradeFields[i] = new JComboBox<>(gradeOptions);
            gradeFields[i].setFont(fieldFont);
            gradeFields[i].setPreferredSize(new Dimension(100, 30));
            gbc.gridx = 1;
            cardPanel.add(gradeFields[i], gbc);

            JLabel creditLabel = new JLabel("Credit:");
            creditLabel.setFont(labelFont);
            gbc.gridx = 2;
            cardPanel.add(creditLabel, gbc);

            creditFields[i] = new JComboBox<>(new Integer[]{0, 1, 2, 3, 4});
            creditFields[i].setFont(fieldFont);
            creditFields[i].setPreferredSize(new Dimension(100, 30));
            gbc.gridx = 3;
            cardPanel.add(creditFields[i], gbc);
        }

        JButton calculateButton = new JButton("Calculate GPA");
        calculateButton.setFont(buttonFont);
        calculateButton.setForeground(Color.WHITE);
        calculateButton.setBackground(new Color(46, 204, 113));
        calculateButton.setFocusPainted(false);
        calculateButton.addActionListener(new CalculateGPAListener());
        gbc.gridwidth = 4;
        gbc.gridx = 0;
        gbc.gridy = subjects.length + 1;
        cardPanel.add(calculateButton, gbc);

        JButton resetButton = new JButton("Reset");
        resetButton.setFont(buttonFont);
        resetButton.setForeground(Color.WHITE);
        resetButton.setBackground(new Color(231, 76, 60));
        resetButton.setFocusPainted(false);
        resetButton.addActionListener(e -> resetFields());
        gbc.gridy = subjects.length + 2;
        cardPanel.add(resetButton, gbc);

        JButton goBackButton = new JButton("Go Back");
        goBackButton.setFont(buttonFont);
        goBackButton.setForeground(Color.WHITE);
        goBackButton.setBackground(new Color(52, 152, 219));
        goBackButton.setFocusPainted(false);
        goBackButton.addActionListener(e -> cardLayout.show(mainPanel, "Home"));
        gbc.gridy = subjects.length + 3;
        cardPanel.add(goBackButton, gbc);

        totalCreditsLabel = new JLabel("Total Credits: ");
        totalCreditsLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        gbc.gridy = subjects.length + 4;
        cardPanel.add(totalCreditsLabel, gbc);

        gpaLabel = new JLabel("GPA: ");
        gpaLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        gbc.gridy = subjects.length + 5;
        cardPanel.add(gpaLabel, gbc);

        calculatePanel.add(cardPanel, rootGbc);

        JScrollPane scrollPane = new JScrollPane(calculatePanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        mainPanel.add(scrollPane, "GPA Calculator");
    }


    private void resetFields() {
        for (JComboBox<String> gradeField : gradeFields) {
            gradeField.setSelectedIndex(0);
        }
        for (JComboBox<Integer> creditField : creditFields) {
            creditField.setSelectedIndex(0);
        }
        totalCreditsLabel.setText("Total Credits: ");
        gpaLabel.setText("GPA: ");
    }

    public JPanel getPanel() {
        return calculatePanel;
    }

    private class CalculateGPAListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            double totalGradePoints = 0.0;
            int totalCredits = 0;

            for (int i = 0; i < gradeFields.length; i++) {
                String grade = (String) gradeFields[i].getSelectedItem();
                int credits = (Integer) creditFields[i].getSelectedItem();

                if (credits > 0) {
                    double gradePoint = getGradePoint(grade);
                    totalGradePoints += gradePoint * credits;
                    totalCredits += credits;
                }
            }

            if (totalCredits > 0) {
                double gpa = totalGradePoints / totalCredits;
                gpaLabel.setText("GPA: " + String.format("%.2f", gpa));
                totalCreditsLabel.setText("Total Credits: " + totalCredits);
            } else {
                gpaLabel.setText("GPA: -");
                totalCreditsLabel.setText("Total Credits: ");
            }
        }

        private double getGradePoint(String grade) {
            switch (grade) {
                case "O": return 10.0;
                case "A+": return 9.0;
                case "A": return 8.0;
                case "B+": return 7.0;
                case "B": return 6.0;
                case "C": return 5.0;
                default: return 0.0;
            }
        }
    }
}
