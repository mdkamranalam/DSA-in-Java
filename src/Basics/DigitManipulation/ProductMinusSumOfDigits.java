package Basics.DigitManipulation;

public class ProductMinusSumOfDigits {
    public static void main(String[] args) {
        int n = 4421;

        System.out.println(subtractProductAndSum(n));
    }

    static int subtractProductAndSum(int n) {
        int product = 1;
        int sum = 0;
        while (n > 0) {
            int digit = n % 10;
            product *= digit;
            sum += digit;
            n /= 10;
        }
        return product - sum;
    }
}
