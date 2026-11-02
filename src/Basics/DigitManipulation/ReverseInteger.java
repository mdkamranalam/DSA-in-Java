package Basics.DigitManipulation;

public class ReverseInteger {
    public static void main(String[] args) {
        int num = -123;
        System.out.println(reverse(num));
    }

    static int reverse(int num) {
        int rev = 0;
        while (num != 0) {
            if (rev > Integer.MAX_VALUE / 10 || rev < Integer.MIN_VALUE / 10) {
                return 0;
            }

            int digit = num % 10;
            rev = rev * 10 + digit;
            num /= 10;
        }
        return rev;
    }
}
