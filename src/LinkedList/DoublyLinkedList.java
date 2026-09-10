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

        // Node Constructor
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
    public Node removeFirst() {
        if (length == 0) return null;
        Node temp = head;
        if (length == 1) {
            head = null;
            tail = null;
        } else {
            head = head.next;
            head.prev = null;
            temp.next = null;
        }
        length--;
        return temp;
    }

    // Remove a Node at a particular Index
    public Node remove(int index) {
        if (index < 0 || index >= length) return null;
        if (index == 0) return removeFirst();
        if (index == length - 1) return removeLast();

        Node temp = get(index);
        temp.next.prev = temp.prev;
        temp.prev.next = temp.next;
        temp.next = null;
        temp.prev = null;
        length--;

        return temp;
    }

    // Delete from End
    public Node removeLast() {
        if (length == 0) return null;
        Node temp = tail;
        if (length == 1) {
            head = null;
            tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
            temp.prev = null;
        }
        length--;
        return temp;
    }

    /*
     * ========== EXERCISES ==========
     * */
    // Ex 1: Palindrome Checker
    public boolean isPalindrome() {
        if (length <= 1) return true;

        Node forwardNode = head;
        Node backwardNode = tail;

        for (int i = 0; i < length/2; i++) {
            if (forwardNode.value != backwardNode.value) {
                return false;
            }
            forwardNode = forwardNode.next;
            backwardNode = backwardNode.prev;
        }
        return true;
    }

    // Ex 2: Reverse
    public void reverse() {
        Node curr = head;
        Node temp = null;

        while (curr != null) {
            temp = curr.prev;
            curr.prev = curr.next;
            curr.next = temp;
            curr = curr.prev;
        }

        temp = head;
        head = tail;
        tail = temp;
    }

    // Ex 3: Partition List
    public void partitionList(int x) {
        if (head == null) return;

        Node dummy1 = new Node(0);
        Node dummy2 = new Node(0);
        Node prev1 = dummy1;
        Node prev2 = dummy2;
        Node curr = head;

        while (curr != null) {
            if (curr.value < x) {
                prev1.next = curr;
                curr.prev = prev1;
                prev1 = curr;
            } else {
                prev2.next = curr;
                curr.prev = prev2;
                prev2 = curr;
            }

            curr = curr.next;
        }

        prev2.next = null;
        prev1.next = dummy2.next;

        if (dummy2.next != null) {
            dummy2.next.prev = prev1;
        }

        head = dummy1.next;

        if (head != null) {
            head.prev = null;
        }
    }

    // Ex 4: Reverse Between
    public void reverseBetween(int startIndex, int endIndex) {
        if (head == null || startIndex == endIndex) return;

        Node dummyNode = new Node(0);
        dummyNode.next = head;
        head.prev = dummyNode;
        Node previousNode = dummyNode;

        for (int i = 0; i < startIndex; i++) {
            previousNode = previousNode.next;
        }

        Node currentNode = previousNode.next;

        for (int i = 0; i < endIndex - startIndex; i++) {
            Node nodeToMove = currentNode.next;
            currentNode.next = nodeToMove.next;

            if (nodeToMove.next != null) {
                nodeToMove.next.prev = currentNode;
            }

            nodeToMove.next = previousNode.next;
            previousNode.next.prev = nodeToMove;

            previousNode.next = nodeToMove;
            nodeToMove.prev = previousNode;
        }

        head = dummyNode.next;
        head.prev = null;
    }
}
