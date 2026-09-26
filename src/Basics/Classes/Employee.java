package Basics.Classes;

public class Employee {
    private final int id;
    private final String position;
    private final String name;
    private boolean isLogin;

    // Constructor
    public Employee(String name, int id, boolean isLogin, String position) {
        this.name = name;
        this.id = id;
        this.isLogin = isLogin;
        this.position = position;
    }

    public void displayInfo() {
        System.out.println("Name: " + this.name +
                "\nID: " + this.id +
                "\nisLogin: " + this.isLogin +
                "\nPosition: " + this.position);
    }

    // Getters and Setters
    public boolean getIsLogin() {
        return this.isLogin;
    }

    public void setIsLogin(boolean isLogin) {
        this.isLogin = isLogin;
    }
}
