import java.util.ArrayList;

public class MakeEleven {
    public static void main(String[] args) {
        MakeEleven makeEleven = new MakeEleven();

        //makeEleven.runVisualTests();
        
        makeEleven.startGame();
    }

    private void startGame(){
        GameRunner gameRunner = new GameRunner();
        gameRunner.runGame();
    }

    private void runVisualTests(){
        VisualTesting vt = new VisualTesting();
        vt.testGenerateDeckAndNewHand();
    }


}
