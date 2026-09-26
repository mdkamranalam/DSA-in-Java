# Binary Search Tree (BST)

## 1. What is a Binary Search Tree?

A **Binary Search Tree (BST)** is a binary tree in which every node follows an ordering rule:

```text
                Node
               /    \
              /      \
   smaller values   larger values
```

For every node:

```text
All values in left subtree  <  Node value
All values in right subtree >  Node value
```

For example:

```text
                 50
                /  \
              30    70
             / \    / \
           20  40  60  80
```

For node `50`:

```text
Left subtree  = 20, 30, 40
Right subtree = 60, 70, 80
```

Therefore:

```text
20 < 30 < 40 < 50 < 60 < 70 < 80
```

This ordering property is what makes searching in a BST efficient when the tree is reasonably balanced.

---

# 2. Binary Tree vs Binary Search Tree

A **Binary Tree** only guarantees that each node has at most two children.

```text
        10
       /  \
      50   5
```

This is a valid binary tree.

But it is NOT a valid BST because:

```text
50 > 10
```

yet `50` is in the left subtree.

A BST must maintain:

```text
left < node < right
```

Example of a valid BST:

```text
        10
       /  \
      5    50
```

---

# 3. Important BST Terminology

## Node

A node stores:

1. A value
2. A reference to the left child
3. A reference to the right child

Conceptually:

```text
        +---------+
        | value   |
        +---------+
        | left    |
        +---------+
        | right   |
        +---------+
```

Your Java implementation:

```java
class Node {
    int value;
    Node left;
    Node right;

    Node(int value) {
        this.value = value;
    }
}
```

---

## Root

The **root** is the topmost node of the tree.

Example:

```text
        50        <-- root
       /  \
     30    70
```

Your implementation stores it using:

```java
Node root;
```

If the tree is empty:

```java
root == null
```

---

## Parent

A node directly above another node is its parent.

```text
        50
       /
      30
```

`50` is the parent of `30`.

---

## Child

A node directly below another node is its child.

```text
        50
       /
      30
```

`30` is the left child of `50`.

---

## Leaf Node

A node with no children is called a **leaf**.

```text
        50
       /  \
     30    70
```

`30` and `70` are leaf nodes.

Their references are:

```java
left == null
right == null
```

---

## Subtree

A node together with all of its descendants forms a subtree.

For:

```text
                 50
                /  \
              30    70
             / \    / \
           20  40  60  80
```

The subtree rooted at `30` is:

```text
        30
       /  \
     20    40
```

---

# 4. Your BST Node Structure

Your code:

```java
class Node {
    int value;
    Node left;
    Node right;

    Node(int value) {
        this.value = value;
    }
}
```

Each node contains:

```text
value
  |
  +---- left  ---> another Node
  |
  +---- right ---> another Node
```

For example:

```java
Node node = new Node(50);
```

Initially:

```text
value = 50
left  = null
right = null
```

Visual representation:

```text
       +------+
       |  50  |
       +------+
       | left | ---> null
       +------+
       |right | ---> null
       +------+
```

When children are added:

```text
          50
         /  \
        30   70
```

the references become:

```text
50.left  ---> 30
50.right ---> 70
```

---

# 5. Empty BST

Initially:

```java
Node root;
```

Since no value has been inserted:

```text
root
 |
 v
null
```

The tree is empty.

Your code checks this using:

```java
if (root == null)
```

---

# 6. Insert Operation

The purpose of `insert()` is to add a new value while maintaining the BST property.

Your method:

```java
public boolean insert(int value)
```

returns:

```text
true  -> insertion succeeded
false -> value already exists
```

Your implementation does not allow duplicate values.

---

# 7. Insert: Basic Algorithm

Suppose the tree is:

```text
        50
       /  \
     30    70
```

We want to insert:

```text
40
```

Start at the root:

```text
40 vs 50
```

Since:

```text
40 < 50
```

go left.

```text
        50
       /
     30
```

Now compare:

```text
40 vs 30
```

Since:

```text
40 > 30
```

go right.

```text
        50
       /
     30
       \
        40
```

`30.right` is `null`, so insert `40` there.

Final tree:

```text
        50
       /  \
     30    70
       \
        40
```

---

# 8. Insert Pseudocode

```text
INSERT(value):

    create a new node

    if root is null:
        root = new node
        return true

    current = root

    while true:

        if value == current.value:
            return false

        if value < current.value:

            if current.left is null:
                current.left = new node
                return true

            current = current.left

        else:

            if current.right is null:
                current.right = new node
                return true

            current = current.right
```

