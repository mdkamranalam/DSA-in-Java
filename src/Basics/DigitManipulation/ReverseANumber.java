package Basics.DigitManipulation;

public class ReverseANumber {
    public static void main(String[] args) {
        int n = 1203;
        System.out.println(reverseNumber(n));
    }

    static int reverseNumber(int n) {
        int rev = 0;
        while (n > 0) {
            int digit = n % 10;
            rev = rev * 10 + digit;
            n /= 10;
        }
        return rev;
    }
}
