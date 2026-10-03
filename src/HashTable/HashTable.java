package HashTable;

import java.util.ArrayList;

public class HashTable {

    // Number of buckets
    private final int size = 7;

    // Array of buckets
    private final Node[] dataMap;

    // Stores one key-value pair
    class Node {
        String key;
        int value;
        Node next;

        Node(String key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    // Constructor
    public HashTable() {
        dataMap = new Node[size];
    }

    // ============================================================
    // PRINT TABLE
    // ============================================================

    public void printTable() {
        for (int i = 0; i < dataMap.length; i++) {

            System.out.println(i + ": ");

            Node temp = dataMap[i];

            while (temp != null) {
                System.out.println(
                        "    { " + temp.key + " = " + temp.value + " }"
                );

                temp = temp.next;
            }
        }
    }

    // ============================================================
    // HASH FUNCTION
    // ============================================================

    private int hash(String key) {

        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }

        int hash = 0;

        for (char c : key.toCharArray()) {
            hash = (hash * 31 + c) % dataMap.length;
        }

        return hash;
    }

    // ============================================================
    // SET / INSERT
    // ============================================================

    public void set(String key, int value) {

        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }

        int index = hash(key);

        Node newNode = new Node(key, value);

        // Bucket is empty
        if (dataMap[index] == null) {
            dataMap[index] = newNode;
            return;
        }

        Node temp = dataMap[index];

        while (true) {

            // Key already exists → update value
            if (temp.key.equals(key)) {
                temp.value = value;
                return;
            }

            // Reached end of linked list
            if (temp.next == null) {
                break;
            }

            temp = temp.next;
        }

        // Add new node to the end
        temp.next = newNode;
    }

    // ============================================================
    // GET
    // ============================================================

    public Integer get(String key) {

        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }

        int index = hash(key);

        Node temp = dataMap[index];

        while (temp != null) {

            if (temp.key.equals(key)) {
                return temp.value;
            }

            temp = temp.next;
        }

        // Key does not exist
        return null;
    }

    // ============================================================
    // CONTAINS KEY
    // ============================================================

    public boolean containsKey(String key) {

        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }

        int index = hash(key);

        Node temp = dataMap[index];

        while (temp != null) {

            if (temp.key.equals(key)) {
                return true;
            }

            temp = temp.next;
        }

        return false;
    }

    // ============================================================
    // REMOVE
    // ============================================================

    public void remove(String key) {

        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }

        int index = hash(key);

        Node current = dataMap[index];
        Node previous = null;

        while (current != null) {

            if (current.key.equals(key)) {

                // Removing first node
                if (previous == null) {
                    dataMap[index] = current.next;
                }

                // Removing middle/end node
                else {
                    previous.next = current.next;
                }

                return;
            }

            previous = current;
            current = current.next;
        }
    }

    // ============================================================
    // GET ALL KEYS
    // ============================================================

    public ArrayList<String> keys() {

        ArrayList<String> allKeys = new ArrayList<>();

        for (int i = 0; i < dataMap.length; i++) {

            Node temp = dataMap[i];

            while (temp != null) {

                allKeys.add(temp.key);

                temp = temp.next;
            }
        }

        return allKeys;
    }

    // ============================================================
    // SIZE
    // ============================================================

    public int size() {

        int count = 0;

        for (Node bucket : dataMap) {

            Node temp = bucket;

            while (temp != null) {
                count++;
                temp = temp.next;
            }
        }

        return count;
    }
}