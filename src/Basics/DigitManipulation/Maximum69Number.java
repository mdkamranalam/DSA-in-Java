package Basics.DigitManipulation;

public class Maximum69Number {
    public static void main(String[] args) {
        int num = 9669;
        System.out.println(maximum69Number(num));
    }

    static int maximum69Number (int num) { // or return Integer.valueOf(String.valueOf(num).replaceFirst("6", "9"));
        char[] digits = String.valueOf(num).toCharArray();
        for (int i = 0; i < digits.length; i++) {
            if (digits[i] == '6') {
                digits[i] = '9';
                break;
            }
        }
        return Integer.parseInt(new String(digits));
    }
}
