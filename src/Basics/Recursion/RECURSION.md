# Recursion in Java

> A revision guide covering the recursion concept, call stack, base cases, recursive cases, Java implementations, visualizations, pseudocode, complexity, common patterns, exercises, LeetCode problems, and interview questions.

---

## 1. What Is Recursion?

**Recursion** is a programming technique in which a method calls itself to solve a smaller version of the same problem.

A recursive solution generally contains two essential parts:

1. **Base Case** — the condition that stops recursion.
2. **Recursive Case** — the part where the method calls itself with a smaller/simpler input.

### General Structure

```java
returnType method(parameters) {

    // Base Case
    if (condition) {
        return result;
    }

    // Recursive Case
    return method(smallerProblem);
}
```

### Simple Example

```java
public static void print(int n) {

    if (n == 0) {
        return;
    }

    System.out.println(n);
    print(n - 1);
}
```

Calling:

```java
print(5);
```

Produces:

```text
5
4
3
2
1
```

---

# 2. The Two Most Important Parts

## Base Case

The **base case** tells the recursive function when to stop.

Example:

```java
if (n == 0) {
    return;
}
```

Without a base case, the method can continue calling itself indefinitely.

This eventually results in:

```text
StackOverflowError
```

in Java.

---

## Recursive Case

The recursive case reduces the problem and calls the same method again.

```java
print(n - 1);
```

The input changes from:

```text
5 → 4 → 3 → 2 → 1 → 0
```

Eventually the base case is reached.

---

# 3. The Core Mental Model

When solving recursion problems, think:

> "Can I solve the current problem using the solution of a smaller version of the same problem?"

For example:

```text
factorial(5)
```

can be represented as:

```text
5 × factorial(4)
```

and:

```text
factorial(4)
= 4 × factorial(3)

factorial(3)
= 3 × factorial(2)

factorial(2)
= 2 × factorial(1)

factorial(1)
= 1
```

Therefore:

```text
factorial(5)
= 5 × 4 × 3 × 2 × 1
= 120
```

---

# 4. How Recursion Works Internally

Recursion uses the **call stack**.

Every time a method calls itself, Java creates a new stack frame containing information about that method call.

Consider:

```java
static void print(int n) {

    if (n == 0) {
        return;
    }

    System.out.println(n);
    print(n - 1);
}
```

Calling:

```java
print(3);
```

creates the following calls:

```text
print(3)
   ↓
print(2)
   ↓
print(1)
   ↓
print(0)
```

At `print(0)`, the base case executes.

Then the calls return:

```text
print(0) returns
     ↑
print(1) returns
     ↑
print(2) returns
     ↑
print(3) returns
```

### Visual Representation

```text
CALL PHASE

┌─────────────┐
│ print(3)    │
├─────────────┤
│ print(2)    │
├─────────────┤
│ print(1)    │
├─────────────┤
│ print(0)    │ ← Base Case
└─────────────┘

        ↓

RETURN PHASE

print(0) returns
    ↓
print(1) returns
    ↓
print(2) returns
    ↓
print(3) returns
```

The stack follows:

> **LIFO — Last In, First Out**

---

# 5. Recursion vs Iteration

The same problem can often be solved using either recursion or a loop.

### Iterative

```java
for (int i = 5; i >= 1; i--) {
    System.out.println(i);
}
```

### Recursive

```java
static void print(int n) {

    if (n == 0) {
        return;
    }

    System.out.println(n);
    print(n - 1);
}
```

### Comparison

| Feature                       | Recursion                       | Iteration                     |
| ----------------------------- | ------------------------------- | ----------------------------- |
| Uses call stack               | Yes                             | Usually no                    |
| Memory usage                  | Usually higher                  | Usually lower                 |
| Code                          | Often concise                   | Often straightforward         |
| Risk of stack overflow        | Yes                             | No                            |
| Useful for trees              | Very useful                     | Often requires explicit stack |
| Useful for divide-and-conquer | Very useful                     | Sometimes harder              |
| Performance                   | Can have function-call overhead | Usually lower overhead        |

