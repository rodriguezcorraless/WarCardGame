/**
 * 
 */
package edu.wit.cs.comp2000;

/**
 * @author
 *
 */
public class Hand extends Pile {

	/**
	 * @param name
	 *            what the hand should be called
	 */
	public Hand(String name) {
		super(name);

	}

	/**
	 * Call to see if a hand is empty
	 * 
	 * @return whether or not the hand is empty based on if the pile is empty
	 */
	public boolean handIsEmpty() {
		return isEmpty();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

}
