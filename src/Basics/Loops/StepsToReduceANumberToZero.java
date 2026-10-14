package Basics.Loops;

public class StepsToReduceANumberToZero {
    public static void main(String[] args) {
        int num = 8;

        int result = numberOfSteps(num);
        System.out.println(result);
    }

    private static int numberOfSteps(int num) {
        int count = 0;
        while (num > 0) {
            if (num % 2 == 0) {
                num /= 2;
            } else {
                num -= 1;
            }
            count++;
        }
        return count;
    }
}