---

# 9. Your Insert Code Explained

Your code:

```java
public boolean insert(int value) {
    Node newNode = new Node(value);

    if (root == null) {
        root = newNode;
        return true;
    }

    Node temp = root;

    while (true) {
        if (newNode.value == temp.value) return false;

        if (newNode.value < temp.value) {
            if (temp.left == null) {
                temp.left = newNode;
                return true;
            }

            temp = temp.left;
        } else {
            if (temp.right == null) {
                temp.right = newNode;
                return true;
            }

            temp = temp.right;
        }
    }
}
```

---

## Step 1: Create a new node

```java
Node newNode = new Node(value);
```

For:

```java
insert(50);
```

this creates:

```text
newNode
   |
   v
+------+
|  50  |
+------+
```

---

## Step 2: Check whether the tree is empty

```java
if (root == null) {
    root = newNode;
    return true;
}
```

If this is the first insertion:

```text
Before:

root ---> null
```

After:

```text
root
 |
 v
50
```

So the first inserted value automatically becomes the root.

---

# 10. Tracking the Current Node

You use:

```java
Node temp = root;
```

`temp` represents the node currently being examined.

Example:

```text
                 50
                /  \
              30    70
             / \
           20  40
```

Initially:

```text
temp ---> 50
```

If the value is smaller than `50`:

```java
temp = temp.left;
```

Now:

```text
temp ---> 30
```

Then the algorithm continues from there.

This is essentially a pointer/reference that moves through the tree.

---

# 11. Duplicate Values

Your implementation contains:

```java
if (newNode.value == temp.value) return false;
```

Therefore:

```java
insert(50);
insert(50);
```

produces:

```text
First insertion  -> true
Second insertion -> false
```

The tree remains:

```text
        50
```

No duplicate node is created.

---

# 12. Moving Left

Your code:

```java
if (newNode.value < temp.value) {
```

means:

```text
new value < current value
```

Therefore, according to the BST property:

```text
go to left subtree
```

Example:

```text
        50
       /
      30
```

Insert `20`:

```text
20 < 50
```

so:

```text
        50
       /
      30
```

then:

```text
20 < 30
```

so:

```text
        50
       /
      30
     /
    20
```

---

# 13. Moving Right

Your code:

```java
else {
```

handles:

```text
new value > current value
```

Therefore:

```text
go to right subtree
```

Example:

```text
        50
          \
           70
```

Insert `80`:

```text
80 > 50
```

go right.

Then:

```text
80 > 70
```

go right again.

Final:

```text
        50
          \
           70
             \
              80
```

---

# 14. Finding the Insertion Position

The important condition is:

```java
if (temp.left == null)
```

or:

```java
if (temp.right == null)
```

For example:

```text
        50
       /
      30
```

Insert `20`.

We eventually reach:

```text
temp ---> 30
```

and:

```java
temp.left == null
```

Therefore:

```java
temp.left = newNode;
```

creates:

```text
        50
       /
      30
     /
    20
```

---

# 15. Complete Insert Example

Insert these values:

```text
50, 30, 70, 20, 40, 60, 80
```

### Insert 50

```text
50
```

### Insert 30

```text
   50
  /
30
```

### Insert 70

```text
   50
  /  \
30    70
```

### Insert 20

```text
     50
    /  \
  30    70
 /
20
```

### Insert 40

```text
     50
    /  \
  30    70
 / \
20 40
```

### Insert 60

```text
     50
    /  \
  30    70
 / \    /
20 40  60
```

### Insert 80

```text
        50
       /  \
     30    70
    / \    / \
   20 40  60 80
```

This is a balanced-looking BST.

---

# 16. Contains Operation

The purpose of:

```java
contains(int value)
```

is to determine whether a value exists in the BST.

It returns:

```text
true  -> value exists
false -> value does not exist
```

Example:

```java
contains(40);
```

returns:

```text
true
```

while:

```java
contains(100);
```

returns:

```text
false
```

---

# 17. Contains Pseudocode

```text
CONTAINS(value):

    if root is null:
        return false

    current = root

    while current is not null:

        if value < current.value:
            current = current.left

        else if value > current.value:
            current = current.right

        else:
            return true

    return false
```

---

# 18. Your Contains Code

