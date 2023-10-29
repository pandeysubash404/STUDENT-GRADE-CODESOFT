package student.grade;

public class StudentGradeMain {
    public static void main(String[] args) {
        StudentGradeModel model = new StudentGradeModel(100);
        StudentGradeView view = new StudentGradeView(model);
        StudentGradeController controller = new StudentGradeController(model, view);

        view.setController(controller);

    }
}
