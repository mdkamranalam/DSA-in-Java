# Doubly Linked List in Java

A **Doubly Linked List (DLL)** is a linear data structure where every node stores:

1. Its own data/value
2. A reference to the **next** node
3. A reference to the **previous** node

Unlike a singly linked list, a doubly linked list can be traversed in **both directions**.

---

# 1. Mental Model

The most important thing to understand is the structure of a node.

```text
        ┌───────────────┐
        │     Node      │
        ├───────────────┤
        │     value     │
        │     next ─────┼──────► next node
        │     prev ─────┼──────► previous node
        └───────────────┘
```

For example:

```text
NULL ◄── [10] ◄──► [20] ◄──► [30] ──► NULL
         ↑                         ↑
        head                      tail
```

The list maintains three important pieces of information:

```java
private Node head;
private Node tail;
private int length;
```

Think of them as:

```text
head   → first node
tail   → last node
length → number of nodes
```

Your implementation maintains these fields as the list's core state.

---

# 2. Node Structure

Your `Node` class is:

```java
class Node {
    int value;
    Node next;
    Node prev;

    Node(int value) {
        this.value = value;
    }
}
```

## What does each field mean?

### `value`

Stores the actual data.

```text
[10]
 ↑
value
```

### `next`

Points to the node after the current node.

```text
[10] ──next──► [20]
```

### `prev`

Points to the node before the current node.

```text
[10] ◄──prev── [20]
```

Together:

```text
[10] ◄────────► [20]
      prev/next
```

---

# 3. The Most Important DLL Rule

Whenever two nodes are connected:

```text
A ◄──► B
```

there are **two relationships**:

```java
A.next = B;
B.prev = A;
```

If you insert `X` between them:

```text
Before:

A ◄──► B
```

After:

```text
A ◄──► X ◄──► B
```

You need **four pointer updates**:

```java
X.prev = A;
X.next = B;

A.next = X;
B.prev = X;
```

This is one of the most important concepts to remember.

> **DLL = every forward connection should have a corresponding backward connection.**

---

# 4. Complete Structure

Suppose we have:

```text
10 → 20 → 30 → 40
```

The actual DLL is:

```text
NULL
  ▲
  │
prev
  │
[10] ◄────► [20] ◄────► [30] ◄────► [40]
  │                                      │
  │                                      │
 head                                   tail
  │                                      │
  ▼                                      ▼
NULL                                    NULL
```

More precisely:

```text
head
 │
 ▼
[10]
 │ next
 ▼
[20]
 │ next
 ▼
[30]
 │ next
 ▼
[40]
 │
 ▼
null
```

And backwards:

```text
null ◄── [10] ◄── [20] ◄── [30] ◄── [40]
                                      ▲
                                      │
                                     tail
```

---

# 5. Why `head`, `tail`, and `length`?

## `head`

Allows immediate access to the first node.

```text
head
 ↓
[10] ⇄ [20] ⇄ [30]
```

Without `head`, finding the first node would be difficult.

---

## `tail`

Allows immediate access to the last node.

```text
[10] ⇄ [20] ⇄ [30]
                 ↑
                tail
```

This is particularly useful for `append()` and backward traversal.

---

## `length`

Stores the number of nodes.

```text
[10] ⇄ [20] ⇄ [30]

length = 3
```

Because we maintain `length`, we don't need to traverse the list every time we want to know its size.

---

# 6. Constructors

Your implementation provides two constructors.

## Empty List

```java
public DoublyLinkedList() {
    head = null;
    tail = null;
    length = 0;
}
```

Creates:

```text
head
 ↓
null

tail
 ↓
null

length = 0
```

---

## List With Initial Value

```java
public DoublyLinkedList(int value) {
    Node newNode = new Node(value);

    head = newNode;
    tail = newNode;
    length = 1;
}
```

For:

```java
DoublyLinkedList list = new DoublyLinkedList(10);
```

we get:

```text
       head
        ↓
      [10]
        ↑
       tail

length = 1
```

Because there is only one node:

```text
head == tail
```

---

# 7. Empty, One-Node, and Multi-Node States

These three states are extremely important.

## Empty

```text
head = null
tail = null
length = 0
```

Visualization:

```text
NULL
```

---

## One Node

```text
head
 ↓
[10]
 ↑
tail
```

And:

```text
10.next = null
10.prev = null
```

---

## Multiple Nodes

```text
head
 ↓
[10] ⇄ [20] ⇄ [30]
                 ↑
                tail
```

---

# 8. Printing the List

```java
public void printList() {
    Node temp = head;

    System.out.print("HEAD <-> ");

    while (temp != null) {
        System.out.print(temp.value + " <-> ");
        temp = temp.next;
    }

    System.out.println("NULL");
}
```

## How it works

Start at `head`:

```java
Node temp = head;
```

Then repeatedly move forward:

```java
temp = temp.next;
```

Example:

```text
HEAD
 ↓
[10] ⇄ [20] ⇄ [30]
```

Iteration 1:

```text
temp = 10
```

Iteration 2:

```text
temp = 20
```

Iteration 3:

```text
temp = 30
```

Then:

```text
temp = null
```

Output:

```text
HEAD <-> 10 <-> 20 <-> 30 <-> NULL
```

### Complexity

| Complexity | Value |
|------------|------:|
| Time       |  O(n) |
| Space      |  O(1) |

We visit every node once.

---

# 9. Get Head

```java
public void getHead() {
    if (head == null) {
        System.out.println("Head: null");
        return;
    }

    System.out.println("Head: " + head.value);
}
```

Since `head` already points to the first node, no traversal is required.

