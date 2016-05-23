package dpate85Proj1;

import java.util.Scanner;

public class Game {

    private static final int DECK_SIZE = 52;
    private static final int NUM_PILES = 8;
    private static final int PILE_SIZE = 13;
    private static final int DEAL_SIZE = 7;
    private static final int DRAW_CARD = 2;
    CardPile[] piles;
    Card[] deck;
    int d_size;

    Player human;
    Player comp;

    Game() {

      resetAll();
    }

    public void resetAll()
    {
        human = new Player();
        comp = new Player();

        reset();
    }
    public void reset() {
        deck = new Card[DECK_SIZE];
        d_size = 0;

        piles = new CardPile[NUM_PILES];

        for (int i = 0; i < NUM_PILES / 2; i++) {
            piles[i] = new CardPile(true);
        }
        for (int i = NUM_PILES / 2; i < NUM_PILES; i++) {
            piles[i] = new CardPile(false);
        }

        int i = 0;

        for (int j = 1; j <= PILE_SIZE; j++) {
            Card c = new Card();
            c.setCard(j, 'C', true);
            deck[i] = c;
            i++;
        }
        for (int j = 1; j <= PILE_SIZE; j++) {
            Card c = new Card();
            c.setCard(j, 'D', false);
            deck[i] = c;
            i++;
        }
        for (int j = 1; j <= PILE_SIZE; j++) {
            Card c = new Card();
            c.setCard(j, 'H', false);
            deck[i] = c;
            i++;
        }
        for (int j = 1; j <= PILE_SIZE; j++) {
            Card c = new Card();
            c.setCard(j, 'S', true);
            deck[i] = c;
            i++;
        }
        d_size = i;
    }

    public void shuffleDeck() {
        for (int i = 0; i < DECK_SIZE; i++) {
            int randomPos1 = (int) (Math.random() * DECK_SIZE);
            int randomPos2 = (int) (Math.random() * DECK_SIZE);

            if (randomPos1 != randomPos2) {
                Card temp = deck[randomPos1];
                deck[randomPos1] = deck[randomPos2];
                deck[randomPos2] = temp;
            }
        }
    }

    Card dealCard() {
        Card c = new Card();
        if (d_size > 0) {
            c = deck[0];
            for (int i = 0; i < d_size - 1; i++) {
                deck[i] = deck[i + 1];
            }
            d_size--;
        }

        return c;
    }

    public void setup() {
        Card c = new Card();

        shuffleDeck();

        for (int i = 0; i < DEAL_SIZE; i++) {
            c = dealCard();
            human.addToHand(c);
            c = dealCard();
            comp.addToHand(c);
        }

        for (int i = 0; i < 4; i++) {
            c = dealCard();
            piles[i].addCard(c);
        }
    }

    public void print() {
        for (int i = 0; i < NUM_PILES; i++) {
            System.out.print("CardPile " + (i + 1) + ": ");
            piles[i].print();
            System.out.println();
        }
        System.out.print("Computer Player has " + comp.numCards() + " cards");
        System.out.println();
        System.out.print("Your hand: ");
        human.printHand();
        System.out.println();
        System.out.print("Move> ");
    }

    public int command() {
        Scanner scan = new Scanner(System.in);
        String input = scan.next();
        if (input.equals("Q") || input.equals("q")) {
            System.out.println("Exiting program, Goodbye!");
            return 0;
        } else if (input.equals("H") || input.equals("h")) {
            System.out.println("Commands: \nQ (quit program)\nH (help instructions)"
                    + "\nA (about) \nD (draw a card from the draw pile)\n"
                    + "L <Card> <CardPile> (Lay a card on a pile)\n"
                    + "M <CardPile1> <CardPile2> (Move One CardPile on top of Another CardPile)");
        } else if (input.equals("A") || input.equals("a")) {
            System.out.println("\n\nName: Dhrumil Patel - netID : dpate85 \nProgram Info: https://www.cs.uic.edu/pub/CS342/AssignmentsS16/proj1.pdf\n\n");
        } else if (input.equals("D") || input.equals("d")) {
            Card c = dealCard();
            human.addToHand(c);
            return DRAW_CARD;
        } else if (input.equals("L") || input.equals("l")) {
            input = scan.next();
            input = input.toUpperCase();

            int pile = scan.nextInt() - 1;

            if (pile >= 0 && pile < NUM_PILES) {
                if (human.checkIfExists(input)) {
                    Card c = human.getIndex(input);
                    if (piles[pile].addCard(c)) {
                        human.remove(c);
                    } else {
                        System.out.println("Error: invalid move\n");
                    }
                } else {
                    System.out.println("Error: no such card in hand\n");
                }
            } else {
                System.out.println("Error: invalid pile entered\n");
            }

        } else if (input.equals("M") || input.equals("m")) {
            int from = scan.nextInt() - 1;
            int to = scan.nextInt() - 1;

            if (to >= 0 && to < NUM_PILES && from >= 0 && from < 4) {
                if (!moveCardPile(from, to)) {
                    System.out.println("Error: invalid move\n");
                }
            } else {
                System.out.println("Error: invalid pile entered\n");
            }
        } else {
            System.out.println("Error: invalid command\n");
        }
        return 1;
    }

