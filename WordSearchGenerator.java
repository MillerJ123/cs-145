// Name: Zhiyi Jiang

// Date: 06/02/24

// Class: CS145 2958 - W24 - Computer Science II

// Assignment: Assignment 1: Word Search Generator

import java.util.*;

public class WordSearchGenerator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> words = new ArrayList<>();
        char[][] wordSearch = new char[10][10];
        boolean[][] wordMask = new boolean[10][10];

        // Display welcome message and options
        System.out.println("Welcome to my word search generator!");
        System.out.println("This program will allow you to generate your own word search puzzle.");
        System.out.println("Please select an option:");
        System.out.println("Generate a new word search (G)");
        System.out.println("Print out your word search (P)");
        System.out.println("Show the solution to your word search (S)");
        System.out.println("Quit the program (Q)");

        boolean running = true;
        while (running) {
            // Prompt user for choice
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine().trim().toUpperCase(); // Convert choice to uppercase
            switch (choice) {
                case "G":
                    // Generate new word search
                    System.out.print("Enter the number of words: ");
                    int numWords = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    for (int i = 0; i < numWords; i++) {
                        System.out.print("Enter word " + (i + 1) + ": ");
                        String word = scanner.nextLine().trim().toUpperCase(); // Convert word to uppercase
                        words.add(word);
                    }
                    wordSearch = generateWordSearch(words, wordMask);
                    System.out.println("Word search generated!");
                    break;
                case "P":
                    // Print word search
                    printWordSearch(wordSearch);
                    break;
                case "S":
                    // Show solution to word search
                    printWordSearchSolution(wordSearch, wordMask);
                    break;
                case "Q":
                    // Quit program
                    running = false;
                    break;
                default:
                    // Invalid choice
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
        System.out.println("Thank you for using the word search generator!");
    }

    // Generate a word search puzzle based on user input words
    private static char[][] generateWordSearch(List<String> words, boolean[][] wordMask) {
        char[][] wordSearch = new char[10][10];
        Random random = new Random();
        for (String word : words) {
            boolean placed = false;
            int tries = 0;
            while (!placed && tries < 100) {
                int row = random.nextInt(10);
                int col = random.nextInt(10);
                int direction = random.nextInt(8); // 0-3: horizontal, 4-7: vertical/diagonal
                if (checkFit(wordSearch, word, row, col, direction)) {
                    placeWord(wordSearch, wordMask, word, row, col, direction);
                    placed = true;
                }
                tries++;
            }
            if (!placed) {
                System.out.println("Warning: Word \"" + word + "\" could not be placed within 100 tries and is omitted.");
            }
        }
        // Fill remaining empty spaces with random letters
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (wordSearch[i][j] == '\u0000') {
                    wordSearch[i][j] = (char) (random.nextInt(26) + 'A');
                }
            }
        }
        return wordSearch;
    }

    // Check if a word fits at the specified position and direction in the word search
    private static boolean checkFit(char[][] wordSearch, String word, int row, int col, int direction) {
        int len = word.length();
        if (direction < 2) { // Horizontal
            if (col + len > wordSearch[row].length) return false;
            for (int i = 0; i < len; i++) {
                if (wordSearch[row][col + i] != '\u0000' && wordSearch[row][col + i] != word.charAt(i)) return false;
            }
        } else if (direction < 4) { // Vertical
            if (row + len > wordSearch.length) return false;
            for (int i = 0; i < len; i++) {
                if (wordSearch[row + i][col] != '\u0000' && wordSearch[row + i][col] != word.charAt(i)) return false;
            }
        } else if (direction < 6) { // Diagonal (top-left to bottom-right)
            if (row + len > wordSearch.length || col + len > wordSearch[row].length) return false;
            for (int i = 0; i < len; i++) {
                if (wordSearch[row + i][col + i] != '\u0000' && wordSearch[row + i][col + i] != word.charAt(i)) return false;
            }
        } else { // Diagonal (top-right to bottom-left)
            if (row + len > wordSearch.length || col - len < 0) return false;
            for (int i = 0; i < len; i++) {
                if (wordSearch[row + i][col - i] != '\u0000' && wordSearch[row + i][col - i] != word.charAt(i)) return false;
            }
        }
        return true;
    }

    // Place a word at the specified position and direction in the word search
    private static void placeWord(char[][] wordSearch, boolean[][] wordMask, String word, int row, int col, int direction) {
        int len = word.length();
        if (direction < 2) { // Horizontal
            for (int i = 0; i < len; i++) {
                wordSearch[row][col + i] = word.charAt(i);
                wordMask[row][col + i] = true;
            }
        } else if (direction < 4) { // Vertical
            for (int i = 0; i < len; i++) {
                wordSearch[row + i][col] = word.charAt(i);
                wordMask[row + i][col] = true;
            }
        } else if (direction < 6) { // Diagonal (top-left to bottom-right)
            for (int i = 0; i < len; i++) {
                wordSearch[row + i][col + i] = word.charAt(i);
                wordMask[row + i][col + i] = true;
            }
        } else { // Diagonal (top-right to bottom-left)
            for (int i = 0; i < len; i++) {
                wordSearch[row + i][col - i] = word.charAt(i);
                wordMask[row + i][col - i] = true;
            }
        }
    }

    // Print the word search puzzle
    private static void printWordSearch(char[][] wordSearch) {
        for (char[] row : wordSearch) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    // Print the solution to the word search puzzle
    private static void printWordSearchSolution(char[][] wordSearch, boolean[][] wordMask) {
        for (int i = 0; i < wordSearch.length; i++) {
            for (int j = 0; j < wordSearch[i].length; j++) {
                if (wordMask[i][j]) {
                    System.out.print(wordSearch[i][j] + " ");
                } else {
                    System.out.print("X ");
                }
            }
            System.out.println();
        }
    }
}
