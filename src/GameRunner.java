/**
 * GameRunner
 */
public class GameRunner {


    public void runGame(){
        Deck deck = new Deck();
        deck.shuffleDeck();
        Hand playerHand = deck.generateNewHand();
        Opponent computerAdversary = new Opponent();

        System.out.println(playerHand);
        playerHand.sortHandByValue();
        System.out.println(playerHand);
        playerHand.sortHandBySuit();
        System.out.println(playerHand);


        


    }
}