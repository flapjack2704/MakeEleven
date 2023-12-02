package main.java;

public class MakeEleven {
    public static void main(String[] args) {
        MakeEleven makeEleven = new MakeEleven();

        //makeEleven.runVisualTests();

        makeEleven.runAsJavaxSwingGUI();

        //makeEleven.startGame();
    }

    private void startGame(){
        // Run game as a CLI
        GameRunner gameRunner = new GameRunner();
        gameRunner.runGameAsConsoleApp();
    }

    private void runVisualTests(){
        VisualTesting vt = new VisualTesting();
        vt.testGenerateDeckAndNewHand();
    }

    private void runAsJavaxSwingGUI(){
        // Run game in a JFrame
        GameRunner gameRunner = new GameRunner();
        GameFrame gf = new GameFrame(gameRunner);
    }


}
