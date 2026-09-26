package Array;

public class Easy {

    /*
     *   Check Sorted Array
     *       Time Complexity: O(n)
     *       Space Complexity: O(1)
     * */
    public boolean isSorted(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            if (arr[i - 1] > arr[i]) return false;
        }
        return true;
    }

    /*
     *   Replace with Adjacent Multiplication
     *       Time Complexity: O(n)
     *       Space Complexity: O(1)
     * */
    public void replaceWithAdjacentMultiplication(int[] arr) {
        int n = arr.length;
        int prev = 1;
        for (int i = 0; i < n; i++) {
            int curr = arr[i];
            int next = (i == n - 1) ? 1 : arr[i + 1];
            arr[i] = prev * curr * next;
            prev = curr;
        }
    }

    /*
     *   Reverse with Two Pointers
     *       Time Complexity: O(n)
     *       Space Complexity: O(1)
     * */
    public void reverse(int[] arr) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;

            left++;
            right--;
        }
    }

    /*
     *   Reverse in groups
     *       Time Complexity: O(n)
     *       Space Complexity: O(1)
     * */
    public void reverseInGroups(int[] arr, int k) {
        int n = arr.length;

        for (int i = 0; i < n; i += k) {
            int left = i;
            int right = Math.min(i + k - 1, n - 1);

            while (left < right) {
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;

                left++;
                right--;
            }
        }
    }
}
