package edu.wit.cs.comp2000;

import java.util.Scanner;

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
		// create Deck
		deck = new Deck("Main Deck");

		// scanner
		Scanner input = new Scanner(System.in);

		// ask for player 1 name, create player 1
		System.out.printf("What should we call Player 1?%n");
		player1 = new Player(input.nextLine());

		// ask for player 2 name, create player 2
		System.out.printf("What should we call Player 2?%n");
		player2 = new Player(input.nextLine());

		// creates judge
		judge = new Player("Judge");

		// deal cards
		deck.deal(player1, player2);

		// close scanner
		input.close();
	}

	/**
	 * Method used to loop through the game and actually play.
	 * 
	 */
	private static void play() {
		// variable used for while loop
		boolean playing = true;
		// game is started
		System.out.printf("Let's get started!%n");

		// while game is being played
		while (playing == true) {
			// plays top card of each players maind hand
			System.out.printf("%s plays: %s%n", player1.getName(), player1.getHand().getTop());
			System.out.printf("%s plays: %s%n", player2.getName(), player2.getHand().getTop());

			// judge determines winner based on comparing value of the two players hands
			int playValue = judge.determineWinner(player1, player2);

			// categorize who won and where cards should be moved
			if (playValue == 1) {
				judge.winningMove(player1, player2);
			} else if (playValue == -1) {
				judge.winningMove(player2, player1);
			} else {
				// needs to be implemented
				// for now ignore
				player1.getHand().remove(player1.getHand().getTop());
				player2.getHand().remove(player1.getHand().getTop());
			}

			// used for validation of how many cards a player has left
			System.out.printf("%n");
			System.out.printf("%s has %d cards left%n", player1.getName(), player1.numOfCards());
			System.out.printf("%s has %d cards left%n", player2.getName(), player2.numOfCards());
			System.out.printf("%n");

			// if player1 has no more cards in their main hand, add their winnings to their
			// hand.
			if (player1.runOut()) {
				player1.getWinnings().shuffle();
				player1.getHand().addAll(player1.getWinnings().getList());
				player1.getWinnings().reset();
			}

			// if player2 has no more cards in their main hand, add their winnings to their
			// hand.
			if (player2.runOut()) {
				player2.getWinnings().shuffle();
				player2.getHand().addAll(player2.getWinnings().getList());
				player2.getWinnings().reset();
			}

			// check for loss condition, set playing = false, end play
			if (player1.hasLost() || player2.hasLost()) {
				if (player1.hasLost()) {
					System.out.printf("%s has lost!%n", player1.getName());
				} else {
					System.out.printf("%s has lost!%n", player2.getName());
				}
				playing = false;
			}
		}
		// call method to see if the player wants to play again
		// goAhead();
	}

	/**
	 * Broken! Scanner would not stop and wait for input! Going to be used to
	 * determine if a player wants to go again!
	 */
	public static void goAhead() {
		Scanner input = new Scanner(System.in);
		String response = "";
		System.out.printf("Do you want to play again? Type Y to play again!%n");

		if (response.equals("Y") || response.equals("y")) {
			initialize();
			play();
		} else {
			System.out.printf("Exiting!%n");
			System.exit(0);
		}
	}

	/**
	 * Main method where the game is initialized and then played.
	 */
	public static void main(String[] args) {
		initialize();
		play();
	}
}
