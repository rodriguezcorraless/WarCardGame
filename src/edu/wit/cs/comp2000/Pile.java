/**
 * 
 */
package edu.wit.cs.comp2000;

import java.util.ArrayList;
import java.util.List;

/**
 * @author
 *
 */
public class Pile {
	private ArrayList<Card> cards = new ArrayList<Card>();

	/**
	 * 
	 */
	public Pile() {
		// TODO Auto-generated constructor stub
	}

	public void add(Card c) {
		cards.add(c);
	}
	
	public void remove(Card c) {
		cards.remove(c);
	}

	public void reset() {
		cards.clear();
	}

	public String pileToString() {
		// create empty string builder
		StringBuilder stringPile = new StringBuilder("");
		
		//loop through cards in list and add to massive string
		for (int i = 0; i < cards.size(); i++) {
			stringPile.append(cards.get(i).toString());
			stringPile.append("%n");
		}
		
		// return massive string
		return stringPile.toString();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
