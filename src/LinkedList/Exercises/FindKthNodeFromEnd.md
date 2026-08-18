## Exercise 03: Find Kth Node From End

### Explanation with code
```java
public Node findKthFromEnd(int k) {
 
    // If k is zero or negative, not valid
    if (k <= 0) {
        return null;
    }
 
    // Both pointers start at the head
    Node slow = head;
    Node fast = head;
 
    // Move the fast pointer k steps ahead
    for (int i = 0; i < k; i++) {
        // If fast hits null before k steps,
        // the list has fewer than k nodes
        if (fast == null) {
            return null;
        }
        fast = fast.next;
    }
 
    // Move both pointers until fast hits the end
    while (fast != null) {
        slow = slow.next;
        fast = fast.next;
    }
 
    // Slow is now at the k-th node from the end
    return slow;
}
```