package main.java;

public class MakeEleven {
    public static void main(String[] args) {
        MakeEleven makeEleven = new MakeEleven();

        makeEleven.runAsJavaxSwingGUI();

        //makeEleven.runConsoleGame();
    }

    private void runConsoleGame(){
        // Run game with CLI
        GameRunner gameRunner = new GameRunner();
        gameRunner.runGameAsConsoleApp();
    }

    private void runAsJavaxSwingGUI(){
        // Run game in a JFrame
        GameRunner gameRunner = new GameRunner();
        GameFrame gf = new GameFrame(gameRunner);
    }


}
