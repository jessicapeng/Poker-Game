# Poker Game
A game of Poker between the user and the computer

Task: 

In this assignment,you will implement a simulation of a popular casino game usually called video poker. The card deck contains 52 cards, 13 of each suit. At the beginning of the game, the deck is shuffled. You need to devise a fair method for shuffling. (It does not have to be efficient.) The player pays a token for each game. Then the top five cards of the deck are presented to the player. The player can reject none, some, or all of the cards. The rejected cards are replaced from the top of the deck. Now the hand is scored. Your program should pronounce it to be one of the following:

• Nopair— The lowest hand,containing five separate cards that do not matchup to create any of the hands below.

• Onepair— Two cards of thesamevalue,for example two queens. Payout:1

• Twopairs— Two pairs,forexampletwoqueensandtwo5’s. Payout:2

• Threeofakind— Three cards of thesame value,for example three queens. Payout: 3

• Straight— Fivecardswithconsecutivevalues,notnecessarilyofthesamesuit, such as 4, 5, 6, 7, and 8. The ace can either precede a 2 or follow a king. Payout: 4

• Flush— Fivecards,notnecessarilyinorder,ofthesamesuit. Payout:5

• FullHouse— Threeofakindandapair,forexamplethreequeensandtwo5’s. Payout: 6

• FourofaKind— Fourcardsofthesamevalue,suchasfourqueens. Payout:25

• StraightFlush— Astraightandaflush:Fivecardswithconsecutivevaluesof the same suit. Payout: 50

• RoyalFlush— Thebestpossiblehandinpoker. A10,jack,queen,king,andace, all of the same suit. Payout: 250

|| 7.9: Poker Game ||

To code the Poker Game, I had to code 4 different classes: Game, Player, Deck, and Card. There is also a 5th tester class to test the Game either with manual input with the deck or for the user to actually play the game. 

Card Class

The card class represents a card object in the game and contains the suit of the card, the rank of the card, the respective suit & rank names, and the name of the entire card (ex. “Ace of Hearts”). When the Card object is created, the suits and ranks are set, and immediately in the constructor the methods to get the suitName, rankName, and cardName are called as well so that all the instance variables in Card are initialized and set after the constructor is called. I also wrote a separate override compareTo method in card that compares cards based on their suits and ranks so it is convenient to sort the cards for the player or when evaluating. The methods—suitName, getSuitName, getSuit, rankName, getRankName, getRank, cardName—are all used either to set the name of the element for code or get it. Finally, the toString method in card returns the entire name of the card.

Deck Class

The deck class contains an array of cards that represents a deck of 52 cards and an integer top that points at the position/index of the “top” of the deck which used while dealing cards. In the constructor of the deck, 52 cards are created with the according suit & ranks (a nested for loop with indexes up to 4 and up to 13) and the top is set to 0. In the shuffle method for deck, I shuffled the cards by first inserting them into an array list and then using the Collections.shuffle() method to shuffle the cards. After shuffling the cards, I set top to 0 again. In the deal method of the deck, the card that is on the top is returned and then the index of top is incremented. If the card is at the end of the deck, I reset the deck which will reshuffle the deck and set the top back to 0. There is also the getTop method that returns the index of the top of the deck and the toString that prints out the deck by returning a string with all the cards in deck.

Player Class

The player class contains the hand and bankroll for the user. In the constructor of the player class, I set bankroll to 100 which is the starting amount that the player has to bet. The player also contains methods to sort the hand, add/remove cards, remove/add cards at certain indexes, add/subtract from bankroll, get bankroll/getHand. Lastly, player has a reset method that resets the hand by removing all of its Cards.

Game Class

The game class is essentially the brain of the game where the play method of the game runs. The game method contains instance variables player, deck, tokenBet (how much tokens the player bets for their original hand), redraw array list (for when the player wants to redraw), handName (name of player’s hand), payout (payout of user’s hand), and playAgain (a String to see whether the player wants to keep playing or n). The game class contains 2 constructors: the first constructor is used to test the game and the 2nd constructor is for the user to actually play the game. In the testing constructor, the args String is taken (in the form “s1 d2 c3 d4 s5”) and parsed to create the hand. In the second constructor, all the instance variables in the class are initialized, but no argument/parameter is taken. Since both constructors are meant to call play, in order to check whether it was the testing constructor that called play or the user interactive constructor, in the constructors I set a boolean of true/false that checked whether it was the test or not. If it was the test, I would only evaluate the hand and give users their payout. If it was the constructor meant to interact with the user and play, I wrote a separate set of instructors: First, the user was welcomed to the game of Poker and given instructions. They are asked to place a bet and then their hand is presented to them. Then, they are asked if they want to redraw all their cards, redraw none, or redraw some. They can change some of their cards by entering the index of their cards and then ‘done’ (Ex. to change some they might enter something like 1 2 done’). After the redraw, the deck is evaluated and if they received a good hand (as long as they got anything but no pair) then the would receive the payout of the hand times the number of tokens they bet. If they did not receive anything, they would lose their tokens. As long as their bankroll is positive, they are allowed to keep playing, so they will be asked if they want to continue playing. If they say yes, the game will continue with their bankroll and repeat the process described earlier. If they say no, the game will end and give user their bankroll. And that's the game of Poker.

(There is 1 small bug with the scanner in this code, considering changing everything to BufferedReader)
