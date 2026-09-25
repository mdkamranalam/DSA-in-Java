# Matrix / Grid in Java

> A complete study and revision reference covering matrix fundamentals, Java implementation, traversal patterns, searching, sorting, transpose, diagonals, rotation, spiral traversal, common algorithms, LeetCode problems, pseudocode, complexity analysis, and interview questions.

---

# 1. What Is a Matrix?

A **matrix** is a collection of elements arranged in rows and columns.

In Java, a matrix is generally represented using a **2D array**:

```java
int[][] matrix;
```

Example:

```java
int[][] matrix = {
    {1, 2, 3},
    {4, 5, 6},
    {7, 8, 9}
};
```

Visual representation:

```text
             Columns
              0   1   2
            +---+---+---+
       Row 0| 1 | 2 | 3 |
            +---+---+---+
       Row 1| 4 | 5 | 6 |
            +---+---+---+
       Row 2| 7 | 8 | 9 |
            +---+---+---+
```

This matrix has:

```text
Rows    = 3
Columns = 3
Elements = 3 × 3 = 9
```

For a matrix with `R` rows and `C` columns:

```text
Total elements = R × C
```

---

# 2. Matrix Terminology

Important terms:

| Term               | Meaning                            |
| ------------------ | ---------------------------------- |
| Row                | Horizontal collection of elements  |
| Column             | Vertical collection of elements    |
| Cell               | Individual element                 |
| Dimensions         | Number of rows × number of columns |
| Square matrix      | Same number of rows and columns    |
| Rectangular matrix | Number of rows and columns differ  |
| Main diagonal      | From top-left to bottom-right      |
| Secondary diagonal | From top-right to bottom-left      |
| Transpose          | Rows become columns                |
| Boundary           | Outer layer of the matrix          |

Example:

```text
1 2 3
4 5 6
7 8 9
```

Dimensions:

```text
3 × 3
```

Main diagonal:

```text
1
   5
      9
```

Secondary diagonal:

```text
      3
   5
7
```

---

# 3. Matrix Representation in Java

## Declaration

```java
int[][] matrix;
```

## Creation

```java
int[][] matrix = new int[3][4];
```

This creates:

```text
3 rows
4 columns
```

Initially:

```text
0 0 0 0
0 0 0 0
0 0 0 0
```

## Initialization

```java
int[][] matrix = {
    {10, 20, 30},
    {40, 50, 60},
    {70, 80, 90}
};
```

---

# 4. Accessing Matrix Elements

Use:

```java
matrix[row][column]
```

Example:

```java
int[][] matrix = {
    {10, 20, 30},
    {40, 50, 60},
    {70, 80, 90}
};
```

```java
matrix[0][0] // 10
matrix[0][1] // 20
matrix[1][0] // 40
matrix[1][2] // 60
matrix[2][1] // 80
```

Remember:

```text
matrix[i][j]

i → row
j → column
```

Visual:

```text
             j
          0    1    2
       +----+----+----+
 i=0   | 10 | 20 | 30 |
       +----+----+----+
 i=1   | 40 | 50 | 60 |
       +----+----+----+
 i=2   | 70 | 80 | 90 |
       +----+----+----+
```

---

# 5. Rows and Columns

For:

```text
1 2 3
4 5 6
7 8 9
```

Rows:

```text
Row 0 → 1 2 3
Row 1 → 4 5 6
Row 2 → 7 8 9
```

Columns:

```text
Column 0 → 1 4 7
Column 1 → 2 5 8
Column 2 → 3 6 9
```

In Java:

```java
int rows = matrix.length;
int columns = matrix[0].length;
```

For a regular rectangular matrix:

```text
rows    = matrix.length
columns = matrix[0].length
```

For a jagged matrix, use:

```java
matrix[i].length
```

for each individual row.

---

# 6. The Most Important Matrix Pattern

Most basic matrix problems start with nested loops:

```java
for (int i = 0; i < matrix.length; i++) {

    for (int j = 0; j < matrix[i].length; j++) {

        // process matrix[i][j]
    }
}
```

The traversal order is:

```text
→ → →
→ → →
→ → →
```

For:

```text
1 2 3
4 5 6
7 8 9
```

the order is:

```text
1 → 2 → 3 → 4 → 5 → 6 → 7 → 8 → 9
```

### Pseudocode

```text
FOR each row i
    FOR each column j
        process matrix[i][j]
```

### Complexity

```text
Time  = O(R × C)
Space = O(1)
```

---

# 7. Printing a Matrix

```java
private static void printMatrix(int[][] matrix) {

    for (int i = 0; i < matrix.length; i++) {

        for (int j = 0; j < matrix[i].length; j++) {

            if (j > 0) {
                System.out.print(", ");
            }

            System.out.print(matrix[i][j]);
        }

        System.out.println();
    }
}
```

Output:

```text
1, 2, 3
4, 5, 6
7, 8, 9
```

### Pseudocode

```text
FOR i = 0 to rows - 1
    FOR j = 0 to columns - 1
        print matrix[i][j]
    print newline
```

---

# 8. Traversing Row by Row

This is called **row-major traversal**.

```java
for (int i = 0; i < matrix.length; i++) {

    for (int j = 0; j < matrix[i].length; j++) {

        System.out.print(matrix[i][j] + " ");
    }

    System.out.println();
}
```

Visual:

```text
→ → →
→ → →
→ → →
```

Use this pattern for:

* Printing
* Searching
* Counting
* Summing
* Finding maximum/minimum
* Updating elements
* Frequency calculations

---

# 9. Traversing Column by Column

```java
for (int j = 0; j < matrix[0].length; j++) {

    for (int i = 0; i < matrix.length; i++) {

        System.out.print(matrix[i][j] + " ");
    }

    System.out.println();
}
```

Visual:

```text
↓ ↓ ↓
↓ ↓ ↓
↓ ↓ ↓
```

For:

```text
1 2 3
4 5 6
7 8 9
```

output:

```text
1 4 7
2 5 8
3 6 9
```

### Pseudocode

```text
FOR each column j
    FOR each row i
        process matrix[i][j]
```

---

# 10. Searching in an Unsorted Matrix

For an unsorted matrix, the straightforward solution is linear search.

```java
private static boolean searchInMatrix(int[][] matrix, int target) {

    for (int i = 0; i < matrix.length; i++) {

        for (int j = 0; j < matrix[i].length; j++) {

            if (matrix[i][j] == target) {
                return true;
            }
        }
    }

    return false;
}
```

Example:

```text
1 2 3
4 5 6
7 8 9
```

Search:

```text
target = 6
```

Traversal:

```text
1 → 2 → 3 → 4 → 5 → 6 ✓
```

### Pseudocode

```text
FOR each cell
    IF matrix[i][j] == target
        return true

return false
```

### Complexity

```text
Time  = O(R × C)
Space = O(1)
```

Worst case:

* Target does not exist.
* Target is the last element.

---

# 11. Find Maximum Element

```java
int max = matrix[0][0];

for (int i = 0; i < matrix.length; i++) {

    for (int j = 0; j < matrix[i].length; j++) {

        if (matrix[i][j] > max) {
            max = matrix[i][j];
        }
    }
}
```

### Pseudocode

```text
max = first element

FOR every element
    IF current > max
        max = current

RETURN max
```

Complexity:

```text
Time  = O(R × C)
Space = O(1)
```

---

# 12. Find Minimum Element

```java
int min = matrix[0][0];

for (int i = 0; i < matrix.length; i++) {

    for (int j = 0; j < matrix[i].length; j++) {

        if (matrix[i][j] < min) {
            min = matrix[i][j];
        }
    }
}
```

