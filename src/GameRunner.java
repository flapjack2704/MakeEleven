import java.util.Scanner;

/**
 * GameRunner
 */
public class GameRunner {
    private static final Scanner sc = new Scanner(System.in);


    public void runGame(){
        Deck deck = new Deck();

        deck.shuffleDeck();
        Hand playerHand = deck.dealNewHand();
        Opponent computerAdversary = new Opponent();
        int points = 0;

        computerAdversary.setOpponentCard(deck.pickCardFromTop());

        /*
            The while loop here is the basic game loop, i.e it breaks when it is "game over"
         */
        while(true){

            int cardChoice = 0;
            Card cardChosen = new Card();
            // Pick a card, or sort the hand loop
            while(true){
                System.out.println("\nDeck cards left: " + deck.getCardsDeck().size());
                System.out.println("Your hand: " + playerHand);
                System.out.println("Computer's card: " + computerAdversary.getOpponentCard());

                // Menu
                System.out.println("Enter \"1\" to play a card, \"2\" to sort hand by value, \"3\" to sort by suit");
                int menuChoice = inputInteger();
                switch (menuChoice){
                    case(1):
                        System.out.println("\nSelect a card to play against " + computerAdversary.getOpponentCard()
                                + " by entering either \"1\",\"2\",\"3\",\"4\", or \"5\":");
                        cardChoice = inputInteger(1,5);
                        cardChosen = playerHand.getCards().get(cardChoice-1);
                        playerHand.removeCardFromHand(cardChosen);
                        break;
                    case(2):
                        playerHand.sortHandByValue();
                        break;
                    case(3):
                        playerHand.sortHandBySuit();
                        break;
                    default:
                        System.out.println("Wrong input buddy, go again");
                }
                if(menuChoice==1) break;
            }  // end of card select/sort loop

            // By this stage, the user has selected which card to play

            if(cardChosen.getValue() + computerAdversary.getOpponentCard().getValue() == 11){
                System.out.println("You made Eleven with " + cardChosen + " + " + computerAdversary.getOpponentCard());
                points++;
            }
            else if(cardChosen.getSuit().equals(computerAdversary.getOpponentCard().getSuit())){
                System.out.println("You didn't make Eleven, but at least it was the same suit so we carry on...");
            }
            else break;




            for(int i = 0; i < (5-playerHand.getCards().size()); i++){
                playerHand.addCardToHand(deck.pickCardFromTop());
            }
            computerAdversary.setOpponentCard(deck.pickCardFromTop());
            if(deck.getCardsDeck().size()==0){
                System.out.println("Bloody hell, the deck is out of cards, you did well staying in that long!");
                break;
            }
        }//end of main game loop


        System.out.println("gg ez: " + points);
        


    }



    private boolean canMakeEleven(Hand hand, Card targetCard){



        return false;
    }


    public int inputInteger(){

        while(true){
            try{
                return Integer.parseInt(sc.nextLine());
            }
            catch(Exception e){
                System.out.println("Not an integer! try again:");
            }
        }
    }

    public int inputInteger(int min, int max){
        // inputInteger but with boundaries (min and max are inclusive)

        while(true){
            int out = inputInteger();
            if(out >= min && out <= max){
                return out;
            }
            else{
                System.out.println("Input wasn't within bounds " + min + " and " + max + ", try again");
            }
        }
    }
}