```text
head
 ↓
[10] ⇄ [20] ⇄ [30]
```

Therefore:

```text
Head = 10
```

### Complexity

```text
Time:  O(1)
Space: O(1)
```

---

# 10. Get Tail

```java
public void getTail() {
    if (tail == null) {
        System.out.println("Tail: null");
        return;
    }

    System.out.println("Tail: " + tail.value);
}
```

Because `tail` directly references the final node:

```text
[10] ⇄ [20] ⇄ [30]
                 ↑
                tail
```

### Complexity

```text
Time:  O(1)
Space: O(1)
```

---

# 11. Get Length

```java
public void getLength() {
    System.out.println("Length: " + length);
}
```

Since `length` is stored directly:

```text
length = 3
```

No traversal is necessary.

### Complexity

```text
Time:  O(1)
Space: O(1)
```

---

# 12. `get(index)`

This is one of the most important optimizations in a doubly linked list.

```java
public Node get(int index) {

    if (index < 0 || index >= length) {
        return null;
    }

    if (index < length / 2) {

        Node temp = head;

        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }

        return temp;
    }

    Node temp = tail;

    for (int i = length - 1; i > index; i--) {
        temp = temp.prev;
    }

    return temp;
}
```

---

# 13. Why Search From Both Directions?

Consider:

```text
Index:    0      1      2      3      4
          ↓      ↓      ↓      ↓      ↓
        [10] ⇄ [20] ⇄ [30] ⇄ [40] ⇄ [50]
         ↑                              ↑
        head                           tail
```

Suppose we want index `1`.

Instead of:

```text
head → 10 → 20
```

we search from the head.

But if we want index `4`, searching from `tail` is immediate.

```text
tail
 ↓
[50]
```

So the algorithm chooses the closer side.

---

# 14. `get()` Decision

```java
if (index < length / 2)
```

If the index is in the first half:

```text
head → → →
```

Otherwise:

```text
tail ← ← ←
```

Visualization:

```text
                 Middle
                   ↓
[10] [20] [30] [40] [50] [60]
 ↑                   │       ↑
head                 │      tail
                     │
              choose direction
```

---

# 15. Example of `get()`

Suppose:

```text
Index:  0   1   2   3   4
        ↓   ↓   ↓   ↓   ↓
       10  20  30  40  50
```

`length = 5`

Request:

```java
get(4)
```

Since index `4` is in the second half:

```text
tail → 50
```

Only one backward movement is required.

```text
50
 ↑
40
 ↑
30
 ↑
20
 ↑
10
```

But we stop at `50`.

---

# 16. `get()` Complexity

Worst case:

```text
O(n)
```

But because we search from the closest end, the practical traversal distance is reduced.

### Complexity

```text
Time:  O(n)
Space: O(1)
```

---

# 17. `set(index, value)`

```java
public boolean set(int index, int value) {

    Node temp = get(index);

    if (temp == null) {
        return false;
    }

    temp.value = value;
    return true;
}
```

The operation is:

1. Find node.
2. Change its value.

Example:

```text
Before:

[10] ⇄ [20] ⇄ [30]

set(1, 99)

After:

[10] ⇄ [99] ⇄ [30]
```

No pointers need to change.

### Complexity

Because `get()` is used:

```text
Time:  O(n)
Space: O(1)
```

---

# 18. `prepend()`

Prepend means:

> Insert a node at the beginning.

Suppose:

```text
head
 ↓
[20] ⇄ [30] ⇄ [40]
```

We execute:

```java
prepend(10);
```

Desired result:

```text
head
 ↓
[10] ⇄ [20] ⇄ [30] ⇄ [40]
```

---

# 19. Pointer Changes in `prepend()`

The important code is:

```java
newNode.next = head;
head.prev = newNode;
head = newNode;
```

Before:

```text
head
 ↓
[20]
```

After:

```text
       ┌──────────────┐
       ↓              │
     [10] ⇄ [20]
      ↑
     head
```

The operations are:

```text
newNode.next = oldHead

oldHead.prev = newNode

head = newNode
```

---

# 20. Empty List Case

If:

```text
length == 0
```

then:

```java
head = newNode;
tail = newNode;
```

Result:

```text
head
 ↓
[10]
 ↑
tail
```

Then:

```java
length++;
```

---

# 21. `prepend()` Complexity

No traversal is required.

```text
Time:  O(1)
Space: O(1)
```

---

# 22. `append()`

Append means:

> Insert a node at the end.

Before:

```text
[10] ⇄ [20] ⇄ [30]
                 ↑
                tail
```

Execute:

```java
append(40);
```

After:

```text
[10] ⇄ [20] ⇄ [30] ⇄ [40]
                         ↑
                        tail
```

---

# 23. Pointer Changes in `append()`

The important code:

```java
newNode.prev = tail;
tail.next = newNode;
tail = newNode;
```

Conceptually:

```text
newNode.prev = oldTail

oldTail.next = newNode

tail = newNode
```

Before:

```text
[30] ← tail
```

After:

```text
[30] ⇄ [40]
          ↑
         tail
```

---

# 24. Why `tail` Makes `append()` O(1)

Without `tail`, we would have to traverse:

```text
head → 10 → 20 → 30
```

to find the last node.

That would be:

```text
O(n)
```

With `tail`:

```text
tail → 30
```

We immediately know where the last node is.

Therefore:

```text
append() = O(1)
```

---

# 25. `insert(index, value)`

This inserts a node at a specific position.

Example:

```text
Index:  0     1     2
       [10] ⇄ [20] ⇄ [30]
```

Execute:

```java
insert(1, 15);
```

Result:

```text
Index:  0     1     2     3
       [10] ⇄ [15] ⇄ [20] ⇄ [30]
```

