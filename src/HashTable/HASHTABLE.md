# Hash Table — Complete DSA Documentation

> A revision-oriented guide to Hash Tables, hashing, collisions, collision resolution, complexity, Java implementation, common patterns, LeetCode problems, interview questions, tricks, and pitfalls.

---

## 1. What Is a Hash Table?

A **Hash Table** is a data structure that stores data as **key-value pairs** and uses a **hash function** to determine where each key-value pair should be stored.

Example:

```text
Key       Value
----------------
"nails"   100
"tile"     50
"bolts"   200
```

Conceptually:

```text
             KEY
              |
              v
       +---------------+
       | Hash Function |
       +---------------+
              |
              v
            INDEX
              |
              v
       +-------------+
       | Hash Table  |
       +-------------+
              |
              v
          KEY + VALUE
```

The main reason Hash Tables are useful is that insertion, lookup, and deletion are **O(1) on average**.

---

# 2. Hash Table vs Hash Map

The terms are often used interchangeably in DSA discussions, but they can refer to different things depending on the language.

In Java:

```java
HashMap<K, V>
```

is the standard general-purpose hash-table-based map.

Java also has:

```java
Hashtable<K, V>
```

which is a legacy synchronized map with different behavior and tradeoffs.

For DSA learning, the important concept is the underlying **hash-table mechanism**, not the Java class name.

---

# 3. Core Components

A hash table generally consists of:

```text
Hash Table
│
├── Bucket Array
│
├── Hash Function
│
├── Key-Value Entries
│
├── Collision Handling
│
├── Load Factor
│
└── Resizing / Rehashing
```

The simplest implementation can be visualized as:

```text
Index       Bucket
--------------------------------
  0          null
  1          Node -> Node -> null
  2          null
  3          Node -> null
  4          Node -> Node -> Node
  5          null
  6          Node -> null
```

Each array position is called a **bucket**.

---

# 4. Key-Value Pair

A Hash Table stores an association:

```text
key -> value
```

Example:

```text
"apple" -> 50
"banana" -> 30
"orange" -> 40
```

The key identifies the value.

Typical operations:

```text
set(key, value)
get(key)
remove(key)
containsKey(key)
```

---

# 5. Hash Function

A **hash function** converts a key into an integer or hash value.

Conceptually:

```text
key
 |
 v
hash function
 |
 v
hash value
 |
 v
bucket index
```

For an array of size `7`:

```text
index = hash(key) % 7
```

The result must be within:

```text
0 to 6
```

because valid array indexes are:

```text
0, 1, 2, 3, 4, 5, 6
```

---

# 6. Example of Hashing

Suppose:

```text
Table size = 7
```

and a simplified hash function produces:

```text
hash("apple") = 17
```

Then:

```text
17 % 7 = 3
```

Therefore:

```text
"apple" -> bucket 3
```

Visual:

```text
"apple"
   |
   v
hash("apple")
   |
   v
17
   |
   v
17 % 7
   |
   v
3
   |
   v
bucket[3]
```

---

# 7. Why Use Modulo?

The hash value can be very large.

For example:

```text
hash = 927381
```

but the table may contain only:

```text
7 buckets
```

Using:

```text
927381 % 7
```

maps the value into a valid array index.

General formula:

```text
index = hash(key) % tableSize
```

---

# 8. Good Hash Function

A good hash function should:

1. Be deterministic.
2. Be reasonably fast.
3. Produce valid indexes.
4. Distribute keys uniformly.
5. Minimize collisions.

Deterministic means:

```text
hash("apple") = same result
```

when called with the same key under the same table configuration.

A poor hash function might send many keys into one bucket:

```text
bucket[0]

A -> B -> C -> D -> E -> F
```

A better distribution looks more like:

```text
bucket[0]  -> A
bucket[1]  -> null
bucket[2]  -> B -> C
bucket[3]  -> D
bucket[4]  -> null
bucket[5]  -> E
bucket[6]  -> F
```

---

# 9. Collision

A **collision** occurs when two different keys map to the same bucket.

Example:

```text
hash("apple") % 7 = 3
hash("orange") % 7 = 3
```

Both keys want bucket `3`.

```text
"apple"  ----\
              \
               -> bucket[3]
              /
"orange" ----/
```

A hash table must have a strategy for handling this.

---

# 10. Collision Resolution

Two major approaches are:

```text
Collision Handling
│
├── Separate Chaining
│
└── Open Addressing
    ├── Linear Probing
    ├── Quadratic Probing
    └── Double Hashing
```

---

# 11. Separate Chaining

Your Java implementation uses **separate chaining**.

Each bucket stores a linked list.

Example:

```text
bucket[3]

       +-----------+
       | apple=10  |
       +-----------+
             |
             v
       +-----------+
       | orange=20 |
       +-----------+
             |
             v
           null
```

The array contains the first node:

```text
dataMap[3] -> apple -> orange -> null
```

When searching:

```text
bucket[3]
   |
   v
apple?
   |
   no
   |
   v
orange?
   |
  yes
   |
   v
return 20
```

---

# 12. Why Linked Lists?

Suppose:

```text
hash("apple") = 3
hash("orange") = 3
hash("banana") = 3
```

All three keys must coexist.

A linked list allows:

```text
bucket[3]

apple -> orange -> banana -> null
```

Without collision handling, one value would overwrite another.

