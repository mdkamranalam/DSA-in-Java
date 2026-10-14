package Basics.Loops;

public class CountOddNumbersInARange {
    public static void main(String[] args) {
        int low = 3;
        int high = 10;

        int result = countOddByFormula(low, high);
        System.out.println("Count: " + result);
    }

    // Using Loop
    // Time Complexity: O(high - low + 1)
    // Space Complexity: O(1)
    private static int countOdd(int low, int high) {
        int count = 0;
        for (int i = low; i <= high; i++) {
            if (i % 2 != 0) {
                System.out.println("Odd Numbers: " + i);
                count++;
            }
        }

        return count;
    }

    // By using formula
    // Time Complexity: O(1)
    private static int countOddByFormula(int low, int high) {
        return (high + 1) / 2 - low / 2; // Or Bitwise -> ((high + 1) >> 1) - (low >> 1)
    }
}
