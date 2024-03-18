// Name: Zhiyi Jiang

// Date: 05/03/24

// Class: CS145 2958 - W24 - Computer Science II

// Assignment:Lab 5: Pascal's Triangle or Fractals

import java.util.Scanner;

public class PascalsTriangle {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Display program introduction
        System.out.println("Hi, Welcome to the Pascals Triangle program");
        System.out.println("Pascal's Triangle gives the coefficients of expanded binomials.");
        System.out.println("to an nth degree, n being a positive integer.");

        while (true) {
            // Ask user if they want to see Pascal's Triangle or quit
            System.out.println("Want to see Pascal's Triangle? (y)");
            System.out.println("Or quit? (q)");
            String choice = scanner.nextLine();

            // Check user's choice
            if (choice.equalsIgnoreCase("q")) {
                // Exit the program if user chooses to quit
                break;
            }

            if (!choice.equalsIgnoreCase("y")) {
   // If user enters an invalid choice, prompt them to enter 'y' to see 
   //Pascal's Triangle or 'q' to quit
System.out.println("Invalid input. Please enter 'y' to see Pascal's Triangle or 'q' to quit.");
                continue;
            }

            // Ask user for the degree of binomial they would like to see
            System.out.println("To what degree of a binomial would you like? (input number)");
            int degree = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            // Print Pascal's Triangle for the specified degree
            printPascalsTriangle(degree);

            // Ask user if they want to continue or quit
            System.out.println("Keep going? (y)");
            System.out.println("Or quit? (q)");
            choice = scanner.nextLine();

            // Check user's choice
            if (choice.equalsIgnoreCase("q")) {
                // Exit the program if user chooses to quit
                break;
            }
        }

        // Close the scanner
        scanner.close();
    }

    // Method to print Pascal's Triangle for a given degree
    private static void printPascalsTriangle(int degree) {
        for (int i = 0; i <= degree; i++) {
            // Add spaces for alignment
            for (int j = 0; j < degree - i; j++) {
                System.out.print(" ");
            }
            // Calculate and print binomial coefficients
            for (int j = 0; j <= i; j++) {
                System.out.print(binomialCoefficient(i, j) + " ");
            }
            System.out.println(); // Move to the next line after printing each row
        }
    }

    // Recursive method to calculate binomial coefficients
    private static int binomialCoefficient(int n, int k) {
        if (k == 0 || k == n) {
            return 1; // Base case: C(n, 0) = C(n, n) = 1
        }
        // Recursive case: C(n, k) = C(n-1, k-1) + C(n-1, k)
        return binomialCoefficient(n - 1, k - 1) + binomialCoefficient(n - 1, k);
    }
}