---

# 13. Your Hash Table Structure

Your implementation can be visualized as:

```text
                HashTable
                   |
                   v
             Node[] dataMap
                   |
        +----------+----------+
        |          |          |
        v          v          v
     bucket[0]  bucket[1]  bucket[2] ...
        |
        v
      Node
     /    \
   key   value
        |
       next
        |
        v
      Node
```

A Node contains:

```java
class Node {
    String key;
    int value;
    Node next;
}
```

So every node stores:

```text
key
value
next
```

---

# 14. Hash Table Insertion

Pseudocode:

```text
FUNCTION set(key, value):

    index = hash(key)

    IF bucket[index] is empty:
        bucket[index] = new Node(key, value)
        RETURN

    current = bucket[index]

    WHILE current is not null:

        IF current.key equals key:
            current.value = value
            RETURN

        IF current.next is null:
            BREAK

        current = current.next

    current.next = new Node(key, value)
```

Flow:

```text
              set(key, value)
                     |
                     v
                hash(key)
                     |
                     v
                   index
                     |
                     v
                bucket[index]
                 /        \
             empty       occupied
               |             |
             insert        traverse
                             |
                             v
                       key already exists?
                         /           \
                       yes           no
                        |             |
                     update        append
```

---

# 15. Hash Table Lookup

Pseudocode:

```text
FUNCTION get(key):

    index = hash(key)

    current = bucket[index]

    WHILE current is not null:

        IF current.key equals key:
            RETURN current.value

        current = current.next

    RETURN null
```

Visual:

```text
get("orange")
      |
      v
 hash("orange")
      |
      v
   bucket[3]
      |
      v
 apple -> orange -> banana -> null
           |
           v
         found
           |
           v
         value
```

---

# 16. Hash Table Deletion

Pseudocode:

```text
FUNCTION remove(key):

    index = hash(key)

    current = bucket[index]
    previous = null

    WHILE current is not null:

        IF current.key equals key:

            IF previous is null:
                bucket[index] = current.next

            ELSE:
                previous.next = current.next

            RETURN

        previous = current
        current = current.next
```

Example:

```text
Before:

A -> B -> C -> null

Remove B

After:

A ------> C -> null
```

The important operation is:

```text
previous.next = current.next
```

---

# 17. Updating an Existing Key

A Hash Table normally treats keys as unique.

Example:

```java
set("bolts", 200);
set("bolts", 500);
```

Expected result:

```text
bolts -> 500
```

not:

```text
bolts -> 200 -> bolts -> 500
```

Therefore `set()` should check whether the key already exists.

---

# 18. `==` vs `.equals()` in Java

Incorrect for comparing String contents:

```java
if (temp.key == key)
```

Correct:

```java
if (temp.key.equals(key))
```

Why?

```text
==

compares object references
```

while:

```text
.equals()

compares String contents
```

Example:

```java
String a = new String("hello");
String b = new String("hello");

a == b        // false
a.equals(b)   // true
```

For String keys in your Hash Table:

```java
temp.key.equals(key)
```

is the appropriate comparison.

---

# 19. Complete Java Implementation

```java
package HashTable;

import java.util.ArrayList;

public class HashTable {

    private final int size = 7;
    private final Node[] dataMap;

    class Node {
        String key;
        int value;
        Node next;

        Node(String key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public HashTable() {
        dataMap = new Node[size];
    }

    // Print the entire table
    public void printTable() {
        for (int i = 0; i < dataMap.length; i++) {

            System.out.println(i + ": ");

            Node temp = dataMap[i];

            while (temp != null) {
                System.out.println(
                    "    { " + temp.key + " = " + temp.value + " }"
                );

                temp = temp.next;
            }
        }
    }

    // Hash function
    private int hash(String key) {

        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }

        int hash = 0;

        for (char c : key.toCharArray()) {
            hash = (hash * 31 + c) % dataMap.length;
        }

        return hash;
    }

    // Insert or update
    public void set(String key, int value) {

        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }

        int index = hash(key);

        Node newNode = new Node(key, value);

        if (dataMap[index] == null) {
            dataMap[index] = newNode;
            return;
        }

        Node temp = dataMap[index];

        while (true) {

            if (temp.key.equals(key)) {
                temp.value = value;
                return;
            }

            if (temp.next == null) {
                break;
            }

            temp = temp.next;
        }

        temp.next = newNode;
    }

    // Get value
    public Integer get(String key) {

        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }

        int index = hash(key);

        Node temp = dataMap[index];

        while (temp != null) {

            if (temp.key.equals(key)) {
                return temp.value;
            }

            temp = temp.next;
        }

        return null;
    }

    // Check whether key exists
    public boolean containsKey(String key) {

        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }

        int index = hash(key);

        Node temp = dataMap[index];

        while (temp != null) {

            if (temp.key.equals(key)) {
                return true;
            }

            temp = temp.next;
        }

        return false;
    }

    // Remove key
    public void remove(String key) {

        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }

        int index = hash(key);

        Node current = dataMap[index];
        Node previous = null;

        while (current != null) {

            if (current.key.equals(key)) {

                if (previous == null) {
                    dataMap[index] = current.next;
                } else {
                    previous.next = current.next;
                }

                return;
            }

            previous = current;
            current = current.next;
        }
    }

    // Get all keys
    public ArrayList<String> keys() {

        ArrayList<String> allKeys = new ArrayList<>();

        for (Node bucket : dataMap) {

            Node temp = bucket;

            while (temp != null) {
                allKeys.add(temp.key);
                temp = temp.next;
            }
        }

        return allKeys;
    }

    // Number of stored entries
    public int size() {

        int count = 0;

        for (Node bucket : dataMap) {

            Node temp = bucket;

            while (temp != null) {
                count++;
                temp = temp.next;
            }
        }

        return count;
    }
}
```

