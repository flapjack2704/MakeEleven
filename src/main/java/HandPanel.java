package main.java;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

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