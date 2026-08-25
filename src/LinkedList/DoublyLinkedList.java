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
        System.out.print("HEAD <-> ");
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

    // Get the Node of the particular Index
    public Node get(int index) {
        if (index < 0 || index >= length) return  null;
        Node temp = head;
        if (index < length/2) {
            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }
        } else {
            temp = tail;
            for (int i = length - 1; i > index; i--) {
                temp = temp.prev;
            }
        }

        return temp;
    }

    // Set the Node value at the particular Index
    public boolean set(int index, int value) {
        Node temp = get(index);
        if (temp != null) {
            temp.value = value;
            return true;
        }
        return false;
    }

    // Insert at Beginning
    public void prepend(int value) {
        Node newNode = new Node(value);
        if (length == 0) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        length++;
    }

    // Insert a new Node with a particular value at a particular index
    public boolean insert(int index, int value) {
        if (index < 0 || index > length) return false;
        if (index == 0) {
            prepend(value);
            return true;
        }
        if (index == length) {
            append(value);
            return true;
        }

        Node newNode = new Node(value);
        Node before = get(index - 1);
        Node after = before.next;

        newNode.prev = before;
        newNode.next = after;
        before.next = newNode;
        after.prev = newNode;
        length++;

        return true;
    }

    // Insert at End
    public void append(int value) {
        Node newNode = new Node(value);
        if (length == 0) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        }
        length++;
    }

    // Delete from the Beginning
//    public Node removeFirst() {}

    // Remove a Node at a particular Index
//    public boolean remove(int index) {}

    // Delete from End
//    public Node removeLast() {}
}
