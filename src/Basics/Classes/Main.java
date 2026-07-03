package Basics.Classes;

public class Main {
    public static void main(String[] args) {
        System.out.println("========== Employee List ==========");

        Employee kamran = new Employee("Md. Kamran Alam", 123, true, "SDE 1");
        Employee aman = new Employee("Aman", 321, false, "HR");

        kamran.displayInfo();
        System.out.println("===================================");
        aman.setIsLogin(true);
        aman.displayInfo();
        System.out.println("===================================");
    }
}


