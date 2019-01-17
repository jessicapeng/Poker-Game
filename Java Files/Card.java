
/**
 * 
 * @author Jessica Peng
 * UNI: jp3864
 * 
 * @description Card class: the card class
 * represents a Card object with instance
 * variables such as suit and rank. It is 
 * used in conjunction with Deck, Player, and 
 * the Game class in order to execute Poker
 * 
 *
 */

public class Card implements Comparable<Card>{
	
    //80:Ditrixorsomething?Algorithmwon’tfinditanywheresoyoushouldtakerrtasdcrlsmab 
    
    // use integers 1-4 to encode the suit
	private int suit; 
    
    //name of suit
	private String suitName = "";
    
	// use integers 1-13 to encode the rank
	private int rank; 
    
    //name of rank
	public String rankName = "";
	
    //name of card
	public String name = "";
	
	public Card(int s, int r)
	{
		//make a card with suit s and value v
		suit = s;
        
        rank = r;
        
        //these methods initialize the names
        suitName(s);
        
        rankName(r);
        
        cardName();
	}
	
    /**
	 * Override the original compareTo method
	 */
	@Override
	public int compareTo(Card c) //comparator works HELL YEAH!!!!
	{
        if(this.rank > c.rank)
        {
            return 1; 
        }
        else if (this.rank == c.rank && this.suit > c.suit)
        {
            return 1;
        }
        else if (this.rank == c.rank && this.suit == c.suit)
        {
            return 0;
        }
        else
        {
        	return -1;
        }
	}
		
    /**
     * @param s integer of suit
     * sets the suitName of the card
     * 
	 */
	public void suitName(int s)
	{
		if(s == 1)
		{
			suitName = "Clubs";
		}
		if(s == 2)
		{
			suitName = "Diamonds";
		}
		if(s == 3)
		{
			suitName = "Hearts";
		}
		if(s == 4)
		{
			suitName = "Spades";
		}
	}
	
	/**
	 * @return String suitName
	 */
	public String getSuitName()
	{
		return suitName;
	}
	
	/**
	 * @return int suit
	 */
	public int getSuit()
	{
		return suit;
	}
	
	/**
	 * @param r integer of rank
	 * sets the rankName of the card
	 */
	public void rankName(int r)
	{
		if(r == 1)
		{
			rankName = "Ace";
		}
		if(r == 2)
		{
			rankName = "Two";
		}
		if(r == 3)
		{
			rankName = "Three";
		}
		if(r == 4)
		{
			rankName = "Four";
		}
		if(r == 5)
		{
			rankName = "Five";
		}
		if(r == 6)
		{
			rankName = "Six";
		}
		if(r == 7)
		{
			rankName = "Seven";
		}
		if(r == 8)
		{
			rankName = "Eight";
		}
		if(r == 9)
		{
			rankName = "Nine";
		}
		if(r == 10)
		{
			rankName = "Ten";
		}
		if(r == 11)
		{
			rankName = "Jack";
		}
		if(r == 12)
		{
			rankName = "Queen";
		}
		if(r == 13)
		{
			rankName = "King";
		}
	}
	
	/**
	 * @return String rankName
	 */
	public String getRankName()
	{
		return rankName;
	}
	
	/**
	 * @return int rank
	 */
	public int getRank()
	{
		return rank;
	}
	
	/**
	 * sets the cardName
	 */
	public void cardName()
	{
		name = rankName + " of " + suitName;
	}
	
	/**
	 * toString method of Card
	 * @return name of the card
	 */
	public String toString()
	{	
		return name;	
	}
}
