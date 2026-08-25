# Doubly Linked List

A **Doubly Linked List (DLL)** is a linked list where every node stores:

1. Its own `value`
2. A reference to the **next** node
3. A reference to the **previous** node

Because every node knows both its previous and next node, we can traverse the list in **both directions**.

---

## 1. Node Structure

Each node contains three important parts:

```text
┌────────┬────────┬────────┐
│  prev  │ value  │  next  │
└────────┴────────┴────────┘
```

Example:

```text
        prev             next
         ↓                ↓
      ┌───────┐        ┌───────┐
NULL ←│   10  │ ⇄────⇄ │  20   │ ⇄────⇄ ...
      └───────┘        └───────┘
```

A node is defined as:

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

### Important

For the first node:

```text
head.prev = null
```

For the last node:

```text
tail.next = null
```

So a complete list looks like:

```text
NULL ← [10] ⇄ [20] ⇄ [30] ⇄ [40] → NULL
        ↑                         ↑
       head                      tail
```

---

# 2. Doubly Linked List Properties

The list maintains three important variables:

```java
private Node head;
private Node tail;
private int length;
```

### `head`

Points to the **first node**.

```text
head
 ↓
[10] ⇄ [20] ⇄ [30]
```

### `tail`

Points to the **last node**.

```text
[10] ⇄ [20] ⇄ [30]
                  ↑
                 tail
```

### `length`

Stores the number of nodes.

```text
[10] ⇄ [20] ⇄ [30]

length = 3
```

---

# 3. Why Doubly Linked List?

A singly linked list has:

```text
[10] → [20] → [30] → NULL
```

A doubly linked list has:

```text
NULL ← [10] ⇄ [20] ⇄ [30] → NULL
```

The major advantage is that every node knows its **previous node**.

Therefore:

```text
Forward traversal:
10 → 20 → 30

Backward traversal:
30 → 20 → 10
```

This also allows us to start searching from either `head` or `tail`.

---

# 4. Constructor

```java
public DoublyLinkedList(int value) {
    Node newNode = new Node(value);
    head = newNode;
    tail = newNode;
    length = 1;
}
```

When the list contains only one node:

```text
head
 ↓
[10]
 ↑
tail
```

Both `head` and `tail` point to the same node.

The node itself has:

```text
prev = null
next = null
```

Therefore:

```text
NULL ← [10] → NULL
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

    System.out.print("HEAD <-> ");

    while (temp != null) {
        System.out.print(temp.value + " <-> ");
        temp = temp.next;
    }

    System.out.println(" NULL");
}
```

We start from `head` and repeatedly follow:

```text
temp = temp.next
```

Example:

```text
HEAD
 ↓
[10] ⇄ [20] ⇄ [30] ⇄ [40]
                         ↓
                        NULL
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

`temp` is only a reference, so we don't create `n` new nodes.

---

# 6. Get Head

```java
public void getHead() {
    System.out.println("Head: " + head.value);
}
```

`head` already points to the first node.

Therefore no traversal is required.

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

Because we maintain a `tail` pointer, we don't need to traverse the list.

### Complexity

```text
Time:  O(1)
Space: O(1)
```

### Important DLL advantage

If we didn't maintain `tail`, finding the last node would require:

```text
head → next → next → next → ...
```

which would take:

```text
O(n)
```

Maintaining `tail` makes it:

```text
O(1)
```

---

# 8. Get Length

```java
public void getLength() {
    System.out.println("Length: " + length);
}
```

Since `length` is maintained during every insertion/removal, we don't need to count the nodes.

### Complexity

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

    if (index < length / 2) {
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
    } else {
        temp = tail;

        for (int i = length - 1; i > index; i--) {
            temp = temp.prev;
        }
    }

    return temp;
}
```

This is one of the most important parts of a **doubly** linked list.

Instead of always starting from `head`, we choose the closest side.

---

## Example

Suppose:

```text
Index:   0      1      2      3      4      5
       [10] ⇄ [20] ⇄ [30] ⇄ [40] ⇄ [50] ⇄ [60]
        ↑                                      ↑
       head                                   tail
```

If we want index `1`:

```text
Start from head

head → [10] → [20]
```

If we want index `4`:

```text
Start from tail

tail → [60] → [50]
```

This works because:

```java
temp = temp.next;
```

moves forward, while:

```java
temp = temp.prev;
```

moves backward.

### Why this condition?

```java
if (index < length / 2)
```

If the index is in the first half:

```text
start from head
```

Otherwise:

```text
start from tail
```

### Complexity

```text
Best case:    O(1)
Average case: O(n)
Worst case:   O(n)
Space:        O(1)
```

