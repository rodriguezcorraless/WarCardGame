/**
 * 
 */
package edu.wit.cs.comp2000;

import java.util.ArrayList;

/**
 * @author
 *
 */
public class Player {
	private Hand hand;
	private Hand winnings;
	private String name;

	/**
	 * Construct Player object where each has a main hand and a hand of winnings
	 * 
	 * @param name
	 *            name of player
	 */
	public Player(String name) {
		hand = new Hand("main");
		winnings = new Hand("winnings");
		this.name = name;
	}

	/**
	 * Call to get main hand (pile object)
	 * 
	 * @return hand
	 */
	public Hand getHand() {
		return hand;
	}

	/**
	 * Call to get winnings hand (pile object)
	 * 
	 * @return winnings
	 */
	public Hand getWinnings() {
		return winnings;
	}

	/**
	 * Method use for a player to determine the winner of a play
	 * 
	 * @param p1
	 *            first player in the play
	 * @param p2
	 *            second player in the play
	 * 
	 * @return the integer value of 0, 1, -1 based on list comparision
	 */
	public int determineWinner(Player p1, Player p2) {
		return p1.getHand().getTop().compareTo(p2.getHand().getTop());
	}

	public int determineWinner(Player p1, Player p2, int i) {
		return p1.getHand().getList().get(i).compareTo(p2.getHand().getList().get(i));
	}

	/**
	 * A Player makes a winning move where the first player in the argument receives
	 * the winnings of the play added to their winnings. Cards are removed from the
	 * main hand and stored in winnings.
	 * 
	 * @param p1
	 *            player who won
	 * @param p2
	 *            player who lost
	 */
	public void winningMove(Player p1, Player p2) {
		p1.winnings.add(p1.getHand().getTop());
		p1.winnings.add(p2.getHand().getTop());
		p1.getHand().remove(p1.getHand().getTop());
		p2.getHand().remove(p2.getHand().getTop());
	}

	public void winningMove(Player p1, Player p2, int i) {
		for (int j = i; j >= 0; j--) {
			p1.winnings.add(p1.getHand().getList().get(j));
			p1.winnings.add(p2.getHand().getList().get(j));
			p1.getHand().remove(p1.getHand().getList().get(j));
			p2.getHand().remove(p2.getHand().getList().get(j));
		}
	}

	public boolean notEnoughForWar() {
		return (hand.getList().size() + winnings.getList().size()) < 3;
	}

	/**
	 * A player has lost if both of their hands are empty.
	 * 
	 * @return true if a player has no cards.
	 */
	public boolean hasLost() {
		return hand.isEmpty() && winnings.isEmpty();
	}

	public void fillHand() {
		winnings.shuffle();
		hand.addAll(winnings.getList());
		winnings.reset();
	}

	/**
	 * A player has ranout of cards in their main hands and needs to use their
	 * winnings.
	 * 
	 * @return true if the player has cards to use in their winnings
	 */
	public boolean runOut() {
		return hand.isEmpty() && !winnings.isEmpty();
	}

	/**
	 * A player's number of cards is dependent on the size of their two hands
	 * 
	 * @return int value of the number of cards
	 */
	public int numOfCards() {
		return hand.getList().size() + winnings.getList().size();
	}

	/**
	 * Accessor for a players name
	 * 
	 * @return string value of a players name
	 */
	public String getName() {
		return name;
	}

	public Player lessCards(Player p1, Player p2) {
		if (p1.numOfCards() > p2.numOfCards()) {
			return p1;
		} else if (p2.numOfCards() > p1.numOfCards()) {
			return p2;
		} else {
			return p1;
		}
	}

	public void war(Player p1, Player p2) {
		boolean play = true;
		boolean warWon = false;

		while (play = true) {
			p1.fillHand();
			p2.fillHand();

			if (p1.notEnoughForWar() || p2.notEnoughForWar()) {
				if (p1.notEnoughForWar()) {
					System.out.printf("Player 1 has not enough for War!%n");
					p1.getHand().reset();
					p1.getWinnings().reset();
				} else {
					System.out.printf("Player 2 has not enough for War!%n");
					p2.getHand().reset();
					p2.getWinnings().reset();
				}
			}
			
			
			
			
			for (int i = 2; i < lessCards(p1, p2).getHand().getList().size(); i = i + 2) {
				if (determineWinner(p1, p2, i) == 1) {
					System.out.printf("Player 1 plays: %s%n", p1.getHand().getList().get(i));
					System.out.printf("Player 2 plays: %s%n", p2.getHand().getList().get(i));
					winningMove(p1, p2, i);
					warWon = true;
				} else if (determineWinner(p1, p2, i) == -1) {
					System.out.printf("Player 1 plays: %s%n", p1.getHand().getList().get(i));
					System.out.printf("Player 2 plays: %s%n", p2.getHand().getList().get(i));
					winningMove(p2, p1, i);
					warWon = true;
					break;
				} else {
					play = true;
				}
				
				if(warWon = true) {
					break;
				}
			}

			if (warWon = true) {
				play = false;
			}
			// System.out.printf("war card is: %s%n", p1.getHand().getTop().toString());
			// System.out.printf("war card is: %s%n", p2.getHand().getTop().toString());
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Player player1 = new Player("ok");
		Player player2 = new Player("awesome");
		Player player3 = new Player("cool");

	}

}