Recursion is not automatically better. Choose it when it naturally represents the problem.

---

# 6. Your First Recursion Problem: Factorial

The factorial of `n` is:

```text
n! = n × (n - 1) × (n - 2) × ... × 1
```

For example:

```text
5! = 5 × 4 × 3 × 2 × 1
   = 120
```

Mathematically:

```text
n! = n × (n - 1)!
```

Base case:

```text
0! = 1
```

Therefore:

```text
factorial(n) =
    1                  if n == 0
    n × factorial(n-1) otherwise
```

---

## Java Implementation

```java
public static int factorial(int n) {

    if (n == 0) {
        return 1;
    }

    return n * factorial(n - 1);
}
```

Usage:

```java
public static void main(String[] args) {

    int result = factorial(5);

    System.out.println(result);
}
```

Output:

```text
120
```

---

# 7. Factorial Recursion Visualization

For:

```java
factorial(5)
```

the recursive calls are:

```text
factorial(5)
    |
    └── 5 × factorial(4)
                |
                └── 4 × factorial(3)
                            |
                            └── 3 × factorial(2)
                                        |
                                        └── 2 × factorial(1)
                                                    |
                                                    └── 1 × factorial(0)
                                                                |
                                                                └── 1
```

Now the recursion returns upward:

```text
factorial(0) = 1

factorial(1)
= 1 × 1
= 1

factorial(2)
= 2 × 1
= 2

factorial(3)
= 3 × 2
= 6

factorial(4)
= 4 × 6
= 24

factorial(5)
= 5 × 24
= 120
```

---

# 8. Important Observation: Recursion Has Two Phases

A recursive function can have work happening in two different phases.

## Before Recursive Call

```java
System.out.println(n);
print(n - 1);
```

Output:

```text
5
4
3
2
1
```

The work happens while going **down** the recursion.

---

## After Recursive Call

```java
print(n - 1);
System.out.println(n);
```

Output:

```text
1
2
3
4
5
```

The printing happens while recursion is **returning**.

This concept becomes extremely important for:

* Tree traversal
* Backtracking
* DFS
* Recursive sorting
* Subset generation

---

# 9. A Simple Example

```java
static void printIncreasing(int n) {

    if (n == 0) {
        return;
    }

    printIncreasing(n - 1);

    System.out.println(n);
}
```

Calling:

```java
printIncreasing(5);
```

Output:

```text
1
2
3
4
5
```

### Why?

The calls go downward:

```text
printIncreasing(5)
        ↓
printIncreasing(4)
        ↓
printIncreasing(3)
        ↓
printIncreasing(2)
        ↓
printIncreasing(1)
        ↓
printIncreasing(0)
```

Then execution returns:

```text
0 → 1 → 2 → 3 → 4 → 5
```

---

# 10. Recursion Template

When solving a recursive problem, start with this structure:

```java
function(problem) {

    // 1. Base case
    if (smallestProblem) {
        return answer;
    }

    // 2. Solve smaller problem
    smallerAnswer = function(smallerProblem);

    // 3. Combine current problem with smaller answer
    return combine(smallerAnswer);
}
```

This is one of the most useful templates to remember.

---

# 11. How to Identify the Base Case

Ask:

> "What is the smallest input for which I already know the answer?"

Examples:

### Factorial

```text
0! = 1
```

Base case:

```java
if (n == 0)
```

### Sum of numbers

```text
sum(0) = 0
```

### Fibonacci

```text
fib(0) = 0
fib(1) = 1
```

### Traversing an array

```text
index == array.length
```

### Traversing a linked list

```text
node == null
```

### Binary tree

```text
root == null
```

---

# 12. How to Identify the Recursive Case

Ask:

> "How can I reduce the current problem to a smaller version of the same problem?"

Examples:

```text
factorial(n)
→ factorial(n - 1)

sum(n)
→ sum(n - 1)

array(index)
→ array(index + 1)

tree(node)
→ tree(node.left)
→ tree(node.right)
```

---

# 13. Recursion Tree

A **recursion tree** is a visualization of recursive calls.

Consider:

