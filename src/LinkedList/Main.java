package LinkedList;

public class Main {
    public static void main(String[] args) {
        System.out.println("***========== Linked List ==========***");
//        System.out.println("*** Singly Linked List ***");
//        SinglyLinkedList sll = new SinglyLinkedList(1);
//        sll.prepend(100);
//        sll.append(2);
//        sll.append(3);
//        sll.append(10);
//        sll.append(4);
//        sll.append(2);
//        sll.append(5);
//        sll.append(0);
//        sll.append(1);
//        System.out.println(sll.removeFirst().value);
//        System.out.println(sll.removeLast().value);
//        System.out.println(sll.get(1).value);
//        sll.set(1, 50);
//        sll.insert(1, 200);
//        sll.remove(2);
//        sll.reverse();
//        sll.getHead();
//        sll.getTail();
//        sll.getLength();
//        sll.printList();
//        System.out.println(sll.findMiddleNode().value); // Middle Node
//        System.out.println(sll.hasLoop()); // Has Loop
//        System.out.println(sll.findKthFromEnd(1).value); // Kth Node From End
//        sll.removeDuplicates(); // Remove Duplicates
//        sll.printList();
//        System.out.println(sll.binaryToDecimal()); // Binary to Decimal eg. 101 ----> 5
//        sll.partitionList(5); // Partition List eg. x = 5
//        sll.reverseBetween(1, 3); // Reverse Between eg. startIndex: 1, endIndex: 3
//        sll.swapPairs(); // Swap Nodes in Pairs
//        sll.printList();

        System.out.println("*** Doubly Linked List ***");
        DoublyLinkedList dll = new DoublyLinkedList(1);

        dll.append(2);
        dll.append(3);
        dll.append(4);
        dll.append(5);
        dll.append(50);
        dll.insert(2, 30);
        dll.prepend(100);

        dll.removeFirst();
        dll.remove(2);
        dll.removeLast();

        dll.printList();
        dll.getHead();
        dll.getTail();
        dll.getLength();
    }
}
