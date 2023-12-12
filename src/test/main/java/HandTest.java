package main.java;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HandTest {

    @Test
    void addCardToHand() {
        Hand hand = new Hand();
        assertTrue(hand.getCards().size() == 0);
        hand.addCardToHand(new Card("♣", "9", 9));
        hand.addCardToHand(new Card("♣", "9", 9));
        hand.addCardToHand(new Card("♣", "9", 9));
        assertTrue(hand.getCards().size() == 3);
        hand.addCardToHand(new Card("♣", "9", 9));
        hand.addCardToHand(new Card("♣", "9", 9));
        assertTrue(hand.getCards().size() == 5);

        hand.addCardToHand(new Card("♣", "9", 9));
        assertTrue(hand.getCards().size() == 5);
    }

    @Test
    void removeCardFromHand() {
        Hand hand = new Hand();
        Card nine = new Card("♣", "9", 9);
        Card ace = new Card("♠", "A", 1);
        Card jack = new Card("♠", "J", 10);
        Card king = new Card("♣", "K", 10);
        Card ten = new Card("♦", "10", 10);
        assertTrue(hand.getCards().size() == 0);
        hand.addCardToHand(nine);
        hand.addCardToHand(ace);
        hand.addCardToHand(jack);
        hand.addCardToHand(king);
        hand.addCardToHand(ten);
        assertTrue(hand.getCards().size() == 5);

        hand.removeCardFromHand(ace);
        assertTrue(hand.getCards().size() == 4);
    }

    @Test
    void sortHandByValue() {
        //String[] ALL_SUITS = {"♣", "♦", "♥", "♠"};
        Hand hand = new Hand();
        Card nine = new Card("♣", "9", 9);
        Card ace = new Card("♠", "A", 1);
        Card jack = new Card("♠", "J", 10);
        Card king = new Card("♣", "K", 10);
        Card ten = new Card("♦", "10", 10);
        hand.addCardToHand(nine);
        hand.addCardToHand(ace);
        hand.addCardToHand(jack);
        hand.addCardToHand(king);
        hand.addCardToHand(ten);

        hand.sortHandByValue();

        assertTrue(hand.getCards().get(0).equals(ace));
        assertTrue(hand.getCards().get(1).equals(nine));
        assertTrue(hand.getCards().get(2).equals(ten));
        assertTrue(hand.getCards().get(3).equals(jack));
        assertTrue(hand.getCards().get(4).equals(king));
    }

    @Test
    void sortHandBySuit() {
        //String[] ALL_SUITS = {"♣", "♦", "♥", "♠"};
        Hand hand = new Hand();
        Card nineClub = new Card("♣", "9", 9);
        Card aceSpade = new Card("♠", "A", 1);
        Card jackSpade = new Card("♠", "J", 10);
        Card kingClub = new Card("♣", "K", 10);
        Card tenDiamond = new Card("♦", "10", 10);

        hand.addCardToHand(nineClub);
        hand.addCardToHand(aceSpade);
        hand.addCardToHand(jackSpade);
        hand.addCardToHand(kingClub);
        hand.addCardToHand(tenDiamond);

        hand.sortHandBySuit();

        assertTrue(hand.getCards().get(0).equals(nineClub));
        assertTrue(hand.getCards().get(1).equals(kingClub));
        assertTrue(hand.getCards().get(2).equals(tenDiamond));
        assertTrue(hand.getCards().get(3).equals(aceSpade));
        assertTrue(hand.getCards().get(4).equals(jackSpade));
    }
}