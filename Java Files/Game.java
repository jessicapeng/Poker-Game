import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * 
 * @author Jessica Peng
 * UNI: jp3864
 * 
 * @description Game class: the game class simulates 
 * the game by creating a deck and player. Game contains
 * 2 constructors, 1 for testing purposes and 1
 * for playing. Game's "play" method runs the game. 
 * Game also contains submethods to evaluate the hand
 * of the cards
 *
 */

public class Game {

	//test boolean to signify 
	//if the testing game constructor is called
	//is the 2nd way of running play 
	private boolean test;

	//player object
	private Player p;

	//deck object that contains 52 cards
	private Deck cards;

	//how much the player bets for their original hand
	private int tokenBet;

	//arraylist that contains the player's
	//cards that they want to redraw
	private ArrayList<Integer> redraw;

	//name of hand
	private String handName;

	//payout of their hand
	private int payout;

	//String for whether they want to play again or not (y/n)
	private String playAgain;

	//first Game constructor: this is used to test the hand
	public Game(String[] testHand){

		//first we initialize all the variables
		test = true;

		int testSuit = 0;

		cards = new Deck();

		p = new Player();

		tokenBet = 0;

		redraw = new ArrayList<Integer>();

		handName = "";

		payout = 0;

		playAgain = "";

		//then we parse the String args entered
		//this checks for the suit of the card
		for(int i = 0; i < 5; i++)
		{

			if(testHand[i].substring(0,1).equals("c"))
			{
				testSuit = 1;
			}
			if(testHand[i].substring(0,1).equals("d"))
			{
				testSuit = 2;
			}
			if(testHand[i].substring(0,1).equals("h"))
			{
				testSuit = 3;
			}
			if(testHand[i].substring(0,1).equals("s"))
			{
				testSuit = 4;
			}

			//this checks for the rank of the card:
			p.addCard(new Card(testSuit, 
					Integer.parseInt(testHand[i].substring(1))));

		}

		//prints out the hand for the user to see
		System.out.println(p.getHand());
	}

	//constructor to play the actual game
	public Game()
	{

		//initialize variables 

		test = false;

		cards = new Deck();

		p = new Player();

		tokenBet = 0;

		redraw = new ArrayList<Integer>();

		handName = "";

		payout = 0;

		playAgain = "";
	}