```java
static int sum(int n) {

    if (n == 0) {
        return 0;
    }

    return n + sum(n - 1);
}
```

For:

```text
sum(4)
```

we get:

```text
sum(4)
  |
  └── 4 + sum(3)
            |
            └── 3 + sum(2)
                      |
                      └── 2 + sum(1)
                                |
                                └── 1 + sum(0)
                                          |
                                          └── 0
```

Return:

```text
0
↑
1 + 0 = 1
↑
2 + 1 = 3
↑
3 + 3 = 6
↑
4 + 6 = 10
```

---

# 14. Recursion Depth

**Recursion depth** is the maximum number of active recursive calls on the call stack at one time.

For:

```java
factorial(5)
```

the depth is approximately:

```text
5 → 4 → 3 → 2 → 1 → 0
```

So the stack grows proportionally to `n`.

Therefore:

```text
Space Complexity = O(n)
```

for recursive factorial.

---

# 15. Time and Space Complexity

For:

```java
factorial(n)
```

there are approximately `n` recursive calls.

Therefore:

```text
Time Complexity = O(n)
Space Complexity = O(n)
```

The `O(n)` space comes from the recursive call stack.

---

# 16. Recursion and Stack Overflow

Consider:

```java
static void infinite(int n) {
    infinite(n);
}
```

There is no base case.

The calls continue:

```text
infinite()
   ↓
infinite()
   ↓
infinite()
   ↓
infinite()
   ↓
...
```

Eventually Java throws:

```text
java.lang.StackOverflowError
```

### Common causes

* Missing base case
* Incorrect base case
* Recursive call does not move toward the base case
* Extremely large recursion depth

---

# 17. Common Recursion Mistakes

## Mistake 1: No Base Case

```java
static void test(int n) {
    System.out.println(n);
    test(n - 1);
}
```

Problem:

```text
No stopping condition
```

---

## Mistake 2: Wrong Base Case

```java
if (n == 10) {
    return;
}
```

when starting with:

```text
n = 5
```

The function moves away from the base case:

```text
5 → 4 → 3 → 2 → 1 → 0 → -1 → ...
```

---

## Mistake 3: Not Reducing the Problem

```java
static void test(int n) {

    if (n == 0) {
        return;
    }

    test(n);
}
```

The argument never changes.

```text
n → n → n → n → ...
```

---

## Mistake 4: Forgetting Return

Incorrect:

```java
static int factorial(int n) {

    if (n == 0) {
        return 1;
    }

    factorial(n - 1);
}
```

The recursive result is not returned.

Correct:

```java
return n * factorial(n - 1);
```

---

# 18. Sum of First N Natural Numbers

Problem:

```text
1 + 2 + 3 + ... + n
```

### Mathematical relation

```text
sum(n) = n + sum(n - 1)
```

Base case:

```text
sum(0) = 0
```

### Pseudocode

```text
FUNCTION sum(n):

    IF n == 0:
        RETURN 0

    RETURN n + sum(n - 1)
```

### Java

```java
static int sum(int n) {

    if (n == 0) {
        return 0;
    }

    return n + sum(n - 1);
}
```

Example:

```text
sum(5)
= 5 + sum(4)
= 5 + 4 + sum(3)
= 5 + 4 + 3 + sum(2)
= 5 + 4 + 3 + 2 + sum(1)
= 5 + 4 + 3 + 2 + 1
= 15
```

Complexity:

```text
Time:  O(n)
Space: O(n)
```

---

# 19. Power of a Number

Calculate:

```text
x^n
```

For example:

```text
2^5 = 32
```

Recursive relation:

```text
x^n = x × x^(n-1)
```

Base case:

```text
x^0 = 1
```

### Pseudocode

```text
FUNCTION power(x, n):

    IF n == 0:
        RETURN 1

    RETURN x × power(x, n - 1)
```

### Java

```java
static int power(int x, int n) {

    if (n == 0) {
        return 1;
    }

    return x * power(x, n - 1);
}
```

Complexity:

```text
Time:  O(n)
Space: O(n)
```

---

# 20. Fibonacci Numbers

The Fibonacci sequence is:

