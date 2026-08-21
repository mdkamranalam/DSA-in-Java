package LinkedList;

public class DoublyLinkedList {
    private Node head;
    private Node tail;
    private int length;

    // Node class
    class Node {
        int value;
        Node next;
        Node prev;

        // Node Constructore
        Node(int value) {
            this.value = value;
        }
    }

    // Constructor Doubly Linked List
    public DoublyLinkedList(int value) {
        Node newNode = new Node(value);
        head = newNode;
        tail = newNode;
        length = 1;
    }

    // Print List
    public void printList() {
        Node temp = head;
        System.out.print("HEAD -> ");
        while (temp != null) {
            System.out.print(temp.value + " <-> ");
            temp = temp.next;
        }
        System.out.println(" NULL");
    }

    // Get Head
    public void getHead() {
        System.out.println("Head: " + head.value);
    }

    // Get Tail
    public void getTail() {
        System.out.println("Tail: " + tail.value);
    }

    // Get length
    public void getLength() {
        System.out.println("Length: " + length);
    }
}
