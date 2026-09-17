package Tree.BinarySearchTree;

public class Main {
    public static void main(String[] args) {
        System.out.println("***========== Binary Search Tree (BST) ==========***");
        BinarySearchTree myBST = new BinarySearchTree();

        myBST.insert(47); // root
        myBST.insert(21); // root -> left
        myBST.insert(76); //root -> right
        myBST.insert(18); // root -> left -> left
        myBST.insert(52); // root -> right -> left
        myBST.insert(82); // root -> right -> right

        myBST.insert(27); // root -> left -> right

        System.out.println("Root = " + myBST.root.value);
        System.out.println("Value = " + myBST.root.left.right.value);
        System.out.println("***==============================================***");
    }
}
