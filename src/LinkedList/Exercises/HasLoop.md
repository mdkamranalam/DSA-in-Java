## Exercise 02: Has Loop

### Explanation with code
```java
public boolean hasLoop() {
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
 
        // If slow pointer meets fast pointer, then there is a loop in the linked list
        if (slow == fast) {
            return true;
        }
    }
 
    // If the loop has not been detected after the traversal, then there is no loop in the linked list
    return false;
}
```