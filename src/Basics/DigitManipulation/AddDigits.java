package Basics.DigitManipulation;

public class AddDigits {
    public static void main(String[] args) {
        int num = 38;
        System.out.println(addDigits(num));
    }

    static int addDigits(int num) { // or { return 1 + (num - 1) % 9; }
        while (num >= 10) {
            int sum = 0;
            while (num > 0) {
                sum += num % 10;
                num /= 10;
            }
            num = sum;
        }
        return num;
    }
}
