package Stack.StackUsingArrayList;

import java.util.ArrayList;

public class Stack<T> {
    private final ArrayList<T> stackList = new ArrayList<>();

    public ArrayList<T> getStackList() {
        return stackList;
    }

    // Print Stack
    public void printStack() {
        for (int i = stackList.size() - 1; i >= 0; i--) {
            System.out.println(stackList.get(i));
        }
    }

    // Empty Stack
    public boolean isEmpty() {
        return stackList.size() == 0;
    }

    // Get Top or Peek
    public T peek() {
        if (isEmpty()) {
            return null;
        } else {
            return stackList.get(stackList.size() - 1);
        }
    }

    // Push
    public void push(T value) {
        stackList.add(value);
    }

    // Pop
    public T pop() {
        if (isEmpty()) return null;

        return stackList.remove(stackList.size() - 1);
    }
}
