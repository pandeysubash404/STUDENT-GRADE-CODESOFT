package student.grade;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Objects;

public class StudentGradeView extends KeyAdapter implements ActionListener {

    private final JLabel errorLabel;
    private final JComboBox<String> subjectField;
    private final JTextField marksField;
    private JButton addButton;
    private final JTable marksTable;
    private final JButton resetButton;

    private final JButton resultButton;
    private final JLabel totalMarks;
    private final JLabel avgPercentage;
    private final JLabel gradeObtained;
    private final StudentGradeModel model;
    private StudentGradeController controller;

    public StudentGradeView(StudentGradeModel model) {
        this.model=model;

        JFrame frame = new JFrame("Student Grade");
        frame.setSize(500, 530);
        frame.setVisible(true);
        frame.setLocation(300, 100);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //Input Panel Start
        JLabel inputTitleLabel = new JLabel("Student Grade Calculation");
        inputTitleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        inputTitleLabel.setBounds(80, 30, 400, 30);
        frame.add(inputTitleLabel);

        errorLabel = new JLabel("");
        errorLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        errorLabel.setForeground(Color.RED);
        errorLabel.setBounds(100, 60, 400, 30);
        frame.add(errorLabel);


        JLabel subjectLabel = new JLabel("Select Subject");
        subjectLabel.setBounds(50, 100, 100, 30);
        frame.add(subjectLabel);

        String[] subjects = {"Select","Computer", "English", "Math", "Nepali", "Science"};
        subjectField = new JComboBox<>(subjects);
        subjectField.setBounds(200, 100, 200, 30);
        frame.add(subjectField);
        subjectField.addKeyListener(this);

        JLabel marksLabel = new JLabel("Enter Marks");
        marksLabel.setBounds(50, 150, 100, 30);
        frame.add(marksLabel);

        marksField = new JTextField();
        marksField.setBounds(200, 150, 200, 30);
        frame.add(marksField);
        marksField.addKeyListener(this);

        addButton = new JButton("Add Marks");
        addButton.setBounds(350, 200, 100, 30);
        frame.add(addButton);
        addButton.addActionListener(this);

        // Create a table to display student information
        String[] columnName = {"Subject", "Full Marks", "Obtained Marks"};
        Object[][] data = new Object[][]{
                {"Computer", model.getFullMarks(), model.getComputerMarks()},
                {"English", model.getFullMarks(), model.getEnglishMarks()},
                {"Math", model.getFullMarks(), model.getMathMarks()},
                {"Nepali", model.getFullMarks(), model.getNepaliMarks()},
                {"Science", model.getFullMarks(), model.getScienceMarks()}
        };
        marksTable = new JTable(data,columnName);
        marksTable.setEnabled(false);

        JScrollPane scrollPane = new JScrollPane(marksTable);
        scrollPane.setBounds(50, 250, 400, 103);
        frame.add(scrollPane);

       /* marksTable = new JTable(rowData, columnName);

        JScrollPane scrollPane = new JScrollPane(marksTable);
        scrollPane.setBounds(50, 250, 400, 103);
        frame.add(scrollPane);*/


        JLabel totalMarksLabel = new JLabel("Total Marks : ");
        totalMarksLabel.setFont(new Font("Arial", Font.BOLD, 12));
        totalMarksLabel.setBounds(50, 360, 150, 30);
        frame.add(totalMarksLabel);

        totalMarks = new JLabel(model.getTotalMarks()+" ");
        totalMarks.setFont(new Font("Arial", Font.PLAIN, 12));
        totalMarks.setBounds(150, 360, 150, 30);
        frame.add(totalMarks);

        JLabel avgPercentageLabel = new JLabel("Average Percentage : ");
        avgPercentageLabel.setFont(new Font("Arial", Font.BOLD, 12));
        avgPercentageLabel.setBounds(250, 360, 150, 30);
        frame.add(avgPercentageLabel);

        avgPercentage = new JLabel(model.getAveragePercentage());
        avgPercentage.setFont(new Font("Arial", Font.PLAIN, 12));
        avgPercentage.setBounds(400, 360, 50, 30);
        frame.add(avgPercentage);

        JLabel gradeObtainedLabel = new JLabel("Grade Obtained : ");
        gradeObtainedLabel.setFont(new Font("Arial", Font.BOLD, 12));
        gradeObtainedLabel.setBounds(50, 390, 150, 30);
        frame.add(gradeObtainedLabel);

        gradeObtained = new JLabel(model.getGradeLevel());
        gradeObtained.setFont(new Font("Arial", Font.PLAIN, 12));
        gradeObtained.setBounds(150, 390, 150, 30);
        frame.add(gradeObtained);

        resetButton = new JButton("Reset");
        resetButton.setBounds(50, 440, 100, 30);
        frame.add(resetButton);
        resetButton.addActionListener(this);

        resultButton = new JButton("Generate Result");
        resultButton.setBounds(300, 440, 150, 30);
        frame.add(resultButton);
        resultButton.addActionListener(this);

    }

    public void setController(StudentGradeController controller) {
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object object = e.getSource();
        if (object == resetButton) {
            controller.resetAll();
        } else if (object == resultButton) {
            updateResult();
        } else {
            controller.handleUserInput();
            //updateResult();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            showError(" ");
        }
        else if (controller.getUserInput() == -1) {
            errorLabel.setText("Invalid input. Please enter a number.");
        }


    }


    public void showError(String error) {
        errorLabel.setText(error);
    }

    public String getMarksField() {
        return marksField.getText();
    }

    public String getSelectedSubject() {
        return Objects.requireNonNull(subjectField.getSelectedItem()).toString();
    }

    public void updateAll(Object[][] data) {
        DefaultTableModel model = new DefaultTableModel(data, new String[]{"Subject", "Full Marks", "Obtained Marks"});
        marksTable.setModel(model);
        if (subjectField.getSelectedIndex() <= 4) {
            setInputField(subjectField.getSelectedIndex() + 1);
        } else {
            setInputField(subjectField.getSelectedIndex());
        }
        showError("");
    }

    public void updateResult(){
        totalMarks.setText((Math.max(model.getTotalMarks(), 0))+" ");
        avgPercentage.setText(model.getAveragePercentage());
        gradeObtained.setText(model.getGradeLevel());
    }

    public void setInputField(int index){
        marksField.setText("");
        subjectField.setSelectedIndex(index);
    }
}
