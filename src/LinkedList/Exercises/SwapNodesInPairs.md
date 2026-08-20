## Exercise 8: Swap Nodes in Pairs

### Code with explanation
```java
public void swapPairs() {
 
    // Create a dummy node pointing to the head
    // This simplifies edge cases, like swapping the first pair
    Node dummy = new Node(0);
    dummy.next = head;
 
    // previous tracks the node before the current pair
    Node previous = dummy;
 
    // first is the first node in the pair to be swapped
    Node first = head;
 
    // Loop while there are at least two nodes to swap
    while (first != null && first.next != null) {
 
        // second is the second node in the pair
        Node second = first.next;
 
        // Point previous to second, starting the swap
        previous.next = second;
 
        // Point first to the node after the second
        first.next = second.next;
 
        // Point second to first, completing the swap
        second.next = first;
 
        // Move previous to first (end of swapped pair)
        previous = first;
 
        // Move first to the next pair's first node
        first = first.next;
    }
 
    // Reset head to point to the new start of the list
    head = dummy.next;
}
```