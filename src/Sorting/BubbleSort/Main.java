package Sorting.BubbleSort;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] arr = {4, 2, 6, 5, 1, 3};
        System.out.println("Original Array: " + Arrays.toString(arr));

        bubbleSort(arr);
        System.out.println("Bubble Sort: " + Arrays.toString(arr));
    }

    private static void bubbleSort(int[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }
}
