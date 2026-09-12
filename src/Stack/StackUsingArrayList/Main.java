package Stack.StackUsingArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("***========== Stack Using ArrayList ==========***");
        Stack<Integer> stack = new Stack<>();

        stack.push(10);
        stack.push(20);
        stack.push(30);

        stack.pop();

        stack.printStack();
        System.out.println("***===========================================***");
    }
}
