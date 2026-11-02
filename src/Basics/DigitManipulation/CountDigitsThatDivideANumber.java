package Basics.DigitManipulation;

public class CountDigitsThatDivideANumber {
    public static void main(String[] args) {
        int num = 1248;
        System.out.println(countDigits(num));
    }

    static int countDigits(int num) {
        int ans = 0;
        for (int i = num; i > 0; i /= 10) {
            ans++;
        }
        return ans;
    }
}
