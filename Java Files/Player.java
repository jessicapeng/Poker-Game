import java.util.ArrayList;
import java.util.Collections;

/**
 * 
 * @author Jessica Peng
 * UNI: jp3864
 * 
 * @description Player class: the player class
 * represents a player object with instance
 * variables arraylist of the hand and bankroll. 
 * It is used in conjunction with Deck, Card, and 
 * the Game class in order to execute Poker
 * 
 *
 */

public class Player {

	// the player's cards
	private ArrayList<Card> hand; 

	//bankroll of player's tokens
	private double bankroll;

	//constructor method for player
	public Player()
	{		
		hand = new ArrayList<Card>();
		bankroll = 100; 

	}

	//for testing purposes
	public Player(ArrayList<Card> h)
	{
		hand = h;
	}

	/**
	 * sorts hand of player
	 * 
	 */
	public void sortHand()
	{
		Collections.sort(hand);
	}


	/**
	 * @param Card c card to add
	 * adds card to hand of player
	 * 
	 */
	public void addCard(Card c)
	{
		hand.add(c);
	}

	/**
	 * @param Card c card to remove
	 * remove card from hand of player
	 * 
	 */
	public void removeCard(Card c)
	{
		hand.remove(c);
	}

	/**
	 * @param int index to remove
	 * removes card at that index
	 * 
	 */
	public void removeCardAtIndex(int index)
	{
		hand.remove(index);
	}

	/**
	 * @param int index to add
	 * adds card at that index
	 * 
	 */
	public void addCardAtIndex(int index, Card c)
	{
		hand.add(index, c);
	}

	/**
	 * @param int index to get card
	 * @return Card
	 * returns card at that index
	 * 
	 */
	public Card getCardAtIndex(int index)
	{
		return hand.get(index);
	}

	/**
	 * @param double amt
	 * calculates how much they
	 *  lose from bankroll
	 * 
	 */
	public void bets(double amt)
	{
		bankroll = bankroll - amt; 
	}

	/**
	 * @param double odds
	 * calculates how much they win
	 * odds is (amt of token) * (hand)
	 * 
	 */
	public void winnings(double odds) 
	{
		bankroll = bankroll + odds;
	}

	/**
	 * @return double bankroll
	 * returns bankroll
	 * 
	 */
	public double getBankroll()
	{
		return bankroll;
	}

	/**
	 * @return hand
	 * returns the hand of the player
	 * 
	 */
	public ArrayList<Card> getHand()
	{
		return hand;
	}

	/**
	 * resets the entire hand by removing
	 * all the Cards
	 * 
	 */
	public void reset()
	{	
		while(hand.size()>0)
		{
			hand.remove(0);
		}
	}
}


