package HashTable.Practice;

import java.util.HashMap;

public class ItemInCommon {
    public static void main(String[] args) {
        int[] arr1 = {1, 3, 5};
        int[] arr2 = {2, 4, 5};

        System.out.println(itemInCommon(arr1, arr2));
    }

    private static boolean itemInCommon(int[] arr1, int[] arr2) {
        HashMap<Integer, Boolean> myHashMap = new HashMap<>();

        for (int i : arr1) {
            myHashMap.put(i, true);
        }

        for (int j : arr2) {
            if (myHashMap.get(j) != null) return true;
        }

        return false;
    }
}
