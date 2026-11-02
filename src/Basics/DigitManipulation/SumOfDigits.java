package Basics.DigitManipulation;

public class SumOfDigits {
    public static void main(String[] args) {
        int n = 123;
        System.out.println(sumOfDigits(n));
    }

    static int sumOfDigits(int n) {
        int sum = 0;
        while (n > 0) {
            int digit = n % 10;
            sum += digit;
            n /= 10;
        }
        return sum;
    }
}
