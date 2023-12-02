package main.java;

public class Card {
    private String suit;
    private String rank;
    private int value;
    private boolean isPictureCard;

    public Card(String suit, String face, int value){
        this.suit = suit;
        this.rank = face;
        this.value = value;
        this.isPictureCard = value==10 && !face.equals("10");
    }

    public boolean isPictureCard(){
        return isPictureCard;
    }

    public Card(){

    }


    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }



    
    public String toString(){
        return rank + suit;
    }
}
