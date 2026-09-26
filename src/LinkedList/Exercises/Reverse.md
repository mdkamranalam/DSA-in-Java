# Exercise 2: Reverse Doubly LinkedList

```java
public void reverse() {
    // 'current' starts at the head of the list. This is the starting point
    // for the reversal process.
    Node current = head;
    
    // 'temp' is a temporary variable used for swapping nodes. It is initially
    // set to null since we haven't started the swapping process yet.
    Node temp = null;
 
    // We enter a loop that will continue as long as 'current' is not null.
    // This loop goes through each node in the list.
    while (current != null) {
        // Store the previous node of 'current' in 'temp'.
        // This is needed because we will be overwriting 'current.prev' next,
        // and we don't want to lose this reference.
        temp = current.prev;
 
        // The next two lines are where we swap the 'next' and 'prev' references
        // of the 'current' node. This effectively reverses the direction of the
        // links for 'current'.
        current.prev = current.next; // 'prev' now points to what used to be 'next'
        current.next = temp;         // 'next' now points to what used to be 'prev'
 
        // Move to the next node in the original list. After the swap, the original
        // 'next' node is now in 'current.prev', so we update 'current' to this node.
        current = current.prev;
    }
 
    // After the while loop, the list is reversed, but our 'head' and 'tail' pointers
    // are still pointing to the original head and tail. So we need to swap them.
    temp = head;   // Store the original head in 'temp'
    head = tail;   // Update head to be the original tail
    tail = temp;   // Update tail to be what was originally the head
}

```