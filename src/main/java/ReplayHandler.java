package main.java;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class ReplayHandler {
    private final File replayFile;
    private PrintWriter nonAppendingWriter;
    private PrintWriter appendingWriter;

    public ReplayHandler(){
        replayFile = new File("src/data/replayFile.txt");
        try{
            appendingWriter = new PrintWriter(new BufferedWriter(new FileWriter(replayFile, true)));
            nonAppendingWriter = new PrintWriter(new BufferedWriter(new FileWriter(replayFile)));
        }
        catch (FileNotFoundException e){
            System.out.println("Replay file not found when creating replay viewer");
        }
        catch (IOException e){
            System.out.println("Something went wrong with the replay viewer...");
        }
    }


    public void writeLineToReplayFile(String lineToWrite){
        appendingWriter.println(lineToWrite);
        appendingWriter.flush();
    }

    public void wipeReplayFile(){
        nonAppendingWriter.print("-------------------------------- Replay --------------------------------\n");
        nonAppendingWriter.flush();
    }

    public void playConsoleReplay(){
        try{
            BufferedReader reader= new BufferedReader(new FileReader(replayFile));
            String line = "";
            while((line = reader.readLine()) != null){
                System.out.println(line);
            }
        }
        catch (FileNotFoundException e){
            System.out.println("File not found...");
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }


    //-----------------------------------------GUI version methods--------------------------------------//


    public void playGuiReplay(){

        ArrayList<String> rounds = new ArrayList<>();
        String roundText = "";
        try{
            BufferedReader reader = new BufferedReader(new FileReader(replayFile));

            String line = "";
            while((line = reader.readLine()) != null){
                if(line.equals("------------------------------------------------------------------------")){
                    rounds.add(roundText);
                    roundText = "";
                }  // the long line of dashes is the round separator in the replay file
                else roundText += line + "\n";
            }
            rounds.add(roundText);  // catches final round
        }
        catch (FileNotFoundException e){
            System.out.println("File not found...");
        }
        catch (IOException e){
            e.printStackTrace();
        }

        for(int i = 0; i < rounds.size(); i++){
            JOptionPane.showMessageDialog(null, rounds.get(i), "Round " + (i+1), JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
