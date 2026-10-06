package HashTable.Practice;

import java.util.*;

public class FirstNonRepeatingChar {
    public static void main(String[] args) {
        String str = "swiss";
        System.out.println(firstNonRepeatingChar(str));
    }

    public static Character firstNonRepeatingChar(String str) {
        int n = str.length();
        Map<Character, Integer> charCounts = new HashMap<>();

        for (int i = 0; i < n; i++) {
            char ch = str.charAt(i);
            charCounts.put(ch, charCounts.getOrDefault(ch, 0) + 1);
        }

        for (int i = 0; i < n; i++) {
            char ch = str.charAt(i);
            if (charCounts.get(ch) == 1) {
                return ch;
            }
        }

        return null;
    }
}