### Pseudocode

```text
min = first element

FOR every element
    IF current < min
        min = current

RETURN min
```

---

# 13. Sum of All Matrix Elements

```java
int sum = 0;

for (int i = 0; i < matrix.length; i++) {

    for (int j = 0; j < matrix[i].length; j++) {

        sum += matrix[i][j];
    }
}
```

Example:

```text
1 2 3
4 5 6
7 8 9
```

```text
sum = 45
```

### Pseudocode

```text
sum = 0

FOR every cell
    sum = sum + matrix[i][j]

RETURN sum
```

Complexity:

```text
Time  = O(R × C)
Space = O(1)
```

---

# 14. Count Even and Odd Elements

```java
int even = 0;
int odd = 0;

for (int i = 0; i < matrix.length; i++) {

    for (int j = 0; j < matrix[i].length; j++) {

        if (matrix[i][j] % 2 == 0) {
            even++;
        } else {
            odd++;
        }
    }
}
```

### Pseudocode

```text
even = 0
odd = 0

FOR every cell
    IF element % 2 == 0
        even++
    ELSE
        odd++
```

---

# 15. Row Sum

For:

```text
1 2 3
4 5 6
7 8 9
```

row sums:

```text
Row 0 → 1 + 2 + 3 = 6
Row 1 → 4 + 5 + 6 = 15
Row 2 → 7 + 8 + 9 = 24
```

Java:

```java
for (int i = 0; i < matrix.length; i++) {

    int sum = 0;

    for (int j = 0; j < matrix[i].length; j++) {
        sum += matrix[i][j];
    }

    System.out.println("Row " + i + " = " + sum);
}
```

### Pseudocode

```text
FOR each row i
    sum = 0

    FOR each column j
        sum += matrix[i][j]

    print sum
```

---

# 16. Column Sum

```java
for (int j = 0; j < matrix[0].length; j++) {

    int sum = 0;

    for (int i = 0; i < matrix.length; i++) {
        sum += matrix[i][j];
    }

    System.out.println("Column " + j + " = " + sum);
}
```

Example:

```text
1 2 3
4 5 6
7 8 9
```

Column sums:

```text
Column 0 = 12
Column 1 = 15
Column 2 = 18
```

---

# 17. Main Diagonal

The main diagonal runs from:

```text
top-left → bottom-right
```

Example:

```text
1 2 3
4 5 6
7 8 9
```

Visual:

```text
[1]  2   3
 4  [5]  6
 7   8  [9]
```

The indexes satisfy:

```text
row == column
```

Therefore:

```java
for (int i = 0; i < matrix.length; i++) {
    System.out.println(matrix[i][i]);
}
```

Elements:

```text
1, 5, 9
```

---

# 18. Main Diagonal Sum

```java
int sum = 0;

for (int i = 0; i < matrix.length; i++) {
    sum += matrix[i][i];
}
```

For:

```text
1 2 3
4 5 6
7 8 9
```

```text
sum = 1 + 5 + 9 = 15
```

### Pseudocode

```text
sum = 0

FOR i = 0 to n - 1
    sum += matrix[i][i]

RETURN sum
```

---

# 19. Secondary Diagonal

The secondary diagonal runs:

```text
top-right → bottom-left
```

Example:

```text
1  2 [3]
4 [5] 6
[7] 8  9
```

Indexes:

```text
[0][2]
[1][1]
[2][0]
```

Formula:

```text
column = n - 1 - row
```

Java:

```java
int n = matrix.length;

for (int i = 0; i < n; i++) {

    int j = n - 1 - i;

    System.out.println(matrix[i][j]);
}
```

### Pseudocode

```text
n = number of rows

FOR i = 0 to n - 1
    j = n - 1 - i
    process matrix[i][j]
```

---

# 20. Secondary Diagonal Sum

```java
int sum = 0;

int n = matrix.length;

for (int i = 0; i < n; i++) {
    sum += matrix[i][n - 1 - i];
}
```

---

# 21. Both Diagonal Sums

```java
int primary = 0;
int secondary = 0;

int n = matrix.length;

for (int i = 0; i < n; i++) {

    primary += matrix[i][i];

    secondary += matrix[i][n - 1 - i];
}
```

### Complexity

```text
Time  = O(N)
Space = O(1)
```

---

# 22. Transpose of a Matrix

Transpose converts:

```text
rows → columns
columns → rows
```

Example:

```text
Original:

1 2 3
4 5 6
```

Transpose:

```text
1 4
2 5
3 6
```

The fundamental relationship is:

```text
transpose[j][i] = matrix[i][j]
```

---

# 23. Transpose — Java Implementation

```java
private static int[][] transpose(int[][] matrix) {

    int rows = matrix.length;
    int columns = matrix[0].length;

    int[][] transpose = new int[columns][rows];

    for (int i = 0; i < rows; i++) {

        for (int j = 0; j < columns; j++) {

            transpose[j][i] = matrix[i][j];
        }
    }

    return transpose;
}
```

### Pseudocode

```text
rows = number of rows
columns = number of columns

CREATE transpose[columns][rows]

FOR i = 0 to rows - 1
    FOR j = 0 to columns - 1
        transpose[j][i] = matrix[i][j]

RETURN transpose
```

### Complexity

```text
Time  = O(R × C)
Space = O(R × C)
```

because a new matrix is created.

---

# 24. In-Place Transpose

For a **square matrix**, transpose can be done without another matrix.

Example:

```text
1 2 3
4 5 6
7 8 9
```

Swap:

```text
matrix[i][j] ↔ matrix[j][i]
```

Only the elements above the main diagonal need to be swapped.

Java:

```java
for (int i = 0; i < matrix.length; i++) {

    for (int j = i + 1; j < matrix.length; j++) {

        int temp = matrix[i][j];

        matrix[i][j] = matrix[j][i];

        matrix[j][i] = temp;
    }
}
```

Visual:

```text
[1]  2   3
 4  [5]  6
 7   8  [9]
```

Only swap:

```text
2 ↔ 4
3 ↔ 7
6 ↔ 8
```

Result:

```text
1 4 7
2 5 8
3 6 9
```

Complexity:

```text
Time  = O(N²)
Space = O(1)
```

---

# 25. Row-wise Sorting

Java provides:

```java
Arrays.sort()
```

Example:

```java
import java.util.Arrays;
```

```java
int[][] matrix = {
    {77, 11, 22, 3},
    {11, 89, 1, 12},
    {32, 11, 56, 7},
    {11, 22, 44, 33}
};
```

Method:

```java
private static void rowWiseSort(int[][] matrix) {

    for (int i = 0; i < matrix.length; i++) {
        Arrays.sort(matrix[i]);
    }
}
```

Result:

```text
3  11 22 77
1  11 12 89
7  11 32 56
11 22 33 44
```

Each row is sorted independently.

### Pseudocode

```text
FOR each row
    sort that row
```

---

# 26. Column-wise Sorting

One elegant technique is:

```text
Transpose
    ↓
Rows become original columns
    ↓
Sort rows
    ↓
Transpose again
    ↓
Original columns are sorted
```

Java:

```java
private static int[][] colWiseSort(int[][] matrix) {

    int[][] transposed = transpose(matrix);

    rowWiseSort(transposed);

    return transpose(transposed);
}
```

Usage:

```java
arr = colWiseSort(arr);
```

Example:

```text
Original:

7 2 5
9 1 4
6 8 3
```

Transpose:

