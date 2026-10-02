package HashTable;

public class Main {
    public static void main(String[] args) {
        System.out.println("***==========*** Hash Table ***==========***");
        HashTable myHashTable = new HashTable();

        myHashTable.set("nails", 100);
        myHashTable.set("tile", 50);
        myHashTable.set("lumber", 80);
        myHashTable.set("bolts", 200);
        myHashTable.set("screws", 140);

        myHashTable.printTable();

        System.out.println(myHashTable.get("bolts"));
        System.out.println(myHashTable.get("paints"));

        System.out.println("All Keys: " + myHashTable.keys());
        System.out.println("***==========***============***==========***");
    }
}
