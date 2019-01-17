
import java.util.ArrayList;
import java.util.Collections;

/**
 * 
 * @author Jessica Peng
 * UNI: jp3864
 * 
 * @description Deck class: the deck class
 * represents a deck object that contains an
 * array of 52 cards used to deal to the player
 * in the game. It is used in conjunction with Card, Player, 
 * and the Game class in order to execute Poker
 * 
 *
 */

public class Deck {

	private Card[] cards;

	// the index of the top of the deck
	private int top; 

	String s = "";

	//constructor for deck
	public Deck()
	{
		cards = new Card[52];
		int count = 0;
		for(int i = 1; i <= 4; i++)
		{
			for (int j = 1; j <= 13; j++)
			{
				cards[count] = new Card(i,j);
				count++;
			}
		}
		top = 0;
	}

	/**
	 * shuffles the deck and resets top to 0
	 */
	public void shuffle()
	{
		ArrayList<Card> arrListDeck = new ArrayList<Card>();
		for(int i = 0; i < 52; i++)
		{
			arrListDeck.add(cards[i]);
		}
		Collections.shuffle(arrListDeck);   
		for(int j = 0; j < 52; j++)
		{
			cards[j] = arrListDeck.get(j);
		}
		top = 0;
	}

	/**
	 * deals a card to player
	 * and increments top by 1
	 * resets if top at end of deck
	 */
	public Card deal() 
	{ 
		if(top == 51)
		{
			reset();
		}
		Card c = cards[top];
		top++;
		return c;
	}

	/**
	 * @return top of deck
	 * 
	 */
	public int getTop()
	{
		return top;
	}


	/**
	 * resets entire deck
	 * 
	 */
	public void reset()
	{
		cards = new Card[52];
		int count = 0;
		for(int i = 1; i <= 4; i++)
		{
			for (int j = 1; j <= 13; j++)
			{
				cards[count] = new Card(i,j);
				count++;
			}
		}
		top = 0;
		shuffle(); 
	}

	/**
	 * toString method of Deck
	 * @return String name of deck
	 */
	public String toString()
	{
		for(int i = 0; i < 52; i++)
		{
			s = s + ", " + cards[top];
		}
		return s;
	}

}