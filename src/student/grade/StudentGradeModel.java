package student.grade;

public class StudentGradeModel {
    private final int fullMarks;
   // private int passMarks;
    private float computerMarks;
    private float englishMarks;
    private float mathMarks;
    private float nepaliMarks;
    private float scienceMarks;
    private float totalMarks;


    public StudentGradeModel(int fullMarks) {
   // public StudentGradeModel(int fullMarks, int passMarks) {
        this.fullMarks = fullMarks;
       // this.passMarks = passMarks;
        this.totalMarks = 0;
        this.scienceMarks = this.computerMarks = this.mathMarks = this.nepaliMarks = this.englishMarks = 0;
    }

    public int getFullMarks() {
        return fullMarks;
    }
/*public float getPassMarks() {
        return passMarks;
    }*/
    public float getComputerMarks() {
        return computerMarks;
    }

    public void setComputerMarks(float computerMarks) {
        this.computerMarks = computerMarks;
    }

    public float getEnglishMarks() {
        return englishMarks;
    }

    public void setEnglishMarks(float englishMarks) {
        this.englishMarks = englishMarks;
    }

    public float getMathMarks() {
        return mathMarks;
    }

    public void setMathMarks(float mathMarks) {
        this.mathMarks = mathMarks;
    }

    public float getNepaliMarks() {
        return nepaliMarks;
    }

    public void setNepaliMarks(float nepaliMarks) {
        this.nepaliMarks = nepaliMarks;
    }

    public float getScienceMarks() {
        return scienceMarks;
    }

    public void setScienceMarks(float scienceMarks) {
        this.scienceMarks = scienceMarks;
    }

    public float getTotalMarks() {
        this.totalMarks = this.scienceMarks + this.computerMarks + this.mathMarks + this.nepaliMarks + this.englishMarks;
        return totalMarks;
    }


    public String getGradeLevel() {
        if (this.totalMarks == 0) {
            return "Not defined";
        }

        float percent = this.totalMarks / 5;

        if (percent >= 90 && percent <= 100) {
            return "A+ , Outstanding";
        } else if (percent >= 80 && percent < 90) {
            return "A , Excellent";
        } else if (percent >= 70 && percent < 80) {
            return "B+ , Very Good";
        } else if (percent >= 60 && percent < 70) {
            return "B , Good";
        } else if (percent >= 50 && percent < 60) {
            return "C+ , Satisfactory";
        } else if (percent >= 40 && percent < 50) {
            return "C , Acceptable";
        } else if (percent >= 30 && percent < 40) {
            return "D+ , Partially Acceptable";
        } else if (percent >= 20 && percent < 30) {
            return "D , Insufficient";
        } else if (percent >= 0 && percent < 20) {
            return "E , Very Insufficient";
        }

        return "Not defined";
    }


    public String getAveragePercentage() {
        if (this.totalMarks <= 0) {
            return "0%";
        }

        return String.format("%.2f%%", this.totalMarks / 5); // format the number
    }
}

