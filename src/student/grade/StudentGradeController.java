package student.grade;

public class StudentGradeController {
    private final StudentGradeModel model;
    private final StudentGradeView view;

    public StudentGradeController(StudentGradeModel model, StudentGradeView view) {
        this.model = model;
        this.view = view;
    }

    public void handleUserInput() {
        int userInput = (int)getUserInput();
        System.out.println(userInput + " userGuess");

        if (view.getMarksField().isEmpty() || view.getSelectedSubject() == "Select") {
            view.showError("Please select subject and enter a marks.");
        } else if (userInput == -1) {
            view.showError("Invalid input. Please enter a number.");
        } else if (userInput > model.getFullMarks() || userInput < 0) {
            view.showError("Invalid input. Out of the range.");
        } else {
            updateStudentTable();
        }
    }


    public float getUserInput() {
        String inputText = view.getMarksField();
        try {
           // return Integer.parseInt(inputText);
            return Float.parseFloat(inputText);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public void updateStudentTable() {
        String subject = view.getSelectedSubject();
        switch (subject) {
            case "Computer" -> {
                model.setComputerMarks(getUserInput());
            }
            case "English" -> {
                model.setEnglishMarks(getUserInput());
            }
            case "Nepali" -> {
                model.setNepaliMarks(getUserInput());
            }
            case "Math" -> {
                model.setMathMarks(getUserInput());
            }
            case "Science" -> {
                model.setScienceMarks(getUserInput());
            }
        }


        view.updateAll(tableData());
    }

    public Object[][] tableData(){
        return new Object[][]{
                {"Computer", model.getFullMarks(), model.getComputerMarks()},
                {"English", model.getFullMarks(), model.getEnglishMarks()},
                {"Math", model.getFullMarks(), model.getMathMarks()},
                {"Nepali", model.getFullMarks(), model.getNepaliMarks()},
                {"Science", model.getFullMarks(), model.getScienceMarks()}
        };
    }
    public void resetAll(){
        model.setComputerMarks(0);
        model.setEnglishMarks(0);
        model.setNepaliMarks(0);
        model.setMathMarks(0);
        model.setScienceMarks(0);
        view.updateAll(tableData());
        view.setInputField(0);
    }

}