```text
7 9 6
2 1 8
5 4 3
```

Sort rows:

```text
6 7 9
1 2 8
3 4 5
```

Transpose:

```text
6 1 3
7 2 4
9 8 5
```

Now the original columns are sorted:

```text
Column 0 → 6 7 9
Column 1 → 1 2 8
Column 2 → 3 4 5
```

---

# 27. Important Java Concept: Reference Assignment

Consider:

```java
private static int[][] colWiseSort(int[][] matrix) {

    int[][] transposed = transpose(matrix);

    rowWiseSort(transposed);

    return transpose(transposed);
}
```

Then:

```java
arr = colWiseSort(arr);
```

This is important.

If inside a method you write:

```java
matrix = newMatrix;
```

you only change the local reference.

It does **not** replace the caller's reference automatically.

Therefore:

```java
arr = colWiseSort(arr);
```

is the correct approach when the method creates and returns a new matrix.

---

# 28. Matrix Rotation

Matrix rotation is one of the most common matrix interview patterns.

For a square matrix, 90° clockwise rotation can be performed using:

```text
Transpose
    ↓
Reverse every row
```

Example:

Original:

```text
1 2 3
4 5 6
7 8 9
```

After transpose:

```text
1 4 7
2 5 8
3 6 9
```

Reverse every row:

```text
7 4 1
8 5 2
9 6 3
```

Final result:

```text
7 4 1
8 5 2
9 6 3
```

---

# 29. Rotate Matrix 90° Clockwise — Java

```java
private static void rotate90Clockwise(int[][] matrix) {

    int n = matrix.length;

    // Step 1: Transpose
    for (int i = 0; i < n; i++) {

        for (int j = i + 1; j < n; j++) {

            int temp = matrix[i][j];

            matrix[i][j] = matrix[j][i];

            matrix[j][i] = temp;
        }
    }

    // Step 2: Reverse every row
    for (int i = 0; i < n; i++) {

        int left = 0;
        int right = n - 1;

        while (left < right) {

            int temp = matrix[i][left];

            matrix[i][left] = matrix[i][right];

            matrix[i][right] = temp;

            left++;
            right--;
        }
    }
}
```

Complexity:

```text
Time  = O(N²)
Space = O(1)
```

---

# 30. Rotate Matrix 90° Counterclockwise

A common method:

```text
Transpose
    ↓
Reverse the order of rows
```

Example:

```text
Original:

1 2 3
4 5 6
7 8 9
```

Transpose:

```text
1 4 7
2 5 8
3 6 9
```

Reverse row order:

```text
3 6 9
2 5 8
1 4 7
```

Result:

```text
3 6 9
2 5 8
1 4 7
```

---

# 31. Spiral Matrix Traversal

Spiral traversal visits the matrix from the outside toward the center.

Example:

```text
1   2   3   4
5   6   7   8
9  10  11  12
13 14  15  16
```

Traversal:

```text
1 → 2 → 3 → 4
              ↓
              8
              ↓
              12
              ↓
16 ← 15 ← 14 ← 13
↑
9
↑
5
→ 6 → 7
      ↓
     11
      ↓
10
```

Spiral order:

```text
1 2 3 4 8 12 16 15 14 13 9 5 6 7 11 10
```

---

# 32. Spiral Traversal Algorithm

Maintain four boundaries:

```text
top
bottom
left
right
```

Initially:

```java
int top = 0;
int bottom = matrix.length - 1;
int left = 0;
int right = matrix[0].length - 1;
```

Process:

```text
1. Traverse top row left → right
2. Traverse right column top → bottom
3. Traverse bottom row right → left
4. Traverse left column bottom → top

Shrink boundaries.
Repeat.
```

### Pseudocode

```text
top = 0
bottom = rows - 1
left = 0
right = columns - 1

WHILE top <= bottom AND left <= right

    FOR j = left to right
        visit matrix[top][j]
    top++

    FOR i = top to bottom
        visit matrix[i][right]
    right--

    IF top <= bottom
        FOR j = right down to left
            visit matrix[bottom][j]
        bottom--

    IF left <= right
        FOR i = bottom down to top
            visit matrix[i][left]
        left++
```

Complexity:

```text
Time  = O(R × C)
Space = O(1)
```

---

# 33. Boundary Traversal

Boundary traversal means visiting only the outer boundary.

Example:

```text
1  2  3  4
5  6  7  8
9 10 11 12
13 14 15 16
```

Boundary order:

```text
1 → 2 → 3 → 4
              ↓
              8
              ↓
              12
              ↓
16 ← 15 ← 14 ← 13
↑
9
↑
5
```

Important edge cases:

```text
1 × N matrix
N × 1 matrix
1 × 1 matrix
```

When implementing boundary traversal, avoid printing the same row or column twice.

---

# 34. Search in a Row-wise and Column-wise Sorted Matrix

Consider:

```text
1   4   7   11
2   5   8   12
3   6   9   16
10 13  14   17
```

Properties:

```text
Every row is sorted.
Every column is sorted.
```

Start from the top-right corner:

```text
1   4   7  [11]
2   5   8   12
3   6   9   16
10 13  14   17
```

For target `9`:

```text
11 > 9
move left

7 < 9
move down

8 < 9
move down

9 == 9
FOUND
```

### Decision rule

```text
current > target
    → move left

current < target
    → move down

current == target
    → found
```

### Pseudocode

```text
row = 0
col = columns - 1

WHILE row < rows AND col >= 0

    IF matrix[row][col] == target
        return true

    ELSE IF matrix[row][col] > target
        col--

    ELSE
        row++

return false
```

Complexity:

```text
Time  = O(R + C)
Space = O(1)
```

This is better than `O(R × C)` because every move eliminates an entire row or column.

---

# 35. Matrix Multiplication

For multiplication:

```text
A × B
```

the number of columns in `A` must equal the number of rows in `B`.

If:

```text
A = 2 × 3
B = 3 × 4
```

then:

```text
Result = 2 × 4
```

Formula:

```text
C[i][j] = Σ A[i][k] × B[k][j]
```

Java:

```java
private static int[][] multiply(int[][] A, int[][] B) {

    int rowsA = A.length;
    int colsA = A[0].length;
    int colsB = B[0].length;

    int[][] result = new int[rowsA][colsB];

    for (int i = 0; i < rowsA; i++) {

        for (int j = 0; j < colsB; j++) {

            for (int k = 0; k < colsA; k++) {

                result[i][j] += A[i][k] * B[k][j];
            }
        }
    }

    return result;
}
```

### Pseudocode

```text
FOR i from 0 to rowsA - 1

    FOR j from 0 to columnsB - 1

        result[i][j] = 0

        FOR k from 0 to columnsA - 1

            result[i][j] += A[i][k] × B[k][j]
```

Complexity:

```text
O(R × K × C)
```

For two `N × N` matrices:

```text
O(N³)
```

---

# 36. Identity Matrix

An identity matrix is a square matrix with:

```text
1 on the main diagonal
0 everywhere else
```

Example:

```text
1 0 0
0 1 0
0 0 1
```

Condition:

```text
i == j → 1
i != j → 0
```

---

# 37. Diagonal Matrix

A diagonal matrix contains non-zero values only on the main diagonal.

Example:

```text
5 0 0
0 8 0
0 0 3
```

Condition:

```text
if i != j
    matrix[i][j] must be 0
```

---

# 38. Symmetric Matrix

A matrix is symmetric when:

```text
matrix[i][j] == matrix[j][i]
```

Example:

```text
1 2 3
2 4 5
3 5 6
```

Observe:

```text
matrix[0][1] = 2
matrix[1][0] = 2

matrix[0][2] = 3
matrix[2][0] = 3

matrix[1][2] = 5
matrix[2][1] = 5
```

### Pseudocode

```text
FOR i = 0 to n - 1
    FOR j = i + 1 to n - 1

        IF matrix[i][j] != matrix[j][i]
            return false

RETURN true
```

Complexity:

```text
Time  = O(N²)
Space = O(1)
```

---

# 39. Upper Triangular Matrix

All elements below the main diagonal are zero.

Example:

```text
1 2 3
0 4 5
0 0 6
```

Condition:

```text
i > j
```

must contain `0`.

---

# 40. Lower Triangular Matrix

All elements above the main diagonal are zero.

Example:

```text
1 0 0
2 3 0
4 5 6
```

Condition:

```text
i < j
```

must contain `0`.

---

# 41. Jagged Arrays in Java

A Java `int[][]` is technically an **array of arrays**.

Therefore, rows can have different lengths.

Example:

```java
int[][] matrix = {
    {1, 2},
    {3, 4, 5},
    {6}
};
```

Visual:

```text
1 2
3 4 5
6
```

This is a **jagged array**.

Correct traversal:

```java
for (int i = 0; i < matrix.length; i++) {

    for (int j = 0; j < matrix[i].length; j++) {

        System.out.print(matrix[i][j] + " ");
    }

    System.out.println();
}
```

Avoid blindly assuming:

```java
matrix[0].length
```

when dealing with arbitrary jagged arrays.

---

# 42. Important Boundary Formulas

For a matrix:

```text
rows = R
columns = C
```

Valid indexes are:

```text
row    : 0 → R - 1
column : 0 → C - 1
```

Last row:

```text
R - 1
```

Last column:

```text
C - 1
```

Top-right:

```text
[0][C - 1]
```

Bottom-left:

```text
[R - 1][0]
```

Bottom-right:

```text
[R - 1][C - 1]
```

---

# 43. Direction Vectors

Many advanced grid problems require movement.

Four-direction movement:

```text
       (-1,0)
          ↑
          |
(0,-1) ← cell → (0,1)
          |
          ↓
       (1,0)
```

Arrays:

```java
int[] dr = {-1, 1, 0, 0};
int[] dc = {0, 0, -1, 1};
```

For each direction:

```java
for (int d = 0; d < 4; d++) {

    int newRow = row + dr[d];
    int newCol = col + dc[d];
}
```

The four directions are:

```text
up
down
left
right
```

---

# 44. Eight-Direction Movement

For problems involving diagonals as well:

```text
(-1,-1)  (-1,0)  (-1,1)

( 0,-1)  ( 0,0)  ( 0,1)

( 1,-1)  ( 1,0)  ( 1,1)
```

Java:

```java
int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
int[] dc = {-1,  0,  1,-1, 1,-1, 0, 1};
```

Used in:

* Grid DFS
* Grid BFS
* Number of islands
* Minesweeper
* Word search
* Flood fill variants

---

# 45. Boundary Validation

When moving around a matrix, validate indexes:

```java
private static boolean isValid(int row, int col, int rows, int cols) {

    return row >= 0 &&
           row < rows &&
           col >= 0 &&
           col < cols;
}
```

Or:

```java
if (newRow >= 0 &&
    newRow < matrix.length &&
    newCol >= 0 &&
    newCol < matrix[0].length) {
    
    // valid cell
}
```

This prevents:

```text
ArrayIndexOutOfBoundsException
```

---

# 46. Matrix as a Grid

The terms **matrix** and **grid** are often used interchangeably in DSA, although the problem context may differ.

Example:

```text
1 1 0 0
1 0 0 1
0 0 1 1
```

A grid problem might ask:

* Count connected components.
* Find shortest path.
* Flood fill.
* Find islands.
* Find maximum area.
* Search for a word.

These usually require:

```text
Matrix + traversal + boundaries + DFS/BFS
```

---

# 47. Flood Fill

Flood Fill is similar to the paint bucket tool in image editors.

Given:

```text
1 1 1
1 1 0
1 0 1
```

Starting at a cell, replace all connected cells of the same color.

Typical approach:

```text
DFS or BFS
```

### Pseudocode

```text
oldColor = grid[startRow][startCol]

IF oldColor == newColor
    return grid

DFS(startRow, startCol)

DFS(row, col):
    IF outside grid
        return

    IF grid[row][col] != oldColor
        return

    grid[row][col] = newColor

    DFS(row - 1, col)
    DFS(row + 1, col)
    DFS(row, col - 1)
    DFS(row, col + 1)
```

---

# 48. Number of Islands

Grid:

```text
1 1 0 0
1 0 0 1
0 0 1 1
```

Each connected group of `1`s represents an island.

General idea:

```text
FOR every cell
    IF cell is land
        count++
        DFS/BFS and mark entire island visited
```

### Pseudocode

```text
count = 0

FOR each cell

    IF cell == '1'

        count++

        DFS(cell)

DFS(row, col):

    IF out of bounds
        return

    IF cell != '1'
        return

    mark cell visited

    DFS(up)
    DFS(down)
    DFS(left)
    DFS(right)

RETURN count
```

Complexity:

```text
Time  = O(R × C)
Space = O(R × C) worst case for recursion/queue
```

---

# 49. Matrix Problem-Solving Framework

When you receive a matrix problem, first identify:

```text
1. Matrix dimensions?
2. Is it square?
3. Is it sorted?
4. Can I modify it in-place?
5. Is traversal row-wise?
6. Is traversal column-wise?
7. Are diagonals involved?
8. Is it spiral/boundary traversal?
9. Are neighboring cells involved?
10. Is DFS/BFS required?
11. Can binary search be used?
12. Is extra space allowed?
```

A useful decision process:

```text
                 Matrix Problem
                       |
          +------------+------------+
          |                         |
       Sorted?                    Unsorted?
          |                         |
     Search property?          Normal traversal
          |
      +---+---+
      |       |
    Yes       No
      |       |
  Binary /    O(R×C)
  staircase
```

---

# 50. Common Matrix Time Complexities

| Operation                      | Complexity     |
| ------------------------------ | -------------- |
| Access element                 | O(1)           |
| Traverse matrix                | O(R × C)       |
| Search unsorted matrix         | O(R × C)       |
| Find max/min                   | O(R × C)       |
| Sum                            | O(R × C)       |
| Transpose                      | O(R × C)       |
| Diagonal traversal             | O(N)           |
| Spiral traversal               | O(R × C)       |
| Rotate square matrix           | O(N²)          |
| Sorted matrix staircase search | O(R + C)       |
| Matrix multiplication          | O(R × K × C)   |
| Row sorting                    | O(R × C log C) |
| Column sorting via transpose   | O(R × C log R) |

---

# 51. Matrix Space Complexity

For an existing matrix, basic traversal usually needs:

```text
O(1)
```

extra space.

Examples:

```text
search
sum
max
min
in-place transpose
in-place rotation
```

Creating another matrix requires:

```text
O(R × C)
```

Examples:

```text
normal transpose
copy matrix
matrix multiplication result
```

DFS/BFS problems can require:

```text
O(R × C)
```

in the worst case because of:

* recursion stack
* queue
* visited array

---

# 52. Common Matrix Mistakes

## Mistake 1 — Row and column confusion

Incorrect:

```java
matrix[j][i]
```

when you actually need:

```java
matrix[i][j]
```

Always remember:

```text
[i][j]

i → row
j → column
```

---