---

# 26. Why Handle Index 0 and `length` Separately?

Your implementation does:

```java
if (index == 0) {
    prepend(value);
    return true;
}

if (index == length) {
    append(value);
    return true;
}
```

This is good design.

Instead of duplicating pointer logic, we reuse already-correct operations.

```text
index == 0
    ↓
prepend()

index == length
    ↓
append()

otherwise
    ↓
insert in middle
```

---

# 27. Inserting in the Middle

Suppose:

```text
before        after
  ↓             ↓
[10] ⇄ [20]
```

We want:

```text
[10] ⇄ [15] ⇄ [20]
```

Code:

```java
Node before = get(index - 1);
Node after = before.next;

newNode.prev = before;
before.next = newNode;

newNode.next = after;
after.prev = newNode;
```

---

# 28. The Four Pointer Updates

Always remember this pattern:

```java
newNode.prev = before;
newNode.next = after;

before.next = newNode;
after.prev = newNode;
```

Visualization:

```text
BEFORE:

before ◄──► after


AFTER:

before ◄──► newNode ◄──► after
```

---

# 29. `insert()` Complexity

Finding the insertion location requires `get()`.

Therefore:

```text
Time:  O(n)
Space: O(1)
```

If the insertion is at the beginning or end:

```text
O(1)
```

because `prepend()` and `append()` are O(1).

---

# 30. `removeFirst()`

Removes the first node.

Before:

```text
head
 ↓
[10] ⇄ [20] ⇄ [30]
```

After:

```text
head
 ↓
[20] ⇄ [30]
```

---

# 31. Removing the First Node

Code:

```java
Node temp = head;

head = head.next;
head.prev = null;

temp.next = null;
```

Step-by-step:

### Step 1

Save old head:

```text
temp → [10]
head → [10]
```

### Step 2

Move head:

```text
head → [20]
```

### Step 3

Remove backward connection:

```text
[20].prev = null
```

### Step 4

Disconnect removed node:

```text
[10].next = null
```

Result:

```text
[10]       [20] ⇄ [30]
 ↑           ↑
temp        head
```

The returned node is `10`.

---

# 32. One-Node Case

Before:

```text
head
 ↓
[10]
 ↑
tail
```

After:

```text
head = null
tail = null
length = 0
```

---

# 33. `removeFirst()` Complexity

No traversal.

```text
Time:  O(1)
Space: O(1)
```

---

# 34. `removeLast()`

Removes the last node.

Before:

```text
[10] ⇄ [20] ⇄ [30]
                 ↑
                tail
```

After:

```text
[10] ⇄ [20]
          ↑
         tail
```

---

# 35. Pointer Changes

```java
tail = tail.prev;
tail.next = null;

temp.prev = null;
```

Conceptually:

```text
old tail = 30

tail = 20

20.next = null

30.prev = null
```

Result:

```text
[10] ⇄ [20]    [30]
          ↑
         tail
```

---

# 36. `removeLast()` Complexity

Because `tail` exists:

```text
Time:  O(1)
Space: O(1)
```

This is another major advantage of maintaining a tail pointer.

---

# 37. `remove(index)`

This combines the previous operations.

```java
if (index == 0) {
    return removeFirst();
}

if (index == length - 1) {
    return removeLast();
}
```

Otherwise, remove from the middle.

---

# 38. Removing a Middle Node

Suppose:

```text
[10] ⇄ [20] ⇄ [30] ⇄ [40]
```

Remove index `2`:

```text
[10] ⇄ [20] ⇄ [40]
```

Let:

```text
temp  = 30
before = 20
after  = 40
```

Then:

```java
before.next = after;
after.prev = before;
```

The node `30` becomes detached:

```java
temp.prev = null;
temp.next = null;
```

---

# 39. Visualization

Before:

```text
        temp
         ↓
[10] ⇄ [20] ⇄ [30] ⇄ [40]
        ↑       ↑
      before   after
```

After:

```text
[10] ⇄ [20] ⇄ [40]

[30]   ← completely detached
```

---

# 40. Why Disconnect the Removed Node?

These lines:

```java
temp.prev = null;
temp.next = null;
```

are good practice.

They ensure the removed node no longer points into the list.

```text
Removed node:

[30]
 /  \
null null
```

rather than:

```text
[30] ──► [40]
 ↑
could still reference list
```

---

# 41. `remove()` Complexity

Finding the node requires `get()`.

Therefore:

```text
Time:  O(n)
Space: O(1)
```

Removing first/last is:

```text
O(1)
```

---

# 42. Core Operations Complexity Table

| Operation     | Time | Space |
|---------------|-----:|------:|
| Get head      | O(1) |  O(1) |
| Get tail      | O(1) |  O(1) |
| Get length    | O(1) |  O(1) |
| Print         | O(n) |  O(1) |
| Get(index)    | O(n) |  O(1) |
| Set(index)    | O(n) |  O(1) |
| Prepend       | O(1) |  O(1) |
| Append        | O(1) |  O(1) |
| Insert        | O(n) |  O(1) |
| Remove first  | O(1) |  O(1) |
| Remove last   | O(1) |  O(1) |
| Remove(index) | O(n) |  O(1) |

---

# 43. Pseudocode — Core Operations

## Get

```text
GET(index):

    if index is invalid:
        return null

    if index is in first half:
        start from head
        move forward until index
    else:
        start from tail
        move backward until index

    return node
```

---

## Set

```text
SET(index, value):

    node = GET(index)

    if node does not exist:
        return false

    node.value = value

    return true
```

---

## Prepend

