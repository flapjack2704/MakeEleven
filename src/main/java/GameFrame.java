package main.java;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/*
    Class draws the game screen graphics, using a GameRunner object to keep track of status changes
 */

public class GameFrame extends JFrame implements ActionListener{

    private JButton suitSortButton;
    private JButton valueSortButton;
    private GameRunner gameRunner;
    private HandPanel handPanel;
    private OpponentPanel opponentPanel;
    private JLabel infoLabel;
    private JLabel pointsLabel;
    private JLabel deckSizeLabel;

    public GameFrame(GameRunner gameRunner){
        this.gameRunner = gameRunner;
        initGameFrame(this.gameRunner);
    }


    private void initGameFrame(GameRunner gameRunner){
        /*
            Method used in gameFrame constructor to draw all initial necessary parts
         */
        this.setSize(800,800);
        this.setResizable(false);
        this.setTitle("Make Eleven");
        this.getContentPane().setBackground(new Color(6, 189, 32));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);

        this.addSortButtons();
        this.drawInfoLabel("Pick a card to make 11 with the opponent's card.<br><br>" +
                "If you make 11, you may choose to remove all picture cards from your hand.<br><br>" +
                "If you cannot make 11, you can select a card of the same suit, but you won't score a point.<br><br>" +
                "If you can't even match the suit, it is game over!");
        handPanel = new HandPanel(gameRunner);
        this.drawPlayerHandPanel();
        this.initOpponentPanel();
        this.drawPointsLabel();
        this.drawDeckSizeLabel();
        this.setVisible(true);