	/**
	 * play method to play game
	 * 
	 */
	public void play()
	{
		//if this boolean is true, then play
		//is only used to test the hand and payout
		//of the game
		if(test == true)
		{
			//first sort hand
			p.sortHand();

			//evaluate hand and tell user what they got
			handName = checkHand(p.getHand());

			payout = getHandPayout(p.getHand());

			if(!handName.equals("No Pair"))
			{
				System.out.println("Congratulations!"
						+ " You have a " + handName + "!");

				System.out.println("The payout for "
						+ "this hand is " + payout + ".");
			}
			else
			{
				System.out.println("Sorry. You have no pairs."); 
			}
		}
		else 
		{

			//greeting
			System.out.println("Welcome to the game of Poker! ");
			System.out.println("How this game works: "
					+ "You will be dealt 5 cards. ");
			System.out.println("Based on your initial draw, "
					+ "you can choose the number "
					+ "of tokens you bet on your hand. ");
			System.out.println("Then, you get to choose"
					+ " which cards you want to redraw "
					+ "(up to 5). For your convenience, ");
			System.out.println("each time your recieve your cards"
					+ " (even after a redraw) they will be sorted and "
					+ "ordered for ");
			System.out.println("you by their "
					+ "rank and suit so you can evaluate it easily. "
					+ "Finally, your hand will be ");
			System.out.println("evaluated, and you will either "
					+ "recieve winnings or lose bets. "
					+ "You can keep playing until ");
			System.out.println("you run out of money  ");


			//initiate scanner and make the first 
			//time for playAgain yes to start game
			@SuppressWarnings("resource")
			Scanner scan = new Scanner(System.in);

			playAgain = "y";

			//while bankroll is positive and they want 
			//to play again
			while(p.getBankroll()!=0 && playAgain.equals("y"))
			{
				System.out.println();

				//ask user how much they want to bet first
				System.out.println("Your current bankroll is "
						+ "" + p.getBankroll());

				System.out.println("How many tokens do you "
						+ "want to bet on your hand? (1-5)");

				tokenBet = scan.nextInt();
				//shuffling cards
				cards.shuffle();

				//deals 5 cards to player
				for (int i = 0; i < 5; i++) 
				{
					p.addCard(cards.deal());
				}

				//sort the cards
				p.sortHand();


				//gives user their hand and takes their bets			
				System.out.print("Your five cards are: ");

				System.out.println(p.getHand());


				//System.out.println("Would you like to keep"
				//		+ " re-drawing? Please enter y/n only");
				System.out.println("Would you like to redraw all, "
						+ "some, or none of the cards? Please"
						+ " enter 2 for all, 0 for none, "
						+ "and 1 for some of the cards. ");

				int ans = scan.nextInt();

				if(ans == 2)
				{
					//remove all cards in hand first
					p.reset();

					//deal and add new cards
					for(int i = 0; i < 5; i++) 
					{

						p.addCardAtIndex(i, cards.deal()); 
					}

					p.sortHand();

					System.out.println("Your new hand with "
							+ "all cards replaced is: ");

					System.out.println(p.getHand());
				}
				else if(ans==1)
				{
					//asks user what card they would like to change
					System.out.println("What cards would you "
							+ "like to change?");
					System.out.println("Type the indexes "
							+ "with a space between "
							+ "each. Do not type commas in between.");
					System.out.println("Do not press enter"
							+ " key in your input."
							+ " At the end of your index "
							+ "entries, type 'done'.");
					System.out.println("The #s should be "
							+ "between 1-5: Ex. '1 3 4 done'. "
							+ "If you want to replace all, "
							+ "you enter '1 2 3 4 5 done' ");

					while(scan.hasNextLine() && scan.hasNextInt())
					{
						//adding the cards to redraw
						redraw.add(scan.nextInt()); 
					}

					int i = 0; //index that iterates through redraw

					int indexRemove;

					//test this algorithm on PokerTest
					while(i<redraw.size()) 
					{
						indexRemove = redraw.get(i); //gets value to remove

						p.removeCardAtIndex(indexRemove-1); 

						p.addCardAtIndex(indexRemove-1, cards.deal()); 

						i++;
					}

					//clean out redraw
					while(redraw.size()>0)
					{
						redraw.remove(0);
					}

					p.sortHand();

					System.out.println("Your new hand with the "
							+ "replaced cards are: ");

					System.out.println(p.getHand());

				}

				//evaluate hand and tell user what they got
				System.out.println("Since you are satisfied"
						+ " with your hand, we "
						+ "will now evaluate your hand:");

				handName = checkHand(p.getHand());

				payout = getHandPayout(p.getHand());

				//evaluate the winnings/losses
				if(!handName.equals("No Pair"))
				{
					int winnings = payout * tokenBet;

					System.out.println("Congratulations!"
							+ " You have a " + handName + "!");

					System.out.println("The payout for this hand is " 
							+ payout + ".");

					System.out.println("Since you bet " + tokenBet
							+ " tokens, "

							+ "this means that you win " + winnings);

					p.winnings(winnings); //add winnings
				}
				else
				{
					System.out.println("Sorry. You have no pairs."); 

					System.out.println("Since you have no pairs, "
							+ "you will lose " + tokenBet + " tokens.");

					p.bets(tokenBet);
				}

				System.out.println("Your current bankroll is " 
						+ p.getBankroll());

				//choose to keep playing or no
				if(p.getBankroll()>0)
				{
					System.out.println("Since you still have a positive"
							+ " bankroll, would you like to keep "
							+ "playing? Please enter y/n");


					//there was a bug with previous scanner, 
					//so to be able to scan, implemented new 
					//scanner for terminating/continuing game

					Scanner scan1 = new Scanner(System.in);

					playAgain = scan1.next();

					if(playAgain.equals("n"))
					{
						System.out.println("Thank you for playing! "
								+ "Your final bankroll is " +
								p.getBankroll() + ". Goodbye!");
					}
				}
				else
				{
					System.out.println("Sorry. You ran out of"
							+ " tokens. Game Over.");
					break;
				}
				reset();	
			}
		}
	}