```text
PREPEND(value):

    create newNode

    if list is empty:
        head = newNode
        tail = newNode

    else:
        newNode.next = head
        head.prev = newNode
        head = newNode

    length++
```

---

## Append

```text
APPEND(value):

    create newNode

    if list is empty:
        head = newNode
        tail = newNode

    else:
        newNode.prev = tail
        tail.next = newNode
        tail = newNode

    length++
```

---

## Remove First

```text
REMOVE_FIRST():

    if list is empty:
        return null

    save head

    if only one node:
        head = null
        tail = null

    else:
        head = head.next
        head.prev = null
        old head.next = null

    length--

    return old head
```

---

## Remove Last

```text
REMOVE_LAST():

    if list is empty:
        return null

    save tail

    if only one node:
        head = null
        tail = null

    else:
        tail = tail.prev
        tail.next = null
        old tail.prev = null

    length--

    return old tail
```

---

## Remove at Index

```text
REMOVE(index):

    if index invalid:
        return null

    if index == 0:
        return REMOVE_FIRST()

    if index == last index:
        return REMOVE_LAST()

    node = GET(index)

    before = node.prev
    after = node.next

    before.next = after
    after.prev = before

    node.prev = null
    node.next = null

    length--

    return node
```

---

# 44. Exercise 1 — Palindrome Checker

A palindrome reads the same from both directions.

Examples:

```text
1 → 2 → 3 → 2 → 1
```

is a palindrome.

But:

```text
1 → 2 → 3 → 4
```

is not.

---

# 45. Why DLL Is Perfect for Palindrome Checking

A DLL has:

```text
head
 ↓
[1] ⇄ [2] ⇄ [3] ⇄ [2] ⇄ [1]
                              ↑
                             tail
```

We can simultaneously move:

```text
forward → →
← ← backward
```

So:

```text
forwardNode = head
backwardNode = tail
```

---

# 46. Algorithm

Compare:

```text
head.value
tail.value
```

Then:

```text
head.next
tail.prev
```

Continue toward the center.

Example:

```text
[1] ⇄ [2] ⇄ [3] ⇄ [2] ⇄ [1]
 ↑                           ↑
 F                           B
```

First:

```text
1 == 1
```

Move:

```text
  [1] ⇄ [2] ⇄ [3] ⇄ [2] ⇄ [1]
         ↑             ↑
         F             B
```

Then:

```text
2 == 2
```

Move toward center:

```text
[1] ⇄ [2] ⇄ [3] ⇄ [2] ⇄ [1]
              ↑
            center
```

Palindrome confirmed.

---

# 47. Code

```java
public boolean isPalindrome() {

    if (length <= 1) {
        return true;
    }

    Node forwardNode = head;
    Node backwardNode = tail;

    for (int i = 0; i < length / 2; i++) {

        if (forwardNode.value != backwardNode.value) {
            return false;
        }

        forwardNode = forwardNode.next;
        backwardNode = backwardNode.prev;
    }

    return true;
}
```

---

# 48. Why `length / 2`?

We only need to compare half the list.

For:

```text
1 2 3 2 1
```

we need:

```text
1 vs 1
2 vs 2
```

We don't need to compare:

```text
3 vs 3
```

because the middle is automatically symmetric if the other pairs match.

---

# 49. Palindrome Pseudocode

```text
PALINDROME():

    if length <= 1:
        return true

    left = head
    right = tail

    repeat length / 2 times:

        if left.value != right.value:
            return false

        left = left.next
        right = right.prev

    return true
```

### Complexity

```text
Time:  O(n)
Space: O(1)
```

---

# 50. Exercise 2 — Reverse the Entire List

Original:

```text
NULL ← [10] ⇄ [20] ⇄ [30] ⇄ [40] → NULL
        ↑                           ↑
       head                        tail
```

After reversal:

```text
NULL ← [40] ⇄ [30] ⇄ [20] ⇄ [10] → NULL
        ↑                           ↑
       head                        tail
```

The nodes don't change.

Only their links change.

---

# 51. The Key Idea Behind `reverse()`

For every node:

```text
prev ↔ next
```

That means:

```java
current.prev = current.next;
current.next = oldPrev;
```

Suppose:

```text
A ⇄ B
```

For node `A`:

```text
A.prev = null
A.next = B
```

After reversing:

```text
A.prev = B
A.next = null
```

---

# 52. Why Move Using `current.prev`?

This line is subtle:

```java
current = current.prev;
```

Why?

Because immediately before it, we did:

```java
current.prev = current.next;
```

So `current.prev` now contains the **old next node**.

Example:

```text
Before:

A → B → C
```

After swapping A:

```text
A
prev → B
next → old prev
```

Therefore:

```java
current = current.prev;
```

moves to `B`.

This is one of the easiest places to make a mistake when implementing DLL reversal.

---

# 53. Finally Swap Head and Tail

After reversing all pointers:

```text
old head = 10
old tail = 40
```

So:

```java
head = tail;
tail = oldHead;
```

The implementation does this using a temporary variable.

Result:

```text
head → 40 ⇄ 30 ⇄ 20 ⇄ 10 ← tail
```

---

# 54. Reverse Pseudocode

```text
REVERSE():

    if length <= 1:
        return

    current = head

    while current is not null:

        temp = current.prev

        current.prev = current.next
        current.next = temp

        current = current.prev

    swap head and tail
```

### Complexity

```text
Time:  O(n)
Space: O(1)
```

This is an **in-place reversal**.

---

# 55. Exercise 3 — Partition List

The goal is:

> Put all nodes with values `< x` before nodes with values `>= x`.

Example:

