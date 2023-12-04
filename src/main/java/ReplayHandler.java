package main.java;

import java.io.*;

public class ReplayHandler {
    File replayFile;

    public ReplayHandler(){
        replayFile = new File("src/data/replayFile.txt");
    }


    public void writeLineToReplayFile(String lineToWrite){
        try{
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(replayFile, true)));
            writer.println(lineToWrite);
            writer.close();
        }
        catch (FileNotFoundException e){
            System.out.println("File not found...");
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void wipeReplayFile(){
        try{
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(replayFile)));
            writer.print("");
            writer.close();
        }
        catch (FileNotFoundException e){
            System.out.println("File not found...");
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void playReplay(){
        try{
            BufferedReader reader = new BufferedReader(new FileReader(replayFile));

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
}
