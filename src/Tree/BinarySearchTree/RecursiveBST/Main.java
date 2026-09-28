package Tree.BinarySearchTree.RecursiveBST;

class RecursiveBST {
    Node root;

    // Insert
    private Node rInsert(Node root, int value) {
        if (root == null) return new Node(value);

        if (value < root.value) {
            root.left = rInsert(root.left, value);
        } else if (value > root.value) {
            root.right = rInsert(root.right, value);
        }
        return root;
    }

    public void rInsert(int value) {
        if (root == null) root = new Node(value);
        rInsert(root, value);
    }

    // Contains
    private boolean rContains(Node root, int value) {
        if (root == null) return false;
        if (root.value == value) return true;

        if (value < root.value) {
            return rContains(root.left, value);
        } else {
            return rContains(root.right, value);
        }
    }

    public boolean rContains(int value) {
        return rContains(root, value);
    }

    // Delete
    private Node deleteNode(Node root, int value) {
        if (root == null) return null;

        if (value < root.value) {
            root.left = deleteNode(root.left, value);
        } else if (value > root.value) {
            root.right = deleteNode(root.right, value);
        } else {
            if (root.left == null && root.right == null) {
                return null;
            } else if (root.left == null) {
                root = root.right;
            } else if (root.right == null) {
                root = root.left;
            } else {
                int subTreeMin = minValue(root.right);
                root.value = subTreeMin;
                root.right = deleteNode(root.right, subTreeMin);
            }
        }
        return root;
    }

    public void deleteNode(int value) {
        deleteNode(root, value);
    }

    // Minimum value
    public int minValue(Node root) {
        while (root.left != null) {
            root = root.left;
        }
        return root.value;
    }

    class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }
    }

    // In-order traversal
    private void inOrder(Node root) {
        if (root == null) return;

        inOrder(root.left);
        System.out.print(root.value + " ");
        inOrder(root.right);
    }

    public void inOrder() {
        inOrder(root);
    }

    /* ========== Exercises ========== */
    /*
    *   Exercise - 1: Convert Sorted Array to Balanced BST
    * */
    private Node sortedArrayToBST(int[] nums, int left, int right) {
        if (left > right) return null;
        int mid = left + (right - left) / 2;
        Node node = new Node(nums[mid]);
        node.left = sortedArrayToBST(nums, left, mid - 1);
        node.right = sortedArrayToBST(nums, mid + 1, right);
        return node;
    }

    public void sortedArrayToBST(int[] nums) {
        this.root = sortedArrayToBST(nums, 0, nums.length - 1);
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("***========== Binary Search Tree (BST) Recursive ==========***");
        RecursiveBST myBST = new RecursiveBST();
        myBST.rInsert(47); // root
        myBST.rInsert(21); // root -> left
        myBST.rInsert(76); //root -> right
        myBST.rInsert(18); // root -> left -> left
        myBST.rInsert(52); // root -> right -> left
        myBST.rInsert(82); // root -> right -> right

        myBST.rInsert(27); // root -> left -> right

        System.out.println("Root: " + myBST.root.value);
        System.out.println("Root->Left: " + myBST.root.left.value);

        System.out.println("Root->Right: " + myBST.root.right.value);

        System.out.println("Minimum Value: " + myBST.minValue(myBST.root));
        System.out.println("Minimum Value at Right of the Root: " + myBST.minValue(myBST.root.right));

        myBST.deleteNode(27);

        System.out.println(myBST.rContains(27));
        System.out.println(myBST.rContains(17));

        System.out.println("\nExercise - 1: Convert Sorted Array to Balanced BST:");
        int[] arr = {1, 2, 3, 4, 5};

        myBST.sortedArrayToBST(arr);

        System.out.println("Root: " + myBST.root.value);
        System.out.println("Root->Left: " + myBST.root.left.value);
        System.out.println("Root->Right: " + myBST.root.right.value);

        System.out.print("In-order Traversal: ");
        myBST.inOrder();

        System.out.println();
        System.out.println("***========================================================***");
    }
}

