package main.java;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {

    @Test
    void dealNewHand() {
        Deck deck = new Deck();
        Hand hand = deck.dealNewHand();
        assertTrue(hand.getCards().size() == 5);
    }

    @Test
    void pickCardFromTop() {
        Deck deck = new Deck();
        assertTrue(deck.getCardsDeck().size() == 52);
        deck.pickCardFromTop();
        assertTrue(deck.getCardsDeck().size() == 51);
    }

}