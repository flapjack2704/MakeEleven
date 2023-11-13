package main.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> cardsDeck = new ArrayList<>();
    public static final int[] ALL_VALUES = {1,2,3,4,5,6,7,8,9,10,10,10,10};
    public static final String[] ALL_SUITS = {"♣", "♦", "♥", "♠"};
    public static final String[] ALL_RANKS = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
    public static final ArrayList<String> ALL_SUITSLIST = new ArrayList<>(Arrays.asList(ALL_SUITS));
    public static final ArrayList<String> ALL_RANKSLIST = new ArrayList<>(Arrays.asList(ALL_RANKS));

    public Deck(){
        for(String suit : ALL_SUITS){
            for(int i = 0; i < ALL_RANKS.length; i++){
                cardsDeck.add(new Card(suit, ALL_RANKS[i], ALL_VALUES[i]));
            }
        }
    }


    public ArrayList<Card> getCardsDeck() {
        return cardsDeck;
    }

    public void setCardsDeck(ArrayList<Card> cardsDeck) {
        this.cardsDeck = cardsDeck;
    }

    public String[] getALL_SUITS() {
        return ALL_SUITS;
    }

    public String[] getALL_RANKS() {
        return ALL_RANKS;
    }

    public int[] getALL_VALUES() {
        return ALL_VALUES;
    }

    
    public Hand dealNewHand(){
        Hand hand = new Hand();
        for(int i = 0; i < 5; i++){
            Card cardToAdd = pickCardFromTop();
            hand.addCardToHand(cardToAdd);
        }
        return hand;
    }

    public Card pickCardFromTop(){
        Card cardToPick = new Card();
        try{
            cardToPick = cardsDeck.get(0);
            cardsDeck.remove(cardToPick);
        }
        catch(Exception ignoreMe){

        }

        return cardToPick;
    }

    public void shuffleDeck(){
        Collections.shuffle(cardsDeck);
    }
    
}
