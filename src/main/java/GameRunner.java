package main.java;

import javax.swing.*;
import java.util.*;

/*
    Class handles the main legwork of running the game + keeps track of progress,
    i.e keeping track of cards in deck+hand+opponent, points ...
 */
public class GameRunner {
    public static final Scanner scanner = new Scanner(System.in);
    private Deck deck;
    private Hand playerHand;
    private Opponent computerAdversary;
    private int points;
    private ReplayHandler replayHandler;
    private HighScoreHandler highscoreHandler;

    public GameRunner(){
        deck = new Deck();
        playerHand = deck.dealNewHand();
        computerAdversary = new Opponent();
        computerAdversary.setOpponentCard(deck.pickCardFromTop());
        points = 0;
        highscoreHandler = new HighScoreHandler();
        replayHandler = new ReplayHandler();
        replayHandler.wipeReplayFile();
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


    public void runGameAsConsoleApp(){

        /*
            The while loop here is the basic game loop, i.e it breaks when it is "game over"
         */
        while(true){

            int cardChoice = 0;
            Card cardChosen = new Card();
            System.out.println("------------------------------------------------------------------------");
            System.out.println("Current score: " + points);
            replayHandler.writeLineToReplayFile("Current score: " + points);
            replayHandler.writeLineToReplayFile("Deck cards left: " + deck.getCardsDeck().size());
            replayHandler.writeLineToReplayFile("Your hand: " + playerHand);
            replayHandler.writeLineToReplayFile("Computer's card: " + computerAdversary.getOpponentCard());
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
                        replayHandler.writeLineToReplayFile("Hand sorted by value");
                        replayHandler.writeLineToReplayFile("New hand: " + playerHand);
                        break;
                    case(3):
                        playerHand.sortHandBySuit();
                        replayHandler.writeLineToReplayFile("Hand sorted by suit");
                        replayHandler.writeLineToReplayFile("New hand: " + playerHand);
                        break;
                    default:
                        System.out.println("Wrong input buddy, go again");
                }
                if(menuChoice==1) break;
            }  // end of card select/sort loop

            // By this stage, the user has selected which card to play
            replayHandler.writeLineToReplayFile("Card chosen: " + cardChosen);

            if(cardChosen.getValue() + computerAdversary.getOpponentCard().getValue() == 11){
                System.out.println("You made Eleven with " + cardChosen + " + " + computerAdversary.getOpponentCard());
                replayHandler.writeLineToReplayFile(
                        "You made Eleven with " + cardChosen + " + " + computerAdversary.getOpponentCard());
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
                    if(picCardPicked.isPictureCard()){
                        playerHand.removeCardFromHand(picCardPicked);
                        System.out.println("Picture card " + picCardPicked + " removed from hand");
                        replayHandler.writeLineToReplayFile(
                                "Picture card " + picCardPicked + " removed from hand");

                    }
                    else{
                        System.out.println("That's not a picture card, nice try >:P");
                    }


                }//end of picture card discard loop

            }
            else if(cardChosen.getSuit().equals(computerAdversary.getOpponentCard().getSuit())){
                System.out.println("You didn't make Eleven, but at least it was the same suit so we carry on...");
                replayHandler.writeLineToReplayFile(
                        "You didn't make Eleven, but at least it was the same suit so we carry on...");
            }
            else{
                System.out.println("You didn't make Eleven, and the suit didn't match. Therefore it is game over");
                replayHandler.writeLineToReplayFile(
                        "You didn't make Eleven, and the suit didn't match. Therefore it is game over");
                break;
            }




            while(playerHand.getCards().size() != 5){
                playerHand.addCardToHand(deck.pickCardFromTop());
            }

            if(deck.getCardsDeck().isEmpty()){
                System.out.println("Bloody hell, the deck is out of cards, you did well staying in that long!");
                replayHandler.writeLineToReplayFile(
                        "Bloody hell, the deck is out of cards, you did well staying in that long!");
                break;
            }  // No sense ending the game when we have an empty deck BUT have a card everywhere else it needs to be

