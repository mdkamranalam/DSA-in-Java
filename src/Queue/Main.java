package Queue;

public class Main {
    public static void main(String[] args) {
        System.out.println("***========== Queue ==========***");
        Queue myQueue = new Queue(1);

        myQueue.enqueue(2);
        myQueue.enqueue(3);
        myQueue.enqueue(4);
        myQueue.enqueue(5);

        myQueue.dequeue();

        myQueue.getFirst();
        myQueue.getLast();
        myQueue.getLength();

        myQueue.printQueue();
        System.out.println("***===========================***");
    }
}
