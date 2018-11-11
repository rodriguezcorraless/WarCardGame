/**
 * 
 */
package edu.wit.cs.comp2000;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Anthony & Sancho
 *
 */
public class Deck extends Pile {
	public Deck(String name) {
		super(name);
		// call setup on deck
		setup();
		// shuffle pile
		shuffle();
	}

	/**
	 * Setups deck by filling the deck with all necessary cards
	 * 
	 */
	private void setup() {
		// nested loop through enums creating cards and adding them to the list within
		// the deck (pile)
		for (Rank r : Rank.values()) {
			for (Suit s : Suit.values()) {
				Card o = new Card(r, s);
				add(o);
			}
		}
	}

	/**
	 * Deck deals cards to players
	 * 
	 * @param p1
	 *            first player to be dealt
	 * 
	 * @param p2
	 *            second player to be dealt
	 */
	public void deal(Player p1, Player p2) {
		// deals deck into two even piles
		splitInHalf(p1.getHand(), p2.getHand());
		// clears deck once cards have been dealt
		reset();
	}
}
