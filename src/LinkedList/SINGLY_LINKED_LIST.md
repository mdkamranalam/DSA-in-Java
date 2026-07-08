# Foundation of Singly Linked List
```java
package LinkedList;

/**
 * ============================================================================
 *                           SINGLY LINKED LIST
 * ============================================================================
 *
 * WHAT IS A LINKED LIST?
 * ----------------------
 * A Linked List is a linear data structure where elements (called Nodes)
 * are connected using references (links).
 *
 * Unlike an Array:
 *
 * Array
 * -----
 * Index
 *  0   1   2   3
 * +---+---+---+---+
 * |10 |20 |30 |40 |
 * +---+---+---+---+
 *
 * • Stored in contiguous memory.
 * • Fast random access.
 * • Difficult to insert/delete in the middle.
 *
 *
 * Linked List
 * -----------
 *
 * Head
 *  ↓
 * +------+      +------+      +------+
 * | 10 | o----->| 20 | o----->| 30 | o-----> NULL
 * +------+      +------+      +------+
 *                                      ↑
 *                                    Tail
 *
 * Every node stores:
 *
 * 1. Data (value)
 * 2. Address of the next node
 *
 * Therefore, nodes do NOT need contiguous memory.
 *
 * ============================================================================
 *
 * WHY USE A LINKED LIST?
 * ----------------------
 *
 * ✔ Dynamic Size
 * ✔ Fast insertion
 * ✔ Fast deletion
 * ✔ No memory shifting required
 *
 * ============================================================================
 *
 * DISADVANTAGES
 * -------------
 *
 * ✘ No direct indexing like arrays.
 * ✘ Extra memory for storing next pointer.
 * ✘ Slower traversal because of pointer chasing.
 *
 * ============================================================================
 *
 * TIME COMPLEXITY TABLE
 * ---------------------
 *
 * Operation              Time
 * ------------------------------
 * Append                 O(1)
 * Prepend                O(1)
 * Get                    O(n)
 * Set                    O(n)
 * Insert                 O(n)
 * Remove                 O(n)
 * Remove First           O(1)
 * Remove Last            O(n)
 * Reverse                O(n)
 * Size                   O(1)
 *
 * ============================================================================
 */

public class SinglyLinkedList {

    /**
     * Head always points to the first node.
     *
     * Example
     *
     * head
     *  ↓
     * 10 -> 20 -> 30 -> NULL
     */
    private Node head;

    /**
     * Tail always points to the last node.
     *
     * Example
     *
     * head
     *  ↓
     * 10 -> 20 -> 30 -> NULL
     *                  ↑
     *                tail
     *
     * Why store tail?
     *
     * Without tail:
     * Every append operation would need to traverse
     * the entire list.
     *
     * Time = O(n)
     *
     * With tail:
     *
     * Append happens instantly.
     *
     * Time = O(1)
     */
    private Node tail;

    /**
     * Stores the current number of nodes.
     *
     * Why keep length?
     *
     * Without length:
     *
     * 10 -> 20 -> 30 -> 40
     *
     * To know size,
     * we'd count every node.
     *
     * O(n)
     *
     * Instead,
     *
     * length = 4
     *
     * O(1)
     */
    private int length;

    /**
     * =========================================================================
     * NODE
     * =========================================================================
     *
     * A Node is the fundamental building block of a Linked List.
     *
     * Every node stores:
     *
     * +----------------------+
     * | value | next address |
     * +----------------------+
     *
     * Example
     *
     * +---------+      +---------+
     * | 10 |  o------->| 20 |NULL |
     * +---------+      +---------+
     *
     * The "next" variable stores the reference of the next node.
     *
     * If next == null
     *
     * it means this node is the last node.
     */
    private class Node {

        /** Actual data stored inside the node. */
        int value;

        /** Reference to the next node. */
        Node next;

        /**
         * Creates a new node.
         *
         * Initially,
         *
         * value = given value
         * next = null
         *
         * Because the node is not connected
         * to any other node yet.
         */
        Node(int value) {
            this.value = value;
            this.next = null;
        }
    }

    /**
     * =========================================================================
     * CONSTRUCTOR
     * =========================================================================
     *
     * Creates a Linked List containing exactly ONE node.
     *
     * Example
     *
     * new SinglyLinkedList(10)
     *
     * Result
     *
     * head
     *  ↓
     * +------+
     * | 10 |NULL
     * +------+
     *  ↑
     * tail
     *
     * length = 1
     *
     * Time Complexity : O(1)
     * Space Complexity: O(1)
     */
    public SinglyLinkedList(int value) {

        Node newNode = new Node(value);

        head = newNode;
        tail = newNode;

        length = 1;
    }

    /**
     * =========================================================================
     * PRINT LINKED LIST
     * =========================================================================
     *
     * Traverses the Linked List from Head to Tail
     * and prints every node.
     *
     * Example
     *
     * HEAD -> 10 -> 20 -> 30 -> NULL
     *
     * Traversal
     *
     * temp
     *  ↓
     * 10 -> 20 -> 30
     *
     * Step 1
     * Print 10
     *
     * Step 2
     * Move temp
     *
     * temp = temp.next
     *
     * Step 3
     * Print 20
     *
     * Continue until temp becomes NULL.
     *
     * Time Complexity : O(n)
     * Space Complexity: O(1)
     */
    public void printList() {

        Node temp = head;

        System.out.print("HEAD -> ");

        while (temp != null) {

            System.out.print(temp.value + " -> ");

            temp = temp.next;
        }

        System.out.println("NULL");
    }

    /**
     * Returns true if the Linked List contains no nodes.
     *
     * Why use this?
     *
     * Instead of writing:
     *
     * if(length == 0)
     *
     * everywhere,
     *
     * we simply write:
     *
     * if(isEmpty())
     *
     * making the code easier to read.
     *
     * Time Complexity : O(1)
     */
    public boolean isEmpty() {
        return length == 0;
    }

    /**
     * Returns the total number of nodes.
     *
     * Time Complexity : O(1)
     */
    public int size() {
        return length;
    }

    /**
     * Returns the first node.
     *
     * Example
     *
     * head
     *  ↓
     * 10 -> 20 -> 30
     *
     * returns
     *
     * 10
     *
     * Returns NULL if the list is empty.
     *
     * Time Complexity : O(1)
     */
    public Node peekFirst() {

        return head;
    }

    /**
     * Returns the last node.
     *
     * Because we already maintain
     * the tail pointer,
     * this operation is extremely fast.
     *
     * Time Complexity : O(1)
     */
    public Node peekLast() {

        return tail;
    }
}
```