---

# 20. Example Usage

```java
public class Main {

    public static void main(String[] args) {

        HashTable table = new HashTable();

        table.set("nails", 100);
        table.set("tile", 50);
        table.set("lumber", 80);
        table.set("bolts", 200);
        table.set("screws", 140);

        table.printTable();

        System.out.println(table.get("bolts"));

        System.out.println(table.get("paints"));

        System.out.println(table.containsKey("bolts"));

        table.set("bolts", 500);

        System.out.println(table.get("bolts"));

        table.remove("bolts");

        System.out.println(table.containsKey("bolts"));

        System.out.println(table.keys());
    }
}
```

---

# 21. Complexity

Let:

- `n` = number of stored entries
- `m` = number of buckets
- `k` = length of the key

## Average Case

With a good hash function and a reasonable load factor:

| Operation | Average |
|---|---:|
| Insert | O(1) |
| Search | O(1) |
| Delete | O(1) |
| Update | O(1) |

If key hashing itself takes `O(k)`, the more precise complexity is:

```text
O(k)
```

for hashing plus expected constant-time bucket access.

## Worst Case

If every key collides:

```text
bucket[0]

A -> B -> C -> D -> E -> F
```

then searching may require:

```text
O(n)
```

Therefore:

| Operation | Average | Worst |
|---|---:|---:|
| Insert | O(1) | O(n) |
| Search | O(1) | O(n) |
| Delete | O(1) | O(n) |
| Update | O(1) | O(n) |

---

# 22. Space Complexity

For `n` stored elements:

```text
Nodes = O(n)
Buckets = O(m)
```

Therefore:

```text
Space = O(n + m)
```

If the number of buckets is treated as constant:

```text
O(n)
```

---

# 23. Load Factor

The **load factor** measures how full the hash table is.

Formula:

```text
load factor = number of elements / number of buckets
```

For example:

```text
elements = 5
buckets = 10

load factor = 5 / 10
            = 0.5
```

Visual:

```text
10 buckets

[X] [X] [X] [X] [X] [ ] [ ] [ ] [ ] [ ]

             5 / 10
              = 0.5
```

A high load factor generally means more collisions.

---

# 24. Why Resizing Is Necessary

Suppose:

```text
buckets = 4
elements = 20
```

Then:

```text
load factor = 20 / 4 = 5
```

Many keys may end up sharing buckets.

```text
bucket[0] -> A -> E -> I -> M -> Q
bucket[1] -> B -> F -> J -> N -> R
bucket[2] -> C -> G -> K -> O -> S
bucket[3] -> D -> H -> L -> P -> T
```

Lookup starts becoming expensive.

A hash table can increase its capacity:

```text
4 buckets
     |
     v
8 buckets
     |
     v
16 buckets
```

This is called **resizing**.

---

# 25. Rehashing

When resizing, existing keys generally need to be hashed again because the bucket count changed.

Example:

```text
Old:

hash(key) % 7
```

After resizing:

```text
hash(key) % 14
```

The resulting index can change.

Therefore:

```text
Old bucket
    |
    v
recalculate hash/index
    |
    v
New bucket
```

This process is called **rehashing**.

---

# 26. Amortized Complexity

Resizing is expensive because many elements must be moved.

A single resize can take:

```text
O(n)
```

However, resizing does not happen after every insertion.

Across many insertions, insertion remains **amortized O(1)** under typical resizing strategies.

This distinction is important in interviews:

```text
Individual resize → O(n)
Normal/amortized insertion → O(1)
```

---

# 27. Open Addressing

Instead of using linked lists, open addressing stores entries directly inside the table.

Example:

```text
Index:

0
1
2
3
4
5
6
```

If bucket `3` is occupied, another location is searched.

Types:

```text
Open Addressing
│
├── Linear Probing
├── Quadratic Probing
└── Double Hashing
```

---

# 28. Linear Probing

If index `3` is occupied:

```text
3 → 4 → 5 → 6 → ...
```

Example:

```text
hash(key) = 3

bucket[3] occupied
        |
        v
bucket[4]?
        |
        v
empty → insert
```

Formula:

```text
index = (hash(key) + i) % tableSize
```

where:

```text
i = 0, 1, 2, 3...
```

---

# 29. Quadratic Probing

Instead of moving one position at a time:

```text
index = (hash(key) + i²) % tableSize
```

Search sequence might look like:

```text
3
4
0
5
...
```

depending on table size and `i`.

It reduces some clustering compared with linear probing.

---

# 30. Double Hashing

Uses a second hash function:

```text
index =
(hash1(key) + i * hash2(key)) % tableSize
```

This can provide better distribution than simple linear probing.

---

# 31. Separate Chaining vs Open Addressing

