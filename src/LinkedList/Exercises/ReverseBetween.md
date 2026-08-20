## Exercise 7: Reverse Between

### Code with explanation
```java
public void reverseBetween(int startIndex, int endIndex) {
    // Check: If linked list is empty, nothing to reverse.
    // Exit the method.
    if (head == null) return;
 
    // Create a 'dummyNode' that precedes the head.
    // Simplifies handling edge cases.
    Node dummyNode = new Node(0);
    dummyNode.next = head;
 
    // 'previousNode' is used to navigate to the node 
    // right before our sublist begins.
    Node previousNode = dummyNode;
 
    // Move 'previousNode' to node just before sublist.
    for (int i = 0; i < startIndex; i++) {
        previousNode = previousNode.next;
    }
 
    // 'currentNode' marks the first node of sublist.
    Node currentNode = previousNode.next;
 
    // Loop reverses the section from startIndex to endIndex.
    for (int i = 0; i < endIndex - startIndex; i++) {
 
        // 'nodeToMove' is the node we'll move to sublist start.
        Node nodeToMove = currentNode.next;
 
        // Detach 'nodeToMove' from its current position.
        currentNode.next = nodeToMove.next;
 
        // Attach 'nodeToMove' at the beginning of the sublist.
        nodeToMove.next = previousNode.next;
        
        // Move 'nodeToMove' to the start of our sublist.
        previousNode.next = nodeToMove;
    }
 
    // Adjust 'head' if the first node was part of sublist.
    head = dummyNode.next;
}
```