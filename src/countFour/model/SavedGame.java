package countFour.model;

import javafx.scene.paint.Color;

import java.io.*;

public class SavedGame implements Serializable {
    private String fileName = "savedGame.ser";
    private String playerOnTurnName;
    private String playerOnTurnColor;
    private int playerOnTurnStack;

    //private String otherPlayerName;
    //private String OtherPlayerColor;


    public SavedGame(Player playerOnTurn) {
        this.playerOnTurnName = playerOnTurn.getName();
        if (playerOnTurn.getColor().equals(Color.RED)) {
            this.playerOnTurnColor = "red";
        } else {
            this.playerOnTurnColor = "yellow";
        };
        this.playerOnTurnStack = playerOnTurn.countStones();
        saveGame();
    }

    public void saveGame() {

        try {
            FileOutputStream playerFileOut = new FileOutputStream(fileName);
            ObjectOutputStream playerObjOut = new ObjectOutputStream(playerFileOut);
            playerObjOut.writeObject(this);
            playerObjOut.close();
            System.out.println("Object was written");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to save");
        }

    }

    public Object loadGame(String file) {
        Object object = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            object = objectInputStream.readObject();
            objectInputStream.close();
            System.out.println("Object was loaded");
            System.out.println(this.playerOnTurnName);
            System.out.print(this.playerOnTurnColor);
            System.out.println(this.playerOnTurnStack);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to load");
        }
        return object;
    }

    public String getFileName() {
        return fileName;
    }

    public String getPlayerOnTurnName() {
        return playerOnTurnName;
    }

    public String getPlayerOnTurnColor() {
        return playerOnTurnColor;
    }

    public int getPlayerOnTurnStack() {
        return playerOnTurnStack;
    }
}


