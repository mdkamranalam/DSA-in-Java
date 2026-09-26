package Queue;

public class Queue {
    private Node first;
    private Node last;
    private int length;

    // Constructor
    public Queue(int value) {
        Node newNode = new Node(value);
        first = newNode;
        last = newNode;
        length = 1;
    }

    // Print Queue
    public void printQueue() {
        Node temp = first;
        while (temp != null) {
            System.out.println(temp.value);
            temp = temp.next;
        }
    }

    // Get First
    public void getFirst() {
        System.out.println("First: " + first.value);
    }

    // Get Last
    public void getLast() {
        System.out.println("Last: " + last.value);
    }

    // Get Length
    public void getLength() {
        System.out.println("Length: " + length);
    }

    // Enqueue
    public void enqueue(int value) {
        Node newNode = new Node(value);
        if (length == 0) {
            first = newNode;
            last = newNode;
        } else {
            last.next = newNode;
            last = newNode;
        }
        length++;
    }

    // Dequeue
    public Node dequeue() {
        if (length == 0) return null;
        Node temp = first;
        if (length == 1) {
            first = null;
            last = null;
        } else {
            first = first.next;
            temp.next = null;
        }
        length--;
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
