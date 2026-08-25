# Singly Linked List

A **Singly Linked List (SLL)** is a linear data structure where each node contains:

1. A `value`
2. A reference to the `next` node

Each node only knows about the node **after it**.

---

# 1. Basic Structure

A node looks like:

```text
┌────────┬────────┐
│ value  │  next  │
└────────┴────────┘
```

Example:

```text
NULL
 ↑
HEAD
 ↓
[10] → [20] → [30] → [40] → NULL
                                      ↑
                                     TAIL
```

Each node points only forward:

```text
[10] → [20] → [30]
```

There is no `prev` pointer.

---

# 2. Node Class

```java
class Node {
    int value;
    Node next;

    Node(int value) {
        this.value = value;
        this.next = null;
    }
}
```

When a new node is created:

```text
value = 10
next  = null
```

Visual:

```text
┌────────┬────────┐
│   10   │  null  │
└────────┴────────┘
```

---

# 3. Linked List Variables

The list maintains:

```java
private Node head;
private Node tail;
private int length;
```

### `head`

Points to the first node.

```text
head
 ↓
[10] → [20] → [30] → NULL
```

### `tail`

Points to the last node.

```text
[10] → [20] → [30]
                  ↑
                 tail
```

### `length`

Stores the number of nodes.

```text
[10] → [20] → [30]

length = 3
```

Maintaining `tail` is important because it allows insertion at the end in **O(1)** time.

---

# 4. Constructor

```java
public SinglyLinkedList(int value) {
    Node newNode = new Node(value);
    head = newNode;
    tail = newNode;
    length = 1;
}
```

For a list containing one node:

```text
head
 ↓
[10]
 ↑
tail
```

The node points to:

```text
next = null
```

Therefore:

```text
HEAD
 ↓
[10] → NULL
 ↑
TAIL
```

### Complexity

```text
Time:  O(1)
Space: O(1)
```

---

# 5. Print List

```java
public void printList() {
    Node temp = head;

    System.out.print("HEAD -> ");

    while (temp != null) {
        System.out.print(temp.value + " -> ");
        temp = temp.next;
    }

    System.out.println("NULL");
}
```

We start from `head` and repeatedly follow:

```java
temp = temp.next;
```

Example:

```text
HEAD
 ↓
[10] → [20] → [30] → [40] → NULL
```

Traversal:

```text
10 → 20 → 30 → 40
```

### Complexity

```text
Time:  O(n)
Space: O(1)
```

---

# 6. Get Head

```java
public void getHead() {
    System.out.println("Head: " + head.value);
}
```

Because `head` already points to the first node, no traversal is needed.

### Complexity

```text
Time:  O(1)
Space: O(1)
```

---

# 7. Get Tail

```java
public void getTail() {
    System.out.println("Tail: " + tail.value);
}
```

Because we maintain a `tail` reference, accessing the last node is immediate.

### Complexity

```text
Time:  O(1)
Space: O(1)
```

Without a `tail` pointer, finding the last node would require traversing the entire list:

```text
HEAD → [10] → [20] → [30] → NULL
                         ↑
                       TAIL
```

That would be `O(n)`.

---

# 8. Get Length

```java
public void getLength() {
    System.out.println("Length: " + length);
}
```

Since `length` is maintained after every insertion/removal:

```text
Time:  O(1)
Space: O(1)
```

---

# 9. Get Node at an Index

```java
public Node get(int index) {
    if (index < 0 || index >= length) return null;

    Node temp = head;

    for (int i = 0; i < index; i++) {
        temp = temp.next;
    }

    return temp;
}
```

Unlike an array, a linked list does **not** support direct indexing.

For example:

```text
Index:   0       1       2       3
       [10] →  [20] →  [30] →  [40]
        ↑
       head
```

To get index `3`:

```text
head
 ↓
[10] → [20] → [30] → [40]
  1       2       3
```

We must follow three `next` references.

### Complexity

```text
Best case:  O(1)
Worst case: O(n)
Space:      O(1)
```

---

# 10. Set Value

