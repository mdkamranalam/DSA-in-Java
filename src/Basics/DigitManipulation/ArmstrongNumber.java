package Basics.DigitManipulation;

public class ArmstrongNumber {
    public static void main(String[] args) {
        int n = 152;

        if (armstrong(n))
            System.out.println("True");
        else
            System.out.println("False");
    }

    static boolean armstrong(int n) {
        String number = Integer.toString(n);
        int length = number.length();
        int output = 0;

        for (char c : number.toCharArray()) {
            output += (int) Math.pow(c - '0', length);
        }

        return output == Integer.parseInt(number);
    }
}
