package edu.wit.cs.comp2000;

import java.util.Scanner;

/**
 * 
 * @author Anthony & Sancho
 *
 */
public class War {
	private static Deck deck;
	private static Player player1;
	private static Player player2;
	private static Player judge;

	/**
	 * Setups up game with new deck. Creates players with names supplied by input.
	 * Creates a judge player who determines whether or not player1 or player2 has
	 * won. Deals cards to players and stores them in their main hand.
	 * 
	 * 
	 */
	private static void initialize() {
		// EPIC WAR PRINT
		System.out.printf("%n%n/ _ \\_/\\  / _ \\_/\\ /  \\    /  \\/  _  \\\\______   \\ / _ \\_/\\  / _ \\_/\\ \r\n"
				+ " \\/ \\___/  \\/ \\___/ \\   \\/\\/   /  /_\\  \\|       _/ \\/ \\___/  \\/ \\___/ \r\n"
				+ "                     \\        /    |    \\    |   \\                    \r\n"
				+ "                      \\__/\\  /\\____|__  /____|_  /                    \r\n"
				+ "                           \\/         \\/       \\/                     %n%n");
		// create Deck
		deck = new Deck("Main Deck");

		// scanner
		Scanner input = new Scanner(System.in);

		// ask for player 1 name, create player 1
		System.out.printf("What should we call Player 1?%n");
		player1 = new Player(input.nextLine());

		System.out.printf("%n");

		// ask for player 2 name, create player 2
		System.out.printf("What should we call Player 2?%n");
		player2 = new Player(input.nextLine());

		// creates judge
		judge = new Player("Judge");

		// deal cards
		deck.deal(player1, player2);
	}

	/**
	 * Method used to loop through the game and actually play.
	 * 
	 */
	private static void play() {
		// main game loop boolean
		boolean playing = true;
		// boolean for turn
		boolean turn = true;

		System.out.printf("%n");

		// game is started
		System.out.printf("Let's get started!%n");
		System.out.printf("Players must press enter to play their turn!%n");

		System.out.printf("%n");

		// while game is being played
		while (playing == true) {

			// turn == true means it's the first players turn
			if (turn == true) {
				// require enter to play
				turn(player1);
				// plays top card of each players maind hand
				System.out.printf("%s plays: %s%n", player1.getName(), player1.getHand().getTop());
				// switch turnA
				turn = false;
			}

			// turn == true means it's the first players turn
			if (turn == false) {
				// require enter to play
				turn(player2);
				// plays top card of each players maind hand
				System.out.printf("%s plays: %s%n", player2.getName(), player2.getHand().getTop());
				// switch turn
				turn = true;
			}

			// judge determines winner based on comparing value of the two players hands
			int playValue = judge.determineWinner(player1, player2);

			// categorize who won and where cards should be moved
			if (playValue == 1) {
				judge.winningMove(player1, player2);
			} else if (playValue == -1) {
				judge.winningMove(player2, player1);
			} else {
				judge.war(player1, player2);
			}

			// used for validation of how many cards a player has left
			System.out.printf("%n");
			System.out.printf("%s has %d cards left%n", player1.getName(), player1.numOfCards());
			System.out.printf("%s has %d cards left%n", player2.getName(), player2.numOfCards());
			System.out.printf("%n");

			// if player1 has no more cards in their main hand, add their winnings to their
			// hand.
			if (player1.runOut()) {
				player1.fillHand();
			}

			// if player2 has no more cards in their main hand, add their winnings to their
			// hand.
			if (player2.runOut()) {
				player2.fillHand();
			}

			// check for loss condition, set playing = false, end play
			if (player1.hasLost() || player2.hasLost()) {
				if (player1.hasLost()) {
					System.out.printf("No more cards to play! %s has lost!%n", player1.getName());
				} else {
					System.out.printf("No more cards to play! %s has lost!%n", player2.getName());
				}
				playing = false;
			}
		}
	}

	/*
	 * Method used to indicate players turn. Waits for enter to be pressed to
	 * continue!
	 * 
	 */
	private static void turn(Player p) {
		System.out.printf("%s it is your turn! Play your card!%n", p.getName());
		Scanner enter = new Scanner(System.in);
		enter.nextLine();
	}

	/**
	 * Main method where the game is initialized and then played.
	 */
	public static void main(String[] args) {
		initialize();
		play();
	}
}
