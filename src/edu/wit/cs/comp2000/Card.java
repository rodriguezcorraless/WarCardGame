/**
 * 
 */
package edu.wit.cs.comp2000;

/**
 * @author Anthony & Sancho
 *
 */
public class Card implements Comparable<Card> {
	private String name;
	private int value;
	private String suit;

	/**
	 * 
	 */
	public Card(Rank r, Suit s) {
		// construct name
		this.name = r.getDisplayName() + " of " + s.getDisplayName();
		// construct value
		this.value = r.getPoints();
		// construct suit
		this.suit = s.getDisplayName();
	}

	/*
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Card o) {
		// card is smaller = 0
		if (o.value > this.value) {
			return 0;
		} else if (o.value < this.value) {
			// card is bigger
			return 1;
		} else {
			// cards are the same
			return 2;
		}
	}
	
	@Override
	public String toString() {
		// call name
		return this.name;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// test card
		//Card test = new Card(Rank.ACE, Suit.CLUBS);
		// test print
		//System.out.printf("%s", test.toString());

	}

}