```text
0, 1, 1, 2, 3, 5, 8, 13, ...
```

Definition:

```text
F(0) = 0
F(1) = 1

F(n) = F(n-1) + F(n-2)
```

### Java

```java
static int fibonacci(int n) {

    if (n == 0) {
        return 0;
    }

    if (n == 1) {
        return 1;
    }

    return fibonacci(n - 1) + fibonacci(n - 2);
}
```

For:

```text
fibonacci(5)
```

the recursion tree looks like:

```text
                    fib(5)
                  /        \
              fib(4)       fib(3)
             /    \        /    \
         fib(3)  fib(2) fib(2) fib(1)
         /   \
     fib(2) fib(1)
```

Notice that the same subproblems are calculated multiple times.

For example:

```text
fib(3)
fib(2)
```

appear repeatedly.

This is why naive recursive Fibonacci is inefficient.

Complexity:

```text
Time:  O(2^n)
Space: O(n)
```

The recursion depth is `O(n)`.

---

# 21. Multiple Recursive Calls

Not every recursive function makes only one recursive call.

Example:

```java
fibonacci(n - 1)
fibonacci(n - 2)
```

This creates a branching recursion tree.

There are two broad patterns:

### Linear Recursion

One recursive call:

```java
f(n)
    ↓
f(n - 1)
    ↓
f(n - 2)
```

Examples:

* Factorial
* Sum
* Power
* Array traversal

### Branching Recursion

Multiple recursive calls:

```text
        f(n)
       /    \
   f(n-1)  f(n-2)
    / \      / \
   ...      ...
```

Examples:

* Fibonacci
* Subsets
* Tree traversal
* Backtracking

---

# 22. Recursion With Arrays

Suppose we want to print every element of an array.

```java
static void printArray(int[] arr, int index) {

    if (index == arr.length) {
        return;
    }

    System.out.println(arr[index]);

    printArray(arr, index + 1);
}
```

Usage:

```java
int[] arr = {10, 20, 30, 40};

printArray(arr, 0);
```

Output:

```text
10
20
30
40
```

The recursive state is:

```text
index = 0
   ↓
index = 1
   ↓
index = 2
   ↓
index = 3
   ↓
index = 4
   ↓
stop
```

---

# 23. Find Maximum Element Recursively

```java
static int findMax(int[] arr, int index) {

    if (index == arr.length - 1) {
        return arr[index];
    }

    int maxOfRest = findMax(arr, index + 1);

    return Math.max(arr[index], maxOfRest);
}
```

Example:

```text
arr = [4, 9, 2, 7, 5]
```

The recursion eventually reaches:

```text
5
```

Then compares while returning:

```text
7 vs 5 → 7
2 vs 7 → 7
9 vs 7 → 9
4 vs 9 → 9
```

Result:

```text
9
```

---

# 24. String Recursion

Recursion can also process strings.

Example: reverse a string.

### Pseudocode

```text
FUNCTION reverse(string, index):

    IF index == string.length:
        RETURN

    reverse(string, index + 1)

    PRINT string[index]
```

Example:

```text
"JAVA"
```

Call sequence:

```text
J
 ↓
A
 ↓
V
 ↓
A
```

Return order:

```text
A
V
A
J
```

Result:

```text
AVAJ
```

---

# 25. Direct vs Indirect Recursion

## Direct Recursion

A method directly calls itself.

```java
static void fun() {
    fun();
}
```

---

## Indirect Recursion

Method A calls method B, and method B eventually calls method A.

```java
static void A(int n) {

    if (n <= 0) {
        return;
    }

    B(n - 1);
}

static void B(int n) {

    if (n <= 0) {
        return;
    }

    A(n - 1);
}
```

Flow:

```text
A()
 ↓
B()
 ↓
A()
 ↓
B()
 ↓
...
```

---

# 26. Tail Recursion

A recursive function is **tail recursive** when the recursive call is the final operation.

Example:

```java
static void print(int n) {

    if (n == 0) {
        return;
    }

    System.out.println(n);

    print(n - 1);
}
```

The recursive call:

