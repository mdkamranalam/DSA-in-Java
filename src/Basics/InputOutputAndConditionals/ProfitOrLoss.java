package Basics.InputOutputAndConditionals;

import java.util.Scanner;

public class ProfitOrLoss {
    public static void main(String[] args) {
        System.out.println("***==========*** Profit Or Loss ***==========***");
        Scanner sc = new Scanner(System.in);
        System.out.print("Cost Price: ");
        int cp = sc.nextInt();
        System.out.print("Selling Price: ");
        int sp = sc.nextInt();

        if (sp > cp) {
            int profit = sp - cp;
            System.out.println("Profit by ₹" + profit);
        } else {
            int loss = cp - sp;
            System.out.println("Loss by ₹" + loss);
        }
    }
}