---

```java
    /**
     * =========================================================================
     * APPEND
     * =========================================================================
     *
     * Appends (adds) a new node at the END of the Linked List.
     *
     * Example
     *
     * Before
     *
     * head
     *  ↓
     * 10 -> 20 -> 30 -> NULL
     *                  ↑
     *                tail
     *
     * Append(40)
     *
     * Step 1
     * Create a new node.
     *
     * newNode
     *   ↓
     * 40 -> NULL
     *
     * Step 2
     * Connect the current tail to the new node.
     *
     * 30 -----------> 40
     *
     * Step 3
     * Move the tail pointer.
     *
     * head
     *  ↓
     * 10 -> 20 -> 30 -> 40 -> NULL
     *                        ↑
     *                      tail
     *
     * Step 4
     * Increase the length.
     *
     * Why is this O(1)?
     *
     * Because we already know where the last node is (tail).
     * No traversal is required.
     *
     * Real-world analogy:
     * Imagine a train where you always know the last coach.
     * Adding another coach takes constant time because you don't
     * have to walk through the entire train to find the end.
     *
     * Time Complexity : O(1)
     * Space Complexity: O(1)
     */
    public void append(int value) {

        Node newNode = new Node(value);

        // If the list is empty, the new node becomes both
        // the head and the tail.
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {

            // Connect the current last node with the new node.
            tail.next = newNode;

            // Move tail to the newly added node.
            tail = newNode;
        }

        // One more node has been added.
        length++;
    }

    /**
     * =========================================================================
     * PREPEND
     * =========================================================================
     *
     * Prepends (adds) a new node at the BEGINNING of the list.
     *
     * Example
     *
     * Before
     *
     * head
     *  ↓
     * 20 -> 30 -> 40 -> NULL
     *
     * prepend(10)
     *
     * Step 1
     *
     * newNode
     *   ↓
     * 10 -> NULL
     *
     * Step 2
     * Point newNode to the current head.
     *
     * 10 ---------> 20
     *
     * Step 3
     * Move head to the new node.
     *
     * head
     *  ↓
     * 10 -> 20 -> 30 -> 40 -> NULL
     *
     * Why is this O(1)?
     *
     * Because we only change two references:
     *
     * newNode.next
     * head
     *
     * No traversal is required.
     *
     * Real-world analogy:
     * Think of people standing in a queue.
     * Instead of inserting someone in the middle,
     * you simply let them stand in front of everyone.
     *
     * Time Complexity : O(1)
     * Space Complexity: O(1)
     */
    public void prepend(int value) {

        Node newNode = new Node(value);

        if (isEmpty()) {

            head = newNode;
            tail = newNode;

        } else {

            // New node points to the current first node.
            newNode.next = head;

            // Update head to the new node.
            head = newNode;
        }

        length++;
    }

    /**
     * =========================================================================
     * GET NODE
     * =========================================================================
     *
     * Returns the node present at the given index.
     *
     * Index Example
     *
     * Index
     *
     * 0    1    2    3
     *
     * 10 ->20 ->30 ->40
     *
     * get(2)
     *
     * returns
     *
     * 30
     *
     * Why can't we directly jump to index 2?
     *
     * Unlike arrays,
     * Linked Lists do NOT store elements in contiguous memory.
     *
     * Therefore,
     * we must start from the head and visit each node one by one.
     *
     * Traversal
     *
     * head
     *  ↓
     * 10 -> 20 -> 30 -> 40
     *
     *          temp
     *           ↓
     *
     * After one iteration
     *
     * 10 -> 20 -> 30 -> 40
     *        ↑
     *      temp
     *
     * Continue until the required index is reached.
     *
     * Edge Cases
     *
     * Negative index
     * Index >= length
     *
     * In both cases,
     * NULL is returned.
     *
     * Time Complexity : O(n)
     * Space Complexity: O(1)
     */
    public Node get(int index) {

        if (index < 0 || index >= length) {
            return null;
        }

        Node temp = head;

        // Move one step at a time until we reach
        // the desired index.
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }

        return temp;
    }

    /**
     * =========================================================================
     * SET VALUE
     * =========================================================================
     *
     * Updates the value stored at a given index.
     *
     * Example
     *
     * Before
     *
     * Index
     * 0    1    2
     *
     * 10 ->20 ->30
     *
     * set(1, 99)
     *
     * After
     *
     * 10 ->99 ->30
     *
     * Notice carefully:
     *
     * We are NOT creating a new node.
     *
     * We are ONLY changing the value inside
     * the existing node.
     *
     * Why reuse get()?
     *
     * Following the DRY Principle (Don't Repeat Yourself),
     * we reuse the traversal logic already implemented in get().
     *
     * If the index is invalid,
     * nothing is modified and false is returned.
     *
     * Time Complexity : O(n)
     * Space Complexity: O(1)
     */
    public boolean set(int index, int value) {

        Node temp = get(index);

        if (temp == null) {
            return false;
        }

        temp.value = value;

        return true;
    }
```

