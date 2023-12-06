package main.java;

import javax.swing.*;
import java.util.ArrayList;

public class HandPanel extends JPanel {
    private ArrayList<CardButton> buttons = new ArrayList<>();
    JLabel handLabel;

    public HandPanel(GameRunner gameRunner){
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

    public void addButtonToArrayList(CardButton button){
        buttons.add(button);
    }

}