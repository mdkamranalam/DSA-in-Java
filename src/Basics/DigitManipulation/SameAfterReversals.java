package Basics.DigitManipulation;

public class SameAfterReversals {
    public static void main(String[] args) {
        int num = 526;
        System.out.println(isSameAfterReversals(num));
    }

    static boolean isSameAfterReversals(int num) {
        return num == 0 || num % 10 != 0;
    }
}
