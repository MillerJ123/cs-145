// Name: Zhiyi Jiang

// Date: 14/02/24

// Class: CS145 2958 - W24 - Computer Science II

// Assignment: Assignment 2: Phone Book

import java.util.Scanner;

// ListNode class to represent each entry in the phone book
class ListNode {
    String firstName;
    String lastName;
    String address;
    String city;
    String phoneNumber;
    ListNode next;

    // Constructor to initialize a ListNode with given values
    public ListNode(String firstName, String lastName, String address, 
                    String city, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.next = null;
    }
}

// PhonebookManager class to manage the phone book entries
class PhonebookManager {
    private ListNode head; // Head of the linked list
    private int size; // Size of the phone book

    // Constructor to initialize PhonebookManager with an empty list
    public PhonebookManager() {
        this.head = null;
        this.size = 0;
    }

    // Method to add a new entry at the end of the phone book
public void addLast(String firstName, String lastName, String address, 
                     String city, String phoneNumber) {
    ListNode newNode = new ListNode(firstName, lastName, address, city, phoneNumber);
    if (head == null) {
        head = newNode;
    } else {
        ListNode current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
    }
    size++; // Increment the size
}
    // Method to delete an entry at a specific index in the phone book
   public void deleteAtIndex(int index) {
       if (index < 1 || head == null) {
          System.out.println("Invalid index");
          return;
      }
    if (index == 1) {
        head = head.next;
        return;
    }
    ListNode current = head;
    ListNode previous = null;
    int currentIndex = 1;
    while (current != null && currentIndex < index) {
        previous = current;
        current = current.next;
        currentIndex++;
    }
    if (current == null) {
        System.out.println("Invalid index");
        return;
    }
    previous.next = current.next;
}


    // Method to print all entries in the phone book
    public void printPhonebook() {
    ListNode current = head;
    int index = 1; // Start index from 1
    while (current != null) {
        System.out.println("Index: " + index);
        System.out.println("Name: " + current.firstName + " " + current.lastName);
        System.out.println("Address: " + current.address + ", " + current.city);
        System.out.println("Phone Number: " + current.phoneNumber);
        System.out.println("--------------------");
        current = current.next;
        index++;
    }
}

    // Method to search for entries by name in the phone book
    public void nameSearch(String name) {
        ListNode current = head;
        while (current != null) {
            if (current.firstName.equalsIgnoreCase(name) || current.lastName.equalsIgnoreCase(name) ||
                    (current.firstName + " " + current.lastName).equalsIgnoreCase(name)) {
                System.out.println("Name: " + current.firstName + " " + current.lastName);
                System.out.println("Address: " + current.address + ", " + current.city);
                System.out.println("Phone Number: " + current.phoneNumber);
                System.out.println("--------------------");
            }
            current = current.next;
        }
    }

    // Method to search for entries by address in the phone book
    public void addressSearch(String address) {
        ListNode current = head;
        while (current != null) {
            if (current.address.equalsIgnoreCase(address) || current.city.equalsIgnoreCase(address) ||
                    (current.address + ", " + current.city).equalsIgnoreCase(address)) {
                System.out.println("Name: " + current.firstName + " " + current.lastName);
                System.out.println("Address: " + current.address + ", " + current.city);
                System.out.println("Phone Number: " + current.phoneNumber);
                System.out.println("--------------------");
            }
            current = current.next;
        }
    }

    // Method to search for entries by phone number in the phone book
    public void phoneNumberSearch(String phoneNumber) {
        ListNode current = head;
        int index = 0;
        while (current != null) {
            if (current.phoneNumber.equals(phoneNumber)) {
                System.out.println("Index: " + index);
                System.out.println("Name: " + current.firstName + " " + current.lastName);
                System.out.println("Address: " + current.address + ", " + current.city);
                System.out.println("Phone Number: " + current.phoneNumber);
                System.out.println("--------------------");
            }
            current = current.next;
            index++;
        }
    }

    // Method to edit the name of an entry at a specific index in the phone book
    public void editName(int index, String currentFirstName, String currentLastName,
                         String newFirstName, String newLastName) {
        ListNode current = head;
        for (int i = 0; i < index; i++) {
            if (current == null) {
                System.out.println("Invalid index");
                return;
            }
            current = current.next;
        }
        if (current != null && current.firstName.equalsIgnoreCase(currentFirstName) &&
                current.lastName.equalsIgnoreCase(currentLastName)) {
            current.firstName = newFirstName;
            current.lastName = newLastName;
        } else {
            System.out.println("Name not found at index");
        }
    }

