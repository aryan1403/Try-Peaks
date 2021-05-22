package console;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HelperFunctions {
    Scanner sc = new Scanner(System.in);
    Scanner sc1 = new Scanner(System.in);
    Scanner sc2 = new Scanner(System.in);

    String[] arr = { "K", "Q", "J", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };
    List<String> list = new ArrayList<>();
    List<String> deck = new ArrayList<>();

    int totalCards = 50;
    int totalDeck = 15;

    String playerName;
    int level;

    boolean isAlive = true;
    boolean isFound = false;

    int points = 0;

    // This will open n Tiles to knock down
    public void createNewTile() {
        int n = nElement();
        int total = n;
        for (int i = 0; i < total; i++) {
            int r = (int) ((Math.random() * 11) + 1);
            if (checkDuplicate(arr[r]) == true) {
            } else {
                list.add(arr[r]); // Add the new Element to the list
                total--;
            }
        }
        int i = 0;
        while (i > total) {
            int r = (int) ((Math.random() * 11) + 1);
            if (checkDuplicate(arr[r]) == true) {
            } else {
                list.add(arr[r]); // Add the new Element to the list
                total--;
            }
        }
    }

    public boolean checkDuplicate(String s) {
        boolean is = false;
        for (String e : list) {
            if (s.equals(e)) {
                is = true;
            }
        }
        if (is == true)
            return true;
        return false;
    }

    // This methosd will show the current available Cards
    public void showTile() {
        System.out.println("Current Cards: ");
        list.forEach(e -> {
            System.out.print(e + "     ");
        });
        System.out.println();
    }

    // This will generate a random value (How many cards to open)
    public int nElement() {
        // min -> 1 Card
        // max -> 3 Cards
        int random = (int) ((Math.random() * 2) + 1); // +1 to handle 0
        if (random > totalCards) { // Check whether there is enough card to open
            nElement();
        }
        totalCards -= random;
        return random;
    }

    // This will delete the knock down Tile
    public void DeleteElement(int index) {
        list.remove(index);
    }

    // This will generate a new Card in your Deck
    public void generateDeck() {
        int r = (int) ((Math.random() * 11) + 1);
        deck.add(arr[r]);
        totalDeck--;
    }

    // This will generate a deck
    public String getCurrentDeck() {
        return deck.get(deck.size() - 1);
    }

    // This will show the Deck
    public void showDeck() {
        System.out.println("Current Deck : " + getCurrentDeck());
    }

    public void showPoints() {
        System.out.println("Points: " + points);
    }

    // This will knockDown Decks
    public void knockDown() {
        System.out.print("Enter the Card from the deck : ");
        String turn = sc2.nextLine();
        if (turn.equals(getCurrentDeck())) {
            for (String e : list) {
                if (e.equals(turn)) {
                    DeleteElement(list.indexOf(turn));
                    points += 50;
                    showPoints();
                    createNewTile();
                    showTile();
                    generateDeck();
                    showDeck();
                    isFound = true;
                    break;
                }
            }

            if (!isFound) {
                System.out.println("Current Cards doesn't contain " + turn);
                showPoints();
                showTile();
                showDeck();
            }
        } else {
            System.out.println("Your Card is not available!");
            generateDeck();
            showPoints();
            showTile();
            showDeck();
        }
    }

    public void checkState() {
        if (list.size() == 0 || deck.size() == 0 || totalCards == 0 || totalDeck == 0) {
            EndGame();
        }
    }

    public void EndGame() {
        if (isAlive)
            System.out.println("You Won!");
        else
            System.out.println("You Lose!");
        System.out.println("Well Played !");
        System.exit(0);
    }

    public void startGame() {
        for (int i = 0; i < 10; i++) {
            int r = (int) ((Math.random() * 11) + 1);
            list.add(arr[r]);
        }
        totalCards -= 10;

        System.out.println("Welcome To The Game!");

        System.out.print("Enter your Name : ");
        playerName = sc.nextLine();

        System.out.println("Choose The Difficulty From below");
        System.out.println("1. Easy");
        System.out.println("2. Medium");
        System.out.println("3. Hard");
        level = sc.nextInt();

        if (level == 1)
            totalCards = 50;
        else if (level == 2)
            totalCards = 70;
        else if (level == 3)
            totalCards = 100;
        else
            System.out.println("Wrong Choice!!");
        showTile();
        generateDeck();
        showDeck();
        while (true) {
            checkState();
            knockDown();
        }
    }
}