```java
public boolean contains(int value) {
    if (root == null) return false;

    Node temp = root;

    while (temp != null) {
        if (value < temp.value) {
            temp = temp.left;
        } else if (value > temp.value) {
            temp = temp.right;
        } else {
            return true;
        }
    }

    return false;
}
```

---

# 19. Contains: Step-by-Step Example

Suppose:

```text
                 50
                /  \
              30    70
             / \    / \
           20  40  60  80
```

Search for:

```text
60
```

### Step 1

```text
60 vs 50
```

Since:

```text
60 > 50
```

move right.

```text
temp ---> 70
```

### Step 2

```text
60 vs 70
```

Since:

```text
60 < 70
```

move left.

```text
temp ---> 60
```

### Step 3

```text
60 == 60
```

Return:

```java
true
```

Search path:

```text
50 → 70 → 60
```

Only three nodes were examined.

---

# 20. Searching for a Value That Doesn't Exist

Search for:

```text
65
```

Tree:

```text
                 50
                /  \
              30    70
             / \    / \
           20  40  60  80
```

Path:

```text
65 > 50
      ↓
70

65 < 70
      ↓
60

65 > 60
      ↓
right = null
```

Therefore:

```java
return false;
```

Search path:

```text
50 → 70 → 60 → null
```

---

# 21. Why BST Search Is Efficient

Consider a sorted list:

```text
10 20 30 40 50 60 70
```

A naive search may examine many values.

A BST allows you to eliminate an entire subtree after every comparison.

For example:

```text
        50
       /  \
     30    70
```

Searching for `70`:

```text
70 > 50
```

You immediately know:

```text
Do not search the left subtree.
```

So half of the tree can potentially be eliminated at that decision.

This is the fundamental reason BST search can achieve `O(log n)` time when the tree is balanced.

---

# 22. Insert vs Contains

Both operations follow essentially the same search path.

### Contains

You follow the path until:

```text
value found
```

or:

```text
null reached
```

### Insert

You follow the path until:

```text
duplicate found
```

or:

```text
empty child position found
```

Conceptually:

```text
                 BST
                  |
           compare value
                  |
          +-------+-------+
          |               |
       smaller          larger
          |               |
        left             right
```

---

# 23. Complete Program

Your current implementation:

```java
package Tree.BinarySearchTree;

public class BinarySearchTree {

    Node root;

    class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }
    }

    // Insert
    public boolean insert(int value) {
        Node newNode = new Node(value);

        if (root == null) {
            root = newNode;
            return true;
        }

        Node temp = root;

        while (true) {
            if (newNode.value == temp.value) return false;

            if (newNode.value < temp.value) {
                if (temp.left == null) {
                    temp.left = newNode;
                    return true;
                }

                temp = temp.left;
            } else {
                if (temp.right == null) {
                    temp.right = newNode;
                    return true;
                }

                temp = temp.right;
            }
        }
    }

    // Contains
    public boolean contains(int value) {
        if (root == null) return false;

        Node temp = root;

        while (temp != null) {
            if (value < temp.value) {
                temp = temp.left;
            } else if (value > temp.value) {
                temp = temp.right;
            } else {
                return true;
            }
        }

        return false;
    }
}
```

---

# 24. A Slightly Cleaner Version

The following version performs the same operations but creates the new node only when its insertion position is known:

```java
package Tree.BinarySearchTree;

public class BinarySearchTree {

    private Node root;

    private class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }
    }

    public boolean insert(int value) {

        if (root == null) {
            root = new Node(value);
            return true;
        }

        Node temp = root;

        while (true) {

            if (value == temp.value) {
                return false;
            }

            if (value < temp.value) {

                if (temp.left == null) {
                    temp.left = new Node(value);
                    return true;
                }

                temp = temp.left;

            } else {

                if (temp.right == null) {
                    temp.right = new Node(value);
                    return true;
                }

                temp = temp.right;
            }
        }
    }

    public boolean contains(int value) {

        Node temp = root;

        while (temp != null) {

            if (value < temp.value) {
                temp = temp.left;

            } else if (value > temp.value) {
                temp = temp.right;

            } else {
                return true;
            }
        }

        return false;
    }
}
```

The difference is mostly implementation cleanliness, not algorithmic behavior.

---

# 25. Time Complexity

Let:

```text
n = number of nodes
h = height of the tree
```

Both `insert()` and `contains()` traverse at most one path from the root downward.

Therefore their complexity is:

```text
O(h)
```

The actual complexity depends on the tree's height.

---

## Best Case

If the value is at the root:

```text
        50
       /  \
     30    70
```

