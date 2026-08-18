package LinkedList;

public class Main {
    public static void main(String[] args) {
        System.out.println("***========== Linked List ==========***");
        System.out.println("*** Singly Linked List ***");
        SinglyLinkedList sll = new SinglyLinkedList(1);
//        sll.prepend(100);
        sll.append(2);
        sll.append(3);
        sll.append(4);
        sll.append(5);
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
        sll.printList();
//        System.out.println(sll.findMiddleNode().value); // Middle Node
        System.out.println(sll.hasLoop());
    }
}