    // Method to edit the address of an entry at a specific index in the phone book
    public void editAddress(int index, String currentAddress, String currentCity,
                            String newAddress, String newCity) {
        ListNode current = head;
        for (int i = 0; i < index; i++) {
            if (current == null) {
                System.out.println("Invalid index");
                return;
            }
            current = current.next;
        }
        if (current != null && current.address.equalsIgnoreCase(currentAddress) &&
                current.city.equalsIgnoreCase(currentCity)) {
            current.address = newAddress;
            current.city = newCity;
        } else {
            System.out.println("Address not found at index");
        }
    }

    // Method to edit the phone number of an entry at a specific index in the phone book
    public void editPhoneNumber(int index, String currentPhoneNumber, String newPhoneNumber) {
        ListNode current = head;
        for (int i = 0; i < index; i++) {
            if (current == null) {
                System.out.println("Invalid index");
                return;
            }
            current = current.next;
        }
        if (current != null && current.phoneNumber.equals(currentPhoneNumber)) {
            current.phoneNumber = newPhoneNumber;
        } else {
            System.out.println("Phone number not found at index");
        }
    }

    // Method to print the menu options for the phone book
    public void printMenu() {
        System.out.println("1 - Add a new contact");
        System.out.println("2 - Delete a contact");
        System.out.println("3 - View all contacts");
        System.out.println("4 - Search contacts by name");
        System.out.println("5 - Search contacts by address");
        System.out.println("6 - Search contacts by phone number");
        System.out.println("7 - Edit contact's name");
        System.out.println("8 - Edit contact's address");
        System.out.println("9 - Edit contact's phone number");
        System.out.println("0 - Quit");
    }

    // Method to run the phone book management system
    public void run() {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;
        while (choice != 0) {
            printMenu();
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character
            switch (choice) {
                case 1:
                    System.out.print("Enter first name: ");
                    String firstName = scanner.nextLine();
                    System.out.print("Enter last name: ");
                    String lastName = scanner.nextLine();
                    System.out.print("Enter address: ");
                    String address = scanner.nextLine();
                    System.out.print("Enter city: ");
                    String city = scanner.nextLine();
                    System.out.print("Enter phone number: ");
                    String phoneNumber = scanner.nextLine();
                    addLast(firstName, lastName, address, city, phoneNumber);
                    break;
                case 2:
                    System.out.print("Enter index to delete: ");
                    int index = scanner.nextInt();
                    deleteAtIndex(index);
                    break;
                case 3:
                    printPhonebook();
                    break;
                case 4:
                    System.out.print("Enter name to search: ");
                    String name = scanner.nextLine();
                    nameSearch(name);
                    break;
                case 5:
                    System.out.print("Enter address to search: ");
                    String searchAddress = scanner.nextLine();
                    addressSearch(searchAddress);
                    break;
                case 6:
                    System.out.print("Enter phone number to search: ");
                    String searchPhoneNumber = scanner.nextLine();
                    phoneNumberSearch(searchPhoneNumber);
                    break;
                case 7:
                    System.out.print("Enter index to edit name: ");
                    int editIndex = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character
                    System.out.print("Enter current first name: ");
                    String currentFirstName = scanner.nextLine();
                    System.out.print("Enter current last name: ");
                    String currentLastName = scanner.nextLine();
                    System.out.print("Enter new first name: ");
                    String newFirstName = scanner.nextLine();
                    System.out.print("Enter new last name: ");
                    String newLastName = scanner.nextLine();
                    editName(editIndex, currentFirstName, currentLastName, newFirstName, newLastName);
                    break;
                case 8:
                    System.out.print("Enter index to edit address: ");
                    int editAddressIndex = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character
                    System.out.print("Enter current address: ");
                    String currentAddress = scanner.nextLine();
                    System.out.print("Enter current city: ");
                    String currentCity = scanner.nextLine();
                    System.out.print("Enter new address: ");
                    String newAddress = scanner.nextLine();
                    System.out.print("Enter new city: ");
                    String newCity = scanner.nextLine();
                    editAddress(editAddressIndex, currentAddress, currentCity, newAddress, newCity);
                    break;
                case 9:
                    System.out.print("Enter index to edit phone number: ");
                    int editPhoneNumberIndex = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character
                    System.out.print("Enter current phone number: ");
                    String currentPhoneNumber = scanner.nextLine();
                    System.out.print("Enter new phone number: ");
                    String newPhoneNumber = scanner.nextLine();
                    editPhoneNumber(editPhoneNumberIndex, currentPhoneNumber, newPhoneNumber);
                    break;
                case 0:
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
        scanner.close();
    }

    // Main method to create an instance of PhonebookManager and run the program
    public static void main(String[] args) {
        PhonebookManager manager = new PhonebookManager();
        manager.run();
    }
}