## Mistake 2 — Wrong bounds

For rows:

```java
i < matrix.length
```

For columns:

```java
j < matrix[i].length
```

Not:

```java
i <= matrix.length
```

because the last valid index is:

```text
length - 1
```

---

## Mistake 3 — Accessing `matrix[0]` when matrix may be empty

This can fail:

```java
matrix[0].length
```

if:

```java
matrix.length == 0
```

Safer:

```java
if (matrix == null || matrix.length == 0) {
    return;
}
```

---

## Mistake 4 — Transpose dimensions

For an:

```text
R × C
```

matrix, transpose is:

```text
C × R
```

Correct:

```java
new int[columns][rows]
```

not:

```java
new int[rows][columns]
```

---

## Mistake 5 — Double-transposing during in-place transpose

Incorrect idea:

```java
for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
        swap(matrix[i][j], matrix[j][i]);
    }
}
```

You swap pairs twice.

Correct:

```java
for (int i = 0; i < n; i++) {
    for (int j = i + 1; j < n; j++) {
        // swap
    }
}
```

---

## Mistake 6 — Forgetting rectangular matrices

Do not assume:

```text
rows == columns
```

unless the problem states it.

---

## Mistake 7 — Spiral traversal duplicates

Always check:

```text
if (top <= bottom)
if (left <= right)
```

before traversing the remaining boundaries.

---

# 53. Your Matrix Program — Complete Analysis

Your current implementation:

```java
package Basics.Matrix;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        int[][] arr = {
                {77, 11, 22, 3},
                {11, 89, 1, 12},
                {32, 11, 56, 7},
                {11, 22, 44, 33}
        };

        System.out.print("Original Matrix: ");
        printMatrix(arr);

        System.out.print("\nRow wise sorted Matrix: ");
        rowWiseSort(arr);
        printMatrix(arr);

        System.out.print("\nColumn wise sorted Matrix: ");
        arr = colWiseSort(arr);
        printMatrix(arr);

        System.out.print("\nSearch result: ");

        if (searchInMatrix(arr, 50)) {
            System.out.println("FOUND");
        } else {
            System.out.println("NOT FOUND");
        }
    }

    // Print Matrix
    private static void printMatrix(int[][] arr) {

        int row = arr.length;
        int col = arr[0].length;

        System.out.println("[");

        for (int i = 0; i < row; i++) {

            System.out.print("    [");

            for (int j = 0; j < col; j++) {

                if (j > 0) {
                    System.out.print(", ");
                }

                System.out.print(arr[i][j]);
            }

            System.out.println("]");
        }

        System.out.println("]");
    }

    // Search in Matrix
    private static boolean searchInMatrix(int[][] arr, int x) {

        int row = arr.length;
        int col = arr[0].length;

        for (int i = 0; i < row; i++) {

            for (int j = 0; j < col; j++) {

                if (arr[i][j] == x) {
                    return true;
                }
            }
        }

        return false;
    }

    // Transpose
    private static int[][] transpose(int[][] matrix) {

        int row = matrix.length;
        int col = matrix[0].length;

        int[][] tr = new int[col][row];

        for (int i = 0; i < row; i++) {

            for (int j = 0; j < col; j++) {

                tr[j][i] = matrix[i][j];
            }
        }

        return tr;
    }

    // Row wise Sorting
    private static void rowWiseSort(int[][] arr) {

        for (int i = 0; i < arr.length; i++) {
            Arrays.sort(arr[i]);
        }
    }

    // Column wise Sorting
    private static int[][] colWiseSort(int[][] matrix) {

        int[][] transposed = transpose(matrix);

        rowWiseSort(transposed);

        return transpose(transposed);
    }
}
```

---

# 54. Analysis of Your `printMatrix()`

```java
private static void printMatrix(int[][] arr)
```

You determine dimensions:

```java
int row = arr.length;
int col = arr[0].length;
```

Then nested loops print each element.

Pattern:

```text
FOR each row
    FOR each column
        print matrix[i][j]
```

Complexity:

```text
Time  = O(R × C)
Space = O(1)
```

---

# 55. Analysis of Your `searchInMatrix()`

```java
private static boolean searchInMatrix(int[][] arr, int x)
```

It performs a simple linear search.

Important optimization:

```java
return true;
```

as soon as the element is found.

Otherwise:

```java
return false;
```

after checking all cells.

Complexity:

```text
Best case  = O(1)
Worst case = O(R × C)
Average    = O(R × C)
```

---

# 56. Analysis of Your `transpose()`

Important line:

```java
tr[j][i] = matrix[i][j];
```

This is the core of transpose.

For:

```text
matrix[1][2] = 6
```

the transposed matrix receives:

```text
tr[2][1] = 6
```

Complexity:

```text
Time  = O(R × C)
Space = O(R × C)
```

---

# 57. Analysis of Your `rowWiseSort()`

```java
for (int i = 0; i < arr.length; i++) {
    Arrays.sort(arr[i]);
}
```

Each individual row is sorted.

For a matrix with:

```text
R rows
C columns
```

sorting each row costs approximately:

```text
O(C log C)
```

for each row.

Total:

```text
O(R × C log C)
```

---

# 58. Analysis of Your `colWiseSort()`

Your implementation:

```java
private static int[][] colWiseSort(int[][] matrix) {

    int[][] transposed = transpose(matrix);

    rowWiseSort(transposed);

    return transpose(transposed);
}
```

Algorithm:

```text
matrix
   ↓
transpose
   ↓
sort rows
   ↓
transpose
   ↓
column-sorted matrix
```

For `R × C` input:

First transpose:

```text
O(R × C)
```

Row sorting of the transposed matrix:

```text
O(C × R log R)
```

Second transpose:

```text
O(R × C)
```

Overall:

```text
O(R × C log R)
```

Extra space:

```text
O(R × C)
```

---

# 59. Why `arr = colWiseSort(arr)` Is Correct

Your current code:

```java
arr = colWiseSort(arr);
```

is correct because `colWiseSort()` returns a new matrix.

Without assignment:

```java
colWiseSort(arr);
```

the returned matrix would be ignored.

The important concept is:

```text
A method can modify an existing array
OR
return a new array.
```

Your:

```java
rowWiseSort(arr);
```

modifies the existing matrix.

Your:

```java
arr = colWiseSort(arr);
```

replaces `arr` with a newly returned matrix.

---

# 60. LeetCode Matrix Problems

The following problems are especially useful for learning matrix/grid DSA.

---

## LeetCode 1572 — Matrix Diagonal Sum

### Problem

Given a square matrix, return the sum of the primary and secondary diagonals.

Example:

```text
1 2 3
4 5 6
7 8 9
```

Primary:

```text
1 + 5 + 9
```

Secondary:

```text
3 + 5 + 7
```

Since `5` belongs to both diagonals, count it only once.

Result:

```text
25
```

### Pseudocode

```text
sum = 0
n = matrix size

FOR i = 0 to n - 1

    sum += matrix[i][i]

    IF i != n - 1 - i
        sum += matrix[i][n - 1 - i]

RETURN sum
```

### Complexity

```text
Time  = O(N)
Space = O(1)
```

---

# 61. LeetCode 867 — Transpose Matrix

### Problem

Return the transpose of a matrix.

Input:

```text
1 2 3
4 5 6
```

Output:

```text
1 4
2 5
3 6
```

### Pseudocode

```text
rows = matrix rows
cols = matrix columns

create result[cols][rows]

FOR i
    FOR j
        result[j][i] = matrix[i][j]

RETURN result
```

### Complexity