```text
Original:

3 ⇄ 5 ⇄ 8 ⇄ 5 ⇄ 10 ⇄ 2

x = 5
```

Partitioned:

```text
3 ⇄ 2 ⇄ 5 ⇄ 8 ⇄ 5 ⇄ 10
```

The important condition is:

```text
values < x
        ↓
values >= x
```

---

# 56. Important: Partition Is Not Sorting

Suppose:

```text
4 1 7 3 8 2
```

with:

```text
x = 5
```

Partition:

```text
4 1 3 2 7 8
```

It is **not**:

```text
1 2 3 4 7 8
```

Partitioning only separates the nodes into two groups.

---

# 57. Two Lists

The algorithm builds:

```text
List 1: values < x

List 2: values >= x
```

Using dummy nodes:

```text
dummy1 → smaller nodes

dummy2 → greater/equal nodes
```

Conceptually:

```text
                 ┌── [3] ── [2]
original nodes ──┤
                 └── [5] ── [8] ── [10]
```

---

# 58. Why Dummy Nodes?

Without dummy nodes, we'd have to repeatedly ask:

```text
Is this the first node in the smaller list?

Is this the first node in the greater list?
```

Dummy nodes provide a stable starting point.

```text
dummy1 → 3 → 2
dummy2 → 5 → 8 → 10
```

The actual lists start at:

```java
dummy1.next
dummy2.next
```

---

# 59. Processing a Node

For every node:

```java
if (current.value < x)
```

put it into the first list.

Otherwise:

```java
else
```

put it into the second list.

---

# 60. Why Save `next` First?

This line is very important:

```java
Node next = current.next;
```

Before changing the current node's links.

Then:

```java
current.next = null;
current.prev = null;
```

Why?

Because we're rebuilding the list.

If we modified the current node before saving its old `next`, we could lose access to the rest of the original list.

General linked-list rule:

> **When rearranging pointers, save the next node before breaking the current connection.**

---

# 61. Joining the Two Partitions

Suppose:

```text
dummy1 → 3 → 2

dummy2 → 5 → 8 → 10
```

Connect them:

```text
3 → 2 → 5 → 8 → 10
```

Code:

```java
prev1.next = dummy2.next;
```

And because this is a DLL:

```java
dummy2.next.prev = prev1;
```

This creates the backward connection.

---

# 62. Updating Head

```java
head = dummy1.next;
```

The dummy node itself is not part of the actual list.

So:

```text
dummy1 → 3 → 2 → 5
          ↑
         head
```

Then:

```java
head.prev = null;
```

because the first real node must not point to the dummy.

---

# 63. Updating Tail

After partitioning, the final node depends on whether the `>= x` partition exists.

If it exists:

```java
tail = prev2;
```

Otherwise:

```java
tail = prev1;
```

This is important because `tail` must always point to the actual final node.

---

# 64. Partition Pseudocode

```text
PARTITION(x):

    if length <= 1:
        return

    create dummy1
    create dummy2

    prev1 = dummy1
    prev2 = dummy2

    current = head

    while current exists:

        save current.next

        detach current

        if current.value < x:
            add current to list 1
            move prev1
        else:
            add current to list 2
            move prev2

        current = saved next

    terminate list 2

    connect list 1 to list 2

    head = first real node of list 1

    head.prev = null

    update tail
```

### Complexity

```text
Time:  O(n)
Space: O(1)
```

The dummy nodes are only a constant amount of extra memory.

---

# 65. Exercise 4 — Reverse Between Two Indices

This operation reverses only part of the list.

Example:

```text
Index:

0    1    2    3    4
↓    ↓    ↓    ↓    ↓
10 ⇄ 20 ⇄ 30 ⇄ 40 ⇄ 50
```

Call:

```java
reverseBetween(1, 3);
```

Result:

```text
10 ⇄ 40 ⇄ 30 ⇄ 20 ⇄ 50
```

Only indices:

```text
1 → 2 → 3
```

were reversed.

---

# 66. Why Use a Dummy Node?

Consider:

```java
reverseBetween(0, 3);
```

The section starts at `head`.

Without a dummy node, special handling is required because there is no node before index `0`.

Dummy node:

```text
dummy → 10 ⇄ 20 ⇄ 30 ⇄ 40
```

Now there is always a node before the reversal section.

---

# 67. Finding `previousNode`

The code:

```java
Node previousNode = dummyNode;

for (int i = 0; i < startIndex; i++) {
    previousNode = previousNode.next;
}
```

moves `previousNode` to the node immediately before the reversal section.

For:

```text
reverseBetween(1, 3)
```

we get:

```text
dummy → 10 ⇄ 20 ⇄ 30 ⇄ 40
          ↑
    previousNode
```

And:

```text
currentNode = 20
```

---

# 68. The Core Reversal Technique

The algorithm repeatedly takes the node after `currentNode` and moves it to the front of the reversal section.

Suppose:

```text
10 ⇄ 20 ⇄ 30 ⇄ 40 ⇄ 50
```

We want to reverse:

```text
20 ⇄ 30 ⇄ 40
```

Start:

```text
10 ⇄ 20 ⇄ 30 ⇄ 40 ⇄ 50
     ↑     ↑
 previous current
```

Move `30` before `20`:

```text
10 ⇄ 30 ⇄ 20 ⇄ 40 ⇄ 50
```

Then move `40` before `30`:

```text
10 ⇄ 40 ⇄ 30 ⇄ 20 ⇄ 50
```

Done.

---

# 69. Why `nodeToMove`?

```java
Node nodeToMove = currentNode.next;
```

The algorithm identifies the node immediately after `currentNode`.

Then removes it:

```java
currentNode.next = nodeToMove.next;
```

