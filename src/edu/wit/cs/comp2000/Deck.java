/**
 * 
 */
package edu.wit.cs.comp2000;

import java.util.ArrayList;

/**
 * @author
 *
 */
public class Deck extends Pile {
	/**
	 * 
	 */
	public Deck() {
		// call setup of deck
		setup();
	}

	public void setup() {
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
	 * @param args
	 */
	public static void main(String[] args) {
		// test printing deck
		// Deck d = new Deck();
		//
		// System.out.printf("%s", d.pileToString());

	}

}
