package main.java;

import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> cards = new ArrayList<>();

    public Hand(){
        
    }

    public Hand(ArrayList<Card> cards){
        this.cards = cards;
    }


    public ArrayList<Card> getCards() {
        return cards;
    }
    public void setCards(ArrayList<Card> cardsHand) {
        this.cards = cardsHand;
    }


    public boolean addCardToHand(Card card){
        if(cards.size()<5){
            cards.add(card);
            return true;
        }
        else return false;
    }

    public boolean removeCardFromHand(Card card){
        if(cards.contains(card)){
            cards.remove(card);
            return true;
        }
        else return false;
    }

    public void sortHandByValue(){
        // Bubble sort 08/11/23
        for(int i = 0; i < cards.size(); i++){
            boolean[] flags = new boolean[4];  // if all gets filled as true, break loop

            for(int k = 0; k < 4; k++){
                if(Deck.ALL_RANKSLIST.indexOf(cards.get(k).getRank()) 
                  > Deck.ALL_RANKSLIST.indexOf(cards.get(k+1).getRank())){
                    Card temp = cards.get(k);
                    cards.set(k, cards.get(k+1));
                    cards.set(k+1, temp);
                }
                else{
                    flags[k] = true;
                }
            }

            if(flags[0]&&flags[1]&&flags[2]&&flags[3]) break;
        }
    }
    public void sortHandBySuit(){
        sortHandByValue();
        // Bubble sort 08/11/23
        for(int i = 0; i < cards.size(); i++){
            boolean[] flags = new boolean[4];  // if all gets filled as true, break loop

            for(int k = 0; k < 4; k++){
                if(Deck.ALL_SUITSLIST.indexOf(cards.get(k).getSuit()) 
                  > Deck.ALL_SUITSLIST.indexOf(cards.get(k+1).getSuit())){
                    Card temp = cards.get(k);
                    cards.set(k, cards.get(k+1));
                    cards.set(k+1, temp);
                }
                else{
                    flags[k] = true;
                }
            }

            if(flags[0]&&flags[1]&&flags[2]&&flags[3]) break;
        }
    }

    public String toString(){
        String out = "";
        for(int i = 0; i<cards.size(); i++){
            out += "(" +(i+1)+ ") " + cards.get(i) + ", ";
        }
        out = out.substring(0,out.length()-2);  // removes the last comma
        return out;
    }
}
