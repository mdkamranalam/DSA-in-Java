package Basics.InputOutputAndConditionals;

public class SimpleCalculator {
    public static void main(String[] args) {
        System.out.println("***==========*** SIMPLE CALCULATOR ***==========***");
        float num1 = 10;
        char op = '/';
        float num2 = 20;

        calculate(num1, num2, op);
        System.out.println("***==========***===================***==========***");
    }

    private static void calculate(float num1, float num2, char op) {
        switch (op) {
            case '+':
                System.out.println(num1 + num2);
                break;
            case '-':
                System.out.println(num1 - num2);
                break;
            case '*':
                System.out.println(num1 * num2);
                break;
            case '/':
                if (num2 == 0) {
                    System.out.println("Denominator is 0");
                    break;
                } else {
                    System.out.println(num1 / num2);
                }
                break;
            default:
                System.out.println("Invalid Operation");
                break;
        }
    }
}
