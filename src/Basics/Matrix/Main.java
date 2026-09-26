package Basics.Matrix;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[][] arr = {
                {77, 11, 22, 3},
                {11, 89, 1, 12},
                {32, 11, 56, 7},
                {11, 22, 44, 33}
        };

        System.out.print("Original Matrix: ");
        printMatrix(arr);

        System.out.print("\nRow wise sorted Matrix: ");
        rowWiseSort(arr);
        printMatrix(arr);

        System.out.print("\nColumn wise sorted Matrix: ");
        arr = colWiseSort(arr);
        printMatrix(arr);

        System.out.print("\nSearch result: ");

        if (searchInMatrix(arr, 50)) {
            System.out.println("FOUND");
        } else {
            System.out.println("NOT FOUND");
        }

    }

    /*
     *   Print Matrix
     *       Time Complexity: O(row x col)
     *       Space Complexity: O(1)
     * */
    private static void printMatrix(int[][] arr) {
        int row = arr.length;
        int col = arr[0].length;

        System.out.println("[");
        for (int i = 0; i < row; i++) {
            System.out.print("    [");
            for (int j = 0; j < col; j++) {
                if (j > 0) System.out.print(", ");
                System.out.print(arr[i][j]);
            }
            System.out.println("]");
        }
        System.out.println("]");
    }

    /*
     *   Search in Matrix
     *       Time Complexity: O(row x col)
     *       Space Complexity: O(1)
     * */
    private static boolean searchInMatrix(int[][] arr, int x) {
        int row = arr.length;
        int col = arr[0].length;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (arr[i][j] == x) {
                    return true;
                }
            }
        }
        return false;
    }

    /*
     *   Transpose
     *       Time Complexity: O(row x col)
     *       Space Complexity: O(row x col)
     * */
    private static int[][] transpose(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;

        int[][] tr = new int[col][row];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                tr[j][i] = matrix[i][j];
            }
        }

        return tr;
    }

    /*
     *   Row wise Sorting
     *       Time Complexity: O(row x col log col)
     *       Space Complexity: depends on Arrays.sort()
     * */
    private static void rowWiseSort(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            Arrays.sort(arr[i]);
        }
    }

    /*
     *   Column wise Sorting
     *       Time Complexity: O(row x col log row)
     *       Space Complexity: O(row x col)
     * */
    private static int[][] colWiseSort(int[][] matrix) {
        int[][] transposed = transpose(matrix);
        rowWiseSort(transposed);
        return transpose(transposed);
    }
}
