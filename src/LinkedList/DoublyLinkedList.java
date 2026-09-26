package LinkedList;

public class DoublyLinkedList {

    private Node head;
    private Node tail;
    private int length;

    // ==================== NODE ====================

    public DoublyLinkedList() {
        head = null;
        tail = null;
        length = 0;
    }

    // ==================== CONSTRUCTOR ====================

    public DoublyLinkedList(int value) {
        Node newNode = new Node(value);

        head = newNode;
        tail = newNode;
        length = 1;
    }

    public void printList() {
        Node temp = head;

        System.out.print("HEAD <-> ");

        while (temp != null) {
            System.out.print(temp.value + " <-> ");
            temp = temp.next;
        }

        System.out.println("NULL");
    }

    // ==================== PRINT ====================

    public void getHead() {
        if (head == null) {
            System.out.println("Head: null");
            return;
        }

        System.out.println("Head: " + head.value);
    }

    // ==================== GETTERS ====================

    public void getTail() {
        if (tail == null) {
            System.out.println("Tail: null");
            return;
        }

        System.out.println("Tail: " + tail.value);
    }

    public void getLength() {
        System.out.println("Length: " + length);
    }

    // Get Node at a particular index
    public Node get(int index) {

        if (index < 0 || index >= length) {
            return null;
        }

        // Search from head
        if (index < length / 2) {

            Node temp = head;

            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }

            return temp;
        }

        // Search from tail
        Node temp = tail;

        for (int i = length - 1; i > index; i--) {
            temp = temp.prev;
        }

