package main.java;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class OpponentPanel extends JPanel {
    private JLabel opponentLabel;
    private JLabel opponentNameLabel;
    public OpponentPanel(GameRunner gameRunner){
        this.setLayout(null);
        this.setBackground(new Color(42, 150, 4));
        this.setBounds(400, 20, 350, 240);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 7));
        this.drawOpponentLabel(gameRunner);
        this.drawOpponentNameLabel();
    }

    public void drawOpponentNameLabel(){
        opponentNameLabel = new JLabel();
        opponentNameLabel.setBounds(50, 170, 250, 50);
        opponentNameLabel.setFont(new Font("Garamond", Font.BOLD, 30));
        opponentNameLabel.setText("Opponent's card");
        opponentNameLabel.setVerticalAlignment(JLabel.CENTER);
        opponentNameLabel.setHorizontalAlignment(JLabel.CENTER);
        opponentNameLabel.setForeground(new Color(255, 255, 255));
        this.add(opponentNameLabel);
    }

    public void drawOpponentLabel(GameRunner gameRunner){
        opponentLabel = new JLabel();
        Card opponentCard = gameRunner.getOpponentCard();

        opponentLabel.setFont(new Font("Helvetica", Font.BOLD, 30));
        opponentLabel.setText(gameRunner.getOpponentCard().toString());
        if(opponentCard.getSuit().equals("♦") || opponentCard.getSuit().equals("♥")){
            opponentLabel.setForeground(new Color(255,0,0));
        }
        opponentLabel.setVerticalAlignment(JLabel.CENTER);
        opponentLabel.setHorizontalAlignment(JLabel.CENTER);

        Border blackBorder = BorderFactory.createLineBorder(Color.black, 5);
        opponentLabel.setBorder(blackBorder);
        opponentLabel.setBackground(new Color(255, 255, 255));
        opponentLabel.setOpaque(true);
        opponentLabel.setBounds(135,45,80,110);

        this.add(opponentLabel);


    }
    public void updateOpponentLabel(GameRunner gameRunner){
        if(gameRunner.getOpponentCard() == null) return;
        this.removeAll();
        Card opponentCard = gameRunner.getOpponentCard();
        opponentLabel.setText(gameRunner.getOpponentCard().toString());
        if(opponentCard.getSuit().equals("♦") || opponentCard.getSuit().equals("♥")){
            opponentLabel.setForeground(new Color(255,0,0));
        }
        else{
            opponentLabel.setForeground(new Color(0,0,0));
        }
        this.add(opponentLabel);

        this.drawOpponentNameLabel();
        this.revalidate();
        this.repaint();
    }

}