```java
public boolean set(int index, int value) {
    Node temp = get(index);

    if (temp != null) {
        temp.value = value;
        return true;
    }

    return false;
}
```

Example:

```text
Before:

[10] → [20] → [30]

set(1, 99)

After:

[10] → [99] → [30]
```

Only the value changes.

The links remain unchanged.

### Complexity

Because `set()` calls `get()`:

```text
Time:  O(n)
Space: O(1)
```

---

# 11. Prepend — Insert at Beginning

```java
public void prepend(int value) {
    Node newNode = new Node(value);

    if (length == 0) {
        head = newNode;
        tail = newNode;
    } else {
        newNode.next = head;
        head = newNode;
    }

    length++;
}
```

Suppose:

```text
HEAD
 ↓
[20] → [30] → [40]
                 ↑
                TAIL
```

Insert `10`.

### Step 1 — Create new node

```text
[10]
```

### Step 2 — Point new node to old head

```text
[10] → [20] → [30] → [40]
```

### Step 3 — Move head

```text
HEAD
 ↓
[10] → [20] → [30] → [40]
                         ↑
                        TAIL
```

### Important pattern

```java
newNode.next = head;
head = newNode;
```

### Complexity

```text
Time:  O(1)
Space: O(1)
```

This is one of the major advantages of linked lists.

---

# 12. Append — Insert at End

```java
public void append(int value) {
    Node newNode = new Node(value);

    if (length == 0) {
        head = newNode;
        tail = newNode;
    } else {
        tail.next = newNode;
        tail = newNode;
    }

    length++;
}
```

Before:

```text
[10] → [20] → [30]
 ↑                 ↑
HEAD              TAIL
```

Create `[40]`.

Connect:

```text
tail.next = newNode;
```

Then move:

```text
tail = newNode;
```

Final:

```text
[10] → [20] → [30] → [40]
 ↑                       ↑
HEAD                    TAIL
```

### Important pattern

```java
tail.next = newNode;
tail = newNode;
```

### Complexity

```text
Time:  O(1)
Space: O(1)
```

This is possible because we maintain a `tail` pointer.

---

# 13. Insert at Index

The valid insertion range is:

```text
0 ≤ index ≤ length
```

### IMPORTANT BUG IN CURRENT CODE

Your current validation is:

```java
if (index < 0 || index >= length) return false;
```

This makes:

```java
index == length
```

invalid.

Therefore this condition:

```java
if (index == length) {
    append(value);
}
```

can never execute.

### Correct validation

```java
if (index < 0 || index > length) return false;
```

---

## Why is `index == length` valid?

Suppose:

```text
Index:  0      1      2
       [10] → [20] → [30]
```

Current length:

```text
length = 3
```

Valid indexes for insertion are:

```text
0, 1, 2, 3
```

Index `3` means:

```text
insert after the current last node
```

Result:

```text
[10] → [20] → [30] → [40]
```

---

## Middle insertion

Suppose:

```text
[10] → [20] → [40]
```

Insert `30` at index `2`.

Get the node before the insertion:

```text
temp = get(index - 1)
```

So:

```text
temp = [20]
```

Then:

```java
newNode.next = temp.next;
temp.next = newNode;
```

Visual:

```text
Before:

[20] → [40]

After:

[20] → [30] → [40]
```

### Pointer pattern

```text
BEFORE → AFTER

BEFORE → NEW → AFTER
```

### Complexity

Finding the insertion position:

```text
O(n)
```

Actual pointer manipulation:

```text
O(1)
```

Overall:

```text
Time:  O(n)
Space: O(1)
```

---

# 14. Remove First

```java
public Node removeFirst() {
    if (length == 0) return null;

    Node temp = head;
    head = head.next;
    temp.next = null;

    length--;

    if (length == 0) {
        tail = null;
    }

    return temp;
}
```

Suppose:

```text
HEAD
 ↓
[10] → [20] → [30]
                 ↑
                TAIL
```

Save old head:

```java
Node temp = head;
```

Move head:

```java
head = head.next;
```

Now:

```text
HEAD
 ↓
[20] → [30]
```

Disconnect removed node:

```java
temp.next = null;
```

Final:

```text
Removed:

[10] → NULL

List:

HEAD
 ↓
[20] → [30]
         ↑
        TAIL
```

### Complexity

```text
Time:  O(1)
Space: O(1)
```

---

# 15. Remove Last

```java
public Node removeLast() {
    if (length == 0) return null;

    Node pre = head;
    Node temp = head;

    while (temp.next != null) {
        pre = temp;
        temp = temp.next;
    }

    tail = pre;
    tail.next = null;

    length--;

    if (length == 0) {
        head = null;
        tail = null;
    }

    return temp;
}
```

The problem with a singly linked list is that the tail does **not** know the previous node.

Suppose:

```text
[10] → [20] → [30] → [40]
                       ↑
                      TAIL
```

To remove `[40]`, we need `[30]`.

So we traverse the list using two references:

```text
pre
 ↓
[30] → [40]
         ↑
        temp
```

When `temp` reaches the last node:

```text
pre  = [30]
temp = [40]
```

Then:

```java
tail = pre;
tail.next = null;
```

Result:

```text
[10] → [20] → [30] → NULL
                       ↑
                      TAIL
```

### Complexity

```text
Time:  O(n)
Space: O(1)
```

### Important comparison

Singly Linked List:

```text
removeFirst → O(1)
removeLast  → O(n)
```

Doubly Linked List with `tail`:

```text
removeFirst → O(1)
removeLast  → O(1)
```

The reason is that DLL has:

```text
tail.prev
```

while SLL does not.

---

# 16. Remove at Index

```java
public Node remove(int index)
```

First validate:

```java
if (index < 0 || index >= length) return null;
```

Then handle special cases:

```java
if (index == 0)
    return removeFirst();

if (index == length - 1)
    return removeLast();
```

For a middle node:

```text
BEFORE → REMOVE → AFTER
```

We want:

```text
BEFORE → AFTER
```

Code:

```java
Node prev = get(index - 1);
Node temp = prev.next;

prev.next = temp.next;
temp.next = null;
```

Example:

```text
Before:

[10] → [20] → [30]

Remove index 1:

[10] → [30]
```

### Complexity

```text
Time:  O(n)
Space: O(1)
```

---

# 17. Reverse Linked List

```java
public void reverse() {
    Node temp = head;

    head = tail;
    tail = temp;

    Node after = temp.next;
    Node before = null;

    for (int i = 0; i < length; i++) {
        after = temp.next;
        temp.next = before;
        before = temp;
        temp = after;
    }
}
```

This is one of the most important linked-list algorithms.

---

## Before

```text
HEAD
 ↓
[10] → [20] → [30] → [40] → NULL
                           ↑
                          TAIL
```

After reversing:

```text
HEAD
 ↓
[40] → [30] → [20] → [10] → NULL
                           ↑
                          TAIL
```

---

# 18. The Three-Pointer Technique

The most important part is:

```java
after = temp.next;
temp.next = before;
before = temp;
temp = after;
```

Think of the pointers as:

```text
before       temp        after
  ↓            ↓           ↓
[10]    →    [20]    →    [30]
```

We want to reverse:

```text
[10] ← [20]
```

### Step 1

Save the next node:

```java
after = temp.next;
```

```text
before    temp       after
  ↓         ↓          ↓
NULL      [20]  →    [30]
```

### Step 2

Reverse current pointer:

```java
temp.next = before;
```

```text
[20] → NULL
```

### Step 3

Move `before` forward:

```java
before = temp;
```

### Step 4

Move `temp` forward:

```java
temp = after;
```

Repeat until all nodes are reversed.

---

# 19. Reverse Mental Model

Always remember:

```text
before ← temp → after
```

The key operation is:

```text
temp.next = before
```

But before changing `temp.next`, **save the original next node**:

```text
after = temp.next
```

Otherwise, you lose access to the remaining list.

### Complexity

```text
Time:  O(n)
Space: O(1)
```

This is an **in-place reversal**.

---

# 20. Exercise 1 — Find Middle Node