and inserts it immediately after `previousNode`.

This is essentially:

```text
Take node
   ↓
Remove it
   ↓
Insert it at front of reversed section
```

---

# 70. Pointer Updates

Removing:

```java
currentNode.next = nodeToMove.next;
```

If there is a node after `nodeToMove`:

```java
nodeToMove.next.prev = currentNode;
```

Then inserting:

```java
nodeToMove.next = previousNode.next;
previousNode.next.prev = nodeToMove;

previousNode.next = nodeToMove;
nodeToMove.prev = previousNode;
```

Again, the important idea is that **both `next` and `prev` links must be updated**.

---

# 71. Example Dry Run

Initial:

```text
10 ⇄ 20 ⇄ 30 ⇄ 40 ⇄ 50
```

Reverse indices `1 → 3`.

### First iteration

Move `30`:

```text
10 ⇄ 30 ⇄ 20 ⇄ 40 ⇄ 50
```

### Second iteration

Move `40`:

```text
10 ⇄ 40 ⇄ 30 ⇄ 20 ⇄ 50
```

Final:

```text
10 ⇄ 40 ⇄ 30 ⇄ 20 ⇄ 50
```

---

# 72. Tail Handling

After reversal, the code recomputes:

```java
tail = head;

while (tail.next != null) {
    tail = tail.next;
}
```

This ensures `tail` points to the actual final node.

This is correct but not maximally optimal.

A more advanced implementation can update `tail` directly during the operation instead of traversing the list afterward.

For learning purposes, the current approach is easier to reason about.

---

# 73. Reverse Between Pseudocode

```text
REVERSE_BETWEEN(start, end):

    if list has <= 1 node:
        return

    if indices are invalid:
        return

    create dummy before head

    previous = dummy

    move previous to node before start

    current = previous.next

    repeat end - start times:

        nodeToMove = current.next

        remove nodeToMove from current position

        insert nodeToMove after previous

    head = dummy.next
    head.prev = null

    update tail
```

### Complexity

The operation performs:

```text
O(n)
```

in the worst case.

The final tail traversal also makes the overall complexity:

```text
Time:  O(n)
Space: O(1)
```

---

# 74. Exercise 5 — Swap Nodes in Pairs

This operation swaps every two adjacent nodes.

Example:

```text
1 ⇄ 2 ⇄ 3 ⇄ 4 ⇄ 5
```

After swapping:

```text
2 ⇄ 1 ⇄ 4 ⇄ 3 ⇄ 5
```

Notice that the last node remains unchanged if the list has an odd number of nodes.

---

# 75. Pair Visualization

Original:

```text
[1] ⇄ [2] ⇄ [3] ⇄ [4] ⇄ [5]
```

Pairs:

```text
[1] [2]    [3] [4]    [5]
```

Swap each pair:

```text
[2] [1]    [4] [3]    [5]
```

Final:

```text
[2] ⇄ [1] ⇄ [4] ⇄ [3] ⇄ [5]
```

---

# 76. Why Dummy Node?

Again, the dummy node simplifies the first pair.

```text
dummy → 1 ⇄ 2 ⇄ 3 ⇄ 4
```

The first swap becomes:

```text
dummy → 2 ⇄ 1 ⇄ 3 ⇄ 4
```

No special case is required for the first pair.

---

# 77. The Three Main Nodes

For each pair:

```text
previousNode → firstNode → secondNode
```

Example:

```text
dummy → 1 → 2
```

We want:

```text
dummy → 2 → 1
```

---

# 78. Step 1 — Previous to Second

```java
previousNode.next = secondNode;
secondNode.prev = previousNode;
```

Before:

```text
previous → first → second
```

After:

```text
previous → second
           ↓
```

---

# 79. Step 2 — Second to First

```java
secondNode.next = firstNode;
firstNode.prev = secondNode;
```

Now:

```text
previous ⇄ second ⇄ first
```

---

# 80. Step 3 — First to Remaining List

```java
firstNode.next = secondNode.next;
```

Then if another node exists:

```java
firstNode.next.prev = firstNode;
```

Result:

```text
previous ⇄ second ⇄ first ⇄ remaining
```

---

# 81. Moving to the Next Pair

After swapping:

```text
2 ⇄ 1 ⇄ 4 ⇄ 3
```

`previousNode` should move to:

```text
1
```

because `1` is now the end of the processed pair.

The code does:

```java
previousNode = firstNode;
```

Then the next pair starts:

```text
previousNode → 4 → 3
```

---

# 82. Pair Swap Dry Run

Original:

```text
1 ⇄ 2 ⇄ 3 ⇄ 4 ⇄ 5
```

First pair:

```text
2 ⇄ 1 ⇄ 3 ⇄ 4 ⇄ 5
```

Second pair:

```text
2 ⇄ 1 ⇄ 4 ⇄ 3 ⇄ 5
```

No pair exists for `5`.

Final:

```text
2 ⇄ 1 ⇄ 4 ⇄ 3 ⇄ 5
```

---

# 83. Swap Pairs Pseudocode

```text
SWAP_PAIRS():

    if length <= 1:
        return

    create dummy before head

    previous = dummy

    while two nodes remain:

        first = previous.next
        second = first.next

        previous.next = second
        second.prev = previous

        second.next = first
        first.prev = second

        first.next = remaining node

        if remaining node exists:
            remaining.prev = first

        previous = first

    head = dummy.next
    head.prev = null

    update tail
```

### Complexity

```text
Time:  O(n)
Space: O(1)
```

---

# 84. All Five Exercises — Complexity

