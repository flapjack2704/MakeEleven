package main.java;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class MakeEleven {
    public static void main(String[] args) {
        MakeEleven makeEleven = new MakeEleven();

        //makeEleven.runVisualTests();

        makeEleven.testJavaxStuff();

        //makeEleven.startGame();
    }

    private void startGame(){
        // Run game as a CLI
        GameRunner gameRunner = new GameRunner();
        gameRunner.runGame();
    }

    private void runVisualTests(){
        VisualTesting vt = new VisualTesting();
        vt.testGenerateDeckAndNewHand();
    }

    private void testJavaxStuff(){
        // Run game in a JFrame
        GameRunner gameRunner = new GameRunner();
        GameFrame gf = new GameFrame(gameRunner);

    }


}
