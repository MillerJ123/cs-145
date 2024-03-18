// Name: Zhiyi Jiang

// Date: 24/01/24

// Class: CS145 2958 - W24 - Computer Science II

// Assignment: Lab 3: Letter Inventory
import java.util.Scanner;

public class LetterInventory {
    private static final int ALPHABET_SIZE = 26;
    private int[] inventory;

    // Constructor to initialize the inventory with counts of letters in the given data
    public LetterInventory(String data) {
        inventory = new int[ALPHABET_SIZE];
        data = data.toLowerCase(); // Convert to lowercase
        for (char c : data.toCharArray()) {
            if (Character.isLetter(c)) {
                inventory[c - 'a']++; // Increment the count for the corresponding letter
            }
        }
    }

    // Get the count of a specific letter in the inventory
    public int get(char letter) {
        letter = Character.toLowerCase(letter); // Convert to lowercase
        if (letter < 'a' || letter > 'z') {
            throw new IllegalArgumentException("Not a letter: " + letter);
        }
        return inventory[letter - 'a'];
    }

    // Set the count of a specific letter in the inventory
    public void set(char letter, int value) {
        letter = Character.toLowerCase(letter); // Convert to lowercase
        if (letter < 'a' || letter > 'z') {
            throw new IllegalArgumentException("Not a letter: " + letter);
        }
        inventory[letter - 'a'] = value;
    }

    // Get the total size of the inventory (sum of all counts)
    public int size() {
        int sum = 0;
        for (int count : inventory) {
            sum += count;
        }
        return sum;
    }

    // Check if the inventory is empty (all counts are zero)
    public boolean isEmpty() {
        for (int count : inventory) {
            if (count > 0) {
                return false; // Inventory is not empty
            }
        }
        return true; // Inventory is empty
    }

    // Convert the inventory to a string representation
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ALPHABET_SIZE; i++) {
            for (int j = 0; j < inventory[i]; j++) {
                sb.append((char) ('a' + i)); // Append the letter to the string based on its count
            }
        }
        return "[" + sb.toString() + "]"; // Return the string representation of the inventory
    }

    // Add another inventory to this inventory and return the result
    public LetterInventory add(LetterInventory other) {
        LetterInventory result = new LetterInventory("");
        for (int i = 0; i < ALPHABET_SIZE; i++) {
            result.inventory[i] = this.inventory[i] + other.inventory[i]; // Add counts for each letter
        }
        return result;
    }

    // Subtract another inventory from this inventory and return the result
    public LetterInventory subtract(LetterInventory other) {
        LetterInventory result = new LetterInventory("");
        for (int i = 0; i < ALPHABET_SIZE; i++) {
            result.inventory[i] = this.inventory[i] - other.inventory[i]; // Subtract counts for each letter
            if (result.inventory[i] < 0) {
                return null; // Invalid subtraction (negative count)
            }
        }
        return result; // Return the result of subtraction
    }

    // Main method to test the LetterInventory class
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt user to input strings
        System.out.print("Enter the first string: ");
        String input1 = scanner.nextLine();
        System.out.print("Enter the second string: ");
        String input2 = scanner.nextLine();

        // Create LetterInventory objects from user input
        LetterInventory inventory1 = new LetterInventory(input1);
        LetterInventory inventory2 = new LetterInventory(input2);

        // Print the original inventories
        System.out.println("Inventory 1: " + inventory1);
        System.out.println("Inventory 2: " + inventory2);

        // Perform addition
        LetterInventory sum = inventory1.add(inventory2);
        System.out.println("Sum: " + sum);

        // Perform subtraction
        LetterInventory diff = inventory1.subtract(inventory2);
        if (diff != null) {
            System.out.println("Difference: " + diff);
        } else {
            System.out.println("Invalid subtraction: Result contains negative counts.");
        }

        scanner.close();
    }
}
