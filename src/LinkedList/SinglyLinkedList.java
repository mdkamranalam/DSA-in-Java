package LinkedList;

import java.util.HashSet;
import java.util.Set;

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
        System.out.print("HEAD -> ");
        while (temp != null) {
            System.out.print(temp.value + " -> ");
            temp = temp.next;
        }
        System.out.println("NULL");
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

    // Get value
    public Node get(int index) {
        if (index < 0 || index >= length) return null;
        Node temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    // Change value at given Index
    public boolean set(int index, int value) {
        Node temp = get(index);
        while (temp != null) {
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
            head = newNode;
        }
        length++;
    }

    // Insert at given index or position
    public boolean insert(int index, int value) {
        if (index < 0 || index >= length) return false;
        if (index == 0) {
            prepend(value);
            return true;
        }
        if (index == length) {
            append(value);
            return true;
        }
        Node newNode = new Node(value);
        Node temp = get(index - 1);
        newNode.next = temp.next;
        temp.next = newNode;
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
            tail.next = newNode;
            tail = newNode;
        }
        length++;
    }

    // Delete at Beginning
    public Node removeFirst() {
        if (length == 0) return null;
        Node temp = head;
        head = head.next;
        temp.next = null;
        length--;
        if (length == 0) {
            tail = null;
        }
        return temp;
    }

    // Delete from the given Index or Position
    public Node remove(int index) {
        if (index < 0 || index >= length) return null;
        if (index == 0) return removeFirst();
        if (index == length - 1) return removeLast();

        Node prev = get(index - 1);
        Node temp = prev.next;

        prev.next = temp.next;
        temp.next = null;
        length--;
        return temp;
    }

    // Delete from End
    public Node removeLast() {
        if (length == 0) return null;
        Node pre = head;
        Node temp = head;
        while (temp.next != null) {
            pre = temp;
            temp = temp.next;
        }
        tail = pre;
        tail.next = null;
        length--;
        if (length == 0) {
            head = null;
            tail = null;
        }
        return temp;
    }

    // Reverse Linked List
    public void reverse() {
        Node temp = head;
        head = tail;
        tail = temp;
        Node after = temp.next;
        Node before = null;
        for (int i = 0; i < length; i++) {
            after = temp.next;
            temp.next = before;
            before = temp;
            temp = after;
        }
    }

    /*
    * ========== EXERCISES ==========
    * */
    // Ex 1: Find Middle Node
    public Node findMiddleNode() {
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    // Ex 2: Has Loop
    public boolean hasLoop() {
        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                return true;
            }
        }

        return false;
    }

    // Ex 3: Find Kth Node From End
    public Node findKthFromEnd(int k) {
        if (k <= 0) return null;

        Node slow = head;
        Node fast = head;

        for (int i = 0; i < k; i++) {
            if (fast == null) return null;
            fast = fast.next;
        }

        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow;
    }

    // Ex 4: Remove Duplicates
//    public void removeDuplicates() { // Without using Set
//        Node current = head;
//
//        while (current != null) {
//            Node runner = current;
//
//            while (runner.next != null) {
//                if (runner.next.value == current.value) {
//                    runner.next = runner.next.next;
//                    length--;
//                } else {
//                    runner = runner.next;
//                }
//            }
//            current = current.next;
//        }
//    }

    public void removeDuplicates() { // Using Set
        Set<Integer> values = new HashSet<>();
        Node previous = null;
        Node current = head;

        while (current != null) {
            if (values.contains(current.value)) {
                previous.next = current.next;
                length--;
            } else {
                values.add(current.value);
                previous = current;
            }
            current = current.next;
        }
    }
}