```java
public Node findMiddleNode() {
    Node slow = head;
    Node fast = head;

    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
    }

    return slow;
}
```

This uses the **Slow and Fast Pointer technique**.

---

## The idea

`slow` moves:

```text
1 step
```

`fast` moves:

```text
2 steps
```

Example:

```text
[10] → [20] → [30] → [40] → [50]
 ↑
slow
 ↑
fast
```

After one iteration:

```text
[10] → [20] → [30] → [40] → [50]
         ↑
        slow
                 ↑
                fast
```

Eventually:

```text
[10] → [20] → [30] → [40] → [50]
                 ↑
                slow
                              ↑
                             fast
```

`slow` reaches the middle when `fast` reaches the end.

### Complexity

```text
Time:  O(n)
Space: O(1)
```

### Important pattern

```text
slow = slow.next;
fast = fast.next.next;
```

This technique is used in many linked-list problems.

---

# 21. Exercise 2 — Detect Loop

```java
public boolean hasLoop() {
    Node slow = head;
    Node fast = head;

    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;

        if (slow == fast) {
            return true;
        }
    }

    return false;
}
```

This is **Floyd's Cycle Detection Algorithm**.

---

## Normal list

```text
[10] → [20] → [30] → NULL
```

`fast` eventually reaches:

```text
null
```

Therefore:

```text
No loop
```

---

## List with a loop

```text
[10] → [20] → [30] → [40]
                ↑       ↓
                └───────┘
```

Now `fast` and `slow` eventually meet.

```text
slow == fast
```

Therefore:

```text
Loop exists
```

---

## Why does this work?

Imagine two runners running around a circular track.

One runs at:

```text
1 step
```

The other runs at:

```text
2 steps
```

If there is a cycle, the faster runner eventually catches the slower runner.

### Complexity

```text
Time:  O(n)
Space: O(1)
```

No `HashSet` is needed.

---

# 22. Exercise 3 — Kth Node From End

```java
public Node findKthFromEnd(int k)
```

This also uses two pointers.

The idea:

```text
fast
 ↓
        k nodes ahead
slow
 ↓
```

First move `fast` forward by `k` nodes.

Then move both:

```text
slow = slow.next
fast = fast.next
```

until `fast == null`.

At that point:

```text
slow = kth node from the end
```

---

## Example

List:

```text
[10] → [20] → [30] → [40] → [50]
```

Find:

```text
k = 2
```

We want:

```text
[10] → [20] → [30] → [40] → [50]
                            ↑
                           2nd
                         from end
```

Answer:

```text
40
```

The important trick is maintaining a **fixed distance of k nodes** between `fast` and `slow`.

### Complexity

```text
Time:  O(n)
Space: O(1)
```

---

# 23. Exercise 4 — Remove Duplicates

Your implementation uses:

```java
Set<Integer> values = new HashSet<>();
```

The basic idea:

```text
current node
     ↓
Have we already seen this value?
     ↓
 YES              NO
  ↓                ↓
remove          remember it
```

Example:

```text
[10] → [20] → [10] → [30] → [20]
```

The `HashSet` stores:

```text
{10, 20, 30}
```

When another `10` appears:

```text
values.contains(10) == true
```

So remove that node.

---

## Pointer idea

You maintain:

```text
previous
current
```

Example:

```text
previous   current
   ↓          ↓
[20] →      [20]
```

When duplicate is found:

```java
previous.next = current.next;
```

This skips the duplicate:

```text
BEFORE → DUPLICATE → AFTER
```

becomes:

```text
BEFORE → AFTER
```

### Complexity

With `HashSet`:

```text
Time:  O(n)
Space: O(n)
```

Average `HashSet.contains()` and `add()` are `O(1)`.

---

# 24. Remove Duplicates Without Extra Space

Your commented-out implementation uses a **runner**.

The idea:

```text
current
   ↓
[10] → [20] → [10] → [30]
         ↑
       runner
```

For every `current`, scan the remaining list using `runner`.

This avoids the `HashSet`.

### Complexity

```text
Time:  O(n²)
Space: O(1)
```

Comparison:

