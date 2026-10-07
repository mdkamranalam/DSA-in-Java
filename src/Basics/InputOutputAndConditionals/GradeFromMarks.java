package Basics.InputOutputAndConditionals;

public class GradeFromMarks {
    public static void main(String[] args) {
        int marks = 55;

        if (marks < 0 || marks > 100) {
            System.out.println("Invalid marks");
        } else if (marks >= 90) {
            System.out.println("A");
        } else if (marks >= 75) {
            System.out.println("B");
        } else if (marks >= 60) {
            System.out.println("C");
        } else if (marks >= 40) {
            System.out.println("D");
        } else {
            System.out.println("FAIL");
        }
    }
}