```text
Time  = O(R × C)
Space = O(R × C)
```

---

# 62. LeetCode 48 — Rotate Image

### Problem

Rotate an `N × N` matrix 90 degrees clockwise in-place.

Input:

```text
1 2 3
4 5 6
7 8 9
```

Output:

```text
7 4 1
8 5 2
9 6 3
```

### Key idea

```text
Transpose
   +
Reverse every row
```

### Pseudocode

```text
FOR i = 0 to n - 1
    FOR j = i + 1 to n - 1
        swap(matrix[i][j], matrix[j][i])

FOR every row
    reverse row
```

### Complexity

```text
Time  = O(N²)
Space = O(1)
```

---

# 63. LeetCode 54 — Spiral Matrix

### Problem

Return all elements in spiral order.

Example:

```text
1  2  3
4  5  6
7  8  9
```

Output:

```text
1 2 3 6 9 8 7 4 5
```

### Pseudocode

```text
top = 0
bottom = rows - 1
left = 0
right = cols - 1

WHILE top <= bottom AND left <= right

    traverse top left → right
    top++

    traverse right top → bottom
    right--

    IF top <= bottom
        traverse bottom right → left
        bottom--

    IF left <= right
        traverse left bottom → top
        left++
```

### Complexity

```text
Time  = O(R × C)
Space = O(1)
```

excluding the output list.

---

# 64. LeetCode 59 — Spiral Matrix II

### Problem

Generate an `N × N` matrix filled with:

```text
1 to N²
```

in spiral order.

For:

```text
n = 3
```

output:

```text
1 2 3
8 9 4
7 6 5
```

### Pseudocode

```text
top = 0
bottom = n - 1
left = 0
right = n - 1
value = 1

WHILE top <= bottom AND left <= right

    fill top row left → right
    top++

    fill right column top → bottom
    right--

    fill bottom row right → left
    bottom--

    fill left column bottom → top
    left++

    increment value each time
```

### Complexity

```text
Time  = O(N²)
Space = O(N²)
```

because the result itself is stored.

---

# 65. LeetCode 74 — Search a 2D Matrix

The matrix has a special sorted structure.

Example:

```text
1  3  5  7
10 11 16 20
23 30 34 60
```

The entire matrix can effectively be viewed as a sorted 1D array.

Binary search can be used.

### Mapping

For flattened index:

```text
mid
```

convert to:

```text
row = mid / columns
col = mid % columns
```

### Pseudocode

```text
left = 0
right = rows × columns - 1

WHILE left <= right

    mid = left + (right - left) / 2

    row = mid / columns
    col = mid % columns

    IF matrix[row][col] == target
        return true

    ELSE IF matrix[row][col] < target
        left = mid + 1

    ELSE
        right = mid - 1

RETURN false
```

### Complexity

```text
Time  = O(log(R × C))
Space = O(1)
```

---

# 66. LeetCode 240 — Search a 2D Matrix II

Rows and columns are both sorted.

Example:

```text
1   4   7   11
2   5   8   12
3   6   9   16
10 13  14   17
```

Use the staircase search.

### Pseudocode

```text
row = 0
col = columns - 1

WHILE row < rows AND col >= 0

    IF matrix[row][col] == target
        return true

    IF matrix[row][col] > target
        col--

    ELSE
        row++

RETURN false
```

### Complexity

```text
Time  = O(R + C)
Space = O(1)
```

---

# 67. LeetCode 73 — Set Matrix Zeroes

### Problem

If an element is `0`, make its entire row and column zero.

Input:

```text
1 1 1
1 0 1
1 1 1
```

Output:

```text
1 0 1
0 0 0
1 0 1
```

### Basic approach

Use two arrays:

```text
rowZero[]
colZero[]
```

### Pseudocode

```text
FOR every cell

    IF matrix[i][j] == 0
        rowZero[i] = true
        colZero[j] = true

FOR every cell

    IF rowZero[i] OR colZero[j]
        matrix[i][j] = 0
```

Complexity:

```text
Time  = O(R × C)
Space = O(R + C)
```

The optimized solution uses the first row and first column as markers and achieves:

```text
O(1)
```

extra space.

---

# 68. LeetCode 289 — Game of Life

The grid contains cells that are alive or dead.

Each cell changes based on its neighbors.

Important concepts:

```text
8-direction neighbors
count neighboring live cells
update simultaneously
```

### Pseudocode

```text
FOR every cell
    count live neighbors

    IF alive and fewer than 2 live neighbors
        becomes dead

    ELSE IF alive and 2 or 3 live neighbors
        remains alive

    ELSE IF alive and more than 3
        becomes dead

    ELSE IF dead and exactly 3
        becomes alive
```

Important challenge:

```text
All cells update simultaneously.
```

Therefore, directly changing the values can destroy information needed for later cells.

Common techniques:

```text
temporary encoding
or
copy of matrix
```

---

# 69. LeetCode 733 — Flood Fill

### Problem

Starting from a pixel, recolor all connected pixels with the same original color.

### Pseudocode

```text
oldColor = image[sr][sc]

IF oldColor == newColor
    return image

DFS(sr, sc)

DFS(row, col):

    IF invalid
        return

    IF image[row][col] != oldColor
        return

    image[row][col] = newColor

    DFS(row - 1, col)
    DFS(row + 1, col)
    DFS(row, col - 1)
    DFS(row, col + 1)
```

Complexity:

```text
Time  = O(R × C)
Space = O(R × C)
```

worst case.

---

# 70. LeetCode 200 — Number of Islands

### Problem

Count the number of connected groups of `'1'`.

Example:

```text
1 1 0 0
1 0 0 1
0 0 1 1
```

There are:

```text
2 islands
```

### Pseudocode

```text
count = 0

FOR every cell

    IF cell == '1'

        count++

        DFS(cell)

DFS(row, col):

    IF outside bounds
        return

    IF cell != '1'
        return

    mark cell visited

    DFS(up)
    DFS(down)
    DFS(left)
    DFS(right)

RETURN count
```

Complexity:

```text
Time  = O(R × C)
Space = O(R × C)
```

---

# 71. LeetCode 695 — Max Area of Island

### Problem

Return the size of the largest connected island.

The main pattern:

```text
DFS/BFS + count cells
```

### Pseudocode

```text
maxArea = 0

FOR every cell

    IF cell == 1

        area = DFS(cell)

        maxArea = max(maxArea, area)

DFS(row, col):

    IF invalid or water
        return 0

    mark as visited

    return 1
         + DFS(up)
         + DFS(down)
         + DFS(left)
         + DFS(right)
```

Complexity:

```text
Time  = O(R × C)
Space = O(R × C)
```

---

# 72. LeetCode 994 — Rotting Oranges

This is a classic **BFS on a grid** problem.

Each rotten orange makes adjacent fresh oranges rotten every minute.

### Why BFS?

Because BFS processes cells level by level.

Each BFS level represents:

```text
1 minute
```

### Pseudocode

```text
Put all rotten oranges into queue

count fresh oranges
minutes = 0

WHILE queue is not empty

    process current level

    FOR every rotten orange in this level

        visit 4 directions

        IF neighboring orange is fresh
            make it rotten
            add to queue
            decrease fresh count

    minutes++

IF fresh > 0
    return -1

RETURN minutes
```

Complexity:

```text
Time  = O(R × C)
Space = O(R × C)
```

---

# 73. LeetCode 79 — Word Search

Given a character matrix and a word, determine whether the word can be constructed by moving:

```text
up
down
left
right
```

without reusing a cell.

Example:

