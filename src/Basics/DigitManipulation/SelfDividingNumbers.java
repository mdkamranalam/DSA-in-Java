package Basics.DigitManipulation;

import java.util.*;

public class SelfDividingNumbers {
    public static void main(String[] args) {
        int left = 1;
        int right = 22;

        System.out.println(selfDividingNumbers(left, right));
    }

    static List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> ans = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            if (check(i)) {
                ans.add(i);
            }
        }
        return ans;
    }
    static boolean check(int x) {
        for (int y = x; y > 10; y /= 10) {
            if (y % 10 == 0 || x % (y % 10) != 0) {
                return false;
            }
        }
        return true;
    }
}
