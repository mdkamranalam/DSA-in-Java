package Sorting.SelectionSort;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] arr = {4, 2, 6, 5, 1, 3};
        System.out.println("Original Array: " + Arrays.toString(arr));

        selectionSort(arr);
        System.out.println("Selection Sort: " + Arrays.toString(arr));
    }

    private static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            if (i != minIndex) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }
    }
}
