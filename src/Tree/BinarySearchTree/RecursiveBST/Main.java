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
        System.out.println("***========================================================***");
    }
}