```text
A B C E
S F C S
A D E E
```

Search:

```text
ABCCED
```

### Main technique

```text
DFS + backtracking
```

### Pseudocode

```text
FOR every cell

    IF DFS(cell, index = 0)
        return true

RETURN false


DFS(row, col, index):

    IF index == word.length
        return true

    IF invalid
        return false

    IF matrix[row][col] != word[index]
        return false

    temporarily mark current cell visited

    result =
        DFS(up, index + 1)
        OR DFS(down, index + 1)
        OR DFS(left, index + 1)
        OR DFS(right, index + 1)

    restore current cell

    return result
```

---

# 74. LeetCode 329 — Longest Increasing Path in a Matrix

This is an advanced matrix problem.

Typical approach:

```text
DFS + memoization
```

Each cell represents a state.

### Pseudocode

```text
DFS(row, col):

    IF already computed
        return memo[row][col]

    best = 1

    FOR each of 4 directions

        IF neighbor is greater

            best = max(
                best,
                1 + DFS(neighbor)
            )

    memo[row][col] = best

    return best
```

Complexity:

```text
Time  = O(R × C)
Space = O(R × C)
```

because each cell is solved once.

---

# 75. Recommended LeetCode Learning Order

A good progression is:

```text
1. 867  - Transpose Matrix
2. 1572 - Matrix Diagonal Sum
3. 48   - Rotate Image
4. 54   - Spiral Matrix
5. 59   - Spiral Matrix II
6. 73   - Set Matrix Zeroes
7. 74   - Search a 2D Matrix
8. 240  - Search a 2D Matrix II
9. 733  - Flood Fill
10. 200 - Number of Islands
11. 695 - Max Area of Island
12. 994 - Rotting Oranges
13. 79  - Word Search
14. 289 - Game of Life
15. 329 - Longest Increasing Path in a Matrix
```

---

# 76. Matrix Interview Questions

## Q1. What is a matrix?

A matrix is a collection of elements arranged in rows and columns.

In Java, it is commonly represented using a 2D array:

```java
int[][]
```

---

## Q2. How do you find the number of rows and columns?

For a rectangular matrix:

```java
int rows = matrix.length;
int cols = matrix[0].length;
```

---

## Q3. What is the time complexity of traversing a matrix?

For `R × C`:

```text
O(R × C)
```

because every cell is visited once.

---

## Q4. How do you access an element?

```java
matrix[i][j]
```

where:

```text
i = row
j = column
```

---

## Q5. How do you transpose a matrix?

Use:

```java
transpose[j][i] = matrix[i][j];
```

For a rectangular matrix, the new dimensions are:

```text
C × R
```

---

## Q6. Can transpose be done in-place?

Yes, but normally only for a square matrix.

Use:

```java
for (int i = 0; i < n; i++) {
    for (int j = i + 1; j < n; j++) {
        swap(matrix[i][j], matrix[j][i]);
    }
}
```

Complexity:

```text
O(N²) time
O(1) space
```

---

## Q7. Why does `j = i + 1` appear in in-place transpose?

Because the elements below the diagonal represent the same pairs in reverse order.

For example:

```text
matrix[0][1]
matrix[1][0]
```

are a pair.

You only need to swap it once.

---

## Q8. How do you rotate a matrix 90° clockwise?

For a square matrix:

```text
1. Transpose
2. Reverse every row
```

Complexity:

```text
O(N²) time
O(1) extra space
```

---

## Q9. How do you search an unsorted matrix?

Perform linear traversal.

```text
O(R × C)
```

---

## Q10. How do you search a matrix whose rows and columns are sorted?

Start from the top-right corner.

```text
current > target → left
current < target → down
```

Complexity:

```text
O(R + C)
```

---

## Q11. Why start from the top-right corner?

Because at the top-right cell:

```text
left side = smaller values
down side = larger values
```

Therefore, every comparison allows us to eliminate either:

```text
a column
```

or:

```text
a row
```

---

## Q12. What is the difference between `matrix.length` and `matrix[0].length`?

For:

```java
int[][] matrix;
```

```java
matrix.length
```

gives the number of rows.

```java
matrix[0].length
```

gives the number of columns in the first row.

---

## Q13. What is a jagged array?

A 2D Java array where rows can have different lengths.

Example:

```java
int[][] arr = {
    {1, 2},
    {3, 4, 5},
    {6}
};
```

---

## Q14. How do you check whether a matrix is symmetric?

Verify:

```text
matrix[i][j] == matrix[j][i]
```

for all relevant pairs.

---

## Q15. How do you find both diagonal sums?

For an `N × N` matrix:

```java
primary += matrix[i][i];
secondary += matrix[i][n - 1 - i];
```

---

## Q16. How do you traverse a matrix in spiral order?

Maintain:

```text
top
bottom
left
right
```

and shrink them after processing each boundary.

---

## Q17. What data structure is commonly used for matrix BFS?

A:

```text
Queue
```

typically storing:

```text
(row, column)
```

or a small object representing a cell.

---

## Q18. What data structure is commonly used for matrix DFS?

Either:

```text
recursion stack
```

or:

```text
explicit Stack
```

---

## Q19. What causes `ArrayIndexOutOfBoundsException` in a matrix?

Usually an invalid:

```text
row index
```

or:

```text
column index
```

For valid rectangular dimensions:

```text
0 <= row < rows
0 <= column < columns
```

---

## Q20. How do you prevent out-of-bounds errors?

Check:

```java
row >= 0 &&
row < rows &&
col >= 0 &&
col < cols
```

---

# 77. Interview Coding Question — Find Maximum Row Sum

### Problem

Given:

```text
1 2 3
9 1 2
4 5 6
```

return the largest row sum.

Row sums:

```text
6
12
15
```

Answer:

```text
15
```

### Solution

```java
private static int maxRowSum(int[][] matrix) {

    int max = Integer.MIN_VALUE;

    for (int i = 0; i < matrix.length; i++) {

        int sum = 0;

        for (int j = 0; j < matrix[i].length; j++) {
            sum += matrix[i][j];
        }

        max = Math.max(max, sum);
    }

    return max;
}
```

### Complexity

```text
Time  = O(R × C)
Space = O(1)
```

---

# 78. Interview Coding Question — Count Zeroes

### Solution

```java
private static int countZeroes(int[][] matrix) {

    int count = 0;

    for (int i = 0; i < matrix.length; i++) {

        for (int j = 0; j < matrix[i].length; j++) {

            if (matrix[i][j] == 0) {
                count++;
            }
        }
    }

    return count;
}
```

---

# 79. Interview Coding Question — Check Identity Matrix

### Solution

```java
private static boolean isIdentity(int[][] matrix) {

    int n = matrix.length;

    for (int i = 0; i < n; i++) {

        for (int j = 0; j < n; j++) {

            if (i == j && matrix[i][j] != 1) {
                return false;
            }

            if (i != j && matrix[i][j] != 0) {
                return false;
            }
        }
    }

    return true;
}
```

---

# 80. Interview Coding Question — Check Symmetric Matrix

```java
private static boolean isSymmetric(int[][] matrix) {

    int n = matrix.length;

    for (int i = 0; i < n; i++) {

        for (int j = i + 1; j < n; j++) {

            if (matrix[i][j] != matrix[j][i]) {
                return false;
            }
        }
    }

    return true;
}
```

Complexity:

```text
Time  = O(N²)
Space = O(1)
```

---

# 81. Interview Coding Question — Transpose In Place

For square matrix:

