package main.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CardButton extends JButton {
    public static final Color DEFAULT_BUTTON_COLOUR = new Color(255, 255, 255);
    public static final Color HOVERED_BUTTON_COLOUR = new Color(197, 197, 197);

    private Card card;

    public CardButton(Card card){
        this.card = card;
        this.setText(card.toString());
        if(card.getSuit().equals("♦") || card.getSuit().equals("♥")){
            this.setForeground(new Color(255,0,0));
        }
        this.setFont(new Font("Helvetica", Font.BOLD, 30));
        this.setHorizontalAlignment(JButton.CENTER);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 6));

        this.setBackground(CardButton.DEFAULT_BUTTON_COLOUR);
        this.setFocusable(false);
        this.setVisible(true);
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

}