Searching for `50` requires one comparison.

```text
Time = O(1)
```

---

## Average / Balanced Case

For a reasonably balanced BST:

```text
             50
           /    \
         30      70
        / \      / \
      20  40   60  80
```

The height is approximately:

```text
log₂(n)
```

Therefore:

```text
Insert   = O(log n)
Contains = O(log n)
```

---

## Worst Case

If values are inserted in sorted order:

```text
10
  \
   20
     \
      30
        \
         40
           \
            50
```

The tree becomes skewed.

Height becomes approximately:

```text
n
```

Therefore:

```text
Insert   = O(n)
Contains = O(n)
```

---

# 26. Complexity Summary

| Operation | Best | Average* | Worst |
|-----------|-----:|---------:|------:|
| Insert    | O(1) | O(log n) |  O(n) |
| Contains  | O(1) | O(log n) |  O(n) |

`*` The `O(log n)` average case assumes a reasonably balanced/randomly shaped BST; an arbitrary unbalanced BST can still
approach `O(n)`.

---

# 27. Space Complexity

Your implementations are iterative.

You use:

```java
Node temp = root;
```

and move the reference through the tree.

You do not use recursion or an explicit stack.

Therefore, the auxiliary space used by `insert()` and `contains()` is:

```text
O(1)
```

This is an important distinction.

The BST itself requires:

```text
O(n)
```

space because every inserted value requires a node.

So:

```text
BST storage        = O(n)
Auxiliary operation space = O(1)
```

---

# 28. Why Your Implementation Uses O(1) Auxiliary Space

Suppose the tree has:

```text
1,000,000 nodes
```

Your search still maintains essentially one traversal reference:

```java
Node temp;
```

It does not create:

```text
1,000,000 stack frames
```

or:

```text
1,000,000 additional objects
```

Therefore auxiliary space remains:

```text
O(1)
```

---

# 29. BST Height

Height is extremely important for understanding BST complexity.

Consider:

```text
        50
       /  \
     30    70
    / \    / \
   20 40  60 80
```

The height is small.

Now consider:

```text
10
  \
   20
     \
      30
        \
         40
           \
            50
```

The height is large.

Since BST operations depend on the path from the root to a node:

```text
Time complexity ≈ O(height)
```

Therefore:

```text
Balanced tree:
height ≈ log n

Skewed tree:
height ≈ n
```

This is one of the most important concepts to remember.

---

# 30. Inorder Traversal

A very important property of a BST:

> Inorder traversal of a valid BST produces values in sorted order.

Inorder traversal means:

```text
LEFT → ROOT → RIGHT
```

For:

```text
        50
       /  \
     30    70
    / \    / \
   20 40  60 80
```

Inorder traversal:

```text
20 → 30 → 40 → 50 → 60 → 70 → 80
```

Notice that the result is sorted.

This property is frequently used in DSA problems.

---

# 31. BST Traversals

There are three fundamental depth-first traversals.

### Inorder

```text
LEFT → ROOT → RIGHT
```

For BST:

```text
sorted order
```

### Preorder

```text
ROOT → LEFT → RIGHT
```

### Postorder

```text
LEFT → RIGHT → ROOT
```

Example tree:

```text
        50
       /  \
     30    70
```

Results:

```text
Inorder:
30 50 70

Preorder:
50 30 70

Postorder:
30 70 50
```

Traversal is the natural next operation to implement after your current `insert()` and `contains()` methods.

---

# 32. Important BST Invariant

The most important rule to memorize:

```text
For every node:

left subtree values < node value < right subtree values
```

For example:

```text
                 50
                /  \
              30    70
             / \    / \
           20  40  60  80
```

Check node `30`:

```text
20 < 30 < 40
```

Check node `70`:

```text
60 < 70 < 80
```

Check root `50`:

```text
all left-side values < 50
all right-side values > 50
```

---

# 33. Important Edge Cases

When implementing BST operations, remember these cases.

## Case 1: Empty tree

```text
root = null
```

Then:

```java
contains(10)
```

returns:

```text
false
```

And:

```java
insert(10)
```

makes:

```text
10
```

the root.

---

## Case 2: Insert duplicate

```java
insert(50)
insert(50)
```

Result:

```text
first  -> true
second -> false
```

---

## Case 3: Search for root

```text
        50
       /  \
     30    70
```

```java
contains(50)
```

returns immediately:

```text
true
```

Complexity:

```text
O(1)
```

---

## Case 4: Search for missing value

```java
contains(100)
```

