package Basics.Loops;

public class SumOfNumbersInArrays {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        int n = arr.length;
        int sum = 0;

        // By loop
        for (int i = 0; i < n; i++) {
            sum += arr[i];
        }

        // sum = n * (n + 1) / 2; // By using formula

        System.out.println(sum);
    }
}