| Feature | Separate Chaining | Open Addressing |
|---|---|---|
| Collision handling | Linked structure | Find another slot |
| Extra node memory | Yes | Usually no |
| Deletion | Relatively simple | More complicated |
| Load factor | Can exceed 1 | Must stay below capacity |
| Implementation | Easier | More complex |

For learning the fundamentals, separate chaining is a good implementation to understand first.

---

# 32. HashSet

A `HashSet` stores unique values.

Conceptually:

```text
HashSet

apple
banana
orange
```

There is no separate user-facing value.

A useful mental model is:

```text
HashSet<T>

value -> value
```

Its primary purpose is fast membership testing and uniqueness.

Example:

```java
HashSet<Integer> set = new HashSet<>();

set.add(10);
set.add(20);

set.contains(10);
set.remove(20);
```

---

# 33. HashMap

A `HashMap` stores:

```text
key -> value
```

Example:

```java
HashMap<String, Integer> map = new HashMap<>();

map.put("apple", 10);
map.put("banana", 20);

int value = map.get("apple");
```

Common operations:

```java
put()
get()
remove()
containsKey()
containsValue()
keySet()
values()
entrySet()
```

---

# 34. Hash Table Mental Model

Memorize this:

```text
                KEY
                 |
                 v
           HASH FUNCTION
                 |
                 v
               INDEX
                 |
                 v
            BUCKET ARRAY
                 |
          +------+------+
          |             |
        empty        collision
          |             |
          v             v
       insert       linked list
                        |
                        v
                    compare key
                        |
                        v
                     value
```

---

# 35. Hash Table Invariants

Important things that must remain true:

1. Every key must map to a valid bucket.
2. A key should identify at most one logical value.
3. Colliding keys must not overwrite each other accidentally.
4. Lookup must search the correct bucket.
5. Key comparison must use logical equality.
6. After resizing, all entries must remain reachable.
7. Deletion must preserve the remaining chain/probing structure.

---

# 36. Common Mistakes

## Mistake 1 — Using `==` for Strings

```java
key1 == key2
```

Use:

```java
key1.equals(key2)
```

---

## Mistake 2 — Returning `0` for a Missing Key

This creates ambiguity:

```text
missing key -> 0
existing key -> 0
```

Prefer:

```java
Integer get(...)
```

and return:

```java
null
```

or use another explicit representation.

---

## Mistake 3 — Ignoring Duplicate Keys

Incorrect:

```text
set("apple", 10)
set("apple", 20)

apple -> 10 -> apple -> 20
```

For map semantics:

```text
apple -> 20
```

---

## Mistake 4 — Forgetting Collisions

Never assume:

```text
one key = one bucket
```

Instead:

```text
many keys can map to one bucket
```

---

## Mistake 5 — Thinking Hashing Guarantees O(1)

Hashing provides expected/average constant-time operations under suitable assumptions.

Worst case can still be:

```text
O(n)
```

---

## Mistake 6 — Forgetting Resizing

A fixed-size implementation is useful for learning but not equivalent to a production-quality dynamic hash table.

---

## Mistake 7 — Poor Hash Distribution

A hash function that sends many keys to the same bucket causes long chains.

---

# 37. Hash Table vs Array

| Feature | Array | Hash Table |
|---|---|---|
| Access by index | O(1) | O(1) average |
| Access by key | Not natural | O(1) average |
| Ordered | Index order | Generally no ordering guarantee |
| Duplicate keys | Allowed | Usually unique keys |
| Search by value | O(n) | O(n) |
| Dynamic resizing | Depends | Usually supported |

---

# 38. Hash Table vs Tree

| Feature | Hash Table | Balanced BST |
|---|---|---|
| Search | O(1) average | O(log n) |
| Insert | O(1) average | O(log n) |
| Delete | O(1) average | O(log n) |
| Sorted order | No | Yes |
| Range queries | Poor | Good |
| Exact lookup | Excellent | Good |

Use a Hash Table when fast exact lookup is the primary requirement.

Use a tree when ordering or range operations matter.

---

# 39. When Should You Think "Hash Table"?

In DSA problems, immediately consider a Hash Table when you see:

```text
frequency
count occurrences
duplicate
unique
seen before
visited
lookup
pair matching
complement
mapping
index lookup
grouping
anagram
subarray sum
```

Especially:

> "Have I seen this before?"

That is a strong Hash Set / Hash Map signal.

---

# 40. Common Hash Table Problem Patterns

## Pattern 1 — Frequency Map

Count occurrences:

```text
Input:
[1, 2, 2, 3, 3, 3]

Map:
1 -> 1
2 -> 2
3 -> 3
```

Pseudocode:

```text
map = empty

FOR each number:
    map[number]++

RETURN map
```

---

# 41. Pattern 2 — Seen Set

Detect duplicates:

```text
Input:
[1, 2, 3, 2]

seen = {}

1 → add
2 → add
3 → add
2 → already exists

duplicate found
```

Pseudocode:

```text
seen = empty set

FOR each element:

    IF element exists in seen:
        RETURN true

    add element to seen

RETURN false
```

---

# 42. Pattern 3 — Complement Lookup

Classic Two Sum pattern.

For:

```text
target = 9
current = 7
```

needed:

```text
9 - 7 = 2
```

Instead of searching the entire array:

```text
complement = target - current
```

Check whether complement is already in the map.

---

# 43. Pattern 4 — Value to Index

Store:

```text
value -> index
```

Example:

