package Stack;

public class Main {
    public static void main(String[] args) {
        System.out.println("***========== Stack ==========***");
        Stack myStack = new Stack(1);

        myStack.push(2);
        myStack.push(3);
        myStack.push(4);
        myStack.push(5);

        myStack.pop();

        myStack.printStack();
        myStack.getTop();
        myStack.getHeight();
        System.out.println("***===========================***");
    }
}