```java
print(n - 1);
```

is the final operation.

---

## Non-Tail Recursion

```java
static int factorial(int n) {

    if (n == 0) {
        return 1;
    }

    return n * factorial(n - 1);
}
```

The recursive call must return before multiplication can complete.

```text
n × factorial(n - 1)
```

Therefore this is not tail recursive.

> Important Java note: Java does not guarantee tail-call optimization, so tail recursion does not automatically eliminate stack usage.

---

# 27. Recursion With Backtracking

Backtracking is an important advanced application of recursion.

The general idea is:

```text
Choose
  ↓
Explore
  ↓
Undo
  ↓
Try another choice
```

Visual:

```text
                Start
              /       \
          Choose A   Choose B
           /   \       /   \
         ...   ...   ...   ...
```

Backtracking appears in:

* Subsets
* Permutations
* Combination Sum
* N-Queens
* Sudoku
* Maze problems
* Word search

---

# 28. General Backtracking Template

```java
void backtrack(state) {

    if (baseCondition) {
        processAnswer();
        return;
    }

    for (choice : choices) {

        makeChoice(choice);

        backtrack(newState);

        undoChoice(choice);
    }
}
```

The key line is:

```java
undoChoice(choice);
```

This restores the previous state before trying another possibility.

---

# 29. Subsets — Important Recursion Pattern

Given:

```text
[1, 2, 3]
```

Possible subsets:

```text
[]
[1]
[2]
[3]
[1,2]
[1,3]
[2,3]
[1,2,3]
```

At every element, we have two choices:

```text
Take
Don't Take
```

Recursion tree:

```text
                    []
                  /    \
              take 1   skip 1
              /          \
          [1]             []
         /   \           /   \
     take 2 skip 2   take 2 skip 2
       ...    ...       ...    ...
```

This creates:

```text
2^n
```

possible subsets.

---

# 30. Recursion Problem-Solving Framework

When you see a recursion problem, follow these steps.

### Step 1 — Identify the smaller problem

Ask:

```text
What smaller version of this problem can I solve?
```

### Step 2 — Define the base case

Ask:

```text
What is the smallest input?
```

### Step 3 — Define the recursive relationship

Write:

```text
current problem = work + smaller problem
```

### Step 4 — Make progress

Every recursive call should move toward the base case.

### Step 5 — Trust the recursive call

This is important.

Don't manually simulate every recursive call while writing the solution.

Assume:

```text
recursiveFunction(smallerInput)
```

correctly solves the smaller problem.

Then determine how to use its result.

### Step 6 — Analyze complexity

Calculate:

* Number of calls
* Work per call
* Maximum recursion depth

---

# 31. The "Trust the Function" Technique

Suppose:

```java
static int sum(int n) {

    if (n == 0) {
        return 0;
    }

    return n + sum(n - 1);
}
```

Instead of thinking:

```text
What exactly happens inside sum(4)?
What happens inside sum(3)?
What happens inside sum(2)?
...
```

think:

> Assume `sum(n - 1)` correctly calculates the sum from `1` to `n - 1`.

Then:

```text
sum(n)
= n + sum(n - 1)
```

That's enough.

For:

```text
sum(5)
```

trust:

```text
sum(4) = 10
```

Therefore:

```text
sum(5) = 5 + 10
       = 15
```

This mental model makes recursion much easier.

---

# 32. Recursion vs Divide and Conquer

Many important algorithms use recursion to divide a problem into smaller parts.

Examples:

### Merge Sort

```text
Array
  ↓
Divide
 /   \
left right
 ↓     ↓
sort  sort
 \     /
 merge
```

Complexity:

```text
O(n log n)
```

### Quick Sort

```text
Array
  ↓
Choose Pivot
 /          \
left        right
 ↓            ↓
recursive    recursive
```

### Binary Search

```text
Sorted Array
     ↓
Middle
 /     \
left   right
```

Complexity:

```text
O(log n)
```

---

# 33. Recursion and Trees

Trees are naturally recursive data structures.

A binary tree is:

```text
             10
            /  \
           5    15
          / \   / \
         2   7 12 20
```

