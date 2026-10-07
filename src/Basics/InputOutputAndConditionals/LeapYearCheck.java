package Basics.InputOutputAndConditionals;

public class LeapYearCheck {
    public static void main(String[] args) {
        int year = 1900;

        if (checkLeapYear(year))
            System.out.println(year + " is leap year");
        else
            System.out.println(year + " is not leap year");
    }

    private static boolean checkLeapYear(int year) {
        return (year % 400 == 0) || (year % 4 == 0 && year % 100 != 0);
    }
}

// Test Cases:
//          1900 -> not leap year
//          2000 -> leap year
//          2023 -> not leap year
//          2024 -> leap year