	/**
	 * checks the cards that the player has and returns the hand
	 *
	 * @param  evaluateHand: copy of hand that player has
	 * @return  royalFlush: boolean of whether the 
	 * hand is a royal flush or not
	 */
	public String checkHand(ArrayList<Card> hand)
	{

		if(isRoyalFlush(hand)==true)
		{
			handName = "Royal Flush";
		}
		else if(isStraightFlush(hand) == true)
		{
			handName = "Straight Flush";
		}
		else if (isFourOfAKind(hand) == true) 
		{
			handName = "Four of a Kind";
		}
		else if(isFullHouse(hand) == true) 
		{
			handName = "Full House";
		}
		else if(isFlush(hand) == true)
		{
			handName = "Flush";
		}
		else if(isThreeOfAKind(hand) == true) 
		{
			handName = "Three of a Kind";
		}
		else if(isStraight(hand) == true)
		{
			handName = "Straight";
		}
		else if(isTwoPairs(hand) == true) 
		{
			handName = "Two Pairs";
		}
		else if(isOnePair(hand) == true) 
		{
			handName = "One Pair";
		}
		else
		{
			handName = "No Pair";
		}
		return handName;
	}

	/**
	 * tests for royal flush
	 *
	 * @param  evaluateHand: copy of hand that player has
	 * @return  royalFlush: boolean of whether the hand is
	 *  a royal flush or not
	 */
	public boolean isRoyalFlush(ArrayList<Card> evaluateHand) 
	{
		boolean royalFlush = false;

		if(tenJackQueenKingAceRanks(evaluateHand)==true 
				&& sameSuit(evaluateHand) == true)
		{
			royalFlush = true;
		}
		return royalFlush;
	}

	public boolean tenJackQueenKingAceRanks(ArrayList<Card> evaluateHand)
	{
		boolean royalFlushRanks = false;
		if(evaluateHand.get(0).getRank() == 1 && 
				evaluateHand.get(1).getRank() == 10 && 
				evaluateHand.get(2).getRank() == 11 && 
				evaluateHand.get(3).getRank() == 12 
				&& evaluateHand.get(4).getRank() == 13)
		{
			royalFlushRanks = true;
		}
		return royalFlushRanks;
	}

	/**
	 * tests if hand is a straight flush - consecutive #s and same suit
	 * 
	 * @param  evaluateHand: card arraylist 
	 * with hand that player has
	 * @return  straightFlush: boolean of whether
	 *  the hand is all in the same suit
	 */
	public boolean isStraightFlush(ArrayList<Card> evaluateHand)
	{
		return isStraight(evaluateHand) && sameSuit(evaluateHand);
	}

