/**
 * 
 */
package edu.wit.cs.comp2000;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Anthony and Sancho
 *
 */
public class Pile {
	private ArrayList<Card> cards;
	private String name;

	/**
	 * Pile constructor takes a name and instance of an ArrayList
	 * 
	 */
	public Pile(String name) {
		this.name = name;
		this.cards = new ArrayList<Card>();
	}

	/**
	 * Accessor of the ArrayList of a Pile
	 * 
	 * @return ArrayList of version of the Pile of cards
	 */
	public ArrayList<Card> getList() {
		return cards;
	}

	/**
	 * Adds card to pile
	 * 
	 * @param c
	 *            the card being added to the pile
	 */
	public void add(Card c) {
		cards.add(c);
	}

	/**
	 * Removes card from pile
	 * 
	 * @param c
	 *            the card to remove from the pile
	 */
	public void remove(Card c) {
		cards.remove(c);
	}

	/**
	 * Checks if pile is empty
	 * 
	 * @return true if the pile is empty
	 */
	public boolean isEmpty() {
		return cards.isEmpty();
	}

	/**
	 * Clears the pile
	 * 
	 */
	public void reset() {
		cards.clear();
	}

	/**
	 * Adds a Pile to Another Pile by adding an arraylist to an arraylist
	 * 
	 * @param c
	 *            arraylist of a pile to be added
	 */
	public void addAll(ArrayList<Card> c) {
		cards.addAll(c);
	}

	/**
	 * Retrieves top element of the Pile's arraylist
	 * 
	 * @return top element of the array list
	 */
	public Card getTop() {
		return cards.get(0);
	}

	/**
	 * Helper method to visualize a pile of cards
	 * 
	 * @return string containing the entire pile
	 */
	public String pileToString() {
		// create empty string builder
		StringBuilder stringPile = new StringBuilder("");

		// loop through cards in list and add to massive string
		for (int i = 0; i < cards.size(); i++) {
			stringPile.append(cards.get(i).toString());
			stringPile.append("\n");
		}

		// return massive string
		return stringPile.toString();
	}

	/**
	 * A pile splits in half into two hands
	 * 
	 * @param h1
	 *            first hand (1/2)
	 * @param h2
	 *            second hand (1/2)
	 */
	public void splitInHalf(Hand h1, Hand h2) {
		// split pile into two even piles
		h1.addAll(new ArrayList<Card>(cards.subList(0, cards.size() / 2)));
		h2.addAll(new ArrayList<Card>(cards.subList(cards.size() / 2, cards.size())));
	}

	/**
	 * Shuffles cards in a Pile's array list
	 * 
	 */
	public void shuffle() {
		//shuffle array list
		Collections.shuffle(cards);
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
