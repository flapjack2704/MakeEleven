package main.java;

public class VisualTesting {
    


    public void testGenerateDeckAndNewHand(){
        Deck deck = new Deck();
        Hand playerHand = deck.dealNewHand();

        System.out.println("\n deck+hand constructed");
        System.out.println("DECK: " + deck.getCardsDeck());
        System.out.println("HAND: " + playerHand.getCards());

        deck.shuffleDeck();
        System.out.println("\n deck shuffled");
        System.out.println("DECK: " + deck.getCardsDeck());
        System.out.println("HAND: " + playerHand.getCards());
    }
}
