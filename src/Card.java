public class Card {
    private String suit;
    private String rank;
    private int value;

    public Card(String suit, String face, int value){
        this.suit = suit;
        this.rank = face;
        this.value = value;
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
        return rank + " of " + suit;
    }
}
