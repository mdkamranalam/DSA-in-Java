package Basics.Loops;

public class FibonacciSeries {
    public static void main(String[] args) {
        int n = 10;
        int a = 0;
        int b = 1;

        for (int i = 0; i <= n; i++) {
            System.out.println("F(" + i + ") = "+ a);
            int next = a + b;
            a = b;
            b = next;
        }
    }
}