Eventually reaches:

```text
null
```

and returns:

```text
false
```

---

## Case 5: Highly skewed tree

```text
10
  \
   20
     \
      30
        \
         40
```

Operations can become:

```text
O(n)
```

---

# 34. Why `while (true)` Works in Insert

Your insert method contains:

```java
while (true)
```

At first this may look dangerous because there is no explicit condition.

However, every possible path eventually executes:

```java
return true;
```

or:

```java
return false;
```

For example:

```text
             compare
                |
        +-------+-------+
        |               |
      equal          not equal
        |               |
     return false      compare
                        |
                  +-----+-----+
                  |           |
                left         right
                  |           |
               null?        null?
               /   \        /   \
             yes   no      yes   no
              |     |       |     |
            insert  move   insert move
              |     |       |     |
           return  temp    return temp
                    |              |
                  repeat         repeat
```

So the loop terminates when either:

```text
duplicate is found
```

or:

```text
an empty child position is found
```

---

# 35. Why `while (temp != null)` Works in Contains

Your search method uses:

```java
while (temp != null)
```

This is directly related to tree traversal.

If:

```text
temp == null
```

there is no node to inspect.

Therefore:

```text
value does not exist
```

and you return:

```java
false;
```

If:

```text
value == temp.value
```

you immediately return:

```java
true;
```

---

# 36. Mental Model for BST Search

Whenever you see a BST search problem, think:

```text
Start at root
     ↓
Compare
     ↓
Smaller? → LEFT
     ↓
Larger?  → RIGHT
     ↓
Equal?   → FOUND
```

Memorize this:

```text
value < current → go LEFT
value > current → go RIGHT
value == current → FOUND
```

This is the core of your `contains()` method.

---

# 37. Mental Model for BST Insertion

For insertion:

```text
Start at root
     ↓
Compare
     ↓
Smaller?
 ├── Yes → LEFT
 └── No  → RIGHT
             ↓
      Is position empty?
        ├── Yes → INSERT
        └── No  → Continue
```

The only difference from searching is what happens when you reach a `null` child.

For search:

```text
null → NOT FOUND
```

For insertion:

```text
null child → INSERT HERE
```

---

# 38. Difference Between Search and Insert

| Situation            | Contains            | Insert              |
|----------------------|---------------------|---------------------|
| Value equals current | Found → `true`      | Duplicate → `false` |
| Value smaller        | Go left             | Go left             |
| Value larger         | Go right            | Go right            |
| Child is `null`      | Not found → `false` | Insert new node     |

This table is useful for quick revision.

---

# 39. Example: Complete Execution

Suppose:

```java
BinarySearchTree bst = new BinarySearchTree();
```

Initially:

```text
root = null
```

Execute:

```java
bst.insert(50);
bst.insert(30);
bst.insert(70);
bst.insert(20);
bst.insert(40);
bst.insert(60);
bst.insert(80);
```

Tree becomes:

```text
                 50
                /  \
              30    70
             / \    / \
           20  40  60  80
```

Now:

```java
bst.contains(40);
```

Path:

```text
50 → 30 → 40
```

Result:

```text
true
```

Now:

```java
bst.contains(100);
```

Path:

```text
50 → 70 → 80 → null
```

Result:

```text
false
```

Now:

```java
bst.insert(40);
```

Path:

```text
50 → 30 → 40
```

Since `40` already exists:

```text
false
```

No new node is added.

---

# 40. Important Observation About Duplicates

Your BST implementation follows:

```text
left < node < right
```

because duplicate values are rejected.

Another BST implementation might allow duplicates using a rule such as:

```text
left <= node < right
```

or:

```text
left < node <= right
```

But your implementation uses:

```text
duplicates not allowed
```

This should be clearly stated whenever describing your implementation.

---

# 41. Common Mistakes

## Mistake 1: Searching both sides

Incorrect approach:

```text
Search left
Search right
```

That defeats the primary advantage of a BST.

Instead:

```text
smaller → left
larger  → right
```

---

## Mistake 2: Forgetting the equality case

You need:

```java
else {
    return true;
}
```

when:

```java
value == temp.value
```

Otherwise the search would continue unnecessarily.

---

## Mistake 3: Treating every binary tree as a BST

This:

```text
        50
       /  \
     80    20
```

is a binary tree but not a BST.

The BST ordering property must always hold.

---

## Mistake 4: Assuming BST is always O(log n)

This is incorrect.

A normal BST is only `O(log n)` when its height is approximately logarithmic.