More precisely, traversal takes at most approximately `n/2` steps, but Big-O ignores constants:

```text
O(n/2) = O(n)
```

---

# 10. Set Value at an Index

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

The operation is:

```text
Find node
   ↓
Change value
```

Example:

```text
Before:

[10] ⇄ [20] ⇄ [30]

set(1, 99)

After:

[10] ⇄ [99] ⇄ [30]
```

The links don't change.

Only:

```java
temp.value
```

changes.

### Complexity

Because `set()` calls `get()`:

```text
Time:  O(n)
Space: O(1)
```

Best case can be `O(1)` if the requested node is immediately accessible.

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
        head.prev = newNode;
        head = newNode;
    }

    length++;
}
```

Suppose:

```text
NULL ← [20] ⇄ [30] → NULL
        ↑         ↑
       head      tail
```

We want to insert `10`.

### Step 1

Create:

```text
[10]
```

### Step 2

Connect new node to old head:

```text
[10].next = [20]
```

### Step 3

Connect old head back to new node:

```text
[20].prev = [10]
```

### Step 4

Move head:

```text
head = newNode
```

Final result:

```text
NULL ← [10] ⇄ [20] ⇄ [30] → NULL
        ↑                   ↑
       head                tail
```

### Key pattern

When inserting at the beginning:

```text
newNode.next = head
head.prev = newNode
head = newNode
```

### Complexity

```text
Time:  O(1)
Space: O(1)
```

---

# 12. Append — Insert at End

```java
public void append(int value) {
    Node newNode = new Node(value);

    if (length == 0) {
        head = newNode;
        tail = newNode;
    } else {
        newNode.prev = tail;
        tail.next = newNode;
        tail = newNode;
    }

    length++;
}
```

Suppose:

```text
[10] ⇄ [20] ⇄ [30]
 ↑                 ↑
head              tail
```

Insert `40`.

### Step 1

Create:

```text
[40]
```

### Step 2

Connect new node to old tail:

```java
newNode.prev = tail;
```

### Step 3

Connect old tail to new node:

```java
tail.next = newNode;
```

### Step 4

Move tail:

```java
tail = newNode;
```

Final:

```text
[10] ⇄ [20] ⇄ [30] ⇄ [40]
 ↑                         ↑
head                      tail
```

### Key pattern

```text
newNode.prev = tail
tail.next = newNode
tail = newNode
```

### Complexity

```text
Time:  O(1)
Space: O(1)
```

---

# 13. Insert at a Particular Index

```java
public boolean insert(int index, int value)
```

Valid insertion indexes are:

```text
0 ≤ index ≤ length
```

Notice:

```text
index == length
```

is valid because we are inserting **after the current last node**.

---

## Example

Current list:

```text
[10] ⇄ [20] ⇄ [40]
```

Insert `30` at index `2`.

We want:

```text
[10] ⇄ [20] ⇄ [30] ⇄ [40]
```

The nodes around the insertion point are:

```text
before              after
  ↓                   ↓
[20] ⇄────────────── [40]
```

Create:

```text
[30]
```

Connect:

```text
newNode.prev = before
newNode.next = after
```

Then connect both surrounding nodes:

```text
before.next = newNode
after.prev = newNode
```

Final:

```text
[10] ⇄ [20] ⇄ [30] ⇄ [40]
             ↑
          newNode
```

### Four important pointer updates

Middle insertion requires:

```java
newNode.prev = before;
newNode.next = after;

before.next = newNode;
after.prev = newNode;
```

Remember:

> **New node connects to both sides, and both neighboring nodes connect back to the new node.**

### Complexity

`insert()` calls `get(index - 1)`, so:

```text
Time:  O(n)
Space: O(1)
```

Insertion itself is `O(1)` once the correct position is known.

---

# 14. Remove First Node

```java
public Node removeFirst() {
    if (length == 0) return null;

    Node temp = head;

    if (length == 1) {
        head = null;
        tail = null;
    } else {
        head = head.next;
        head.prev = null;
        temp.next = null;
    }

    length--;

    return temp;
}
```

Suppose:

```text
NULL ← [10] ⇄ [20] ⇄ [30] → NULL
        ↑
       head
```

We want to remove `10`.

First:

```text
temp = head
```

Then move head:

```text
head = head.next
```

Now:

```text
        head
         ↓
NULL ← [20] ⇄ [30]
```

Remove the backward reference:

```java
head.prev = null;
```

Disconnect the removed node:

```java
temp.next = null;
```

Final:

```text
removed:

[10]

list:

NULL ← [20] ⇄ [30] → NULL
        ↑         ↑
       head      tail
