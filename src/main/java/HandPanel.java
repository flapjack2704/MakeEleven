package main.java;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

public class HandPanel extends JPanel {
    private ArrayList<CardButton> buttons = new ArrayList<>();
    private JLabel handLabel;

    public HandPanel(GameRunner gameRunner){
        this.drawPlayerHandPanel(gameRunner);
        this.setLayout(null);
        //handPanel.setBackground(Color.CYAN);
        this.setOpaque(false);
        this.setBounds(0, 550, 800, 200);
    }

    public ArrayList<CardButton> getButtons() {
        return buttons;
    }
    public void setButtons(ArrayList<CardButton> buttons) {
        this.buttons = buttons;
    }

    public void drawPlayerHandPanel(GameRunner gameRunner){
        this.removeAll();
        handLabel = new JLabel();
        handLabel.setBackground(new Color(88, 154, 96));
        handLabel.setOpaque(true);  // needed to show background colour
        handLabel.setVerticalAlignment(JLabel.CENTER);
        handLabel.setHorizontalAlignment(JLabel.CENTER);

        Border blackBorder = BorderFactory.createLineBorder(Color.black, 6);
        handLabel.setBorder(blackBorder);
        handLabel.setBounds(6,0,776,200);
        this.add(handLabel);

        // initialise hand buttons
        for(int i = 0; i < gameRunner.getPlayerHand().getCards().size(); i++){
            Card card = gameRunner.getPlayerHand().getCards().get(i);
            CardButton button = new CardButton(card);
            button.setBounds((i*150)+((handLabel.getWidth()/2) - 340), 45, 80, 110);

            // Couldn't set background in CardButton constructor with "this.setBackground()"
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    button.setBackground(CardButton.HOVERED_BUTTON_COLOUR);
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    button.setBackground(CardButton.DEFAULT_BUTTON_COLOUR);
                }
            });

            button.addActionListener(e -> HandPanel.handButtonClicked(button));
            handLabel.add(button);
            buttons.add(button);
        }
        this.revalidate();
        this.repaint();

    }

    public void updateHandLabel(GameRunner gameRunner){

        //sort buttons
        ArrayList<CardButton> newButtonsList = new ArrayList<>();
        for(Card card : gameRunner.getPlayerHand().getCards()){
            String cardString = card.toString();
            for(CardButton button : buttons){
                if(button.getCard().toString().equals(cardString)){
                    newButtonsList.add(button);
                    break;
                }
            }
        }
        buttons = newButtonsList;

        handLabel.removeAll();
        //add new buttons
        for(int i = 0; i < newButtonsList.size(); i++){
            CardButton button = buttons.get(i);
            button.setBounds((i*150)+((handLabel.getWidth()/2) - 340), 45, 80, 110);

            // Couldn't set background in CardButton constructor with "this.setBackground()"
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    button.setBackground(CardButton.HOVERED_BUTTON_COLOUR);
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    button.setBackground(CardButton.DEFAULT_BUTTON_COLOUR);
                }
            });

            handLabel.add(button);

        }
        handLabel.revalidate();
        handLabel.repaint();
    }

    public static void handButtonClicked(CardButton button){
        button.setEnabled(false);
        GameFrame.handCardPressed(button);
    }
}
