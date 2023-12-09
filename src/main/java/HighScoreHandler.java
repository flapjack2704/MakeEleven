package main.java;

import java.io.*;
import java.util.*;

public class HighScoreHandler {
    private LinkedHashMap<String, Integer> highscoreMap;

    public HighScoreHandler(){
        highscoreMap = generateHighscoreMap();
    }


    public void checkForHighscore(int points){


        // Check if highscore map is full or not, and add new entry accordingly
        if(highscoreMap.size() < 5){
            String name = inputUsername();
            highscoreMap.put(name, points);
        }
        else if(points >= highscoreMap.lastEntry().getValue()){
            String name = inputUsername();

            highscoreMap.remove(highscoreMap.lastEntry().getKey());
            highscoreMap.put(name, points);

            // Sort map by its entries
            LinkedHashMap<String, Integer> tempMap = new LinkedHashMap<>();
            highscoreMap.entrySet().stream().
                    sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).
                    forEach(entry -> tempMap.put(entry.getKey(), entry.getValue()));
            highscoreMap = tempMap;

        }
    }

    private String inputUsername(){
        System.out.println("New high score! Enter your name to be added to the highscore table.");
        while(true){
            System.out.println("Please do not include any commas in the username (\",\")");
            String input = GameRunner.scanner.nextLine();

            if(input.indexOf(',') != -1) continue;
            if(input.equals("")) continue;
            return input;
        }
    }

    public LinkedHashMap<String, Integer> generateHighscoreMap(){
        /*
            Read highscore file to populate highscore map
         */

        // Normal HashMap didn't keep scores in order, whereas the linked map does keep order
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
        try{
            File file = new File("src/data/highscores.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();  // reads commented line in file + ignores
            while((line = reader.readLine()) != null){
                String[] array = line.split(",");
                map.put(array[0], Integer.parseInt(array[array.length-1]));
            }
        }
        catch (FileNotFoundException e){
            System.out.println("File not found...");
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return map;
    }

    public void writeHighscoreMapToFile(){


        try{
            File file = new File("src/data/highscores.txt");
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(file)));
            writer.println("#username, score");

            String[] usernames = highscoreMap.keySet().toArray(new String[0]);
            for(int i = 0; i<highscoreMap.size(); i++){
                writer.println(usernames[i] + "," + highscoreMap.get(usernames[i]));  // username,score
            }
            writer.close();
        }
        catch (FileNotFoundException e){
            System.out.println("File not found...");
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

    public void printHighscoreTable(){
        String out = "\n====== High Score Table ======\n";

        String[] usernames = highscoreMap.keySet().toArray(new String[0]);
        for(int i = 0; i < highscoreMap.size(); i++){
            out += (i+1) + ". " + usernames[i] + " : " + highscoreMap.get(usernames[i]) + "\n";
        }
        System.out.println(out);
    }


    //-----------------------------------------GUI version methods--------------------------------------//
}
