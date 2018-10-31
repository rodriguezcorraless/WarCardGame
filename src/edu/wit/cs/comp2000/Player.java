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

	/**
	 * A player has lost if both of their hands are empty.
	 * 
	 * @return true if a player has no cards.
	 */
	public boolean hasLost() {
		return hand.isEmpty() && winnings.isEmpty();
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

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Player test = new Player("Okay");
		test.hand.getList();
	}

}
