package main.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CardButton extends JButton {
    public static final Color DEFAULT_BUTTON_COLOUR = new Color(232, 232, 232);
    public static final Color HOVERED_BUTTON_COLOUR = new Color(185, 184, 184);
    public Card card;

    public CardButton(Card card){
        this.card = card;
        this.setText(card.toString());
        if(card.getSuit().equals("♦") || card.getSuit().equals("♥")){
            this.setForeground(new Color(255,0,0));
        }
        this.setFont(new Font("Helvetica", Font.BOLD, 30));
        this.setHorizontalAlignment(JButton.CENTER);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 6));
        this.addActionListener(e -> HandPanel.handButtonClicked(this));

        this.setBackground(CardButton.DEFAULT_BUTTON_COLOUR);
        this.setFocusable(false);
        this.setVisible(true);
    }

}
