package Basics.InputOutputAndConditionals;

public class EvenOrOdd {
    public static void main(String[] args) {
        int num = 10;

        if (checkEvenOrOdd(num)) {
            System.out.println(num + " is even");
        } else {
            System.out.println(num + " is odd");
        }
    }

    // Normal method
//    private static boolean checkEvenOrOdd(int num) {
//        return num % 2 == 0;
//    }

    // Bitwise method
    private static boolean checkEvenOrOdd(int num) {
        return (num & 1) == 0;
    }
}
