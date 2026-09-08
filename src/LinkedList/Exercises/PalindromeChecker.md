# Exercise: 1 - Palindrome Checker in Doubly LinkedList
```java
public boolean isPalindrome() { // Time Complexity -> O(n)
    // if the list has only 1 or 0 nodes, it's automatically a palindrome
    if (length <= 1) return true;
    
    // set up two pointers starting from opposite ends of the list
    Node forwardNode = head;
    Node backwardNode = tail;
    
    // iterate through the list until the two pointers meet in the middle
    for (int i = 0; i < length / 2; i++) {
        // if the values at the two pointers don't match, the list is not a palindrome
        if (forwardNode.value != backwardNode.value) return false;
        
        // move the pointers toward the center of the list
        forwardNode = forwardNode.next;
        backwardNode = backwardNode.prev;
    }
    
    // if the loop completes without returning false, the list is a palindrome
    return true;
}
```