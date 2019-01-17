
public class PokerTest {

    //this class must remain unchanged
    //your code must work with this test class
 
    public static void main(String[] args){
        if (args.length<1)
        {
            Game g = new Game();
            g.play();
        }
        else
        {
            Game g = new Game(args); //testing: java PokerTest s3 d5 s2 h9 s1
            //String[] cards = {"c10", "c11", "c1", "c12", "c13"};
        	//Game g = new Game(cards);
        	g.play();
        }
    }

}