---

```java
    /**
     * =========================================================================
     * INSERT
     * =========================================================================
     *
     * Inserts a new node at the specified index.
     *
     * Example
     *
     * Before
     *
     * Index
     * 0    1    2
     *
     * 10 -> 20 -> 30
     *
     * insert(1, 15)
     *
     * After
     *
     * 10 -> 15 -> 20 -> 30
     *
     * -------------------------------------------------------------------------
     * THINK BEFORE CODING
     * -------------------------------------------------------------------------
     *
     * The new node should NOT replace any existing node.
     *
     * Instead,
     * it should be inserted BETWEEN two existing nodes.
     *
     * Before
     *
     * prev                 next
     *  ↓                    ↓
     * 10 -----------------> 20
     *
     *
     * After
     *
     * prev               newNode              next
     *  ↓                    ↓                  ↓
     * 10 ---------------> 15 ---------------> 20
     *
     * Notice that two links are updated.
     *
     * -------------------------------------------------------------------------
     * ALGORITHM
     * -------------------------------------------------------------------------
     *
     * Step 1
     * Validate the index.
     *
     * Step 2
     * If inserting at index 0,
     * simply call prepend().
     *
     * Step 3
     * If inserting at the end,
     * simply call append().
     *
     * Step 4
     * Find the previous node.
     *
     * Step 5
     * Connect the new node.
     *
     * -------------------------------------------------------------------------
     *
     * Time Complexity : O(n)
     * Space Complexity: O(1)
     */
    public boolean insert(int index, int value) {

        // Valid indexes:
        // 0 ... length
        //
        // Notice:
        // index == length means inserting at the end.
        if (index < 0 || index > length) {
            return false;
        }

        if (index == 0) {
            prepend(value);
            return true;
        }

        if (index == length) {
            append(value);
            return true;
        }

        Node newNode = new Node(value);

        // Previous node
        Node prev = get(index - 1);

        /*
         * Current Situation
         *
         * prev
         *  ↓
         * 10 -------> 20
         *
         * newNode
         *  ↓
         * 15
         *
         * Step 1
         * Make newNode point to 20.
         */
        newNode.next = prev.next;

        /*
         * Step 2
         * Make prev point to newNode.
         *
         * Final
         *
         * 10 -> 15 -> 20
         */
        prev.next = newNode;

        length++;

        return true;
    }

    /**
     * =========================================================================
     * REMOVE FIRST
     * =========================================================================
     *
     * Removes the first node from the Linked List.
     *
     * Example
     *
     * Before
     *
     * head
     *  ↓
     * 10 -> 20 -> 30 -> NULL
     *
     * After
     *
     * head
     *  ↓
     * 20 -> 30 -> NULL
     *
     * -------------------------------------------------------------------------
     * POINTER MOVEMENT
     * -------------------------------------------------------------------------
     *
     * temp = head
     *
     * temp
     *  ↓
     * 10 -> 20 -> 30
     *
     * Move head.
     *
     * head = head.next
     *
     * head
     *  ↓
     * 20 -> 30
     *
     * Disconnect temp.
     *
     * temp.next = null
     *
     * This completely removes the first node
     * from the list.
     *
     * -------------------------------------------------------------------------
     *
     * Time Complexity : O(1)
     * Space Complexity: O(1)
     */
    public Node removeFirst() {

        if (isEmpty()) {
            return null;
        }

        Node temp = head;

        // Move head to the second node.
        head = head.next;

        // Disconnect removed node.
        temp.next = null;

        length--;

        /*
         * If the list became empty,
         * tail should also become null.
         *
         * Example
         *
         * Before
         *
         * head
         * tail
         *  ↓
         * 10
         *
         * After removing
         *
         * head = null
         * tail = null
         */
        if (isEmpty()) {
            tail = null;
        }

        return temp;
    }

    /**
     * =========================================================================
     * REMOVE LAST
     * =========================================================================
     *
     * Removes the last node.
     *
     * Unlike append(),
     * removing the last node is NOT O(1).
     *
     * Why?
     *
     * Because a singly linked list
     * does NOT store a previous pointer.
     *
     * We must find the second-last node.
     *
     * -------------------------------------------------------------------------
     *
     * Example
     *
     * head
     *  ↓
     * 10 -> 20 -> 30 -> 40 -> NULL
     *
     *                    ↑
     *                  temp
     *
     *              ↑
     *            pre
     *
     * pre always stays ONE STEP behind temp.
     *
     * When temp reaches the last node,
     * pre reaches the second-last node.
     *
     * Then
     *
     * tail = pre
     *
     * tail.next = null
     *
     * -------------------------------------------------------------------------
     *
     * Time Complexity : O(n)
     * Space Complexity: O(1)
     */
    public Node removeLast() {

        if (isEmpty()) {
            return null;
        }

        Node temp = head;
        Node pre = head;

        /*
         * Move until temp reaches the last node.
         *
         * temp
         * keeps moving.
         *
         * pre follows one step behind.
         */
        while (temp.next != null) {

            pre = temp;
            temp = temp.next;
        }

        /*
         * temp -> last node
         * pre  -> second-last node
         */
        tail = pre;

        /*
         * Disconnect the last node.
         */
        tail.next = null;

        length--;

        /*
         * Special Case
         *
         * Original List
         *
         * 10
         *
         * After deletion
         *
         * Empty List
         */
        if (isEmpty()) {

            head = null;
            tail = null;
        }

        return temp;
    }

    /**
     * =========================================================================
     * REMOVE
     * =========================================================================
     *
     * Removes the node present at the given index.
     *
     * Example
     *
     * Index
     *
     * 0    1    2    3
     *
     * 10 ->20 ->30 ->40
     *
     * remove(2)
     *
     * Result
     *
     * 10 ->20 ->40
     *
     * -------------------------------------------------------------------------
     *
     * We never jump directly to the node.
     *
     * Instead,
     * we first reach the PREVIOUS node.
     *
     * Why?
     *
     * Because to remove a node,
     * we must change the previous node's next pointer.
     *
     * Before
     *
     * prev                 temp
     *  ↓                    ↓
     * 20 -----------------> 30 -----------------> 40
     *
     * After
     *
     * 20 --------------------------------------> 40
     *
     * temp.next = null
     *
     * This safely disconnects the removed node.
     *
     * -------------------------------------------------------------------------
     *
     * Time Complexity : O(n)
     * Space Complexity: O(1)
     */
    public Node remove(int index) {

        if (index < 0 || index >= length) {
            return null;
        }

        /*
         * Removing the first node
         * has already been implemented.
         */
        if (index == 0) {
            return removeFirst();
        }

        /*
         * Removing the last node
         * has already been implemented.
         */
        if (index == length - 1) {
            return removeLast();
        }

        // Previous node.
        Node prev = get(index - 1);

        // Node to be removed.
        Node temp = prev.next;

        /*
         * Skip the node.
         *
         * Before
         *
         * prev -> temp -> next
         *
         * After
         *
         * prev -------> next
         */
        prev.next = temp.next;

        /*
         * Completely disconnect
         * the removed node.
         *
         * This is not mandatory for correctness,
         * but it clearly separates the removed node
         * from the list and makes debugging easier.
         */
        temp.next = null;

        length--;

        return temp;
    }
```

