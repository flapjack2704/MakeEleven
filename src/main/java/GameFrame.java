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
    private JPanel handPanel;
    private JPanel opponentPanel;

    private Color defaultButtonColour = new Color(232, 232, 232);

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
        this.drawPlayerHandPanel();
        this.drawOpponentHandPanel();
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

        suitSortButton.setBackground(defaultButtonColour);
        suitSortButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                suitSortButton.setBackground(new Color(185, 184, 184));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                suitSortButton.setBackground(defaultButtonColour);
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

        valueSortButton.setBackground(defaultButtonColour);
        valueSortButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                valueSortButton.setBackground(new Color(185, 184, 184));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                valueSortButton.setBackground(defaultButtonColour);
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

    private void drawPlayerHandPanel(){
        handPanel = new JPanel();
        handPanel.setLayout(null);
        //handPanel.setBackground(Color.CYAN);
        handPanel.setOpaque(false);
        handPanel.setBounds(0, 550, 800, 200);
        this.add(handPanel);


        JLabel label = new JLabel();
        label.setBackground(new Color(88, 154, 96));
        label.setOpaque(true);  // needed to show background colour
        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);

        Border blackBorder = BorderFactory.createLineBorder(Color.black, 6);
        label.setBorder(blackBorder);
        label.setBounds(6,0,776,200);
        handPanel.add(label);


        // initialise hand buttons
        for(int i = 0; i < 5; i++){
            JButton button = new JButton();
            Card card = gameRunner.getPlayerHand().getCards().get(i);

            button.setText(card.toString());
            if(card.getSuit().equals("♦") || card.getSuit().equals("♥")){
                button.setForeground(new Color(255,0,0));
            }
            button.setFont(new Font("Helvetica", Font.BOLD, 30));
            button.setBounds((i*150)+((label.getWidth()/2) - 340), 45, 80, 110);
            button.setHorizontalAlignment(JButton.CENTER);
            button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 6));
            button.addActionListener(e ->
                    JOptionPane.showMessageDialog(null,"Card clicked: " + button.getText()));

            button.setBackground(defaultButtonColour);
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    button.setBackground(new Color(185, 184, 184));
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    button.setBackground(defaultButtonColour);
                }
            });
            button.setFocusable(false);
            button.setVisible(true);

            label.add(button);

        }
    }

    public void drawOpponentHandPanel(){
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
            handPanel.removeAll();
            this.drawPlayerHandPanel();
            handPanel.revalidate();
            handPanel.repaint();
        }

        if(e.getSource() == valueSortButton){
            gameRunner.sortHandByValue();
            handPanel.removeAll();
            this.drawPlayerHandPanel();
            handPanel.revalidate();
            handPanel.repaint();
        }
    }
}
