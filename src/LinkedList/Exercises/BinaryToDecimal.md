## Exercise 05: Binary to Decimal

### Code with explanation
```java
// Define the binaryToDecimal method for the LinkedList class
public int binaryToDecimal() {
    // Initialize an integer variable 'num' to 0.
    // This variable will hold the decimal value of the binary number.
    int num = 0;
 
    // Initialize a Node variable 'current' to point to the head of the LinkedList.
    // We will use 'current' to traverse through the LinkedList.
    Node current = head;
 
    // Begin a while loop that continues as long as 'current' is not null.
    // A null 'current' would mean we've reached the end of the LinkedList.
    while (current != null) {
 
        // For each node, shift the existing 'num' value one binary place to the left
        // by multiplying it by 2. Then, add the value of the current node to it.
        // This will build our binary number into its decimal equivalent,
        // one bit at a time.
        num = num * 2 + current.value;
 
        // Move to the next node in the LinkedList.
        // 'current.next' will either point to the next node, or to null
        // if we've reached the end of the list.
        current = current.next;
    }
 
    // After the loop, return the decimal value stored in 'num'.
    return num;
}
```