## Exercise 6: Partition List

### Code with explanation
```java
public void partitionList(int x) {
    // Step 1: Check for an empty list.
    // If the list is empty, there is nothing
    // to partition, so we exit the method.
    if (head == null) return;
 
    // Step 2: Create two dummy nodes.
    // These dummy nodes act as placeholders
    // to simplify list manipulation.
    Node dummy1 = new Node(0);
    Node dummy2 = new Node(0);
 
    // Step 3: Initialize pointers for new lists.
    // 'prev1' and 'prev2' will track the end nodes of
    // the two lists that are being created.
    Node prev1 = dummy1;
    Node prev2 = dummy2;
 
    // Step 4: Start with the head of the original list.
    Node current = head;
 
    // Step 5: Iterate through the original list.
    while (current != null) {
 
        // Step 6: Compare current node value with 'x'.
        // Nodes are partitioned based on their value
        // being less than or greater than/equal to 'x'.
 
        // Step 6.1: If value is less than 'x',
        // add node to the first list.
        if (current.value < x) {
            prev1.next = current;  // Link node to the end of the first list.
            prev1 = current;       // Update the end pointer of the first list.
        } else {
            // Step 6.2: If value is greater or equal,
            // add node to the second list.
            prev2.next = current;  // Link node to the end of the second list.
            prev2 = current;       // Update the end pointer of the second list.
        }
 
        // Move to the next node in the original list.
        current = current.next;
    }
 
    // Step 7: Terminate the second list.
    // This prevents cycles in the list.
    prev2.next = null;
 
    // Step 8: Connect the two lists.
    // The first list is followed by the second list.
    prev1.next = dummy2.next;
 
    // Step 9: Update the head of the original list.
    // The head now points to the start of the first list.
    head = dummy1.next;
}
```