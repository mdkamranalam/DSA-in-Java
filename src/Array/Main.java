package Array;

public class Main {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
        Easy arrayEasy = new Easy();

        System.out.print("Original Array: ");
        printArray(arr); // Print Array

        System.out.println("Length: " + length(arr));

        System.out.println("Sorted: " + arrayEasy.isSorted(arr)); // Check sorted array

        System.out.print("Reverse Array: "); // Reverse array
        arrayEasy.reverse(arr);
        printArray(arr);

        System.out.print("Reverse Array in Groups: "); // Reverse Array in Groups
        arrayEasy.reverseInGroups(arr, 3); // k = 3
        printArray(arr);

        System.out.print("Replace with adjacent multiplication: ");
        arrayEasy.replaceWithAdjacentMultiplication(arr); // Replace with adjacent multiplication
        printArray(arr);
    }

    // Print Array
    private static void printArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            if (i > 0) System.out.print(", ");
            System.out.print(arr[i]);
        }
        System.out.println("]");
    }

    // Length of Array
    private static int length(int[] arr) {
        return arr.length;
    }
}
