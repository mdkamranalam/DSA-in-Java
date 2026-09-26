package Stack;

public class Stack {
    private Node top;
    private int height;

    // Constructor
    public Stack(int value) {
        Node newNode = new Node(value);
        top = newNode;
        height = 1;
    }

    // Print stack
    public void printStack() {
        Node temp = top;
        while (temp != null) {
            System.out.println(temp.value);
            temp = temp.next;
        }
    }

    // Get Top
    public void getTop() {
        System.out.println("Top: " + top.value);
    }

    // Get Height
    public void getHeight() {
        System.out.println("Height: " + height);
    }

    // Push
    public void push(int value) {
        Node newNode = new Node(value);
        if (height == 0) {
            top = newNode;
        } else {
            newNode.next = top;
            top = newNode;
        }

        height++;
    }

    // Pop
    public Node pop() {
        if (height == 0) return null;

        Node temp = top;
        top = top.next;
        temp.next = null;
        height--;

        return temp;
    }

    // Node
    class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
        }
    }
}