---

```java
    /**
     * =========================================================================
     * REVERSE LINKED LIST
     * =========================================================================
     *
     * Reverses the entire Linked List in-place.
     *
     * Example
     *
     * Before
     *
     * head
     *  ↓
     * 10 -> 20 -> 30 -> 40 -> NULL
     *                        ↑
     *                      tail
     *
     * After
     *
     * head
     *  ↓
     * 40 -> 30 -> 20 -> 10 -> NULL
     *                        ↑
     *                      tail
     *
     * =========================================================================
     * WHY IS REVERSING DIFFICULT?
     * =========================================================================
     *
     * Every node only knows where the NEXT node is.
     *
     * Example
     *
     * 10 -----> 20 -----> 30
     *
     * But after reversing,
     *
     * 10 <----- 20 <----- 30
     *
     * Every arrow must point in the opposite direction.
     *
     * Since nodes don't store a "previous" pointer,
     * we must remember it ourselves while traversing.
     *
     * =========================================================================
     * THREE POINTER TECHNIQUE
     * =========================================================================
     *
     * We use three references:
     *
     * before
     * temp
     * after
     *
     * before = previous node
     * temp   = current node
     * after  = next node
     *
     * Why do we need "after"?
     *
     * Suppose we immediately reverse this pointer:
     *
     * 10 -> 20 -> 30
     *
     * If we do
     *
     * 10.next = null
     *
     * we've LOST the connection to 20.
     *
     * Therefore,
     * we first save
     *
     * after = temp.next
     *
     * before changing any links.
     *
     * =========================================================================
     * VISUAL WALKTHROUGH
     * =========================================================================
     *
     * Initial
     *
     * before = null
     *
     * temp
     *  ↓
     * 10 -> 20 -> 30 -> 40
     *
     * after
     *       ↓
     *      20
     *
     * --------------------------------------------------
     * Iteration 1
     *
     * Save
     *
     * after = 20
     *
     * Reverse
     *
     * 10 -> NULL
     *
     * Move
     *
     * before = 10
     * temp = 20
     *
     * --------------------------------------------------
     * Iteration 2
     *
     * before
     *  ↓
     * 10 <- 20 -> 30
     *
     * Save
     *
     * after = 30
     *
     * Reverse
     *
     * 20 -> 10
     *
     * Move
     *
     * before = 20
     * temp = 30
     *
     * Continue until temp becomes null.
     *
     * =========================================================================
     *
     * Time Complexity : O(n)
     * Space Complexity: O(1)
     */
    public void reverse() {

        // Empty list or one-node list doesn't need reversing.
        if (head == null || head.next == null) {
            return;
        }

        // Swap head and tail because the first node
        // becomes the last and vice versa.
        Node temp = head;
        head = tail;
        tail = temp;

        Node before = null;
        Node after;

        /*
         * Visit every node exactly once.
         */
        while (temp != null) {

            // Save the next node before breaking the link.
            after = temp.next;

            // Reverse the direction of the pointer.
            temp.next = before;

            // Move the pointers one step forward.
            before = temp;
            temp = after;
        }
    }

    /**
     * =========================================================================
     * CLEAR
     * =========================================================================
     *
     * Removes every node from the Linked List.
     *
     * Before
     *
     * head
     *  ↓
     * 10 -> 20 -> 30 -> 40
     *
     * After
     *
     * head = null
     * tail = null
     * length = 0
     *
     * Java's Garbage Collector will automatically
     * reclaim the memory of the disconnected nodes
     * because there are no remaining references to them.
     *
     * Time Complexity : O(1)
     * Space Complexity: O(1)
     */
    public void clear() {

        head = null;
        tail = null;
        length = 0;
    }

    /**
     * =========================================================================
     * TO STRING
     * =========================================================================
     *
     * Returns a String representation of the Linked List.
     *
     * Instead of calling
     *
     * printList();
     *
     * we can simply write
     *
     * System.out.println(list);
     *
     * Output
     *
     * HEAD -> 10 -> 20 -> 30 -> NULL
     *
     * StringBuilder is used because repeatedly
     * concatenating Strings creates unnecessary
     * temporary objects.
     *
     * Time Complexity : O(n)
     * Space Complexity: O(n)
     */
    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();

        builder.append("HEAD -> ");

        Node temp = head;

        while (temp != null) {

            builder.append(temp.value).append(" -> ");

            temp = temp.next;
        }

        builder.append("NULL");

        return builder.toString();
    }
```

---