Each node itself represents the root of another smaller tree.

Therefore tree algorithms naturally use recursion.

For example:

```java
static void inorder(Node root) {

    if (root == null) {
        return;
    }

    inorder(root.left);

    System.out.println(root.value);

    inorder(root.right);
}
```

The base case:

```java
root == null
```

This concept will become very important when studying:

* Binary Trees
* BST
* DFS
* Graph traversal
* Tree recursion

---

# 34. Common Recursion Patterns

You should eventually recognize these patterns.

| Pattern            | Example              |
| ------------------ | -------------------- |
| Linear recursion   | Factorial            |
| Multiple recursion | Fibonacci            |
| Array recursion    | Traverse array       |
| String recursion   | Reverse string       |
| Divide and conquer | Merge Sort           |
| Binary recursion   | Tree traversal       |
| Backtracking       | Subsets              |
| Memoized recursion | Dynamic Programming  |
| DFS recursion      | Graph/Tree traversal |

---

# 35. Recurrence Relations

Recursive algorithms can often be represented using a recurrence relation.

For factorial:

```text
T(n) = T(n - 1) + O(1)
```

Therefore:

```text
T(n) = O(n)
```

For binary search:

```text
T(n) = T(n/2) + O(1)
```

Therefore:

```text
T(n) = O(log n)
```

For merge sort:

```text
T(n) = 2T(n/2) + O(n)
```

Therefore:

```text
T(n) = O(n log n)
```

For naive Fibonacci:

```text
T(n) = T(n-1) + T(n-2) + O(1)
```

which results in exponential time.

---

# 36. Practice Problems — Beginner

Solve these without looking at solutions.

### Level 1

1. Print numbers from `1` to `N`.
2. Print numbers from `N` to `1`.
3. Print numbers from `1` to `N` using recursion.
4. Find the sum of first `N` numbers.
5. Calculate factorial of `N`.
6. Calculate `x^n`.
7. Count digits of a number.
8. Find the sum of digits.
9. Reverse a number.
10. Check whether a number is a palindrome.

---

# 37. Practice Problems — Arrays and Strings

11. Print an array recursively.
12. Find the maximum element.
13. Find the minimum element.
14. Calculate the sum of an array.
15. Search for an element.
16. Check whether an array is sorted.
17. Reverse an array recursively.
18. Reverse a string recursively.
19. Check whether a string is a palindrome.
20. Count occurrences of a character.

---

# 38. Practice Problems — Intermediate

21. Fibonacci using recursion.
22. Binary search recursively.
23. Merge sort.
24. Quick sort.
25. Generate all subsets.
26. Generate all subsequences of a string.
27. Generate permutations of a string.
28. Generate combinations.
29. Solve Combination Sum.
30. Generate balanced parentheses.

---

# 39. Practice Problems — Advanced

31. N-Queens.
32. Sudoku Solver.
33. Rat in a Maze.
34. Word Search.
35. Letter Combinations of a Phone Number.
36. Generate Parentheses.
37. Permutations II.
38. Combination Sum II.
39. Palindrome Partitioning.
40. Graph DFS.

---

# 40. Recommended LeetCode Problems

## Beginner

### 1. Fibonacci Number

**LeetCode #509**

Concepts:

* Base cases
* Multiple recursive calls
* Recursion tree

---

### 2. Reverse String

**LeetCode #344**

Concepts:

* Two pointers
* Recursive shrinking

---

### 3. Reverse Linked List

**LeetCode #206**

Concepts:

* Recursion
* Linked list
* Returning recursive results

---

### 4. Maximum Depth of Binary Tree

**LeetCode #104**

Concepts:

* Tree recursion
* Base case
* Maximum of subproblems

---

### 5. Same Tree

**LeetCode #100**

Concepts:

* Binary tree recursion
* Multiple recursive calls

---

## Intermediate

### 6. Pow(x, n)

**LeetCode #50**

Important because it introduces efficient recursive exponentiation.

---

### 7. Subsets

**LeetCode #78**

Important backtracking problem.

---

### 8. Permutations

**LeetCode #46**

