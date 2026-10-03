package HashTable;

public class Main {

    public static void main(String[] args) {

        System.out.println("***========== Hash Table ==========***");

        HashTable myHashTable = new HashTable();

        // Insert
        myHashTable.set("nails", 100);
        myHashTable.set("tile", 50);
        myHashTable.set("lumber", 80);
        myHashTable.set("bolts", 200);
        myHashTable.set("screws", 140);

        // Print
        myHashTable.printTable();

        // Get
        System.out.println("\nGet bolts: "
                + myHashTable.get("bolts"));

        System.out.println("Get paints: "
                + myHashTable.get("paints"));

        // Contains
        System.out.println("\nContains bolts: "
                + myHashTable.containsKey("bolts"));

        System.out.println("Contains paints: "
                + myHashTable.containsKey("paints"));

        // Update existing key
        myHashTable.set("bolts", 500);

        System.out.println("\nUpdated bolts: "
                + myHashTable.get("bolts"));

        // Keys
        System.out.println("\nAll Keys: "
                + myHashTable.keys());

        // Size
        System.out.println("Size: "
                + myHashTable.size());

        // Remove
        myHashTable.remove("bolts");

        System.out.println("\nAfter removing bolts:");

        myHashTable.printTable();

        System.out.println("\nContains bolts: "
                + myHashTable.containsKey("bolts"));

        System.out.println("\n***========== Hash Table ==========***");
    }
}