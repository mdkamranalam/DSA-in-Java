package Basics.InputOutputAndConditionals;

public class PositiveNegativeOrZero {
    public static void main(String[] args) {
        int n = -10;

        if (n > 0) {
            System.out.println("Positive");
        } else if (n < 0) {
            System.out.println("Negative");
        } else {
            System.out.println("Zero");
        }
    }
}
