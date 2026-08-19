## Exercise 04: Remove Duplicates

### 1. Solution Using a Set:
```java
// Solution Using a Set - O(n)
public void removeDuplicates() {
    // Step 1: Create a Set to store unique node values.
    // We initialize a HashSet, which allows for fast look-up
    // to check for duplicates later on.
    Set<Integer> values = new HashSet<>();

    // Step 2: Initialize a Node variable 'previous' to null.
    // 'previous' will help us unlink a node if it's a duplicate.
    Node previous = null;

    // Step 3: Initialize 'current' to point to the head node.
    // 'current' will be used to iterate through the linked list.
    Node current = head;

    // Step 4: Begin iteration through the linked list.
    // We will keep iterating until 'current' becomes null,
    // indicating we have reached the end of the list.
    while (current != null) {

        // Step 5: Check for duplicates.
        // We check if the current node's value is already in the set.
        if (values.contains(current.value)) {

            // Step 6: Remove the duplicate node.
            // By setting 'previous.next' to 'current.next',
            // we remove the link to 'current', effectively
            // deleting it from the list.
            previous.next = current.next;

            // Step 7: Update the length of the list.
            // Since we removed a node, we decrement the length by 1.
            length -= 1;

        } else {

            // Step 8: Add unique value to set.
            // If the current value is not a duplicate,
            // we add it to the set for future reference.
            values.add(current.value);

            // Step 9: Update 'previous' node.
            // We set 'previous' to the 'current' node, as we
            // move forward in the list.
            previous = current;
        }

        // Step 10: Move to the next node.
        // We update 'current' to point to the next node in the list,
        // continuing our iteration.
        current = current.next;
    }
}
```

### 2. Solution without using a Set:
```java
// Solution without using a Set - O(n^2)
public void removeDuplicates() {
    // Step 1: Start at the beginning of the list.
    // Initialize `current` to point to the head node.
    // `current` will be used to navigate through the list,
    // one node at a time, starting from the head node.
    Node current = head;

    // Step 2: Check if we've reached the end of the list.
    // We loop until `current` becomes null, which means
    // we've checked all nodes for duplicates.
    while (current != null) {

        // Step 3: Set up a runner node.
        // Initialize `runner` to the `current` node.
        // We'll use `runner` to scan ahead and find duplicates
        // of the `current` node.
        Node runner = current;

        // Step 4: Loop through the remaining nodes.
        // `runner.next` will be null at the end of the list.
        while (runner.next != null) {

            // Step 5: Compare nodes.
            // Check if `runner.next` (the next node) has the
            // same value as `current`.
            if (runner.next.value == current.value) {

                // Step 6: Remove duplicate.
                // If we find a duplicate, we skip it by
                // setting `runner.next` to `runner.next.next`.
                runner.next = runner.next.next;

                // Step 7: Update list length.
                // We removed a node, so decrease the list length
                // by 1 to keep it accurate.
                length -= 1;

            } else {

                // Step 8: Move to the next node.
                // If the next node is not a duplicate,
                // move `runner` to the next node to continue.
                runner = runner.next;
            }
        }

        // Step 9: Move to the next unique node.
        // After checking all nodes for duplicates of the
        // current value, move `current` to the next node.
        current = current.next;
    }
}
```
