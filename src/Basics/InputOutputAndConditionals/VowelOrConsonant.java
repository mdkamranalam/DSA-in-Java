package Basics.InputOutputAndConditionals;

public class VowelOrConsonant {
    public static void main(String[] args) {
        char ch = 'b';
        char lower = Character.toLowerCase(ch);

        if (checkVowelOrConsonant(lower))
            System.out.println(lower + " is vowel");
        else
            System.out.println(lower + " is consonant");
    }

    private static boolean checkVowelOrConsonant(char ch) {
        if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
            return true;
        }
        return false;
    }
}
