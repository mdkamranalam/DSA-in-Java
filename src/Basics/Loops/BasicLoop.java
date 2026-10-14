package Basics.Loops;

public class BasicLoop {
    public static void main(String[] args) {
        int n = 10;

        System.out.println("Increasing Order:");
        for (int i = 1; i <= n; i++) {
            System.out.println(i);
        }

        System.out.println("Decreasing Order:");
        for (int i = n; i > 0; i--) {
            System.out.println(i);
        }
    }
}
