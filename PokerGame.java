// Name: Zhiyi Jiang

// Date: 18/01/24

// Class: CS145 2958 - W24 - Computer Science II

// Assignment: Lab 2: Card Shuffling and Dealing Program
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

// Enum representing the suits of a card
enum Suit {
    HEARTS, DIAMONDS, CLUBS, SPADES
}

// Enum representing the faces of a card
enum Face {
    ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING
}

// Class representing a card
class Card {
    private final Face face;
    private final Suit suit;

    public Card(Face face, Suit suit) {
        this.face = face;
        this.suit = suit;
    }

    public Face getFace() {
        return face;
    }

    public Suit getSuit() {
        return suit;
    }

    @Override
    public String toString() {
        return face + " of " + suit;
    }
}

// Class representing a deck of cards
class DeckOfCards {
    private final List<Card> deck;
    private int currentCard;

    // Constructor to initialize the deck with 52 cards
    public DeckOfCards() {
        List<Card> tempDeck = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (Face face : Face.values()) {
                tempDeck.add(new Card(face, suit));
            }
        }
        deck = tempDeck;
        currentCard = 0;
    }

    // Method to shuffle the deck
    public void shuffle() {
        Collections.shuffle(deck);
        currentCard = 0;
    }

    // Method to deal a single card from the deck
    public Card dealCard() {
        if (currentCard < deck.size())
            return deck.get(currentCard++);
        else
            return null;
    }

    // Method to deal a hand of cards
    public Card[] dealHand() {
        Card[] hand = new Card[5];
        for (int i = 0; i < 5; i++) {
            hand[i] = dealCard();
        }
        return hand;
    }
}

// Class representing the poker game
public class PokerGame {
    public static void main(String[] args) {
        int playerWins = 0;
        int dealerWins = 0;

        // Play 20 games of poker
        for (int i = 1; i <= 20; i++) {
            System.out.println("Game " + i + ":");
            DeckOfCards deck = new DeckOfCards();
            deck.shuffle();

            Card[] playerHand = deck.dealHand();
            Card[] dealerHand = deck.dealHand();

            // Print player's hand and evaluate hand strength
            System.out.println("Player's hand:");
            Arrays.stream(playerHand).forEach(System.out::println);
            printHandResults(playerHand);

            // Print dealer's hand and evaluate hand strength
            System.out.println("\nDealer's hand:");
            Arrays.stream(dealerHand).forEach(System.out::println);
            printHandResults(dealerHand);

            // Compare hand strengths and determine winner
            int playerResult = evaluateHand(playerHand);
            int dealerResult = evaluateHand(dealerHand);

            if (playerResult > dealerResult) {
                playerWins++;
                System.out.println("\nPlayer wins!");
            } else if (dealerResult > playerResult) {
                dealerWins++;
                System.out.println("\nDealer wins!");
            } else {
                System.out.println("\nIt's a tie!");
            }
            System.out.println();
        }

        // Print the total number of wins for each player
        System.out.println("Player wins: " + playerWins);
        System.out.println("Dealer wins: " + dealerWins);
    }

    // Evaluate the hand and return the hand strength
    public static int evaluateHand(Card[] hand) {
        List<Face> faces = Arrays.stream(hand).map(Card::getFace).sorted().collect(Collectors.toList());
        if (isFlush(hand)) {
            return 5;
        } else if (isStraight(faces)) {
            return 4;
        } else if (isThreeOfAKind(faces)) {
            return 3;
        } else if (isTwoPair(faces)) {
            return 2;
        } else if (isPair(faces)) {
            return 1;
        } else {
            return 0;
        }
    }

    // Check if the hand has a flush
    private static boolean isFlush(Card[] hand) {
        Suit suit = hand[0].getSuit();
        return Arrays.stream(hand).allMatch(card -> card.getSuit() == suit);
    }

    // Check if the hand has a straight
    private static boolean isStraight(List<Face> faces) {
        for (int i = 0; i < faces.size() - 1; i++) {
            if (faces.get(i + 1).ordinal() - faces.get(i).ordinal() != 1) {
                return false;
            }
        }
        return true;
    }

    // Check if the hand has three of a kind
    private static boolean isThreeOfAKind(List<Face> faces) {
        for (int i = 0; i < faces.size() - 2; i++) {
            if (faces.get(i) == faces.get(i + 1) && faces.get(i + 1) == faces.get(i + 2)) {
                return true;
            }
        }
        return false;
    }

    // Check if the hand has two pairs
    private static boolean isTwoPair(List<Face> faces) {
        int pairs = 0;
        for (int i = 0; i < faces.size() - 1; i++) {
            if (faces.get(i) == faces.get(i + 1)) {
                pairs++;
                i++;
            }
        }
        return pairs == 2;
    }

    // Check if the hand has a pair
    private static boolean isPair(List<Face> faces) {
        for (int i = 0; i < faces.size() - 1; i++) {
            if (faces.get(i) == faces.get(i + 1)) {
                return true;
            }
        }
        return false;
    }

    // Print the results of various hand strengths
    private static void printHandResults(Card[] hand) {
        System.out.println("\nHand Results:");
        System.out.println("Flush: " + isFlush(hand));
        System.out.println("Straight: " + isStraight(Arrays.stream(hand).map(Card::getFace).sorted().collect(Collectors.toList())));
        System.out.println("Three of a Kind: " + isThreeOfAKind(Arrays.stream(hand).map(Card::getFace).sorted().collect(Collectors.toList())));
        System.out.println("Two Pair: " + isTwoPair(Arrays.stream(hand).map(Card::getFace).sorted().collect(Collectors.toList())));
        System.out.println("Pair: " + isPair(Arrays.stream(hand).map(Card::getFace).sorted().collect(Collectors.toList())));
    }
}
