// Name: Zhiyi Jiang

// Date: 15/03/24

// Class: CS145 2958 - W24 - Computer Science II

// Assignment:Lab 6: Binary Search Tree Dictionary

import java.util.Scanner;

// Class to represent a record in the dictionary
class Record {
    String key;
    String firstName;
    String lastName;
    String streetAddress;
    String city;
    String state;
    String zip;
    String email;
    String phoneNumber;

    // Constructor to create a new record
    public Record(String key, String firstName, String lastName, String streetAddress, 
    String city, String state, String zip, String email, String phoneNumber) {
        this.key = key;
        this.firstName = firstName;
        this.lastName = lastName;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // Method to return a string representation of the record
    @Override
    public String toString() {
        return "Key: " + key + "\n" +
               "Name: " + firstName + " " + lastName + "\n" +
               "Address: " + streetAddress + ", " + city + ", " + state + " " + zip + "\n" +
               "Email: " + email + "\n" +
               "Phone: " + phoneNumber + "\n";
    }
}

// Class to represent a node in the binary search tree
class TreeNode {
    Record record;
    TreeNode left;
    TreeNode right;

    // Constructor to create a new tree node
    public TreeNode(Record record) {
        this.record = record;
        this.left = null;
        this.right = null;
    }
}

// Binary search tree class
class BinarySearchTree {
    private TreeNode root;

    // Constructor to create an empty binary search tree
    public BinarySearchTree() {
        root = null;
    }

    // Method to add a record to the binary search tree
    public void add(Record record) {
        root = addRec(root, record);
    }

    // Recursive helper method to add a record to the binary search tree
    private TreeNode addRec(TreeNode root, Record record) {
        if (root == null) {
            return new TreeNode(record);
        }
        if (record.key.compareTo(root.record.key) < 0) {
            root.left = addRec(root.left, record);
        } else if (record.key.compareTo(root.record.key) > 0) {
            root.right = addRec(root.right, record);
        }
        return root;
    }

    // Method to find a record in the binary search tree given a key
    public Record find(String key) {
        return findRec(root, key);
    }

    // Recursive helper method to find a record in the binary search tree
    private Record findRec(TreeNode root, String key) {
        if (root == null || root.record.key.equals(key)) {
            return root != null ? root.record : null;
        }
        if (key.compareTo(root.record.key) < 0) {
            return findRec(root.left, key);
        }
        return findRec(root.right, key);
    }

    // Recursive method to perform an in-order traversal of the binary search tree
    private void inOrderRec(TreeNode root) {
        if (root != null) {
            inOrderRec(root.left);
            System.out.println(root.record);
            inOrderRec(root.right);
        }
    }

    // Method to perform an in-order traversal of the binary search tree
    public void inOrder() {
        inOrderRec(root);
    }

    // Recursive method to perform a pre-order traversal of the binary search tree
    private void preOrderRec(TreeNode root) {
        if (root != null) {
            System.out.println(root.record);
            preOrderRec(root.left);
            preOrderRec(root.right);
        }
    }

    // Method to perform a pre-order traversal of the binary search tree
    public void preOrder() {
        preOrderRec(root);
    }

    // Recursive method to perform a post-order traversal of the binary search tree
    private void postOrderRec(TreeNode root) {
        if (root != null) {
            postOrderRec(root.right);
            postOrderRec(root.left);
            System.out.println(root.record);
        }
    }

    // Method to perform a post-order traversal of the binary search tree
    public void postOrder() {
        postOrderRec(root);
    }

    // Method to delete a record from the binary search tree given a key
    public boolean delete(String key) {
        TreeNode[] parent = new TreeNode[1];
        TreeNode[] current = new TreeNode[1];
        findNodeWithParent(key, parent, current);
        if (current[0] == null) {
            return false;
        }
        if (current[0].left == null) {
            transplant(parent, current, current[0].right);
        } else if (current[0].right == null) {
            transplant(parent, current, current[0].left);
        } else {
            TreeNode[] successor = new TreeNode[1];
            TreeNode[] successorParent = new TreeNode[1];
            findMinimum(current[0].right, successorParent, successor);
            if (successorParent[0] != current[0]) {
                transplant(successorParent, successor, successor[0].right);
                successor[0].right = current[0].right;
            }
            transplant(parent, current, successor[0]);
            successor[0].left = current[0].left;
        }
        return true;
    }

    // Helper method to find a node in the binary search tree with its parent
    private void findNodeWithParent(String key, TreeNode[] parent, TreeNode[] current) {
        parent[0] = null;
        current[0] = root;
        while (current[0] != null && !current[0].record.key.equals(key)) {
            parent[0] = current[0];
            if (key.compareTo(current[0].record.key) < 0) {
                current[0] = current[0].left;
            } else {
                current[0] = current[0].right;
            }
        }
    }

