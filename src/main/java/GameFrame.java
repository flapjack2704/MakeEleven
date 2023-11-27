package main.java;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

/*
    Class draws the game graphics
 */

public class GameFrame extends JFrame implements ActionListener{

    private JButton suitSortButton;
    private JButton valueSortButton;
    private GameRunner gameRunner;
    private HandPanel handPanel;
    private JPanel opponentPanel;

    public GameFrame(GameRunner gameRunner){
        this.gameRunner = gameRunner;
        this.setSize(800,800);
        this.setResizable(false);
        this.setTitle("Make Eleven");
        this.getContentPane().setBackground(new Color(6, 189, 32));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);

        this.addSortButtons();
        this.drawInfoLabel("Pick a card to make 11 with the opponent's card");
        this.initHandPanel();
        this.drawOpponentPanel();
        this.drawPointsLabel();
        this.drawDeckSizeLabel();
        this.setVisible(true);

        this.runGame();
    }

    public void runGame(){

    }


    private void addSortButtons(){
        suitSortButton = new JButton();
        suitSortButton.setBounds(20,450, 130,80);
        suitSortButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 6));
        suitSortButton.addActionListener(this);

        suitSortButton.setBackground(CardButton.DEFAULT_BUTTON_COLOUR);
        suitSortButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                suitSortButton.setBackground(CardButton.HOVERED_BUTTON_COLOUR);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                suitSortButton.setBackground(CardButton.DEFAULT_BUTTON_COLOUR);
            }
        });
        suitSortButton.setText("Sort By Suit");
        suitSortButton.setFont(new Font("Helvetica", Font.BOLD, 17));
        suitSortButton.setFocusable(false);
        this.add(suitSortButton);

        valueSortButton = new JButton();
        valueSortButton.setBounds(160, 450, 130, 80);
        valueSortButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 6));
        valueSortButton.addActionListener(this);

        valueSortButton.setBackground(CardButton.DEFAULT_BUTTON_COLOUR);
        valueSortButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                valueSortButton.setBackground(CardButton.HOVERED_BUTTON_COLOUR);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                valueSortButton.setBackground(CardButton.DEFAULT_BUTTON_COLOUR);
            }
        });
        valueSortButton.setText("Sort By Value");
        valueSortButton.setFont(new Font("Helvetica", Font.BOLD, 17));
        valueSortButton.setFocusable(false);
        this.add(valueSortButton);
    }

    private void drawInfoLabel(String info){
        JLabel label = new JLabel();
        label.setText("<html>" + info + "</html>");  // dynamically fits string inside label
        label.setBackground(new Color(113, 218, 124));
        label.setFont(new Font("Helvetica", Font.BOLD, 16));
        label.setOpaque(true);  // needed to show background colour
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);

        Border blackBorder = BorderFactory.createLineBorder(Color.black, 6);
        label.setBorder(blackBorder);
        label.setBounds(10,80,200,300);
        this.add(label);
    }

    private void initHandPanel(){
        this.handPanel = new HandPanel(gameRunner);
        this.add(handPanel);
    }

    private void updatePlayerHandPanel(){
        handPanel.updateHandLabel(gameRunner);
    }

    public void drawOpponentPanel(){
        opponentPanel = new JPanel();
        opponentPanel.setLayout(null);
        opponentPanel.setBackground(new Color(42, 150, 4));
        opponentPanel.setBounds(450, 20, 300, 200);
        opponentPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 7));
        this.add(opponentPanel);

        JLabel opponentCardLabel = new JLabel();
        Card opponentCard = gameRunner.getOpponentCard();

        opponentCardLabel.setFont(new Font("Helvetica", Font.BOLD, 30));
        opponentCardLabel.setText(gameRunner.getOpponentCard().toString());
        if(opponentCard.getSuit().equals("♦") || opponentCard.getSuit().equals("♥")){
            opponentCardLabel.setForeground(new Color(255,0,0));
        }
        opponentCardLabel.setVerticalAlignment(JLabel.CENTER);
        opponentCardLabel.setHorizontalAlignment(JLabel.CENTER);

        Border blackBorder = BorderFactory.createLineBorder(Color.black, 5);
        opponentCardLabel.setBorder(blackBorder);
        opponentCardLabel.setBackground(new Color(239, 239, 239));
        opponentCardLabel.setOpaque(true);
        opponentCardLabel.setBounds(50,45,80,110);

        opponentPanel.add(opponentCardLabel);

    }

    public void drawPointsLabel(){
        JLabel pointsLabel = new JLabel();
        String points = String.valueOf(gameRunner.getPoints());  // Wouldn't direct cast to string -> needed valueOf()
        pointsLabel.setText("Points: " + points);
        pointsLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        pointsLabel.setVerticalAlignment(JLabel.CENTER);
        pointsLabel.setHorizontalAlignment(JLabel.CENTER);
        pointsLabel.setBounds(600,450,180,80);
        pointsLabel.setFont(new Font("Helvetica", Font.BOLD, 30));
        pointsLabel.setBackground(new Color(42, 150, 4));
        pointsLabel.setForeground(new Color(253, 104, 104));
        pointsLabel.setOpaque(true);

        this.add(pointsLabel);
    }

    public void drawDeckSizeLabel(){
        JLabel deckSizeLabel = new JLabel();
        String deckSize = String.valueOf(gameRunner.getDeck().getCardsDeck().size());
        deckSizeLabel.setText("Cards in deck: " + deckSize);
        deckSizeLabel.setVerticalAlignment(JLabel.CENTER);
        deckSizeLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        deckSizeLabel.setHorizontalAlignment(JLabel.CENTER);
        deckSizeLabel.setBounds(10,10, 200, 50);
        deckSizeLabel.setFont(new Font("Helvetica", Font.BOLD, 20));
        deckSizeLabel.setBackground(new Color(42, 150, 4));
        deckSizeLabel.setForeground(new Color(253, 104, 104));
        deckSizeLabel.setOpaque(true);

        this.add(deckSizeLabel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == suitSortButton){
            gameRunner.sortHandBySuit();
            /*handPanel.removeAll();
            this.initHandPanel();
            handPanel.revalidate();
            handPanel.repaint();

             */
            this.updatePlayerHandPanel();
        }

        if(e.getSource() == valueSortButton){
            gameRunner.sortHandByValue();
            /*
            handPanel.removeAll();
            this.initHandPanel();
            handPanel.revalidate();
            handPanel.repaint();

             */
            this.updatePlayerHandPanel();
        }
    }
}