| Method  |  Time | Space |
| ------- | ----: | ----: |
| HashSet |  O(n) |  O(n) |
| Runner  | O(n²) |  O(1) |

This is an important interview trade-off:

> **Use extra memory to reduce time, or save memory at the cost of more time.**

---

# 25. Exercise 5 — Binary to Decimal

```java
public int binaryToDecimal() {
    Node current = head;
    int num = 0;

    while (current != null) {
        num = num * 2 + current.value;
        current = current.next;
    }

    return num;
}
```

Suppose the list represents:

```text
[1] → [0] → [1] → [1]
```

This represents:

```text
1011
```

Start:

```text
num = 0
```

Process each bit:

```text
num = num * 2 + bit
```

### Step-by-step

```text
0

0 × 2 + 1 = 1

1 × 2 + 0 = 2

2 × 2 + 1 = 5

5 × 2 + 1 = 11
```

Answer:

```text
1011₂ = 11₁₀
```

### Why `num * 2`?

Every time we shift a binary number left by one position, its value doubles.

### Complexity

```text
Time:  O(n)
Space: O(1)
```

---

# 26. Exercise 6 — Partition List

```java
public void partitionList(int x)
```

Goal:

Move nodes so that:

```text
values < x
```

come before:

```text
values >= x
```

while preserving their relative order.

Example:

```text
Input:

[3] → [5] → [8] → [5] → [10] → [2]

x = 5
```

Partition:

```text
Less than 5:

[3] → [2]

Greater/equal to 5:

[5] → [8] → [5] → [10]
```

Combine:

```text
[3] → [2] → [5] → [8] → [5] → [10]
```

---

# 27. Why Dummy Nodes?

You create:

```java
Node dummy1 = new Node(0);
Node dummy2 = new Node(0);
```

Think of them as two temporary lists:

```text
dummy1
  ↓
LESS THAN x

dummy2
  ↓
GREATER OR EQUAL x
```

You maintain:

```java
Node prev1 = dummy1;
Node prev2 = dummy2;
```

Then:

```text
current.value < x
        ↓
    list 1

current.value >= x
        ↓
    list 2
```

At the end:

```java
prev2.next = null;
prev1.next = dummy2.next;
head = dummy1.next;
```

Conceptually:

```text
LIST 1                     LIST 2

[3] → [2] → NULL           [5] → [8] → [10] → NULL
       ↓
       └───────────────────────────→
```

Final:

```text
[3] → [2] → [5] → [8] → [10]
```

### Why dummy nodes are useful

They eliminate special cases for inserting the **first node** into each partition.

Instead of asking:

```text
"Is this the first node?"
```

you can always do:

```java
prev1.next = current;
prev1 = current;
```

---

# 28. Partition Complexity

Every node is visited once.

```text
Time:  O(n)
Space: O(1)
```

The dummy nodes are only constant extra memory.

---

# 29. Exercise 7 — Reverse Between

```java
public void reverseBetween(int startIndex, int endIndex)
```

This reverses only a portion of the list.

Example:

```text
Input:

[10] → [20] → [30] → [40] → [50]

startIndex = 1
endIndex = 3
```

Reverse:

```text
20 → 30 → 40
```

Result:

```text
[10] → [40] → [30] → [20] → [50]
```

---

# 30. Dummy Node in Reverse Between

You create:

```java
Node dummy = new Node(0);
dummy.next = head;
```

Visual:

```text
dummy → [10] → [20] → [30] → [40] → [50]
```

The dummy node makes reversing from index `0` much easier because there is always a node before the section.

---

# 31. Core Reversal Technique

You repeatedly take the node after `current` and move it to the front of the section.

Suppose:

```text
prev → [20] → [30] → [40]
```

Move `[30]`:

```text
prev → [30] → [20] → [40]
```

Then move `[40]`:

```text
prev → [40] → [30] → [20]
```

This is an example of **head insertion**.

The important code:

```java
Node nodeToMove = current.next;

current.next = nodeToMove.next;

nodeToMove.next = prev.next;

prev.next = nodeToMove;
```

### Complexity

```text
Time:  O(n)
Space: O(1)
```

