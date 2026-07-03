package Basics.References;

public class Main {

    public static void changePerson(Person person) {
        person.name = "Bob";
        person.age = 30;
    }

    public static void main(String[] args) {

        // Creating an object reference
        Person person1 = new Person("Alice", 25);

        // Another reference pointing to the same object
        Person person2 = person1;

        System.out.println("Before modification:");
        person1.display();

        // Modifying through another reference
        person2.name = "Charlie";

        System.out.println("\nAfter modifying using person2:");
        person1.display();

        // Passing reference to a method
        changePerson(person1);

        System.out.println("\nAfter method call:");
        person1.display();

        // Reference comparison
        Person person3 = new Person("Bob", 30);

        System.out.println("\nReference comparison:");
        System.out.println("person1 == person2: " + (person1 == person2));
        System.out.println("person1 == person3: " + (person1 == person3));
    }
}