	/**
	 * tests if hand is 4 of a kind - 4 cards of same value
	 * 
	 * @param  evaluateHand: card arraylist with hand that player has
	 * @return  boolean of whether there are 4 same cards
	 */
	public boolean isFourOfAKind(ArrayList<Card> evaluateHand) 
	{
		for(int i = 0; i < evaluateHand.size()-1; i++)
		{
			int count = 1;
			for(int j = i+1; j < evaluateHand.size(); j++)
			{
				if(evaluateHand.get(i).getRank()==
						evaluateHand.get(j).getRank())
				{
					count++;
				}
			}
			if(count == 4)
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * tests if hand is a full house - three of a kind and a pair
	 * 
	 * @param  evaluateHand: card arraylist with hand that player has
	 * @return boolean of whether hand is fullhouse
	 */
	public boolean isFullHouse(ArrayList<Card> evaluateHand)
	{
		return isOnePair(evaluateHand) && isThreeOfAKind(evaluateHand);
	}


	/**
	 * tests if hand is a flush - all same suit
	 * 
	 * @param  evaluateHand: card arraylist with hand that player has
	 * @return boolean of whether hand is a flush
	 */
	public boolean isFlush(ArrayList<Card> evaluateHand)
	{
		return sameSuit(evaluateHand);
	}

	/**
	 * tests if hand is a straight - 5 cards with consecutive values
	 * 
	 * @param  evaluateHand: card arraylist with hand that player has
	 * @return  straight: boolean of whether hand is a straight
	 */
	public boolean isStraight(ArrayList<Card> evaluateHand)
	{
		boolean straight = true;

		//exception for ace as the larger number 
		if(tenJackQueenKingAceRanks(evaluateHand)==true)
		{
			straight = true;
		}
		else
		{
			straight = true;
			for(int i = 0; i <evaluateHand.size()-1; i++)
			{
				if(evaluateHand.get(i).getRank() + 1 
						!= evaluateHand.get(i+1).getRank())
				{
					straight = false;
				}
			}
		}
		return straight;
	}

	/**
	 * tests if hand is has two pairs 
	 * 
	 * @param  evaluateHand: card arraylist with hand that player has
	 * @return twoPairs: boolean of whether the card has 2 pairs
	 */
	public boolean isTwoPairs(ArrayList<Card> evaluateHand)
	{
		ArrayList<Card> temp = new ArrayList<Card>();
		for(int i = 0; i < evaluateHand.size(); i++)
		{
			temp.add(evaluateHand.get(i));
		}

		boolean twoPairs = false;
		if(isOnePair(temp))
		{
			removeFirstPair(temp);
			if(isOnePair(temp))
			{
				twoPairs = true;
			}
		}
		return twoPairs;	
	}

	/**
	 * tests if all of hand is in the same suit
	 *
	 * @param  evaluateHand: copy of hand that player has
	 * @return  sameSuit: boolean of whether
	 *  the hand is all in the same suit
	 */
	public boolean sameSuit(ArrayList<Card> evaluateHand)
	{
		boolean sameSuit = true;
		for(int i = 0; i < evaluateHand.size()-1; i++)
		{
			if(evaluateHand.get(i).getSuit() 
					!= evaluateHand.get(i+1).getSuit())
			{
				sameSuit = false;
			}
		}
		return sameSuit;
	}

	/**
	 * 1st sub-method for full house
	 * 
	 * tests if there are 3 of a kind inside the deck (but not 4 of a kind)
	 *
	 * @param  evaluateHand: copy of hand that player has
	 * @return  boolean of whether there are 3 of the same cards
	 */
	public boolean isThreeOfAKind(ArrayList<Card> evaluateHand)
	{
		for(int i = 0; i < evaluateHand.size()-1; i++)
		{
			int count = 0;
			for(int j = 0; j < evaluateHand.size(); j++)
			{
				//changed j so instead of 
				//j = i+1, j starts with 0 so
				//checks the whole array so ONLY
				//3 same NOT 4 or more included
				if(evaluateHand.get(i).getRank()
						== evaluateHand.get(j).getRank())
				{
					count++;
				}
			}
			if(count == 3)
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * 2nd sub-method for full house
	 * 
	 * tests if there is a double in the deck 
	 *
	 * @param  evaluateHand: copy of hand that player has
	 * @return  boolean of whether there are 3 of the same cards
	 */
	public boolean isOnePair(ArrayList<Card> evaluateHand)
	{
		for(int i = 0; i < evaluateHand.size()-1; i++)
		{
			int count = 0;
			for(int j = 0; j < evaluateHand.size(); j++)
			{
				if(evaluateHand.get(i).getRank()
						==evaluateHand.get(j).getRank())
				{
					count++;
				}
			}
			if(count == 2)
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * submethod for isTwoPairs method - removes the first pair
	 *
	 * @param  evaluateHand: copy of hand that player has
	 * @return  arraylist of cards with the first pair removed
	 */
	public ArrayList<Card> removeFirstPair(ArrayList<Card> evaluateHand)
	{
		ArrayList<Card> remove = evaluateHand;
		ArrayList<Integer> intToRemove = new ArrayList<Integer>();

		for(int i = 0; i < evaluateHand.size()-1; i++)
		{
			for(int j = i+1; j < evaluateHand.size(); j++)
			{
				if(evaluateHand.get(i).getRank()
						==evaluateHand.get(j).getRank())
				{
					intToRemove.add(i);

					intToRemove.add(j);
				}
			}

		}

		int x = intToRemove.get(0);

		int y = intToRemove.get(1)-1;

		remove.remove(x);

		remove.remove(y);

		return remove;
	}

	/**
	 * gets value of the payout for the hand
	 *
	 * @param  h: copy of hand that player has
	 * @return  payout: int # of the value of hand
	 */
	public int getHandPayout(ArrayList<Card> h)
	{

		if(handName.equals("Royal Flush"))
		{
			payout = 250;
		}
		else if(handName.equals("Straight Flush"))
		{
			payout = 50;
		}
		else if(handName.equals("Four of a Kind"))
		{
			payout = 25;
		}
		else if(handName.equals("Full House"))
		{
			payout = 6;
		}
		else if(handName.equals("Flush"))
		{
			payout = 5;
		}
		else if(handName.equals("Straight"))
		{
			payout = 4;
		}
		else if(handName.equals("Three of a Kind"))
		{
			payout = 3;
		}
		else if(handName.equals("Two Pairs"))
		{
			payout = 2;
		}
		else if(handName.equals("One Pair"))
		{
			payout = 1;
		}
		else if(handName.equals("No Pair"))
		{
			payout = -1;
		}
		return payout;
	}

	/**
	 * resets the game
	 */
	public void reset()
	{
		cards.reset();

		p.reset();
	}

}