---

# 32. Exercise 8 — Swap Nodes in Pairs

```java
public void swapPairs()
```

Goal:

```text
[1] → [2] → [3] → [4]
```

becomes:

```text
[2] → [1] → [4] → [3]
```

The nodes themselves are swapped.

We are **not swapping values**.

---

# 33. Pair Swapping

Start:

```text
prev → first → second → next
```

Suppose:

```text
dummy → [1] → [2] → [3] → [4]
          ↑      ↑
        first  second
```

After swapping:

```text
dummy → [2] → [1] → [3] → [4]
```

The important operations are:

```java
prev.next = second;
first.next = second.next;
second.next = first;
```

Visual:

```text
Before:

prev → first → second → next


After:

prev → second → first → next
```

Then move forward:

```java
prev = first;
first = first.next;
```

---

# 34. Dummy Nodes — Important Pattern

Dummy nodes appear in several linked-list problems:

```text
partitionList()
reverseBetween()
swapPairs()
```

A dummy node is a fake node placed before the actual head:

```text
dummy → HEAD → ...
```

It simplifies operations involving the first node because you always have a previous node.

### Remember

Dummy node:

```text
is not part of the actual answer
```

At the end:

```java
head = dummy.next;
```

---

# 35. Singly Linked List Complexity Cheat Sheet

| Operation                  |  Time | Space |
| -------------------------- | ----: | ----: |
| Create list                |  O(1) |  O(1) |
| Get head                   |  O(1) |  O(1) |
| Get tail                   |  O(1) |  O(1) |
| Get length                 |  O(1) |  O(1) |
| Print list                 |  O(n) |  O(1) |
| Get(index)                 |  O(n) |  O(1) |
| Set(index)                 |  O(n) |  O(1) |
| Prepend                    |  O(1) |  O(1) |
| Append                     |  O(1) |  O(1) |
| Insert at beginning        |  O(1) |  O(1) |
| Insert at end              |  O(1) |  O(1) |
| Insert at middle           |  O(n) |  O(1) |
| Remove first               |  O(1) |  O(1) |
| Remove last                |  O(n) |  O(1) |
| Remove middle              |  O(n) |  O(1) |
| Reverse                    |  O(n) |  O(1) |
| Find middle                |  O(n) |  O(1) |
| Detect loop                |  O(n) |  O(1) |
| Kth from end               |  O(n) |  O(1) |
| Remove duplicates — Set    |  O(n) |  O(n) |
| Remove duplicates — Runner | O(n²) |  O(1) |
| Binary to decimal          |  O(n) |  O(1) |
| Partition                  |  O(n) |  O(1) |
| Reverse between            |  O(n) |  O(1) |
| Swap pairs                 |  O(n) |  O(1) |

---

# 36. Singly vs Doubly Linked List

## Singly

```text
[10] → [20] → [30] → NULL
```

Node:

```text
[value | next]
```

## Doubly

```text
NULL ← [10] ⇄ [20] ⇄ [30] → NULL
```

Node:

```text
[prev | value | next]
```

---

# 37. Important Difference

| Operation          | Singly |        Doubly |
| ------------------ | -----: | ------------: |
| Insert beginning   |   O(1) |          O(1) |
| Insert end         |  O(1)* |          O(1) |
| Remove beginning   |   O(1) |          O(1) |
| Remove end         |   O(n) |          O(1) |
| Search by index    |   O(n) |          O(n) |
| Backward traversal |      ❌ |             ✅ |
| Extra pointer      | `next` | `next + prev` |
| Memory             |  Lower |        Higher |

`*` Append is O(1) because this implementation maintains a `tail` pointer.

---

# 38. Most Important Linked List Patterns

## Pattern 1 — Traversal

```java
Node current = head;

while (current != null) {
    // work
    current = current.next;
}
```

Visual:

```text
current
   ↓
[10] → [20] → [30] → NULL
```

---

## Pattern 2 — Previous + Current

Useful for deletion:

```text
previous → current → next
```

To remove `current`:

```java
previous.next = current.next;
```

Visual:

```text
BEFORE → CURRENT → AFTER

BEFORE → AFTER
```

---

## Pattern 3 — Slow + Fast

Useful for:

```text
Find middle
Detect cycle
Other two-pointer problems
```

```text
slow → 1 step
fast → 2 steps
```

---

## Pattern 4 — Fixed Distance Pointers

Useful for:

```text
Kth node from end
```

```text
slow
 ↓
[10] → [20] → [30] → [40] → [50]
                         ↑
                        fast
```

Maintain a gap of `k`.

---

## Pattern 5 — Dummy Node

Useful for:

```text
Partition
Reverse between
Swap pairs
Insertion/deletion near head
```

Visual:

```text
dummy → head → ...
```

---

## Pattern 6 — Three Pointers for Reversal

```text
before ← current → after
```

Code:

```java
after = current.next;
current.next = before;
before = current;
current = after;
```

This is one of the most important linked-list patterns to memorize.

---

# 39. Interview Mental Checklist

When you see a linked-list problem, ask:

```text
1. Do I need to traverse the list?
2. Do I need previous + current?
3. Do I need slow + fast?
4. Do I need two pointers with a fixed gap?
5. Would a dummy node simplify the head case?
6. Am I changing links or only values?
7. Does head change?
8. Does tail change?
9. Does length change?
10. Am I accidentally losing the rest of the list?
```

---

# 40. The Most Important Rule

When changing linked-list pointers:

> **Save the reference before overwriting it.**

For example, while reversing:

```java
after = current.next;
current.next = before;
```

If you do:

```java
current.next = before;
```

first, without saving the original `next`, you may lose access to the remaining list.

Think:

```text
SAVE → CHANGE → MOVE
```

---

# 41. Code Quality Notes for This Implementation

The overall implementation is good for learning, but these changes should be made.

### Fix 1 — `insert()`

Current:

```java
if (index < 0 || index >= length) return false;
```

Correct:

```java
if (index < 0 || index > length) return false;
```

Because:

```text
index == length
```

is a valid insertion position.

---

### Fix 2 — `set()`

Current:

```java
while (temp != null) {
    temp.value = value;
    return true;
}
```

Better:

```java
if (temp != null) {
    temp.value = value;
    return true;
}
```

There is no reason for a loop because `get()` already returns exactly one node.

---

### Fix 3 — Extra semicolon

Current:

```java
if (head == null) return;;
```

Better:

```java
if (head == null) return;
```

---

### Fix 4 — Empty constructor

Your current class requires an initial value:

```java
new SinglyLinkedList(10);
```

Eventually consider adding:

```java
public SinglyLinkedList() {
    head = null;
    tail = null;
    length = 0;
}
```

Then:

```java
SinglyLinkedList list = new SinglyLinkedList();
list.append(10);
list.append(20);
```

---

### Fix 5 — Tail maintenance

Be careful with algorithms that rearrange links.

The following methods can change the last node:

```text
removeDuplicates()
partitionList()
reverseBetween()
swapPairs()
```

Your current implementations don't consistently update `tail`.

For a learning implementation, this is an important lesson:

> Whenever nodes are rearranged, always ask whether `head` or `tail` has changed.

---

# 42. Final Mental Picture

A singly linked list is fundamentally:

```text
HEAD
 ↓
[10] → [20] → [30] → [40] → NULL
                              ↑
                             TAIL
```

The entire data structure is built around one pointer:

```text
current.next
```

Most linked-list problems are about changing these arrows safely.

### Insertion

```text
BEFORE → AFTER

BEFORE → NEW → AFTER
```

### Deletion

```text
BEFORE → REMOVE → AFTER

BEFORE → AFTER
```

### Reversal

```text
10 → 20 → 30

10 ← 20 ← 30
```

### Slow/Fast

```text
slow  → 1 step
fast  → 2 steps
```

### Kth from end

```text
fast
  ↓
<--- k distance --->
                    ↓
                   slow
```

### Dummy node

```text
dummy → HEAD → ...
```

If you understand these pointer patterns, you understand the core of the **Singly Linked List** rather than just memorizing individual methods.