A skewed BST can be:

```text
O(n)
```

This distinction is important in technical interviews.

---

# 42. BST vs Balanced BST

A normal BST:

```text
        50
       /  \
     30    70
```

does not automatically balance itself.

If values are inserted in sorted order:

```text
10, 20, 30, 40, 50
```

you can get:

```text
10
  \
   20
     \
      30
        \
         40
           \
            50
```

Balanced BSTs such as:

```text
AVL Tree
Red-Black Tree
```

use additional rules to keep tree height controlled.

This is why they can provide guaranteed logarithmic operations under their balancing rules.

---

# 43. Your Current BST Complexity

For your exact implementation:

```text
insert()
contains()
```

both are:

```text
Time:
    Best    O(1)
    Average O(log n) for a reasonably balanced/randomly shaped tree
    Worst   O(n)

Auxiliary Space:
    O(1)

BST storage:
    O(n)
```

---

# 44. Quick Revision Sheet

## Definition

A BST is a binary tree where:

```text
left < node < right
```

for every node, under your no-duplicates rule.

## Root

Topmost node.

## Leaf

Node with no children.

## Insert

```text
smaller → left
larger  → right
equal   → reject
```

## Contains

```text
smaller → left
larger  → right
equal   → found
null    → not found
```

## Inorder

```text
LEFT → ROOT → RIGHT
```

For a valid BST:

```text
inorder = sorted order
```

## Complexity

```text
Operation   Best    Average    Worst
-------------------------------------
Insert      O(1)    O(log n)   O(n)
Contains    O(1)    O(log n)   O(n)
```

Auxiliary space:

```text
O(1)
```

Tree storage:

```text
O(n)
```

---

# 45. Interview-Level Explanation

If asked:

**"What is a Binary Search Tree?"**

A concise answer:

> A Binary Search Tree is a binary tree that maintains an ordering property where values smaller than a node are stored
> in its left subtree and values larger than the node are stored in its right subtree. This property allows search and
> insertion to follow a single root-to-leaf path instead of examining every node. Their complexity is O(h), where h is the
> tree height, giving O(log n) for a reasonably balanced tree and O(n) in the worst case for a skewed tree.

---

# 46. Core Concepts to Memorize

The following are the most important points from this implementation:

```text
1. BST = ordered binary tree

2. left subtree < node < right subtree

3. root is the starting point

4. smaller value → move left

5. larger value → move right

6. equal value → found / duplicate

7. insert at a null child position

8. contains returns false when null is reached

9. inorder traversal of a valid BST is sorted

10. Complexity depends on tree height

11. Balanced-ish BST → O(log n)

12. Skewed BST → O(n)

13. Your iterative implementation uses O(1)
   auxiliary space
```

---

# 47. Visual Summary

```text
                         BST
                          |
                          v
                       Root
                          |
                    +-----+-----+
                    |           |
                 smaller      larger
                    |           |
                   LEFT        RIGHT
                    |           |
                    +-----+-----+
                          |
                     Compare again
                          |
                 +--------+--------+
                 |        |        |
              smaller   equal    larger
                 |        |        |
                LEFT     FOUND    RIGHT
```

For insertion:

```text
                    Start at root
                         |
                         v
                      Compare
                         |
            +------------+------------+
            |                         |
        value < node              value > node
            |                         |
          LEFT                      RIGHT
            |                         |
       child null?              child null?
        /       \                /       \
      YES       NO             YES       NO
       |         |              |         |
    INSERT      MOVE          INSERT     MOVE
```

For searching:

```text
                    Start at root
                         |
                         v
                      Compare
                         |
          +--------------+--------------+
          |              |              |
       smaller          equal         larger
          |              |              |
        LEFT           FOUND          RIGHT
          |
       null?
          |
        YES
          |
      NOT FOUND
```

---

# 48. Recommended Next Implementation

After mastering this code, the next natural version of your class should add:

```java
insert()
contains()
inorder()
preorder()
postorder()
min()
max()
delete()
```

The most important progression is:

```text
Insert
   ↓
Contains
   ↓
Inorder
   ↓
Min / Max
   ↓
Delete
   ↓
Successor / Predecessor
   ↓
Validate BST
```

The **delete operation** is the most conceptually important next step because it has three different structural cases:

```text
1. Delete a leaf
2. Delete a node with one child
3. Delete a node with two children
```

Understanding those three cases is what takes a basic BST implementation into the more complete DSA implementation.
