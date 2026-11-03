package Sorting.MergeSort;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] arr = {5, 4, 7, 1, 3, 2, 8, 6};
        int[] arr1 = {1, 3, 7, 8};
        int[] arr2 = {2, 4, 5, 6};
        System.out.println("Original Array: " + Arrays.toString(arr));
//        System.out.println(Arrays.toString(merge(arr1, arr2)));
        System.out.println("Merge Sort: " + Arrays.toString(mergeSort(arr)));
    }

    static int[] merge(int[] arr1, int[] arr2) {
        int[] combine = new int[arr1.length + arr2.length];
        int index = 0;
        int i = 0;
        int j = 0;
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] < arr2[j]) {
                combine[index] = arr1[i];
                index++;
                i++;
            } else {
                combine[index] = arr2[j];
                index++;
                j++;
            }
        }

        while (i < arr1.length) {
            combine[index] = arr1[i];
            index++;
            i++;
        }

        while (j < arr2.length) {
            combine[index] = arr2[j];
            index++;
            j++;
        }

        return combine;
    }

    static int[] mergeSort(int[] arr) {
        if (arr.length == 1) return arr;

        int minIndex = arr.length / 2;
        int[] left = mergeSort(Arrays.copyOfRange(arr, 0, minIndex));
        int[] right = mergeSort(Arrays.copyOfRange(arr, minIndex, arr.length));

        return merge(left, right);
    }
}
