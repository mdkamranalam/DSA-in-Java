package Basics.DigitManipulation;

public class StrongNumber {
    public static void main(String[] args) {
        int n = 145;

        System.out.println(isStrong(n) ? "True" : "False");
    }

    static boolean isStrong(int n) {
        int[] fact = {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880};
        int original = n;
        int sum = 0;

        while (n > 0) {
            int digit = n % 10;
            sum += fact[digit];
            n /= 10;
        }

        return sum == original;
    }
}