    public boolean moveCardPile(int from, int to) {
        int move = 0;

        if (from >= 4) {
            return false;
        }

        if (to < 4) {
            if (piles[to].getSize() == 0) {
                return false;
            }
        }

        while (piles[from].getSize() > 0 && piles[to].addCard(piles[from].getTop())) {
            piles[from].removeTop();
            move++;
        }

        if (move == 0) {
            return false;
        } else {
            return true;
        }
    }

    public void compPlay() {

        while (comp.kingExists()) {
            for (int i = 4; i < NUM_PILES; i++) {
                if (piles[i].getSize() == 0) {
                    Card c = comp.getKing();
                    piles[i].addCard(c);
                    comp.remove(c);
                    System.out.print("Computer puts ");
                    c.print();
                    System.out.println(" from hand on pile " + (i + 1));
                    break;
                }
            }
        }

        step2();

        //step 3 - any non empty pile
        int moves = 1;
        while (moves != 0) {
            moves = 0;

            for (int i = 0; i < NUM_PILES; i++) {
                if (piles[i].getSize() != 0) {
                    for (int j = 0; j < comp.getSize(); j++) {
                        Card c = comp.getCard(j);
                        if (piles[i].addCard(c)) {
                            comp.remove(c);
                            System.out.print("Computer puts ");
                            c.print();
                            System.out.println(" from hand on pile " + (i + 1));
                            moves++;
                        }
                    }

                }
            }

            if (moves != 0) {
                step2();
            }
        }

        // step 4 - any empty pile 1 through 4
        moves = 1;
        while (moves != 0) {
            moves = 0;

            for (int i = 0; i < 4; i++) {

                for (int j = 0; j < comp.getSize(); j++) {
                    Card c = comp.getCard(j);
                    if (piles[i].addCard(c)) {
                        comp.remove(c);
                        System.out.print("Computer puts ");
                        c.print();
                        System.out.println(" from hand on pile " + (i + 1));
                        moves++;
                    }
                }

            }

            if (moves != 0) {
                step2();
            }
        }

        if (comp.getSize() > 0) {
            Card c = dealCard();
            comp.addToHand(c);
        }

    }

    public void step2() {
        int moves = 1;
        while (moves != 0) {
            moves = 0;
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < NUM_PILES; j++) {
                    if (i != j) {
                        if (moveCardPile(i, j)) {
                            System.out.println("Computer moves pile " + (i + 1) + " to pile " + (j + 1) + "\n");
                            moves++;
                        }
                    }
                }
            }
        }
    }

    public boolean roundEnd() {
        if (d_size == 0 || comp.getSize() == 0 || human.getSize() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean gameEnd() {
        if (human.calcScore() >= 25 || comp.calcScore() >= 25) {
            if(human.calcScore() >= 25)
                System.out.println("Computer Wins");
            else if(comp.calcScore() >= 25)
                System.out.println("Player Wins");
            return true;
        } else {
            return false;
        }
    }

    public void printScore() {
        System.out.println("Player penalty: " + human.calcScore());
        System.out.println("Computer penalty: " + comp.calcScore());
    }

    public static void main(String[] args) {
        boolean game = true;
        int opt = 0;
        Game g = new Game();
        g.setup();
        
        while (game == true) {
            
            while (g.gameEnd() == false) {
                g.print();
                while (g.roundEnd() == false) {
                    
                    while ((opt = g.command()) != DRAW_CARD) {
                        if (opt == 0) {
                            return;
                        }
                        g.print();
                    }
                    g.compPlay();
                    g.print();
                }
                System.out.println("\nRound ends: \n");
                System.out.println("Score:");
                g.printScore();
                g.reset();
            }
            System.out.println("Would you like to play again? (yes/no): \n");
            Scanner scan = new Scanner(System.in);
            String input = scan.next();
            if(input.equals("yes"))
            {
                g.resetAll();
                g.setup();
            }
            else
                game = false;
        }
    }
}
