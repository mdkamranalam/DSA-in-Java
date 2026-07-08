package LinkedList;

public class SinglyLinkedList {
    private Node head;
    private Node tail;
    private int length;

    // Node class
    class Node {
        int value;
        Node next;

        // Node Constructor
        Node(int value) {
            this.value = value;
            this.next = null;
        }
    }

    // Constructor Singly Linked List
    public SinglyLinkedList(int value) {
        Node newNode = new Node(value);
        head = newNode;
        tail = newNode;
        length = 1;
    }

    // Print the Linked List
    public void printList() {
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.value);
            temp = temp.next;
        }
    }

    // Get the value of head
    public void getHead() {
        System.out.println("Head: " + head.value);
    }

    // Get the value of tail
    public void getTail() {
        System.out.println("Tail: " + tail.value);
    }

    // Get the length of the Linked List
    public void getLength() {
        System.out.println("Length: " + length);
    }
}
