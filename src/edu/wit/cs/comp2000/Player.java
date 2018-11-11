/**
 * 
 */
package edu.wit.cs.comp2000;

import java.util.ArrayList;

/**
 * @author Anthony & Sancho
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
	 * Method used to determine the winner of a turn at a certain index. Used for
	 * when their is war.
	 * 
	 * @param p1
	 * @param p2
	 * @param i
	 * @return
	 */
	private int determineWinner(Player p1, Player p2, int i) {
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
		System.out.printf("%s has won the round!%n", p1.getName());
	}

	/**
	 * Unloads cards from one players hand to the other players hand. For uses with
	 * winning hands in war
	 * 
	 * @param p1
	 *            Player 1
	 * @param p2
	 *            Player 2
	 * @param i
	 *            index from where to start unloading cards
	 */
	private void winningMove(Player p1, Player p2, int i) {
		for (int j = i; j >= 0; j--) {
			p1.winnings.add(p1.getHand().getList().get(j));
			p1.winnings.add(p2.getHand().getList().get(j));
			p1.getHand().remove(p1.getHand().getList().get(j));
			p2.getHand().remove(p2.getHand().getList().get(j));
		}
	}

	/**
	 * Used to determine if a player does not have enough cards left to go to War
	 * 
	 * @return true or false depending if a player has less than 3
	 */
	private boolean notEnoughForWar() {
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

	/**
	 * Shuffles a players winnings and fills their main hand
	 */
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

	/**
	 * Used to determine which player has less cards
	 * 
	 * @param p1
	 *            Player 1
	 * @param p2
	 *            Player 2
	 * @return return player with less cards
	 */
	private Player lessCards(Player p1, Player p2) {
		if (p1.numOfCards() > p2.numOfCards()) {
			return p1;
		} else if (p2.numOfCards() > p1.numOfCards()) {
			return p2;
		} else {
			return p1;
		}
	}

	/**
	 * Method used when two players are at War. conducts the war.
	 * 
	 * @param p1
	 *            Player 1
	 * @param p2
	 *            Player 2
	 */
	public void war(Player p1, Player p2) {
		System.out.printf("%n");
		System.out.printf("War!%n");
		System.out.printf("%n");
		boolean play = true;
		p1.fillHand();
		p2.fillHand();

		for (int i = 2; i < (lessCards(p1, p2).getHand().getList().size()) && (play == true); i = i + 2) {

			if (p1.notEnoughForWar() || p2.notEnoughForWar()) {
				if (p1.notEnoughForWar()) {
					System.out.printf("%s does not have enough cards for War!%n", p1.getName());
					p1.getHand().reset();
					p1.getWinnings().reset();
					if (p1.notEnoughForWar()) {
						System.out.printf("%s has lost!%n", p2.getName());
						System.exit(1);
					}
				} else {
					System.out.printf("%s does not have enough cards for War!%n", p2.getName());
					p2.getHand().reset();
					p2.getWinnings().reset();
					if (p2.notEnoughForWar()) {
						System.out.printf("%s has lost!%n", p1.getName());
						System.exit(1);
					}
				}

			}

			if (determineWinner(p1, p2, i) == 1) {
				System.out.printf("%s plays: %s%n", p1.getName(), p1.getHand().getList().get(i));
				System.out.printf("%s plays: %s%n", p2.getName(), p2.getHand().getList().get(i));
				winningMove(p1, p2, i);
				play = false;
				// warWon = true;
			} else if (determineWinner(p1, p2, i) == -1) {
				System.out.printf("%s plays: %s%n", p1.getName(), p1.getHand().getList().get(i));
				System.out.printf("%s plays: %s%n", p2.getName(), p2.getHand().getList().get(i));
				winningMove(p2, p1, i);
				play = false;
			} else {
				play = true;
			}

		}
	}

}