```

### Complexity

```text
Time:  O(1)
Space: O(1)
```

---

# 15. Remove Last Node

```java
public Node removeLast() {
    if (length == 0) return null;

    Node temp = tail;

    if (length == 1) {
        head = null;
        tail = null;
    } else {
        tail = tail.prev;
        tail.next = null;
        temp.prev = null;
    }

    length--;

    return temp;
}
```

This is the reverse of `removeFirst()`.

Suppose:

```text
[10] ⇄ [20] ⇄ [30]
                  ↑
                 tail
```

Save old tail:

```java
Node temp = tail;
```

Move tail backward:

```java
tail = tail.prev;
```

Remove the new tail's `next` reference:

```java
tail.next = null;
```

Disconnect removed node:

```java
temp.prev = null;
```

Final:

```text
[10] ⇄ [20] → NULL
 ↑         ↑
head      tail
```

### Complexity

```text
Time:  O(1)
Space: O(1)
```

This is one of the major advantages of a doubly linked list with a `tail` pointer.

---

# 16. Remove Node at Index

```java
public Node remove(int index)
```

First validate:

```java
if (index < 0 || index >= length) return null;
```

Then handle special cases:

```java
if (index == 0) return removeFirst();

if (index == length - 1) return removeLast();
```

Why?

Because removing the first or last node requires updating `head` or `tail`.

For a middle node:

```java
Node temp = get(index);
```

Suppose:

```text
before       temp       after
  ↓            ↓          ↓
[10] ⇄       [20]       ⇄ [30]
```

We want to remove `[20]`.

Connect `before` directly to `after`:

```java
temp.prev.next = temp.next;
```

Connect `after` back to `before`:

```java
temp.next.prev = temp.prev;
```

Now:

```text
[10] ⇄ [30]
```

Finally disconnect the removed node:

```java
temp.next = null;
temp.prev = null;
```

Result:

```text
List:

[10] ⇄ [30]

Removed node:

[20]
```

### Four-way pointer idea

For a middle-node deletion:

```text
BEFORE ⇄ TEMP ⇄ AFTER
```

becomes:

```text
BEFORE ⇄ AFTER

TEMP = isolated
```

### Complexity

Because `get(index)` is used:

```text
Time:  O(n)
Space: O(1)
```

Once `temp` is known, the actual removal is:

```text
O(1)
```

---

# 17. Empty List Case

When:

```text
length == 0
```

we should have:

```text
head = null
tail = null
```

Visual:

```text
head
 ↓
null

tail
 ↓
null
```

Your implementation correctly handles this in:

```text
prepend()
append()
removeFirst()
removeLast()
```

---

# 18. One-Node List Case

For a list containing one node:

```text
head
 ↓
[10]
 ↑
tail
```

The node has:

```text
prev = null
next = null
```

If we remove it:

```text
head = null
tail = null
length = 0
```

This case is important because removing the only node changes **both** `head` and `tail`.

---

# 19. Complexity Cheat Sheet

| Operation           | Time | Space |
| ------------------- | ---: | ----: |
| Create list         | O(1) |  O(1) |
| Get head            | O(1) |  O(1) |
| Get tail            | O(1) |  O(1) |
| Get length          | O(1) |  O(1) |
| Print list          | O(n) |  O(1) |
| Get(index)          | O(n) |  O(1) |
| Set(index)          | O(n) |  O(1) |
| Prepend             | O(1) |  O(1) |
| Append              | O(1) |  O(1) |
| Insert at beginning | O(1) |  O(1) |
| Insert at end       | O(1) |  O(1) |
| Insert at middle    | O(n) |  O(1) |
| Remove first        | O(1) |  O(1) |
| Remove last         | O(1) |  O(1) |
| Remove middle       | O(n) |  O(1) |

### Important distinction

The actual pointer manipulation for insertion/removal is usually:

```text
O(1)
```

But **finding the node** can take:

```text
O(n)
```

Therefore:

```text
insert(index) → O(n)
remove(index) → O(n)
```

when the index is arbitrary.

---

# 20. Doubly vs Singly Linked List

### Singly Linked List

```text
[10] → [20] → [30] → NULL
```

Each node has:

```text
value + next
```

### Doubly Linked List

```text
NULL ← [10] ⇄ [20] ⇄ [30] → NULL
```

Each node has:

```text
value + next + prev
```

### Main advantage

DLL can move:

```text
FORWARD
head → next → next → ...

