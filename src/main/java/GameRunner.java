package main.java;

import java.util.Scanner;

/*
    Class handles the main legwork of running the game + keeps track of progress,
    i.e keeping track of cards in deck+hand+opponent, points ...
 */
public class GameRunner {
    private static final Scanner sc = new Scanner(System.in);
    private Deck deck;
    private Hand playerHand;
    private Opponent computerAdversary;
    private int points;

    public GameRunner(){
        deck = new Deck();
        playerHand = deck.dealNewHand();
        computerAdversary = new Opponent();
        computerAdversary.setOpponentCard(deck.pickCardFromTop());
        points = 0;
    }

    public Deck getDeck() {
        return deck;
    }

    public Hand getPlayerHand() {
        return playerHand;
    }

    public Card getOpponentCard(){
        return computerAdversary.getOpponentCard();
    }

    public int getPoints(){
        return points;
    }

    public void runGame(){

        /*
            The while loop here is the basic game loop, i.e it breaks when it is "game over"
         */
        while(true){

            int cardChoice = 0;
            Card cardChosen = new Card();
            System.out.println("------------------------------------------------------------------------");
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

                System.out.println("\nYou may also discard any picture cards you have");
                int picCardChoice = -1;
                while(true){
                    System.out.println("Select a card to discard, or enter \"0\" to continue");
                    System.out.println("Hand = " + playerHand);
                    picCardChoice = inputInteger(0, playerHand.getCards().size());

                    if(picCardChoice == 0){
                        break;
                    }

                    Card picCardPicked = playerHand.getCards().get(picCardChoice-1);
                    if(picCardPicked.getValue()==10 && !picCardPicked.getRank().equals("10")){
                        playerHand.removeCardFromHand(picCardPicked);
                        System.out.println(picCardPicked + " removed from hand");
                    }
                    else{
                        System.out.println("That's not a picture card, nice try >:P");
                    }


                }//end of picture card discard loop

            }
            else if(cardChosen.getSuit().equals(computerAdversary.getOpponentCard().getSuit())){
                System.out.println("You didn't make Eleven, but at least it was the same suit so we carry on...");
            }
            else break;




            while(playerHand.getCards().size() != 5){
                playerHand.addCardToHand(deck.pickCardFromTop());
            }

            if(deck.getCardsDeck().size()==0){
                System.out.println("Bloody hell, the deck is out of cards, you did well staying in that long!");
                break;
            }  // No sense ending the game when we have an empty deck BUT have a card everywhere else it needs to be

            computerAdversary.setOpponentCard(deck.pickCardFromTop());
        }//end of main game loop


        System.out.println("gg ez, game ended with: " + points + " points.");



    }




    public void sortHandBySuit(){
        playerHand.sortHandBySuit();
    }
    public void sortHandByValue(){
        playerHand.sortHandByValue();
    }
    public void removeCardFromHand(Card card) {playerHand.removeCardFromHand(card);}



    public boolean checkSelectedCard(Card card){
        if(card.getValue() + computerAdversary.getOpponentCard().getValue() == 11){
            points++;

            System.out.println("\nYou may also discard any picture cards you have");
            /*
            int picCardChoice = -1;
            while(true){
                System.out.println("Select a card to discard, or enter \"0\" to continue");
                System.out.println("Hand = " + playerHand);
                picCardChoice = inputInteger(0, playerHand.getCards().size());

                if(picCardChoice == 0){
                    break;
                }

                Card picCardPicked = playerHand.getCards().get(picCardChoice-1);
                if(picCardPicked.getValue()==10 && !picCardPicked.getRank().equals("10")){
                    playerHand.removeCardFromHand(picCardPicked);
                    System.out.println(picCardPicked + " removed from hand");
                }
                else{
                    System.out.println("That's not a picture card, nice try >:P");
                }


            }//end of picture card discard loop

             */

        }
        else if(card.getSuit().equals(computerAdversary.getOpponentCard().getSuit())){
            // Maybe add some text pop-up for suit match
        }
        else{
            return false;
        }


        while(playerHand.getCards().size() != 5){
            playerHand.addCardToHand(deck.pickCardFromTop());
        }

        if(deck.getCardsDeck().size()==0){
            System.out.println("Bloody hell, the deck is out of cards, you did well staying in that long!");
            return false;
        }  // No sense ending the game when we have an empty deck BUT have a card everywhere else it needs to be

        computerAdversary.setOpponentCard(deck.pickCardFromTop());
        return true;
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