| Exercise        | Time | Space |
|-----------------|-----:|------:|
| Palindrome      | O(n) |  O(1) |
| Reverse         | O(n) |  O(1) |
| Partition       | O(n) |  O(1) |
| Reverse Between | O(n) |  O(1) |
| Swap Pairs      | O(n) |  O(1) |

The important achievement here is that **all five operations work in-place**.

They do not create a second full linked list.

---

# 85. Why Linked Lists Are Different From Arrays

Consider an array:

```text
[10][20][30][40]
```

Elements are stored contiguously.

A linked list does not require contiguous memory:

```text
[10] → [20] → [30] → [40]
```

The nodes can exist in different memory locations.

Pointers connect them logically.

---

# 86. Array vs Doubly Linked List

| Operation        | Array | Doubly Linked List |
|------------------|------:|-------------------:|
| Access by index  |  O(1) |               O(n) |
| Search           |  O(n) |               O(n) |
| Insert beginning |  O(n) |               O(1) |
| Insert end*      |  O(1) |               O(1) |
| Remove beginning |  O(n) |               O(1) |
| Remove end*      |  O(1) |               O(1) |
| Memory overhead  |   Low |             Higher |

`*` assumes the appropriate end information is maintained.

The key tradeoff:

```text
Array:
Fast random access

Linked List:
Fast structural insertion/removal
```

---

# 87. Why DLL Uses More Memory

A singly linked-list node:

```text
[value | next]
```

A doubly linked-list node:

```text
[value | prev | next]
```

Therefore DLL requires an additional pointer per node.

But the benefit is:

```text
forward traversal
+
backward traversal
```

---

# 88. Important Invariants

An invariant is a condition that should always remain true.

For your DLL, remember these.

## Empty list

```text
length == 0
head == null
tail == null
```

---

## Non-empty list

```text
head != null
tail != null
length > 0
```

---

## First node

```text
head.prev == null
```

---

## Last node

```text
tail.next == null
```

---

## One-node list

```text
head == tail
```

and:

```text
head.prev == null
head.next == null
```

---

# 89. DLL Pointer Integrity

For:

```text
A ⇄ B ⇄ C
```

we should have:

```java
A.next == B
B.prev == A

B.next == C
C.prev == B
```

If you accidentally do:

```java
A.next = B;
```

but forget:

```java
B.prev = A;
```

the list becomes inconsistent.

You might be able to traverse forward:

```text
A → B → C
```

but backward traversal can fail.

---

# 90. A Powerful Debugging Technique

Whenever you modify a DLL, check:

### Forward

```text
head → next → next → ...
```

### Backward

```text
tail → prev → prev → ...
```

They should describe the same nodes in reverse order.

For:

```text
10 ⇄ 20 ⇄ 30
```

forward:

```text
10 → 20 → 30
```

backward:

```text
30 → 20 → 10
```

If those don't match, a pointer update is missing.

---

# 91. Common Pointer Mistakes

## Mistake 1 — Updating only `next`

Wrong:

```java
before.next = newNode;
```

but forgetting:

```java
newNode.prev = before;
```

---

## Mistake 2 — Forgetting the other side

Wrong:

```java
after.prev = newNode;
```

but forgetting:

```java
newNode.next = after;
```

---

## Mistake 3 — Forgetting `head.prev`

After changing the head:

```text
head.prev
```

must be:

```text
null
```

---

## Mistake 4 — Forgetting `tail.next`

The final node must always have:

```text
tail.next == null
```

---

## Mistake 5 — Losing the rest of the list

When rearranging nodes:

```java
Node next = current.next;
```

often needs to happen **before** modifying `current.next`.

---

# 92. Why Temporary Variables Matter

Suppose:

```text
A → B → C
```

and you execute:

```java
A.next = null;
```

You have lost the direct route to `B`.

If you first do:

```java
Node next = A.next;
```

then:

```text
next → B
```

You can safely modify `A`.

This is why linked-list algorithms frequently contain:

```java
Node temp = current.next;
```

---

# 93. Dummy Nodes — The General Pattern

You use dummy nodes in:

* `partitionList()`
* `reverseBetween()`
* `swapNodePairs()`

This is a powerful linked-list technique.

A dummy node is a temporary node placed before the real head:

```text
dummy → head → ...
```

It simplifies operations involving the first node.

Instead of writing:

```text
if modifying head:
    special case
else:
    normal case
```

we can often use:

```text
dummy → normal list
```

and treat the first node like every other node.

---

# 94. When Should You Use a Dummy Node?

Dummy nodes are especially useful when:

* inserting before the head
* deleting the head
* reversing a range beginning at index 0
* merging lists
* partitioning
* swapping nodes
* removing nodes based on conditions

The dummy node is usually **not part of the final list**.

---

# 95. The Big Picture of Your Implementation

Your class can be mentally divided into four layers:

```text
DoublyLinkedList
│
├── 1. Structure
│   ├── Node
│   ├── head
│   ├── tail
│   └── length
│
├── 2. Basic Operations
│   ├── print
│   ├── getHead
│   ├── getTail
│   └── getLength
│
├── 3. CRUD Operations
│   ├── get
│   ├── set
│   ├── prepend
│   ├── insert
│   ├── append
│   ├── removeFirst
│   ├── remove
│   └── removeLast
│
└── 4. Algorithmic Problems
    ├── palindrome
    ├── reverse
    ├── partition
    ├── reverseBetween
    └── swapNodePairs
```

This is a good progression for learning.

---

# 96. How to Think About Any DLL Problem

When you encounter a new DLL problem, ask these questions:

### Question 1

What nodes am I changing?

```text
current
previous
next
```

---

### Question 2

What are their relationships before the operation?

```text
A ⇄ B ⇄ C
```