Important recursion/backtracking pattern.

---

### 9. Combination Sum

**LeetCode #39**

Important for learning recursive choice exploration.

---

### 10. Generate Parentheses

**LeetCode #22**

Important recursion + backtracking problem.

---

### 11. Letter Combinations of a Phone Number

**LeetCode #17**

Important branching recursion problem.

---

### 12. Path Sum

**LeetCode #112**

Important tree recursion problem.

---

# 41. Suggested Learning Order

Don't randomly solve recursion problems.

Follow this progression:

```text
                RECURSION
                    │
                    ▼
            Basic Recursion
                    │
                    ▼
        Base Case + Recursive Case
                    │
                    ▼
             Call Stack
                    │
                    ▼
       ┌────────────┴────────────┐
       ▼                         ▼
   Linear Recursion        Multiple Recursion
       │                         │
       ▼                         ▼
 Factorial / Sum             Fibonacci
       │
       ▼
 Array / String Recursion
       │
       ▼
   Tree Recursion
       │
       ▼
 Divide & Conquer
       │
       ▼
   Backtracking
       │
       ▼
 Dynamic Programming
```

---

# 42. Interview Questions

## Q1. What is recursion?

Recursion is a technique where a function calls itself to solve smaller instances of the same problem.

---

## Q2. What are the two essential components of recursion?

```text
1. Base Case
2. Recursive Case
```

The base case terminates recursion, while the recursive case reduces the problem.

---

## Q3. What happens if there is no base case?

The recursive calls continue until the call stack is exhausted, resulting in:

```text
StackOverflowError
```

in Java.

---

## Q4. What is the call stack?

The call stack is a memory structure used to keep track of active method calls.

Each recursive call creates a new stack frame.

---

## Q5. Why does recursion consume extra memory?

Because every active recursive call occupies a stack frame.

If recursion depth is `n`:

```text
Space = O(n)
```

in the typical case.

---

## Q6. What is recursion depth?

The maximum number of recursive calls active at the same time.

---

## Q7. What is tail recursion?

A recursion where the recursive call is the final operation performed by the function.

Example:

```java
static void print(int n) {

    if (n == 0) {
        return;
    }

    System.out.println(n);

    print(n - 1);
}
```

---

## Q8. Does Java optimize tail recursion?

No. Java does not guarantee tail-call optimization.

Therefore tail-recursive Java methods can still consume stack space.

---

## Q9. Recursion vs iteration — which is better?

Neither is universally better.

Iteration often uses less memory, while recursion can provide a cleaner representation for naturally recursive problems such as trees and divide-and-conquer algorithms.

---

## Q10. What is direct recursion?

A function directly calls itself.

```java
void fun() {
    fun();
}
```

---

## Q11. What is indirect recursion?

Two or more functions call one another recursively.

```text
A → B → A → B → ...
```

---

## Q12. What is a recursion tree?

A tree-like representation of the recursive calls made by an algorithm.

It is particularly useful for analyzing algorithms with multiple recursive calls.

---

## Q13. Why is naive Fibonacci recursion inefficient?

Because it repeatedly solves the same subproblems.

For example:

```text
fib(5)
├── fib(4)
│   ├── fib(3)
│   └── fib(2)
└── fib(3)
    ├── fib(2)
    └── fib(1)
```

The same values are calculated repeatedly.

---

## Q14. How can recursive Fibonacci be optimized?

Use:

* Memoization
* Dynamic programming
* Iterative calculation

Memoization stores previously calculated results.

---

## Q15. Can every recursive problem be solved iteratively?

In principle, many recursive algorithms can be converted to iterative versions by explicitly managing state, often with a stack.

However, recursion may provide a much simpler representation of the problem.

---

# 43. Debugging Recursive Functions

When debugging recursion, print the parameters.

Example:

```java
static void test(int n) {

    System.out.println("Entering: " + n);

    if (n == 0) {
        return;
    }

    test(n - 1);

    System.out.println("Returning: " + n);
}
```

Calling:

```java
test(3);
```

Output:

```text
Entering: 3
Entering: 2
Entering: 1
Entering: 0
Returning: 1
Returning: 2
Returning: 3
```

This makes the two phases visible:

```text
Going Down
    ↓
Entering 3
Entering 2
Entering 1
Entering 0

Returning Up
    ↑
Returning 1
Returning 2
Returning 3
```

This is one of the best ways to understand recursion.

---

# 44. Recursion Checklist

Before submitting a recursive solution, ask:

```text
□ Do I have a base case?
□ Is the base case correct?
□ Does every recursive call move toward the base case?
□ Am I returning the recursive result when necessary?
□ What is the recursion depth?
□ What is the time complexity?
□ What is the auxiliary space complexity?
□ Could the recursion cause StackOverflowError?
□ Am I unnecessarily recalculating the same subproblems?
```

---

# 45. Quick Revision Sheet

## Definition

```text
Recursion = Function calling itself on a smaller problem.
```

## Essential Components

```text
Base Case
+
Recursive Case
```

## Example

```java
static int factorial(int n) {

    if (n == 0) {
        return 1;
    }

    return n * factorial(n - 1);
}
```

## Execution

```text
factorial(5)
→ 5 × factorial(4)
→ 5 × 4 × factorial(3)
→ 5 × 4 × 3 × factorial(2)
→ 5 × 4 × 3 × 2 × factorial(1)
→ 5 × 4 × 3 × 2 × 1
→ 120
```

## Complexity

```text
Time:  O(n)
Space: O(n)
```

## Main Applications

```text
Trees
Graphs / DFS
Divide & Conquer
Backtracking
Dynamic Programming
Sorting
Searching
```

---

# 46. The Most Important Mental Model

When you encounter a recursion problem, remember:

```text
                 PROBLEM
                    │
                    ▼
            Is this the smallest
               possible case?
               /          \
             YES           NO
              │             │
              ▼             ▼
           RETURN       Make problem
                         smaller
                            │
                            ▼
                      Call function
                            │
                            ▼
                     Use its result
```

The key idea is not:

> "How do I execute all recursive calls in my head?"

Instead:

> "If I trust the recursive function to correctly solve the smaller problem, how do I use that result to solve the current problem?"

That shift in thinking is the foundation of solving recursion problems.

---

# 47. Your Practice Roadmap

Since you have already completed factorial, use this sequence:

### Phase 1 — Fundamentals

```text
1. Factorial
2. Sum of N numbers
3. Power of a number
4. Print 1 → N
5. Print N → 1
6. Fibonacci
```

### Phase 2 — Arrays

```text
7. Print array
8. Sum of array
9. Maximum element
10. Minimum element
11. Linear search
12. Check sorted array
13. Reverse array
```

### Phase 3 — Strings

```text
14. Reverse string
15. Palindrome
16. Count characters
17. Remove a character
18. Generate subsequences
```

### Phase 4 — Divide and Conquer

```text
19. Binary Search
20. Merge Sort
21. Quick Sort
22. Fast Power
```

### Phase 5 — Trees

```text
23. Tree traversal
24. Maximum depth
25. Count nodes
26. Search in BST
27. Tree inversion
28. Path Sum
```

### Phase 6 — Backtracking

```text
29. Subsets
30. Permutations
31. Combinations
32. Combination Sum
33. Generate Parentheses
34. N-Queens
35. Sudoku
```

---

# 48. Final Takeaway

Recursion becomes much easier once you consistently identify three things:

```text
1. BASE CASE
   ↓
   When should recursion stop?

2. SMALLER PROBLEM
   ↓
   How do I reduce the input?

3. COMBINATION
   ↓
   How do I use the smaller answer
   to construct the current answer?
```

For example, factorial:

```text
BASE CASE:
factorial(0) = 1

SMALLER PROBLEM:
factorial(n - 1)

COMBINATION:
n × factorial(n - 1)
```

So:

```text
factorial(n)
    =
n × factorial(n - 1)
```

That pattern is the foundation.

Once you are comfortable with it, move from simple linear recursion → array/string recursion → tree recursion → divide-and-conquer → backtracking → dynamic programming.