```java
private static void transposeInPlace(int[][] matrix) {

    int n = matrix.length;

    for (int i = 0; i < n; i++) {

        for (int j = i + 1; j < n; j++) {

            int temp = matrix[i][j];

            matrix[i][j] = matrix[j][i];

            matrix[j][i] = temp;
        }
    }
}
```

---

# 82. Interview Coding Question — Rotate Matrix 90° Clockwise

### Solution

```java
private static void rotate(int[][] matrix) {

    int n = matrix.length;

    // Transpose
    for (int i = 0; i < n; i++) {

        for (int j = i + 1; j < n; j++) {

            int temp = matrix[i][j];

            matrix[i][j] = matrix[j][i];

            matrix[j][i] = temp;
        }
    }

    // Reverse every row
    for (int i = 0; i < n; i++) {

        int left = 0;
        int right = n - 1;

        while (left < right) {

            int temp = matrix[i][left];

            matrix[i][left] = matrix[i][right];

            matrix[i][right] = temp;

            left++;
            right--;
        }
    }
}
```

---

# 83. Interview Coding Question — Search Sorted Matrix

### Solution

```java
private static boolean searchSortedMatrix(
        int[][] matrix,
        int target
) {

    int rows = matrix.length;
    int cols = matrix[0].length;

    int row = 0;
    int col = cols - 1;

    while (row < rows && col >= 0) {

        if (matrix[row][col] == target) {
            return true;
        }

        if (matrix[row][col] > target) {
            col--;
        } else {
            row++;
        }
    }

    return false;
}
```

Complexity:

```text
O(R + C)
```

---

# 84. Interview Coding Question — Spiral Traversal

```java
private static void spiralTraversal(int[][] matrix) {

    int top = 0;
    int bottom = matrix.length - 1;

    int left = 0;
    int right = matrix[0].length - 1;

    while (top <= bottom && left <= right) {

        for (int j = left; j <= right; j++) {
            System.out.print(matrix[top][j] + " ");
        }

        top++;

        for (int i = top; i <= bottom; i++) {
            System.out.print(matrix[i][right] + " ");
        }

        right--;

        if (top <= bottom) {

            for (int j = right; j >= left; j--) {
                System.out.print(matrix[bottom][j] + " ");
            }

            bottom--;
        }

        if (left <= right) {

            for (int i = bottom; i >= top; i--) {
                System.out.print(matrix[i][left] + " ");
            }

            left++;
        }
    }
}
```

---

# 85. Matrix Patterns to Memorize

## Pattern 1 — Complete traversal

```java
for (int i = 0; i < rows; i++) {
    for (int j = 0; j < cols; j++) {
        // matrix[i][j]
    }
}
```

---

## Pattern 2 — Column traversal

```java
for (int j = 0; j < cols; j++) {
    for (int i = 0; i < rows; i++) {
        // matrix[i][j]
    }
}
```

---

## Pattern 3 — Main diagonal

```java
matrix[i][i]
```

---

## Pattern 4 — Secondary diagonal

```java
matrix[i][n - 1 - i]
```

---

## Pattern 5 — Transpose

```java
transpose[j][i] = matrix[i][j]
```

---

## Pattern 6 — In-place transpose

```java
for (int i = 0; i < n; i++) {
    for (int j = i + 1; j < n; j++) {
        // swap
    }
}
```

---

## Pattern 7 — 90° clockwise rotation

```text
Transpose
+
Reverse each row
```

---

## Pattern 8 — Sorted matrix search

```text
Start at top-right.

Greater → left
Smaller → down
```

---

## Pattern 9 — Spiral

```text
top
right
bottom
left
```

with boundary shrinking.

---

## Pattern 10 — Grid DFS/BFS

```text
up
down
left
right
```

using direction arrays:

```java
int[] dr = {-1, 1, 0, 0};
int[] dc = {0, 0, -1, 1};
```

---

# 86. Matrix Cheat Sheet

```text
                         MATRIX
                            |
          +-----------------+------------------+
          |                 |                  |
       Traversal          Search            Transform
          |                 |                  |
      O(R × C)         Unsorted             Transpose
                       O(R × C)             Rotate
                                            Sort
                                                |
                           +--------------------+------------------+
                           |                    |                  |
                        Diagonal             Spiral              Grid
                           |                    |                  |
                       Main/Secondary       Boundaries          DFS/BFS
```

---

# 87. Quick Formula Reference

### Dimensions

```text
Rows    = matrix.length
Columns = matrix[0].length
```

### Element

```text
matrix[i][j]
```

### Main diagonal

```text
matrix[i][i]
```

### Secondary diagonal

```text
matrix[i][n - 1 - i]
```

### Transpose

```text
transpose[j][i] = matrix[i][j]
```

### 90° clockwise rotation

```text
Transpose
+
Reverse each row
```

### Top-right search

```text
Greater → left
Smaller → down
```

### Four directions

```java
int[] dr = {-1, 1, 0, 0};
int[] dc = {0, 0, -1, 1};
```

### Eight directions

```java
int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
int[] dc = {-1,  0,  1,-1, 1,-1, 0, 1};
```

---

# 88. Matrix Revision Checklist

Before considering matrix fundamentals complete, you should be able to implement these without looking at notes:

```text
[ ] Create a matrix
[ ] Access matrix[i][j]
[ ] Traverse every element
[ ] Print a matrix
[ ] Search an element
[ ] Find min/max
[ ] Find sum
[ ] Calculate row sums
[ ] Calculate column sums
[ ] Find main diagonal
[ ] Find secondary diagonal
[ ] Calculate diagonal sums
[ ] Transpose a matrix
[ ] Transpose in-place
[ ] Sort each row
[ ] Sort each column
[ ] Rotate 90° clockwise
[ ] Rotate 90° counterclockwise
[ ] Spiral traversal
[ ] Boundary traversal
[ ] Search sorted matrix
[ ] Matrix multiplication
[ ] Check symmetric matrix
[ ] Check identity matrix
[ ] Handle rectangular matrices
[ ] Handle jagged arrays
[ ] Use direction arrays
[ ] Perform grid DFS
[ ] Perform grid BFS
```

---

# 89. Final Mental Model

When you see a matrix problem, think:

```text
                 MATRIX
                    |
        +-----------+-----------+
        |                       |
     Structure                Movement
        |                       |
 rows / columns             up / down
 square / rectangle         left / right
 sorted / unsorted          diagonals
        |                       |
        +-----------+-----------+
                    |
                Algorithm
                    |
       +------------+-------------+
       |            |             |
    Traversal     Search       DFS/BFS
       |            |             |
   nested loops   binary      recursion
   diagonals      staircase   queue
   spiral
   transpose
   rotation
```

The most important progression is:

```text
2D Array
   ↓
Nested Loops
   ↓
Row / Column Traversal
   ↓
Diagonals
   ↓
Transpose
   ↓
Rotation
   ↓
Spiral Traversal
   ↓
Sorted Matrix Search
   ↓
Grid DFS / BFS
   ↓
Advanced Matrix Problems
```

The three patterns worth remembering first are:

```java
// 1. Visit every cell
for (int i = 0; i < matrix.length; i++) {
    for (int j = 0; j < matrix[i].length; j++) {
        // matrix[i][j]
    }
}
```

```java
// 2. Transpose
transpose[j][i] = matrix[i][j];
```

```java
// 3. Four-direction grid movement
int[] dr = {-1, 1, 0, 0};
int[] dc = {0, 0, -1, 1};
```

Once these patterns become intuitive, a large percentage of basic and intermediate matrix/grid problems reduce to choosing the correct traversal or transformation around them.
