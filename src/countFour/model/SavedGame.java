package countFour.model;

import javafx.scene.paint.Color;

import java.io.*;
import java.util.ArrayList;

public class SavedGame implements Serializable {
    private static final String PATH = "savedGame.ser";
    private ArrayList<Integer> turns = new ArrayList<>();
    private String redPlayerName;
    private String yellowPlayerName;



    //Writes a file containing this object state and store it in the defined path
    public void saveGame(String path) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
            oos.writeObject(this);
            oos.close();
            System.out.println("Object was written");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to save");
        }
    }
    //Reads a file containing object status in the defined path
    public void loadGame(String path) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
            SavedGame restored =(SavedGame) ois.readObject();
            ois.close();
            System.out.println("Object was loaded");
            this.turns = restored.getTurns();
            this.redPlayerName = restored.getRedPlayerName();
            this.yellowPlayerName = restored.getYellowPlayerName();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to load");
        }
    }

    public static String getPATH() {
        return PATH;
    }

    public void addTurn(int column) {
        this.turns.add(column);
    }

    public void setRedPlayerName(String redPlayerName) {
        this.redPlayerName = redPlayerName;
    }

    public void setYellowPlayerName(String yellowPlayerName) {
        this.yellowPlayerName = yellowPlayerName;
    }

    public ArrayList<Integer> getTurns() {
        return turns;
    }

    public String getRedPlayerName() {
        return redPlayerName;
    }

    public String getYellowPlayerName() {
        return yellowPlayerName;
    }
}