        gameRunner.writeGameStatusToReplayFile();
    }

    private void addSortButtons(){
        /*
            Draws sort buttons, used once in GameFrame constructor
         */

        suitSortButton = new JButton();
        suitSortButton.setBounds(26,473, 130,80);
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
        valueSortButton.setBounds(166, 473, 130, 80);
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
        /*
            Draws info text box on left-side of GameFrame
         */

        infoLabel = new JLabel();
        // https://docs.oracle.com/javase/tutorial/uiswing/components/html.html
        infoLabel.setText("<html>" + info + "</html>");  // html tags dynamically fits string inside label
        infoLabel.setBackground(new Color(113, 218, 124));
        infoLabel.setFont(new Font("Helvetica", Font.BOLD, 16));
        infoLabel.setOpaque(true);  // needed to show background colour
        infoLabel.setVerticalAlignment(JLabel.TOP);
        infoLabel.setHorizontalAlignment(JLabel.CENTER);

        Border blackBorder = BorderFactory.createLineBorder(Color.black, 4);
        infoLabel.setBorder(blackBorder);
        infoLabel.setBounds(26,95,250,310);
        this.add(infoLabel);
    }

    public void drawPlayerHandPanel(){
        /*
            Draws new hand panel, not as clean as I would like it to be
         */

        handPanel.removeAll();
        handPanel.handLabel = new JLabel();
        handPanel.handLabel.setBackground(new Color(88, 154, 96));
        handPanel.handLabel.setOpaque(true);  // needed to show background colour
        handPanel.handLabel.setVerticalAlignment(JLabel.CENTER);
        handPanel.handLabel.setHorizontalAlignment(JLabel.CENTER);

        Border blackBorder = BorderFactory.createLineBorder(Color.black, 6);
        handPanel.handLabel.setBorder(blackBorder);
        handPanel.handLabel.setBounds(16,0,756,200);
        handPanel.add(handPanel.handLabel);

        // initialise hand buttons
        for(int i = 0; i < gameRunner.getPlayerHand().getCards().size(); i++){
            Card card = gameRunner.getPlayerHand().getCards().get(i);
            CardButton button = new CardButton(card);
            button.setBounds((i*140)+((handPanel.handLabel.getWidth()/2) - 320), 45, 80, 110);

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

            button.addActionListener(e -> handCardPressed(button));
            handPanel.handLabel.add(button);
            handPanel.addButtonToArrayList(button);
        }
        this.add(handPanel);

        this.revalidate();
        this.repaint();
    }

    public void updatePlayerHandPanel(){
        /*
            Redraws hand panel without creating whole new panel and label.
            Gets current GameRunner hand order, and sorts GUI buttons to fit that order
         */

        //sort buttons
        ArrayList<CardButton> newButtonsList = new ArrayList<>();
        for(Card card : gameRunner.getPlayerHand().getCards()){
            String cardString = card.toString();
            for(CardButton button : handPanel.getButtons()){
                if(button.getCard().toString().equals(cardString)){
                    newButtonsList.add(button);
                    break;
                }
            }
        }
        handPanel.setButtons(newButtonsList);

        handPanel.handLabel.removeAll();

        //add new buttons
        ArrayList<CardButton> buttons = handPanel.getButtons();
        for(int i = 0; i < buttons.size(); i++){
            CardButton button = buttons.get(i);
            button.setBounds((i*140)+((handPanel.handLabel.getWidth()/2) - 320), 45, 80, 110);

            // Couldn't set background in CardButton constructor with "this.setBackground()", so have to have that here
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

            handPanel.handLabel.add(button);

        }
        this.add(handPanel);
        handPanel.handLabel.revalidate();
        handPanel.handLabel.repaint();
    }

    public void initOpponentPanel(){
        opponentPanel = new OpponentPanel(gameRunner);
        this.add(opponentPanel);
    }

    public void drawPointsLabel(){
        pointsLabel = new JLabel();
        pointsLabel.setText("Points: " + gameRunner.getPoints());
        pointsLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        pointsLabel.setVerticalAlignment(JLabel.CENTER);
        pointsLabel.setHorizontalAlignment(JLabel.CENTER);
        pointsLabel.setBounds(592,473,180,80);
        pointsLabel.setFont(new Font("Helvetica", Font.BOLD, 30));
        pointsLabel.setBackground(new Color(42, 150, 4));
        pointsLabel.setForeground(new Color(255, 255, 255));
        pointsLabel.setOpaque(true);

        this.add(pointsLabel);
    }

    public void drawDeckSizeLabel(){
        deckSizeLabel = new JLabel();
        deckSizeLabel.setText("Cards left in deck: " + gameRunner.getDeck().getCardsDeck().size());
        deckSizeLabel.setVerticalAlignment(JLabel.CENTER);
        deckSizeLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        deckSizeLabel.setHorizontalAlignment(JLabel.CENTER);
        deckSizeLabel.setBounds(26,20, 250, 50);
        deckSizeLabel.setFont(new Font("Helvetica", Font.BOLD, 20));
        deckSizeLabel.setBackground(new Color(42, 150, 4));
        deckSizeLabel.setForeground(new Color(255, 255, 255));
        deckSizeLabel.setOpaque(true);

        this.add(deckSizeLabel);
    }


    public void endGameFrame(){
        /*
            Draws end-game screen
         */

        this.getContentPane().removeAll();  // Removes everything attached to GameFrame
        JLabel endGameLabel = new JLabel();
        endGameLabel.setText("Game over, you scored: " + gameRunner.getPoints());
        endGameLabel.setVerticalAlignment(JLabel.CENTER);
        endGameLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        endGameLabel.setHorizontalAlignment(JLabel.CENTER);
        endGameLabel.setBounds(100,70, 600, 320);
        endGameLabel.setFont(new Font("Helvetica", Font.BOLD, 30));
        endGameLabel.setBackground(new Color(42, 150, 4));
        endGameLabel.setForeground(new Color(255, 255, 255));
        endGameLabel.setOpaque(true);

        this.add(endGameLabel);

        JButton restartGameButton = new JButton();
        restartGameButton.setText("Restart Game");
        restartGameButton.setFont(new Font("Helvetica", Font.BOLD, 17));
        restartGameButton.setOpaque(true);
        restartGameButton.setBounds(160, 450, 130, 80);
        restartGameButton.setBackground(CardButton.DEFAULT_BUTTON_COLOUR);
        restartGameButton.setFocusable(false);
        restartGameButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 6));
        restartGameButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                restartGameButton.setBackground(CardButton.HOVERED_BUTTON_COLOUR);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                restartGameButton.setBackground(CardButton.DEFAULT_BUTTON_COLOUR);
            }
        });
        this.add(restartGameButton);

        JButton showHighScoreButton = new JButton();
        showHighScoreButton.setText("High Scores");
        showHighScoreButton.setFont(new Font("Helvetica", Font.BOLD, 17));
        showHighScoreButton.setOpaque(true);
        showHighScoreButton.setBounds(335, 450, 130, 80);
        showHighScoreButton.setBackground(CardButton.DEFAULT_BUTTON_COLOUR);
        showHighScoreButton.setFocusable(false);
        showHighScoreButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 6));
        showHighScoreButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                showHighScoreButton.setBackground(CardButton.HOVERED_BUTTON_COLOUR);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                showHighScoreButton.setBackground(CardButton.DEFAULT_BUTTON_COLOUR);
            }
        });
        this.add(showHighScoreButton);

        JButton showReplayButton = new JButton();
        showReplayButton.setText("Show Replay");
        showReplayButton.setFont(new Font("Helvetica", Font.BOLD, 17));
        showReplayButton.setOpaque(true);
        showReplayButton.setBounds(510, 450, 130, 80);
        showReplayButton.setBackground(CardButton.DEFAULT_BUTTON_COLOUR);
        showReplayButton.setFocusable(false);
        showReplayButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 6));
        showReplayButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                showReplayButton.setBackground(CardButton.HOVERED_BUTTON_COLOUR);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                showReplayButton.setBackground(CardButton.DEFAULT_BUTTON_COLOUR);
            }
        });
        this.add(showReplayButton);

        restartGameButton.addActionListener(e -> restartGameButtonPressed());
        showHighScoreButton.addActionListener(e -> showHighScoreButtonPressed());
        showReplayButton.addActionListener(e -> showReplayButtonPressed());

        this.revalidate();
        this.repaint();
    }


    public void handCardPressed(CardButton button){
        /*
            Custom click ActionEvent method to be added to CardButtons as they are created
         */

        button.setEnabled(false);
        Card card = button.getCard();
        gameRunner.removeCardFromHand(card);

        // Check to see if we made eleven, or have the same suit, or if it's game over
        if(gameRunner.checkSelectedCard(card)){
            pointsLabel.setText("Points: " + gameRunner.getPoints());
            deckSizeLabel.setText("Cards left in deck: " + gameRunner.getDeck().getCardsDeck().size());

            // Darken text colour as the value decreases
            if(gameRunner.getDeck().getCardsDeck().size() <= 6){
                deckSizeLabel.setForeground(new Color(255, 16, 16));
            }
            else if(gameRunner.getDeck().getCardsDeck().size() <= 13){
                deckSizeLabel.setForeground(new Color(255, 122, 122));
            }
            else if(gameRunner.getDeck().getCardsDeck().size() <= 20){
                deckSizeLabel.setForeground(new Color(255, 176, 176));
            }

            opponentPanel.updateOpponentLabel(gameRunner);
            drawPlayerHandPanel();
        }
        else{
            endGameFrame();
        }
    }

    public void restartGameButtonPressed(){
        gameRunner = new GameRunner();
        this.getContentPane().removeAll();
        this.initGameFrame(gameRunner);
        this.revalidate();
        this.repaint();
    }
    public void showHighScoreButtonPressed(){
        gameRunner.showGuiHighscoreTable();
    }
    public void showReplayButtonPressed(){
        gameRunner.showGuiReplay();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == suitSortButton){
            gameRunner.sortHandBySuit();
            this.updatePlayerHandPanel();
        }
        else if(e.getSource() == valueSortButton){
            gameRunner.sortHandByValue();
            this.updatePlayerHandPanel();
        }
    }
}