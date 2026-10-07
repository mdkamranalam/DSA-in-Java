package Basics.InputOutputAndConditionals;

import java.util.Scanner;

public class ElectricityBillBySlabs {
    public static void main(String[] args) {
        System.out.println("***==========*** Electricity Bill by Slabs ***==========***");
        Scanner sc = new Scanner(System.in);
        System.out.print("Units: ");
        int units = sc.nextInt();

        System.out.println("Electricity Bill on " + units + " units is ₹" + calculateElectricityBill(units));
    }

    private static int calculateElectricityBill(int units) {
        int result = 1;
        if (units <= 100) {
            result = units * 5;
        } else if (units <= 200) {
            result = (100 * 5) + ((units - 100) * 7);
        } else {
            result = (100 * 5) + (100 * 7) + ((units - 200) * 10);
        }

        return result;
    }
}
