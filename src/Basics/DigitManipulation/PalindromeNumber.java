package Basics.DigitManipulation;

public class PalindromeNumber {
    public static void main(String[] args) {
        int n = 1010;

        if (isPalindrome(n)) {
            System.out.println(n + " is Palindrome number.");
        } else {
            System.out.println(n + " is not Palindrome number.");
        }
    }

    static boolean isPalindrome(int n) {
        if (n < 0 || (n % 10 == 0 && n != 0)) return false;

        int rev = 0;
        while (n > rev) {
            rev = rev * 10 + (n % 10);
            n /= 10;
        }

        return n == rev || n == rev / 10;
    }
}
