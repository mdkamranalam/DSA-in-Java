package Basics.DigitManipulation;

public class CountDigitsOfANumber {
    public static void main(String[] args) {
        int n = 1;

        System.out.println(countDigits(n));
    }

    static int countDigits(int n) {
        if (n == 0) return 1;
        int count = 0;
        while (n > 0) {
            count++;
            n /= 10;
        }
        return count;
    }
}