```text
nums = [2, 7, 11, 15]

map:
2 -> 0
7 -> 1
11 -> 2
15 -> 3
```

This converts repeated searching into fast lookup.

---

# 44. Pattern 5 — Prefix Sum + Hash Map

Useful for subarray-sum problems.

Maintain:

```text
prefixSum
```

and store:

```text
prefixSum -> index/count
```

The key mathematical relationship is:

```text
prefix[j] - prefix[i] = target
```

Therefore:

```text
prefix[i] = prefix[j] - target
```

A Hash Map lets us check whether the required prefix sum has appeared.

---

# 45. LeetCode Problem 1 — Two Sum

## Problem

Given an array and a target, return indices of two numbers whose sum equals the target.

Example:

```text
nums = [2, 7, 11, 15]
target = 9
```

Answer:

```text
[0, 1]
```

because:

```text
2 + 7 = 9
```

## Brute Force

```text
FOR i:
    FOR j:
        IF nums[i] + nums[j] == target:
            return [i, j]
```

Complexity:

```text
Time: O(n²)
Space: O(1)
```

## Hash Map Solution

Store previously seen values.

```text
map = empty

FOR i from 0 to n-1:

    complement = target - nums[i]

    IF complement exists in map:
        return [map[complement], i]

    map[nums[i]] = i
```

Java:

```java
public int[] twoSum(int[] nums, int target) {

    HashMap<Integer, Integer> map = new HashMap<>();

    for (int i = 0; i < nums.length; i++) {

        int complement = target - nums[i];

        if (map.containsKey(complement)) {
            return new int[] {
                map.get(complement),
                i
            };
        }

        map.put(nums[i], i);
    }

    return new int[0];
}
```

Complexity:

```text
Time: O(n)
Space: O(n)
```

### Trick

Don't ask:

```text
"What number can I pair with this?"
```

Ask:

```text
"What number do I need?"
```

Formula:

```text
needed = target - current
```

---

# 46. LeetCode Problem 2 — Contains Duplicate

## Problem

Determine whether an array contains duplicate values.

Example:

```text
[1, 2, 3, 1]

=> true
```

## HashSet Solution

```java
public boolean containsDuplicate(int[] nums) {

    HashSet<Integer> set = new HashSet<>();

    for (int num : nums) {

        if (set.contains(num)) {
            return true;
        }

        set.add(num);
    }

    return false;
}
```

Pseudocode:

```text
set = empty

FOR each number:

    IF number exists in set:
        return true

    add number

return false
```

Complexity:

```text
Time: O(n) average
Space: O(n)
```

### Trick

If the question asks:

> "Have I seen this element before?"

Think:

```text
HashSet
```

---

# 47. LeetCode Problem 3 — Valid Anagram

## Problem

Determine whether two strings are anagrams.

Example:

```text
s = "anagram"
t = "nagaram"

true
```

Both contain the same characters with the same frequencies.

## Hash Map Solution

```java
public boolean isAnagram(String s, String t) {

    if (s.length() != t.length()) {
        return false;
    }

    HashMap<Character, Integer> map = new HashMap<>();

    for (char c : s.toCharArray()) {
        map.put(c, map.getOrDefault(c, 0) + 1);
    }

    for (char c : t.toCharArray()) {

        if (!map.containsKey(c)) {
            return false;
        }

        map.put(c, map.get(c) - 1);

        if (map.get(c) < 0) {
            return false;
        }
    }

    return true;
}
```

Pseudocode:

```text
IF lengths differ:
    return false

count characters in s

FOR each character in t:
    decrease its count

    IF count becomes invalid:
        return false

return true
```

Complexity:

```text
Time: O(n)
Space: O(k)
```

where `k` is the number of distinct characters.

### Trick

Anagram problems are usually:

```text
character frequency
        ↓
HashMap
```

---

# 48. LeetCode Problem 4 — Group Anagrams

## Idea

Words that are anagrams have the same character-frequency signature.

Example:

```text
eat
tea
ate
```

All have:

```text
a:1
e:1
t:1
```

Use a canonical key.

One simple approach:

```text
sort("eat") -> "aet"
sort("tea") -> "aet"
sort("ate") -> "aet"
```

Then:

```text
"aet" -> ["eat", "tea", "ate"]
```

Java:

```java
public List<List<String>> groupAnagrams(String[] strs) {

    HashMap<String, List<String>> map = new HashMap<>();

    for (String str : strs) {

        char[] chars = str.toCharArray();

        Arrays.sort(chars);

        String key = new String(chars);

        map.computeIfAbsent(
            key,
            k -> new ArrayList<>()
        ).add(str);
    }

    return new ArrayList<>(map.values());
}
```

Complexity with sorting:

```text
Time: O(n * k log k)
Space: O(nk)
```

where:

```text
n = number of strings
k = average string length
```

### Trick

Ask:

> "What property makes these objects equivalent?"

Then create that property as the Hash Map key.

---

# 49. LeetCode Problem 5 — Top K Frequent Elements

## Problem

Given:

```text
[1,1,1,2,2,3]
```

return the `k` most frequent elements.

For:

```text
k = 2
```

answer:

```text
[1,2]
```

First count frequencies:

```text
1 -> 3
2 -> 2
3 -> 1
```

Then use a priority queue or bucket sort.

Hash Map portion:

