package main.java;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    @Test
    void isPictureCard() {
        Card nine = new Card("", "9", 9);
        Card ace = new Card("", "A", 1);
        Card jack = new Card("", "J", 10);
        Card king = new Card("", "K", 10);
        Card ten = new Card("", "10", 10);

        assertFalse(nine.isPictureCard());
        assertFalse(ace.isPictureCard());
        assertTrue(jack.isPictureCard());
        assertTrue(king.isPictureCard());
        assertFalse(ten.isPictureCard());
    }
}