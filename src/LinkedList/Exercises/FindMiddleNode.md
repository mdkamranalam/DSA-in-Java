## Exercise 01: Find Middle Node in LinkedList

### Explanation with code
```java
public Node findMiddleNode() {
    // Initialize slow pointer to the head of the linked list
    Node slow = head;
 
    // Initialize fast pointer to the head of the linked list
    Node fast = head;
 
    // Traverse the linked list with two pointers: slow and fast
    // slow moves one node at a time, while fast moves two nodes at a time
    while (fast != null && fast.next != null) {
        // Move slow pointer to the next node
        slow = slow.next;
 
        // Move fast pointer to the next two nodes
        fast = fast.next.next;
    }
 
    // Return the Node object representing the middle node of the linked list
    return slow;
}
```