```java
HashMap<Integer, Integer> frequency = new HashMap<>();

for (int num : nums) {
    frequency.put(
        num,
        frequency.getOrDefault(num, 0) + 1
    );
}
```

The key idea:

```text
number -> frequency
```

### Trick

When the problem says:

```text
frequency
most frequent
least frequent
count
```

think:

```text
HashMap
```

Then choose the second data structure required by the problem:

```text
HashMap + Heap
HashMap + Bucket Sort
```

---

# 50. LeetCode Problem 6 — Longest Consecutive Sequence

Example:

```text
[100, 4, 200, 1, 3, 2]
```

Longest sequence:

```text
1, 2, 3, 4
```

Length:

```text
4
```

Use a HashSet:

```java
public int longestConsecutive(int[] nums) {

    HashSet<Integer> set = new HashSet<>();

    for (int num : nums) {
        set.add(num);
    }

    int longest = 0;

    for (int num : set) {

        if (!set.contains(num - 1)) {

            int current = num;
            int length = 1;

            while (set.contains(current + 1)) {
                current++;
                length++;
            }

            longest = Math.max(longest, length);
        }
    }

    return longest;
}
```

Complexity:

```text
Time: O(n) average
Space: O(n)
```

### Important Trick

Only start a sequence when:

```text
num - 1 does NOT exist
```

This prevents repeatedly scanning the same sequence.

---

# 51. LeetCode Problem 7 — Subarray Sum Equals K

Given:

```text
nums = [1, 1, 1]
k = 2
```

Answer:

```text
2
```

Subarrays:

```text
[1,1]
[1,1]
```

Use prefix sums.

If:

```text
currentPrefix - previousPrefix = k
```

then:

```text
previousPrefix = currentPrefix - k
```

Store prefix-sum frequencies.

```java
public int subarraySum(int[] nums, int k) {

    HashMap<Integer, Integer> map = new HashMap<>();

    map.put(0, 1);

    int prefixSum = 0;
    int count = 0;

    for (int num : nums) {

        prefixSum += num;

        int needed = prefixSum - k;

        if (map.containsKey(needed)) {
            count += map.get(needed);
        }

        map.put(
            prefixSum,
            map.getOrDefault(prefixSum, 0) + 1
        );
    }

    return count;
}
```

Complexity:

```text
Time: O(n)
Space: O(n)
```

### Critical Trick

Remember:

```text
currentPrefix - oldPrefix = k

oldPrefix = currentPrefix - k
```

Then use the Hash Map to find `oldPrefix` in O(1) average time.

---

# 52. LeetCode Problem 8 — Happy Number

A number is happy if repeatedly replacing it with the sum of the squares of its digits eventually reaches `1`.

Example:

```text
19

1² + 9² = 82
8² + 2² = 68
6² + 8² = 100
1² + 0² + 0² = 1
```

Use a HashSet to detect cycles.

```java
public boolean isHappy(int n) {

    HashSet<Integer> seen = new HashSet<>();

    while (n != 1) {

        if (seen.contains(n)) {
            return false;
        }

        seen.add(n);

        int sum = 0;

        while (n > 0) {
            int digit = n % 10;
            sum += digit * digit;
            n /= 10;
        }

        n = sum;
    }

    return true;
}
```

### Trick

Whenever a process repeatedly transforms a state:

```text
state -> new state -> new state -> ...
```

and you need to detect repetition:

```text
HashSet
```

is often useful.

---

# 53. Interview Question — What Is a Hash Table?

Answer:

> A Hash Table is a key-value data structure that uses a hash function to map keys to bucket indexes. It provides average O(1) insertion, lookup, and deletion under good hashing and a controlled load factor. Collisions are handled using techniques such as separate chaining or open addressing.

---

# 54. Interview Question — What Is a Collision?

Answer:

> A collision occurs when two different keys produce the same bucket index after hashing.

Example:

```text
hash("A") % 7 = 3
hash("B") % 7 = 3
```

Both need bucket `3`.

---

# 55. Interview Question — How Do You Handle Collisions?

Answer:

Common methods are:

```text
1. Separate Chaining
2. Open Addressing
   - Linear Probing
   - Quadratic Probing
   - Double Hashing
```

---

# 56. Interview Question — Why Is Hash Table Lookup O(1)?

Answer:

> A hash function converts the key into a bucket index, allowing the table to directly access the corresponding bucket instead of scanning every element.

Expected:

```text
key -> hash -> index -> bucket
```

Therefore lookup is O(1) on average.

However, poor hashing or excessive collisions can make lookup O(n).

---

# 57. Interview Question — Can Hash Table Lookup Be O(n)?

Yes.

If many keys collide:

```text
bucket[3]

A -> B -> C -> D -> E
```

Searching for `E` requires traversing the chain.

Therefore:

```text
Average: O(1)
Worst:   O(n)
```

---

# 58. Interview Question — What Is Load Factor?

Answer:

> Load factor measures how many entries exist relative to the number of buckets.

Formula:

```text
load factor = entries / buckets
```

A higher load factor generally means more collisions.

---

# 59. Interview Question — What Is Rehashing?

Answer:

> Rehashing is the process of placing existing entries into a new, larger table and recalculating their bucket positions based on the new capacity.

Why?

Because:

```text
hash % oldCapacity
```

and:

```text
hash % newCapacity
```

can produce different indexes.

---

# 60. Interview Question — HashMap vs HashSet

