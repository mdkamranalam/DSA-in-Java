package Basics.InputOutputAndConditionals;

public class SwapTwoNumbers {
    public static void main(String[] args) {
        int a = 5;
        int b = 10;

        System.out.println("Original: a = " + a + " and b = " + b);

        swapWithTemp(a, b);
        swapWithoutVariable(a, b);
        swapWithXOR(a, b);
    }

    // With Temp variable
    private static void swapWithTemp(int a, int b) {
        int temp = a;
        a = b;
        b = temp;

        System.out.println("Swap with temp variable: a = " + a + " and b = " + b);
    }

    // Without any Third Variable
    private static void swapWithoutVariable(int a, int b) {
        a = a + b; // a = 5 + 10 -> a = 15
        b = a - b; // b = 15 - 10 -> b = 5
        a = a - b; // a = 15 - 5 -> a = 10
        System.out.println("Swap without third variable: a = " + a + " and b = " + b);
    }

    // Using XOR method
    private static void swapWithXOR(int a, int b) {
        a = a ^ b;
        b = b ^ a;
        a = a ^ b;
        System.out.println("Swap XOR method: a = " + a + " and b = " + b);
    }
}
