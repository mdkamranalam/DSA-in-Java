package Basics.Loops;

public class Factorial {
    public static void main(String[] args) {
        int n = 13;
        long fact = 1;

        for (int i = 1; i <= n; i++) {
            fact *= i;
        }

        System.out.println(fact);
    }
}
