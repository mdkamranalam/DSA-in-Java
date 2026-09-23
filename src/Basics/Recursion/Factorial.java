package Basics.Recursion;

import java.util.Scanner;

public class Factorial {
    public static void main(String[] args) {
        System.out.println("***========== FACTORIAL ==========***");
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter an Integer: ");
        int n = sc.nextInt();

        int result = factorial(n);

        System.out.println("Factorial of " + n + " : " + result);
        System.out.println("***===============================***");
    }

    public static int factorial(int n) {
        if (n == 1) return 1;
        return n * factorial(n - 1);
    }
}
