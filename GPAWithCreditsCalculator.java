import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class GPAWithCreditsCalculator {
private JFrame frame;
private CardLayout;
private JPanel mainPanel, homePanel, calculatePanel, cgpaPanel;
private JComboBox<String>[] gradeFields;
private JComboBox<Integer>[] creditFields;
private JLabel totalCreditsLabel, gpaLabel;
private JTextField[] gpaFields;
private JLabel cgpaLabel;
public static void main(String[] args) {
SwingUtilities.invokeLater(GPAWithCreditsCalculator::new);
}
public GPAWithCreditsCalculator() {
frame = new JFrame("GradePoint Pro");
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.setSize(600, 500);
cardLayout = new CardLayout();
mainPanel = new JPanel(cardLayout);
createHomePage();
createCalculatePage();
createCgpaPage();
mainPanel.add(homePanel, "Home");
mainPanel.add(calculatePanel, "GPA Calculator");
mainPanel.add(cgpaPanel, "CGPA Calculator");
frame.add(mainPanel);
frame.setVisible(true);
}
private void createHomePage() {
homePanel = new JPanel();
homePanel.setBackground(new Color(18, 78, 102)); // Light blue
color
homePanel.setLayout(new BorderLayout());
// Welcome Label in the center
JLabel welcomeLabel = new JLabel("Hey All! Welcome to
GradePoint Pro...", JLabel.CENTER);
welcomeLabel.setFont(new Font("Verdana", Font.BOLD, 44));
// Set the foreground color to #d3d9d4
welcomeLabel.setForeground(new Color(211, 217, 212)); // RGB for
#d3d9d4
homePanel.add(welcomeLabel, BorderLayout.CENTER);
// Panel for buttons
JPanel buttonPanel = new JPanel();
buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50,
10)); // Centered layout with spacing
// GPA Calculator button
JButton proceedButton = new JButton("GPA Calculator");
proceedButton.setFont(new Font("Verdana", Font.PLAIN, 20));
// Set foreground color to #d3d9d4
proceedButton.setForeground(new Color(211, 217, 212)); // RGB for
#d3d9d4
// Set background color to #212A31
proceedButton.setBackground(new Color(33, 42, 49)); // RGB for
#212A31
proceedButton.setFocusPainted(false);
proceedButton.addActionListener(e -> cardLayout.show(mainPanel,
"GPA Calculator"));
buttonPanel.add(proceedButton);
// CGPA Calculator button
JButton cgpaButton = new JButton("CGPA Calculator");
cgpaButton.setFont(new Font("Verdana", Font.PLAIN, 20));
// Set foreground color to #d3d9d4
cgpaButton.setForeground(new Color(211, 217, 212)); // RGB for
#d3d9d4
// Set background color to #2e3944
cgpaButton.setBackground(new Color(46, 57, 68)); // RGB for #2e3944
cgpaButton.setFocusPainted(false);
cgpaButton.addActionListener(e -> cardLayout.show(mainPanel, "CGPA
Calculator"));
buttonPanel.add(cgpaButton);
// Add the button panel below the welcome label
homePanel.add(buttonPanel, BorderLayout.SOUTH);
}
private void createCalculatePage() {
calculatePanel = new JPanel();
calculatePanel.setBackground(new Color(214, 174, 123));
calculatePanel.setLayout(new GridBagLayout());
GridBagConstraints gbc = new GridBagConstraints();
gbc.insets = new Insets(10, 10, 10, 10);
gbc.fill = GridBagConstraints.HORIZONTAL;
JLabel titleLabel = new JLabel("GPA Calculator",
JLabel.CENTER);
titleLabel.setFont(new Font("Verdana", Font.BOLD, 38));
titleLabel.setForeground(new Color(31,40,51));
gbc.gridwidth = 2;
gbc.gridx = 0;
gbc.gridy = 0;
calculatePanel.add(titleLabel, gbc);
String[] subjects = {"Subject 1", "Subject 2", "Subject 3", "Subject
4", "Subject 5", "Subject 6"};
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
calculateButton.setForeground (Color.WHITE);
calculateButton.setBackground(Color.GREEN);
calculateButton.addActionListener(new CalculateGPAListener());
gbc.gridwidth = 4;
gbc.gridx = 0;
gbc.gridy = subjects.length + 1;
calculatePanel.add(calculateButton, gbc);
JButton resetButton = new JButton("Reset");
resetButton.setFont(new Font("Verdana", Font.BOLD, 18));
resetButton.setForeground(Color.WHITE);
resetButton.setBackground (new Color(255,0,0));
gbc.gridy = subjects.length + 2;
calculatePanel.add(resetButton, gbc);
// Go Back Button
JButton goBackButton = new JButton("Go Back");
goBackButton.setFont(new Font("Verdana", Font.BOLD, 18));
goBackButton.setForeground(Color.WHITE);
goBackButton.setBackground(new Color(0,0,255));
resetButton.addActionListener(e -> resetFields());
goBackButton.addActionListener(e -> cardLayout.show(mainPanel,
"Home"));
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
private void createCgpaPage() {
cgpaPanel = new JPanel();
cgpaPanel.setBackground(new Color(214, 174, 123)); // Light
orange color
cgpaPanel.setLayout(new GridBagLayout());
GridBagConstraints gbc = new GridBagConstraints();
gbc.insets = new Insets(10, 10, 10, 10);
gbc.fill = GridBagConstraints.HORIZONTAL;
JLabel titleLabel = new JLabel("CGPA Calculator",
JLabel.CENTER);
titleLabel.setFont(new Font("Verdana", Font.BOLD, 38));
titleLabel.setForeground(new Color(18, 78, 102));
gbc.gridwidth = 2;
gbc.gridx = 0;
gbc.gridy = 0;
cgpaPanel.add(titleLabel, gbc);
JLabel instructionLabel = new JLabel("Enter Your GPA for Each
Semester ");
instructionLabel.setFont(new Font("Verdana", Font.BOLD, 20));
gbc.gridy = 1;
cgpaPanel.add(instructionLabel, gbc);
gpaFields = new JTextField[8]; // eight GPA inputs for example
