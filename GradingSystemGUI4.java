import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Scanner; 

public class GradingSystemGUI4 extends JFrame implements ActionListener {
    JLabel nameLabel, marksLabel;
    JTextField nameField, marksField;
    JButton submitButton, displayButton;
    JTextArea resultArea;
    Connection conn;

    public GradingSystemGUI4() {
        nameLabel = new JLabel("Student Name:");
        marksLabel = new JLabel("Marks:");
        nameField = new JTextField();
        marksField = new JTextField();
        submitButton = new JButton("Submit");
        displayButton = new JButton("Display Results");
        resultArea = new JTextArea(20, 30);
        
        resultArea.setWrapStyleWord(true);
        resultArea.setLineWrap(true);
        resultArea.append("Name\tMarks\tGrade\n");
        resultArea.setEditable(false);

        resultArea.setPreferredSize(resultArea.getPreferredSize());
        setTitle("Grading System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        nameLabel.setBounds(30, 30, 100, 30);
        marksLabel.setBounds(30, 70, 100, 30);
        nameField.setBounds(150, 30, 150, 30);
        marksField.setBounds(150, 70, 150, 30);
        submitButton.setBounds(50, 120, 100, 30);
        displayButton.setBounds(180, 120, 150, 30);
        resultArea.setBounds(30, 170, 320, 80);

        add(nameLabel);
        add(marksLabel);
        add(nameField);
        add(marksField);
        add(submitButton);
        add(displayButton);
        add(resultArea);
        

        submitButton.addActionListener(this);
        displayButton.addActionListener(this);

        try {
            // Database connection setup
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter MySQL password:");
            String password = scanner.nextLine();
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/project3";
            String username = "root";
            conn = DriverManager.getConnection(url, username, password);
            Statement statement = conn.createStatement();
            statement.execute("CREATE DATABASE IF NOT EXISTS `project3`;");
            statement.execute("USE `project3`;");
            statement.execute("CREATE TABLE IF NOT EXISTS `project3`.`grades` (`id` INT AUTO_INCREMENT PRIMARY KEY,`name` VARCHAR(255) ,`marks` INT, `grade` VARCHAR(1));");
 
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

   public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            try {
                String name = nameField.getText();
                int marks = Integer.parseInt(marksField.getText());

                // Calculate grade based on marks
                String grade = calculateGrade(marks);

                // Insert data into database
                PreparedStatement pstmt = conn.prepareStatement("INSERT INTO grades (name, marks, grade) VALUES (?, ?, ?)");
                pstmt.setString(1, name);
                pstmt.setInt(2, marks);
                pstmt.setString(3, grade);
                pstmt.executeUpdate();

                // Display grade
                JOptionPane.showMessageDialog(this, "Data inserted successfully\nGrade: " + grade);
                nameField.setText("");
                marksField.setText("");
            
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == displayButton) {
            try {
                // Display data from database
                resultArea.setText("Name\tMarks\tGrade\n");
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM grades");
              //  StringBuilder result = new StringBuilder("Name\tMarks\tGrade\n");
              //  resultArea.setText("");
                while (rs.next()) {
                    String name = rs.getString("name");
                    int marks = rs.getInt("marks");
                    String grade = rs.getString("grade");
                  //  result.append(rs.getString("name")).append("\t").append(rs.getInt("marks")).append("\t").append(rs.getString("grades")).append("\n");
                    resultArea.append(name + "\t" + marks + "\t" + grade + "\n");
                }
               // resultArea.setText(result.toString());
             //   resultArea.setText("");

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private String calculateGrade(int marks) {
        if (marks >= 90) {
            return "A";
        } else if (marks >= 80) {
            return "B";
        } else if (marks >= 70) {
            return "C";
        } else if (marks >= 60) {
            return "D";
        } else {
            return "F";
        }
    }

    public static void main(String[] args) {
        GradingSystemGUI4 gui = new GradingSystemGUI4();
        gui.setVisible(true);
    }
}