BACKWARD
tail → prev → prev → ...
```

### Main disadvantage

Every node needs an additional:

```text
prev
```

reference, so it uses more memory than a singly linked list.

---

# 21. Most Important Pointer Patterns

## Add to beginning

```java
newNode.next = head;
head.prev = newNode;
head = newNode;
```

Think:

```text
NEW → OLD HEAD
NEW ← OLD HEAD
HEAD = NEW
```

---

## Add to end

```java
newNode.prev = tail;
tail.next = newNode;
tail = newNode;
```

Think:

```text
OLD TAIL ← NEW
OLD TAIL → NEW
TAIL = NEW
```

---

## Remove first

```java
head = head.next;
head.prev = null;
```

Think:

```text
OLD HEAD → NEW HEAD
             ↑
          prev = null
```

---

## Remove last

```java
tail = tail.prev;
tail.next = null;
```

Think:

```text
NEW TAIL ← OLD TAIL
NEW TAIL.next = null
```

---

## Insert in middle

```text
BEFORE ⇄ AFTER
```

becomes:

```text
BEFORE ⇄ NEW ⇄ AFTER
```

Code:

```java
newNode.prev = before;
newNode.next = after;

before.next = newNode;
after.prev = newNode;
```

---

## Remove from middle

```text
BEFORE ⇄ REMOVE ⇄ AFTER
```

becomes:

```text
BEFORE ⇄ AFTER
```

Code:

```java
temp.prev.next = temp.next;
temp.next.prev = temp.prev;
```

Then isolate:

```java
temp.next = null;
temp.prev = null;
```

---

# 22. The Four-Link Rule

For a middle insertion, remember:

```text
             NEW
            /   \
           ↓     ↓
       BEFORE   AFTER
```

Four references need to be correct:

```text
NEW.prev   → BEFORE
NEW.next   → AFTER
BEFORE.next → NEW
AFTER.prev  → NEW
```

For a middle deletion:

```text
BEFORE ⇄ REMOVE ⇄ AFTER
```

Change two links:

```text
BEFORE.next → AFTER
AFTER.prev  → BEFORE
```

Then remove the deleted node's links.

This is the most important pointer manipulation concept in a doubly linked list.

---

# 23. Common Mistakes to Avoid

### Mistake 1: Forgetting `prev`

Bad:

```java
newNode.next = head;
head = newNode;
```

In a DLL, we also need:

```java
head.prev = newNode;
```

---

### Mistake 2: Forgetting `tail`

When appending:

```java
tail = newNode;
```

must be updated.

---

### Mistake 3: Forgetting `head`

When prepending:

```java
head = newNode;
```

must be updated.

---

### Mistake 4: Forgetting `length`

Every successful insertion:

```java
length++;
```

Every successful removal:

```java
length--;
```

---

### Mistake 5: Not handling a one-node list

For:

```text
[10]
```

removing the node must produce:

```text
head = null
tail = null
```

---

### Mistake 6: Leaving stale references

After removing a node:

```java
temp.next = null;
temp.prev = null;
```

This isn't always required for garbage collection once the node is otherwise unreachable, but explicitly disconnecting it makes the data structure cleaner and easier to debug.

---

# 24. Mental Model for Revision

Whenever you modify a doubly linked list, ask these questions:

```text
1. Who is before the node?
2. Who is after the node?
3. Does NEW.prev point correctly?
4. Does NEW.next point correctly?
5. Does BEFORE.next point correctly?
6. Does AFTER.prev point correctly?
7. Did head change?
8. Did tail change?
9. Did length change?
```

For every operation, think about:

```text
        prev        next
         ↓           ↓
     [ BEFORE ] ⇄ [ CURRENT ] ⇄ [ AFTER ]
```

Then decide which links must change.

---

# 25. Overall Code Assessment

Your code is **good for a learning implementation**.

The core pointer logic is correct, including:

```text
head / tail management
prev / next management
empty list handling
one-node handling
middle insertion
middle deletion
bidirectional get()
```

The most important improvement I'd make next is adding an **empty constructor** and eventually separating the internal `Node` implementation from the public API.

For learning data structures, don't worry too much about making the class production-ready yet. Focus on understanding the pointer transitions.

### Final mental picture

```text
                 DOUBLY LINKED LIST

      forward →
NULL ← [10] ⇄ [20] ⇄ [30] ⇄ [40] → NULL
       ↑                              ↑
      HEAD                           TAIL
       └──────────── backward ←──────┘


Each node:

        prev        next
         ↓           ↓
      ┌───────┬───────┬───────┐
      │ prev  │ value │ next  │
      └───────┴───────┴───────┘


Main advantages:

• O(1) prepend
• O(1) append
• O(1) removeFirst
• O(1) removeLast
• Can traverse in both directions
• Can search from head OR tail


Main disadvantage:

• Extra memory is required for `prev`
```