`HashMap`:

```text
key -> value
```

`HashSet`:

```text
unique values
```

Use:

```text
HashMap → association / frequency / mapping
HashSet → uniqueness / membership / seen-before
```

---

# 61. Interview Question — HashMap vs TreeMap

```text
HashMap
- Average O(1) lookup
- No sorted-key guarantee

TreeMap
- O(log n) operations
- Keys maintained in sorted order
- Useful for ordered/range operations
```

---

# 62. Interview Question — Why Are Mutable Keys Dangerous?

Suppose an object is used as a key and its fields involved in hashing are modified after insertion.

Conceptually:

```text
insert key
   |
   v
hash(key) = bucket 3
```

Then mutate the key:

```text
hash(key) = bucket 6
```

The object may physically remain in bucket `3`, while lookup searches bucket `6`.

Result:

```text
key exists
but lookup cannot find it
```

Therefore keys should generally have stable equality and hashing behavior while stored.

---

# 63. `equals()` and `hashCode()`

In Java, custom objects used as HashMap keys should correctly implement both:

```java
equals()
hashCode()
```

Important contract:

```text
If a.equals(b) is true,
then a.hashCode() must equal b.hashCode().
```

The reverse is not required:

```text
same hash code
≠
objects must be equal
```

Because collisions are allowed.

---

# 64. Hash Code vs Hash Table Index

These are not necessarily the same thing.

Conceptually:

```text
Object
  |
  v
hashCode()
  |
  v
hash value
  |
  v
index calculation
  |
  v
bucket
```

A hash code is not automatically an array index.

The table uses the hash value together with its capacity to determine where to search.

---

# 65. Important DSA Pattern: Frequency Counting

For:

```text
[2, 2, 3, 3, 3, 4]
```

build:

```text
2 -> 2
3 -> 3
4 -> 1
```

Pseudocode:

```text
frequency = empty map

FOR x in array:
    frequency[x] = frequency[x] + 1
```

Java:

```java
map.put(
    x,
    map.getOrDefault(x, 0) + 1
);
```

---

# 66. Important DSA Pattern: Membership

Question:

> Have I seen this before?

Use:

```text
HashSet
```

Pseudocode:

```text
seen = empty set

FOR x:

    IF x in seen:
        duplicate

    add x
```

---

# 67. Important DSA Pattern: Mapping

Question:

> What information do I need to remember about this value?

Use:

```text
HashMap<value, information>
```

Examples:

```text
number -> index
character -> frequency
value -> count
prefix sum -> index
user ID -> user object
```

---

# 68. Important DSA Pattern: Reverse Mapping

Sometimes the input gives:

```text
key -> value
```

but you need:

```text
value -> key
```

Create another map if necessary.

Example:

```text
original:
A -> 1
B -> 2

reverse:
1 -> A
2 -> B
```

This is common in lookup and bijection problems.

---

# 69. Important DSA Pattern: Composite Keys

Sometimes one value is not enough to identify a state.

Instead of:

```text
key = x
```

you may need:

```text
key = (x, y)
```

Conceptually:

```text
(x, y) -> information
```

In Java you can represent this using:

```text
record
custom class
String encoding
```

Example:

```java
record Pair(int x, int y) {}
```

Then:

```java
HashSet<Pair> seen = new HashSet<>();
```

For custom classes/records, correct equality and hashing are essential.

---

# 70. Important DSA Pattern: Prefix Sum

For:

```text
[1, 2, 3, 4]
```

prefix sums:

```text
0
1
3
6
10
```

If:

```text
prefix[j] - prefix[i] = target
```

then the subarray between them has the target sum.

Hash Map:

```text
prefixSum -> index/count
```

is often used to find the required prefix quickly.

---

# 71. Important DSA Pattern: Sliding Window + Hash Map

Hash Maps frequently appear with sliding windows.

Example:

```text
Longest substring without repeating characters
```

Maintain information about characters currently inside the window.

Concept:

```text
left
  |
  v
[a b c a]
      ^
      right
```

When a duplicate appears, move the left boundary.

A Hash Map can store the latest index of each character.

---

# 72. Longest Substring Without Repeating Characters

Example:

```text
"abcabcbb"
```

Longest substring:

```text
"abc"
```

Length:

```text
3
```

Java:

```java
public int lengthOfLongestSubstring(String s) {

    HashMap<Character, Integer> map = new HashMap<>();

    int left = 0;
    int maxLength = 0;

    for (int right = 0; right < s.length(); right++) {

        char current = s.charAt(right);

        if (map.containsKey(current)) {
            left = Math.max(
                left,
                map.get(current) + 1
            );
        }

        map.put(current, right);

        maxLength = Math.max(
            maxLength,
            right - left + 1
        );
    }

    return maxLength;
}
```

Complexity:

```text
Time: O(n)
Space: O(k)
```

where `k` is the number of distinct characters.

### Trick

For substring problems involving:

```text
duplicate characters
frequency
unique characters
```

consider:

```text
Sliding Window + HashMap/HashSet
```

---

# 73. How to Recognize Hash Table Problems

Use this decision process:

