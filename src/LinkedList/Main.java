package LinkedList;

public class Main {
    public static void main(String[] args) {
        System.out.println("***========== Linked List ==========***");
        System.out.println("*** Singly Linked List ***");
        SinglyLinkedList sll = new SinglyLinkedList(1);
        sll.append(2);
        sll.append(3);
        sll.getHead();
        sll.getTail();
        sll.getLength();
        sll.printList();
    }
}
