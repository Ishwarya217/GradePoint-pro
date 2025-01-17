package cgpa_gpa_calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GpaCalculator {
    private JPanel panel;
    private CardLayout cardLayout;
    private JPanel mainPanel, homePanel, calculatePanel, cgpaPanel;
    private JComboBox<String>[] gradeFields;
    private JComboBox<Integer>[] creditFields;
    private JLabel totalCreditsLabel, gpaLabel;

    public GpaCalculator(JPanel mainPanel, CardLayout cardLayout) {
        this.mainPanel = mainPanel;
        this.cardLayout = cardLayout;
        createCalculatePage();
    }

    private void createCalculatePage() {
        calculatePanel = new JPanel();
        calculatePanel.setBackground(new Color(214, 174, 123));
        calculatePanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("GPA Calculator", JLabel.CENTER);
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 38));
        titleLabel.setForeground(new Color(31, 40, 51));
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        calculatePanel.add(titleLabel, gbc);

        String[] subjects = {"Subject 1", "Subject 2", "Subject 3", "Subject 4", "Subject 5", "Subject 6"};
        gradeFields = new JComboBox[6];
        creditFields = new JComboBox[6];

        String[] gradeOptions = {"O", "A+", "A", "B+", "B", "C"};

        for (int i = 0; i < subjects.length; i++) {
            JLabel subjectLabel = new JLabel(subjects[i] + ":");
            subjectLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
            gbc.gridwidth = 1;
            gbc.gridx = 0;
            gbc.gridy = i + 1;
            calculatePanel.add(subjectLabel, gbc);

            gradeFields[i] = new JComboBox<>(gradeOptions);
            gradeFields[i].setFont(new Font("Verdana", Font.PLAIN, 16));
            gbc.gridx = 1;
            calculatePanel.add(gradeFields[i], gbc);

            JLabel creditLabel = new JLabel("Credit:");
            creditLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
            gbc.gridx = 2;
            calculatePanel.add(creditLabel, gbc);

            creditFields[i] = new JComboBox<>(new Integer[]{0, 1, 2, 3, 4});
            creditFields[i].setFont(new Font("Verdana", Font.PLAIN, 16));
            gbc.gridx = 3;
            calculatePanel.add(creditFields[i], gbc);
        }

        JButton calculateButton = new JButton("Calculate GPA");
        calculateButton.setFont(new Font("Verdana", Font.BOLD, 22));
        calculateButton.setForeground(Color.WHITE);
        calculateButton.setBackground(Color.GREEN);
        calculateButton.addActionListener(new CalculateGPAListener());
        gbc.gridwidth = 4;
        gbc.gridx = 0;
        gbc.gridy = subjects.length + 1;
        calculatePanel.add(calculateButton, gbc);

        JButton resetButton = new JButton("Reset");
        resetButton.setFont(new Font("Verdana", Font.BOLD, 18));
        resetButton.setForeground(Color.WHITE);
        resetButton.setBackground(new Color(255, 0, 0));
        gbc.gridy = subjects.length + 2;
        calculatePanel.add(resetButton, gbc);

        JButton goBackButton = new JButton("Go Back");
        goBackButton.setFont(new Font("Verdana", Font.BOLD, 18));
        goBackButton.setForeground(Color.WHITE);
        goBackButton.setBackground(new Color(0, 0, 255));
        resetButton.addActionListener(e -> resetFields());
        goBackButton.addActionListener(e -> cardLayout.show(mainPanel, "Home"));
        gbc.gridy = subjects.length + 3;
        calculatePanel.add(goBackButton, gbc);

        totalCreditsLabel = new JLabel("Total Credits: ");
        totalCreditsLabel.setFont(new Font("Verdana", Font.BOLD, 20));
        gbc.gridy = subjects.length + 4;
        calculatePanel.add(totalCreditsLabel, gbc);

        gpaLabel = new JLabel("GPA: ");
        gpaLabel.setFont(new Font("Verdana", Font.BOLD, 22));
        gbc.gridy = subjects.length + 5;
        calculatePanel.add(gpaLabel, gbc);
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

    // Inner class for GPA calculation listener
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

        // Method to convert grade to grade point
        private double getGradePoint(String grade) {
            switch (grade) {
                case "O":
                    return 10.0;
                case "A+":
                    return 9.0;
                case "A":
                    return 8.0;
                case "B+":
                    return 7.0;
                case "B":
                    return 6.0;
                case "C":
                    return 5.0;
                default:
                    return 0.0; // for case of no grade selected
            }
        }
    }
}