    // Helper method to replace a subtree in the binary search tree with another subtree
    private void transplant(TreeNode[] parent, TreeNode[] current, TreeNode newNode) {
        if (parent[0] == null) {
            root = newNode;
        } else if (parent[0].left == current[0]) {
            parent[0].left = newNode;
        } else {
            parent[0].right = newNode;
        }
    }

    // Helper method to find the node with the minimum key in a subtree
    private void findMinimum(TreeNode node, TreeNode[] parent, TreeNode[] current) {
        parent[0] = null;
        current[0] = node;
        while (current[0].left != null) {
            parent[0] = current[0];
            current[0] = current[0].left;
        }
    }

    // Method to modify a field in a record given a key
    public boolean modify(String key, String field, String value) {
        Record record = find(key);
        if (record == null) {
            return false;
        }
        switch (field) {
            case "firstName":
                record.firstName = value;
                break;
            case "lastName":
                record.lastName = value;
                break;
            case "streetAddress":
                record.streetAddress = value;
                break;
            case "city":
                record.city = value;
                break;
            case "state":
                record.state = value;
                break;
            case "zip":
                record.zip = value;
                break;
            case "email":
                record.email = value;
                break;
            case "phoneNumber":
                record.phoneNumber = value;
                break;
            default:
                return false;
        }
        return true;
    }

    // Method to check if a field is a valid field name
    public boolean isValidField(String field) {
        return field.equals("firstName") ||
               field.equals("lastName") ||
               field.equals("streetAddress") ||
               field.equals("city") ||
               field.equals("state") ||
               field.equals("zip") ||
               field.equals("email") ||
               field.equals("phoneNumber");
    }

    // Method to count the number of records in the binary search tree
    public int countRecords() {
        return countRecordsRec(root);
    }

    // Recursive helper method to count the number of records in a subtree
    private int countRecordsRec(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + countRecordsRec(root.left) + countRecordsRec(root.right);
    }
}

// Main class to interact with the dictionary
public class Dictionary {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        Scanner scanner = new Scanner(System.in);
        String choice;
        do {
            System.out.println("Menu:");
            System.out.println("1. Add a record");
            System.out.println("2. Delete a record");
            System.out.println("3. Modify a record");
            System.out.println("4. Look up records");
            System.out.println("5. List number of records");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    // Add a record
                    System.out.print("Enter key: ");
                    String key = scanner.nextLine();
                    System.out.print("Enter first name: ");
                    String firstName = scanner.nextLine();
                    System.out.print("Enter last name: ");
                    String lastName = scanner.nextLine();
                    System.out.print("Enter street address: ");
                    String streetAddress = scanner.nextLine();
                    System.out.print("Enter city: ");
                    String city = scanner.nextLine();
                    System.out.print("Enter state: ");
                    String state = scanner.nextLine();
                    System.out.print("Enter zip code: ");
                    String zip = scanner.nextLine();
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter phone number: ");
                    String phoneNumber = scanner.nextLine();
                    Record newRecord = new Record(key, firstName, lastName, streetAddress,
                     city, state, zip, email, phoneNumber);
                    bst.add(newRecord);
                    System.out.println("Record added.");
                    break;
                case "2":
                    // Delete a record
                    System.out.print("Enter key of record to delete: ");
                    String keyToDelete = scanner.nextLine();
                    if (bst.delete(keyToDelete)) {
                        System.out.println("Record deleted.");
                    } else {
                        System.out.println("Record not found.");
                    }
                    break;
                case "3":
                    // Modify a record
                    System.out.print("Enter key of record to modify: ");
                    String keyToModify = scanner.nextLine();
                    System.out.print("Enter field to modify: ");
                    String fieldToModify = scanner.nextLine();
                    if (!bst.isValidField(fieldToModify)) {
                        System.out.println("Invalid field.");
                        break;
                    }
                    System.out.print("Enter new value: ");
                    String newValue = scanner.nextLine();
                    if (bst.modify(keyToModify, fieldToModify, newValue)) {
                        System.out.println("Record modified.");
                    } else {
                        System.out.println("Record not found or field not valid.");
                    }
                    break;
                case "4":
                    // Look up records
                    System.out.println("Enter order (1. Pre-order, 2. In-order, 3. Post-order): ");
                    String order = scanner.nextLine();
                    switch (order) {
                        case "1":
                            System.out.println("Pre-order traversal:");
                            bst.preOrder();
                            break;
                        case "2":
                            System.out.println("In-order traversal:");
                            bst.inOrder();
                            break;
                        case "3":
                            System.out.println("Post-order traversal:");
                            bst.postOrder();
                            break;
                        default:
                            System.out.println("Invalid order. Please try again.");
                    }
                    break;
                case "5":
                    // List number of records
                    int count = bst.countRecords();
                    System.out.println("Number of records: " + count);
                    break;
                case "6":
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (!choice.equals("6"));
        scanner.close();
    }
}
