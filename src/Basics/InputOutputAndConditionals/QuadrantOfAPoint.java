package Basics.InputOutputAndConditionals;

public class QuadrantOfAPoint {
    public static void main(String[] args) {
        int x = -10;
        int y = -10;

        if (x > 0 && y > 0) {
            System.out.println("1st Quadrant");
        } else if (x < 0 && y > 0) {
            System.out.println("2nd Quadrant");
        } else if (x < 0 && y < 0) {
            System.out.println("3rd Quadrant");
        } else {
            System.out.println("4th Quadrant");
        }
    }
}
