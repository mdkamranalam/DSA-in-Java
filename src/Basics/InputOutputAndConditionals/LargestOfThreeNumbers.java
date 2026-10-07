package Basics.InputOutputAndConditionals;

public class LargestOfThreeNumbers {
    public static void main(String[] args) {
        int a = 10;
        int b = 20;
        int c = 30;

        int largest = Math.max(a, Math.max(b, c));

        if (largest == a) {
            System.out.println("a is bigger");
        } else if (largest == b) {
            System.out.println("b is bigger");
        } else {
            System.out.println("c is bigger");
        }

        System.out.println(largest + " is bigger");

        // a > b and a > c = a
        // a < b and b > c = b
        // a < b and b < c = c

//        if (a >= b && a >= c) {
//            System.out.println("a is bigger");
//        } else if (b >= a && b >= c){
//            System.out.println("b is bigger");
//        } else {
//            System.out.println("c is bigger");
//        }
    }
}
