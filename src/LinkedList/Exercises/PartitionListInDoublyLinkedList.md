# Exercise 3: Partition List in Doubly Linked List
```java
public void partitionList(int x) {
    // If the list is empty, nothing to do
    if (head == null) return;
 
    // Create two dummy nodes to help build two new lists
    Node dummy1 = new Node(0); // List for nodes < x
    Node dummy2 = new Node(0); // List for nodes >= x
 
    // Use these pointers to build the two lists
    Node prev1 = dummy1;
    Node prev2 = dummy2;
    Node current = head;
 
    // Traverse the original list
    while (current != null) {
        if (current.value < x) {
            // Attach node to dummy1 list
            prev1.next = current;
            current.prev = prev1;
            prev1 = current;
        } else {
            // Attach node to dummy2 list
            prev2.next = current;
            current.prev = prev2;
            prev2 = current;
        }
        current = current.next;
    }
 
    // End the second list to avoid any trailing connections
    prev2.next = null;
 
    // Connect the two lists
    prev1.next = dummy2.next;
 
    // If dummy2 list has nodes, fix their prev pointer
    if (dummy2.next != null) {
        dummy2.next.prev = prev1;
    }
 
    // Update head pointer of the main list
    head = dummy1.next;
 
    // Ensure new head has no previous pointer
    if (head != null) {
        head.prev = null;
    }
}
```