            computerAdversary.setOpponentCard(deck.pickCardFromTop());
            replayHandler.writeLineToReplayFile(
                    "------------------------------------------------------------------------");

        }//end of main game loop

        System.out.println("------------------------------------------------------------------------");
        System.out.println("ggwp, game ended with: " + points + " points.");
        replayHandler.writeLineToReplayFile(
                "------------------------------------------------------------------------");
        replayHandler.writeLineToReplayFile(
                "ggwp, game ended with: " + points + " points.");

        highscoreHandler.checkForHighscore(points, false);
        highscoreHandler.writeHighscoreMapToFile();

        System.out.println("Would you like to view the highscore table? (\"1\" for yes,\"2\" for no)");
        int highscoreViewChoice = inputInteger(1,2);
        if(highscoreViewChoice == 1){
            highscoreHandler.printConsoleHighscoreTable();
        }

        System.out.println("Would you like to view a replay of that game? (\"1\" for yes,\"2\" for no)");
        int replayViewChoice = inputInteger(1,2);
        if(replayViewChoice == 1){
            replayHandler.playConsoleReplay();
        }

    }

    public int inputInteger(){
        /*
            Scanner input validator method for integers
         */

        while(true){
            try{
                return Integer.parseInt(scanner.nextLine());
            }
            catch(Exception e){
                System.out.println("Not an integer! try again:");
            }
        }
    }

    public int inputInteger(int min, int max){
        /*
            inputInteger but with boundaries (min and max are inclusive)
         */

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


    //-----------------------------------------GUI version methods--------------------------------------//
    public void writeGameStatusToReplayFile(){
        replayHandler.writeLineToReplayFile("Current score: " + points);
        replayHandler.writeLineToReplayFile("Deck cards left: " + deck.getCardsDeck().size());
        replayHandler.writeLineToReplayFile("Your hand: " + playerHand);
        replayHandler.writeLineToReplayFile("Computer's card: " + computerAdversary.getOpponentCard());
    }

    public boolean checkSelectedCard(Card card){

        replayHandler.writeLineToReplayFile("Card chosen: " + card);

        if(card.getValue() + computerAdversary.getOpponentCard().getValue() == 11){
            points++;
            replayHandler.writeLineToReplayFile(
                    "You made Eleven with " + card + " + " + computerAdversary.getOpponentCard());

            // Check to see if a picture card is in the hand, then ask if user wants it to be replaced
            for(int i = 0; i<playerHand.getCards().size(); i++){
                if(playerHand.getCards().get(i).isPictureCard()){
                    int removePicCards = JOptionPane.showConfirmDialog(null,
                            "Would you like to replace all picture cards?",null, JOptionPane.YES_NO_OPTION);

                    if(removePicCards == 0){  // yes -> 0
                        for(int j = playerHand.getCards().size()-1; j>=0; j--){
                            if(playerHand.getCards().get(j).isPictureCard()){
                                replayHandler.writeLineToReplayFile(
                                        "Picture card " + playerHand.getCards().get(i) + " removed from hand");
                                removeCardFromHand(playerHand.getCards().get(i));
                            }
                        }
                    }
                    break;
                }
            }

        }
        else if(card.getSuit().equals(computerAdversary.getOpponentCard().getSuit())){
            replayHandler.writeLineToReplayFile(
                    "You didn't make Eleven, but at least it was the same suit so we carry on...");
        }
        else{
            replayHandler.writeLineToReplayFile(
                    "You didn't make Eleven, and the suit didn't match. Therefore it is game over");
            replayHandler.writeLineToReplayFile(
                    "ggwp, game ended with: " + points + " points.");

            guiCheckForHighscore();
            highscoreHandler.writeHighscoreMapToFile();
            return false;
        }

        replayHandler.writeLineToReplayFile(
                "------------------------------------------------------------------------");

        // Repopulate hand
        while(playerHand.getCards().size() != 5){
            playerHand.addCardToHand(deck.pickCardFromTop());
        }

        if(deck.getCardsDeck().isEmpty()){
            replayHandler.writeLineToReplayFile(
                    "Bloody hell, the deck is out of cards, you did well staying in that long!");
            replayHandler.writeLineToReplayFile(
                    "ggwp, game ended with: " + points + " points.");

            guiCheckForHighscore();
            highscoreHandler.writeHighscoreMapToFile();
            return false;
        }  // No sense ending the game when we have an empty deck BUT have a card everywhere else it needs to be

        computerAdversary.setOpponentCard(deck.pickCardFromTop());

        replayHandler.writeLineToReplayFile("Current score: " + points);
        replayHandler.writeLineToReplayFile("Deck cards left: " + deck.getCardsDeck().size());
        replayHandler.writeLineToReplayFile("Your hand: " + playerHand);
        replayHandler.writeLineToReplayFile("Computer's card: " + computerAdversary.getOpponentCard());

        return true;
    }

    public void sortHandBySuit(){
        playerHand.sortHandBySuit();
        replayHandler.writeLineToReplayFile("Hand sorted by suit");
        replayHandler.writeLineToReplayFile("New hand: " + playerHand);
    }
    public void sortHandByValue(){
        playerHand.sortHandByValue();
        replayHandler.writeLineToReplayFile("Hand sorted by value");
        replayHandler.writeLineToReplayFile("New hand: " + playerHand);
    }
    public void removeCardFromHand(Card card) {
        playerHand.removeCardFromHand(card);
    }

    public void showGuiReplay(){
        replayHandler.playGuiReplay();
    }
    public void showGuiHighscoreTable(){
        highscoreHandler.printGuiHighscoreTable();
    }

    public void guiCheckForHighscore(){
        highscoreHandler.checkForHighscore(points, true);
    }

}