---

### Question 3

What should the relationships look like afterward?

```text
A ⇄ C
```

---

### Question 4

Which `next` pointers change?

---

### Question 5

Which `prev` pointers change?

---

### Question 6

Does `head` change?

---

### Question 7

Does `tail` change?

---

### Question 8

Does `length` change?

This checklist will help you solve linked-list problems instead of memorizing implementations.

---

# 97. Example: Designing an Insertion Yourself

Suppose:

```text
10 ⇄ 20 ⇄ 30
```

Insert `15` between `10` and `20`.

Start:

```text
before = 10
after = 20
newNode = 15
```

Desired:

```text
10 ⇄ 15 ⇄ 20 ⇄ 30
```

Ask:

### What should `15.prev` be?

```text
10
```

Therefore:

```java
newNode.prev = before;
```

### What should `15.next` be?

```text
20
```

Therefore:

```java
newNode.next = after;
```

### What should `10.next` be?

```text
15
```

Therefore:

```java
before.next = newNode;
```

### What should `20.prev` be?

```text
15
```

Therefore:

```java
after.prev = newNode;
```

That's the whole insertion algorithm.

---

# 98. Example: Designing Removal Yourself

Suppose:

```text
10 ⇄ 20 ⇄ 30
```

Remove `20`.

Desired:

```text
10 ⇄ 30
```

Set:

```text
before = 10
current = 20
after = 30
```

Ask:

### What should 10.next become?

```text
30
```

```java
before.next = after;
```

### What should 30.prev become?

```text
10
```

```java
after.prev = before;
```

### What should happen to 20?

Detach it:

```java
current.prev = null;
current.next = null;
```

That's the entire conceptual process.

---

# 99. Revision Cheat Sheet

## Node

```java
class Node {
    int value;
    Node next;
    Node prev;
}
```

---

## Forward

```text
current = current.next
```

---

## Backward

```text
current = current.prev
```

---

## First node

```text
head
```

---

## Last node

```text
tail
```

---

## First node invariant

```text
head.prev == null
```

---

## Last node invariant

```text
tail.next == null
```

---

## Insert between A and B

```java
newNode.prev = A;
newNode.next = B;

A.next = newNode;
B.prev = newNode;
```

---

## Remove B between A and C

```java
A.next = C;
C.prev = A;

B.prev = null;
B.next = null;
```

---

# 100. Complexity Cheat Sheet

```text
                    TIME       SPACE

getHead()           O(1)       O(1)
getTail()           O(1)       O(1)
getLength()         O(1)       O(1)

printList()         O(n)       O(1)

get(index)          O(n)       O(1)
set(index)          O(n)       O(1)

prepend()           O(1)       O(1)
append()            O(1)       O(1)
insert()            O(n)       O(1)

removeFirst()       O(1)       O(1)
removeLast()        O(1)       O(1)
remove(index)       O(n)       O(1)

isPalindrome()      O(n)       O(1)
reverse()           O(n)       O(1)
partitionList()     O(n)       O(1)
reverseBetween()    O(n)       O(1)
swapNodePairs()     O(n)       O(1)
```

---

# 101. Final Mental Model

If you remember only one visualization, remember this:

```text
                    DOUBLY LINKED LIST

        prev                         next
         │                            │
         ▼                            ▼

NULL ◄── [10] ◄──────► [20] ◄──────► [30] ◄──────► [40] ──► NULL
          ↑                                             ↑
         head                                          tail
```

Every node has:

```text
value
  +
prev
  +
next
```

And every modification must preserve:

```text
A.next == B
B.prev == A
```

---

# 102. The Three Rules to Never Forget

## Rule 1 — Two-way connections

If:

```text
A ⇄ B
```

then:

```java
A.next = B;
B.prev = A;
```

---

## Rule 2 — Protect the list before breaking links

When rearranging:

```java
Node next = current.next;
```

before changing the current node's pointers.

---

## Rule 3 — Maintain the invariants

After every operation:

```text
head.prev == null
tail.next == null
```

and:

```text
length == number of actual nodes
```

For a non-empty one-node list:

```text
head == tail
```

If you can maintain these invariants, most DLL problems become a matter of carefully changing a few pointers.

---

# 103. Recommended Learning Sequence

Don't memorize all five exercises at once.

Learn them in this order:

```text
1. Node structure
       ↓
2. head / tail / length
       ↓
3. prepend()
       ↓
4. append()
       ↓
5. removeFirst()
       ↓
6. removeLast()
       ↓
7. get()
       ↓
8. insert()
       ↓
9. remove()
       ↓
10. isPalindrome()
       ↓
11. reverse()
       ↓
12. partitionList()
       ↓
13. reverseBetween()
       ↓
14. swapNodePairs()
```

The first nine teach you **DLL mechanics**.

The last five teach you how to apply those mechanics to **algorithmic problems**.

---

# 104. Final Takeaway

A doubly linked list is not difficult because of its syntax. It becomes difficult when you lose track of the
relationships between nodes.

Think in terms of:

```text
NODE
 ↓
value + prev + next
```

and:

```text
LIST
 ↓
head + tail + length
```

For every operation:

```text
1. Identify affected nodes.
2. Save any links that could be lost.
3. Update next pointers.
4. Update prev pointers.
5. Update head if necessary.
6. Update tail if necessary.
7. Update length if nodes were added/removed.
8. Verify:
      head.prev == null
      tail.next == null
```

Once this mental model is solid, problems such as **reverse, palindrome, partition, reverse-between, and pair swapping**
stop being separate tricks. They become different applications of the same fundamental skill:

> **Manipulating bidirectional node references while preserving list invariants.**
