package main.java;

import java.io.*;

public class ReplayHandler {
    File replayFile;
    PrintWriter writer;
    PrintWriter appendingWriter;

    public ReplayHandler(){
        replayFile = new File("src/data/replayFile.txt");
        try{
            appendingWriter = new PrintWriter(new BufferedWriter(new FileWriter(replayFile, true)));
            writer = new PrintWriter(new BufferedWriter(new FileWriter(replayFile)));
        }
        catch (FileNotFoundException e){
            System.out.println("Replay file not found...");
        }
        catch (IOException e){
            System.out.println("Something went wrong with the replayViewer...");
        }
    }


    public void writeLineToReplayFile(String lineToWrite){
        appendingWriter.println(lineToWrite);
        appendingWriter.flush();
    }

    public void wipeReplayFile(){
        writer.print("-------------------------------- Replay --------------------------------\n");
        writer.flush();
    }

    public void playReplay(){
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
}