        return temp;
    }

    // ==================== GET ====================

    // Set value at a particular index
    public boolean set(int index, int value) {

        Node temp = get(index);

        if (temp == null) {
            return false;
        }

        temp.value = value;
        return true;
    }

    // ==================== SET ====================

    // Insert at beginning
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

    // ==================== PREPEND ====================

    // Insert at a particular index
    public boolean insert(int index, int value) {

        if (index < 0 || index > length) {
            return false;
        }

        // Insert at beginning
        if (index == 0) {
            prepend(value);
            return true;
        }

        // Insert at end
        if (index == length) {
            append(value);
            return true;
        }

        Node newNode = new Node(value);

        Node before = get(index - 1);
        Node after = before.next;

        // Connect new node with previous node
        newNode.prev = before;
        before.next = newNode;

        // Connect new node with next node
        newNode.next = after;
        after.prev = newNode;

        length++;

        return true;
    }

    // ==================== INSERT ====================

    // Insert at end
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

    // ==================== APPEND ====================

    // Delete from beginning
    public Node removeFirst() {

        if (length == 0) {
            return null;
        }

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

    // ==================== REMOVE FIRST ====================

    // Remove Node at a particular index
    public Node remove(int index) {

        if (index < 0 || index >= length) {
            return null;
        }

        if (index == 0) {
            return removeFirst();
        }

        if (index == length - 1) {
            return removeLast();
        }

        Node temp = get(index);

        Node before = temp.prev;
        Node after = temp.next;

        before.next = after;
        after.prev = before;

        temp.prev = null;
        temp.next = null;

        length--;

        return temp;
    }

    // ==================== REMOVE ====================

    // Delete from end
    public Node removeLast() {

        if (length == 0) {
            return null;
        }

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

    // ==================== REMOVE LAST ====================

    public boolean isPalindrome() {

        if (length <= 1) {
            return true;
        }

        Node forwardNode = head;
        Node backwardNode = tail;

        for (int i = 0; i < length / 2; i++) {

            if (forwardNode.value != backwardNode.value) {
                return false;
            }

            forwardNode = forwardNode.next;
            backwardNode = backwardNode.prev;
        }

        return true;
    }

    // ============================================================
    //                         EXERCISES
    // ============================================================

    // ==================== EX 1: PALINDROME ====================

    public void reverse() {

        if (length <= 1) {
            return;
        }

        Node current = head;

        while (current != null) {

            // Save old previous node
            Node temp = current.prev;

            // Swap prev and next
            current.prev = current.next;
            current.next = temp;

            // Move to the next node in the original direction
            current = current.prev;
        }

        // Swap head and tail
        Node temp = head;
        head = tail;
        tail = temp;
    }

    // ==================== EX 2: REVERSE ====================

    public void partitionList(int x) {

        if (length <= 1) {
            return;
        }

        /*
         * dummy1 -> nodes smaller than x
         * dummy2 -> nodes greater than or equal to x
         */

        Node dummy1 = new Node(0);
        Node dummy2 = new Node(0);

        Node prev1 = dummy1;
        Node prev2 = dummy2;

        Node current = head;

        while (current != null) {

            // Save next before changing links
            Node next = current.next;

            // Detach current node
            current.next = null;
            current.prev = null;

            if (current.value < x) {

                prev1.next = current;
                current.prev = prev1;

                prev1 = current;

            } else {

                prev2.next = current;
                current.prev = prev2;

                prev2 = current;
            }

            current = next;
        }

        // End second partition
        prev2.next = null;

        // Connect first partition to second partition
        prev1.next = dummy2.next;

        if (dummy2.next != null) {
            dummy2.next.prev = prev1;
        }

        // Update head
        head = dummy1.next;

        // First real node must not point to dummy1
        if (head != null) {
            head.prev = null;
        }

        // Update tail
        if (prev2 != dummy2) {
            tail = prev2;
        } else {
            tail = prev1;
        }
    }

    // ==================== EX 3: PARTITION ====================

    public void reverseBetween(int startIndex, int endIndex) {

        if (length <= 1) {
            return;
        }

        if (startIndex < 0 ||
                endIndex >= length ||
                startIndex >= endIndex) {
            return;
        }

        /*
         * Dummy node makes it easier to handle
         * reversal beginning at index 0.
         */

        Node dummyNode = new Node(0);

        dummyNode.next = head;
        head.prev = dummyNode;

        Node previousNode = dummyNode;

        // Move previousNode to node before startIndex
        for (int i = 0; i < startIndex; i++) {
            previousNode = previousNode.next;
        }

        Node currentNode = previousNode.next;

        /*
         * Move each following node to the
         * front of the reversed section.
         */

        for (int i = 0; i < endIndex - startIndex; i++) {

            Node nodeToMove = currentNode.next;

            // Remove nodeToMove from its current position
            currentNode.next = nodeToMove.next;

            if (nodeToMove.next != null) {
                nodeToMove.next.prev = currentNode;
            }

            // Insert nodeToMove after previousNode
            nodeToMove.next = previousNode.next;
            previousNode.next.prev = nodeToMove;

            previousNode.next = nodeToMove;
            nodeToMove.prev = previousNode;
        }

        // Update head
        head = dummyNode.next;
        head.prev = null;

        // Update tail
        tail = head;

        while (tail.next != null) {
            tail = tail.next;
        }
    }

    // ==================== EX 4: REVERSE BETWEEN ====================

    public void swapNodePairs() {

        if (length <= 1) {
            return;
        }

        Node dummyNode = new Node(0);

        dummyNode.next = head;
        head.prev = dummyNode;

        Node previousNode = dummyNode;

        while (previousNode.next != null &&
                previousNode.next.next != null) {

            Node firstNode = previousNode.next;
            Node secondNode = firstNode.next;

            // Connect previous node -> second node
            previousNode.next = secondNode;
            secondNode.prev = previousNode;

            // Connect second node -> first node
            secondNode.next = firstNode;
            firstNode.prev = secondNode;

            // Connect first node -> remaining list
            firstNode.next = secondNode.next;

            if (firstNode.next != null) {
                firstNode.next.prev = firstNode;
            }

            // Move to the end of this swapped pair
            previousNode = firstNode;
        }

        // Update head
        head = dummyNode.next;
        head.prev = null;

        // Update tail
        tail = head;

        while (tail.next != null) {
            tail = tail.next;
        }
    }

    // ==================== EX 5: SWAP PAIRS ====================

    class Node {
        int value;
        Node next;
        Node prev;

        Node(int value) {
            this.value = value;
        }
    }
}