```text
Does the problem ask about duplicates?
        |
       YES
        |
     HashSet

Does it ask for frequency/count?
        |
       YES
        |
     HashMap

Does it require value -> index?
        |
       YES
        |
     HashMap

Does it ask "have I seen this?"
        |
       YES
        |
     HashSet

Does it involve complements?
        |
       YES
        |
     HashMap

Does it involve prefix sums?
        |
       YES
        |
  HashMap + Prefix Sum

Does it involve unique substring/window?
        |
       YES
        |
 Sliding Window + HashMap/Set
```

---

# 74. Hash Table Problem-Solving Checklist

Before coding, ask:

```text
1. What should be my key?
2. What should be my value?
3. Do I need HashMap or HashSet?
4. What information should I store?
5. Can I turn repeated searching into lookup?
6. Is this a frequency problem?
7. Is this a duplicate/seen problem?
8. Is this a complement problem?
9. Is this a prefix-sum problem?
10. What is the expected complexity?
```

---

# 75. Tricks and Tips

## Trick 1 — "Seen Before?"

Use:

```java
HashSet
```

---

## Trick 2 — "How Many?"

Use:

```java
HashMap<T, Integer>
```

---

## Trick 3 — "Where Was It?"

Use:

```java
HashMap<T, Integer>
```

where the value is the index.

---

## Trick 4 — "What Do I Need?"

For Two Sum-style problems:

```text
needed = target - current
```

Look up `needed`.

---

## Trick 5 — "Same Frequency?"

Think:

```text
frequency map
```

Useful for:

```text
anagrams
character counts
top K frequency
duplicates
```

---

## Trick 6 — "Have I Seen This State?"

Use:

```text
HashSet
```

Useful for:

```text
cycle detection
visited states
duplicate states
```

---

## Trick 7 — "Subarray Sum?"

Think:

```text
Prefix Sum + HashMap
```

---

## Trick 8 — "Substring + Unique/Frequency?"

Think:

```text
Sliding Window + HashMap/HashSet
```

---

## Trick 9 — Store the Most Useful Direction

If you frequently ask:

```text
value -> index
```

store:

```text
value -> index
```

If you frequently ask:

```text
ID -> object
```

store:

```text
ID -> object
```

The key should support the lookup you need.

---

# 76. Complexity Summary

| Concept | Complexity |
|---|---:|
| Hashing a fixed-size key | O(1) |
| HashMap lookup average | O(1) |
| HashMap insertion average | O(1) |
| HashMap deletion average | O(1) |
| HashMap lookup worst case | O(n) |
| HashMap insertion worst case | O(n) |
| HashMap deletion worst case | O(n) |
| HashSet lookup average | O(1) |
| HashSet insertion average | O(1) |
| HashSet deletion average | O(1) |
| Iterate all entries | O(n) |
| Resize / rehash | O(n) |
| Amortized insertion | O(1) |

---

# 77. Hash Table Revision Sheet

```text
HASH TABLE
│
├── Stores key-value pairs
│
├── Hash function
│      │
│      └── key -> hash -> index
│
├── Bucket array
│
├── Collision
│      │
│      ├── Separate chaining
│      └── Open addressing
│
├── Load factor
│
├── Resizing
│
└── Rehashing
```

Core complexity:

```text
Average:
Insert  -> O(1)
Search  -> O(1)
Delete  -> O(1)

Worst:
Insert  -> O(n)
Search  -> O(n)
Delete  -> O(n)
```

Core DSA patterns:

```text
Duplicate          -> HashSet
Frequency          -> HashMap
Seen before        -> HashSet
Value -> index     -> HashMap
Complement         -> HashMap
Anagram            -> HashMap
Prefix sum         -> HashMap
Unique substring   -> Sliding Window + HashMap/Set
Cycle detection    -> HashSet
Grouping           -> HashMap
```

---

# 78. Essential LeetCode Practice Order

A good progression is:

```text
1. Contains Duplicate
        ↓
2. Two Sum
        ↓
3. Valid Anagram
        ↓
4. Group Anagrams
        ↓
5. Longest Substring Without Repeating Characters
        ↓
6. Top K Frequent Elements
        ↓
7. Longest Consecutive Sequence
        ↓
8. Subarray Sum Equals K
        ↓
9. Happy Number
```

The goal is not merely to solve them.

For each problem, identify:

```text
1. Why HashMap/HashSet?
2. What is the key?
3. What is the value?
4. What information is being remembered?
5. Why does lookup reduce the complexity?
6. What is the invariant?
7. What is the time complexity?
8. What is the space complexity?
```

---

# 79. Final Mental Model

Remember Hash Tables as:

```text
                         HASH TABLE
                              |
             +----------------+----------------+
             |                |                |
             v                v                v
         HASHING          BUCKETS         COLLISIONS
             |                |                |
             v                v                v
        key -> index       array         chaining /
                                           probing
                              |
                              v
                         FAST LOOKUP
                              |
                              v
                          O(1) average
```

And for DSA problems:

```text
                 HASH TABLE
                      |
        +-------------+-------------+
        |             |             |
        v             v             v
    HashSet        HashMap      Prefix Sum
        |             |             |
        v             v             v
   uniqueness     mapping       subarrays
   membership     frequency
   duplicates     index
   seen states    complement
```

The single most important idea to retain is:

> **A Hash Table trades additional memory for fast lookup by transforming a key into a location.**

Once you recognize that a problem repeatedly asks you to search for, count, remember, map, or verify the existence of something, consider whether a Hash Map or Hash Set can turn that repeated search into average O(1) lookup.
