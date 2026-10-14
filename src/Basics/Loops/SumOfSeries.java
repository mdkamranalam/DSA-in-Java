package Basics.Loops;

public class SumOfSeries {
    public static void main(String[] args) {
        int n = 10;
        double sum = 0;

        for (int i = 1; i <= n; i++) {
            sum += 1.0 / i;
        }

        System.out.println(sum);
    }
}

/*
* Time complexity: O(n)
* Space complexity: O(1)
* */
