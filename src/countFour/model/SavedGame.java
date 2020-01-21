/**
 * This class handles the serialization functionality for saving and restoring games.
 * The object is written to a *.ser-file
 *
 *
 * @author  Markus Steiner
 * @version 1.0
 * @since   2020-1-19
 */
package countFour.model;
import java.io.*;
import java.util.ArrayList;


public class SavedGame implements Serializable {
    private static final String PATH = "savedGame.ser";
    private ArrayList<Integer> turns = new ArrayList<>();
    private String redPlayerName;
    private String yellowPlayerName;

    /**
     * Writes a file containing this object state and store it in the defined path
     * @param path
     */
    public void saveGame(String path) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
            oos.writeObject(this);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to save");
        }
    }

    /**
     * Reads a file containing this object state and store it in the defined path
     * @param path
     */
    public void loadGame(String path) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
            SavedGame restored =(SavedGame) ois.readObject();
            ois.close();
            this.turns = restored.getTurns();
            this.redPlayerName = restored.getRedPlayerName();
            this.yellowPlayerName = restored.getYellowPlayerName();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to load");
        }
    }

    /**
     *
     * @return path to file where the object state is written
     */
    public static String getPATH() {
        return PATH;
    }

    /**
     * Adds a new int element to the list
     * @param column
     */
    public void addTurn(int column) {
        this.turns.add(column);
    }

    /**
     * Sets the Name of the red player
     * @param redPlayerName
     */
    public void setRedPlayerName(String redPlayerName) {
        this.redPlayerName = redPlayerName;
    }

    /**
     * Sets the Name of the yellow player
     * @param yellowPlayerName
     */
    public void setYellowPlayerName(String yellowPlayerName) {
        this.yellowPlayerName = yellowPlayerName;
    }

    /**
     *
     * @return a list of all turns
     */
    public ArrayList<Integer> getTurns() {
        return turns;
    }

    /**
     *
     * @return the red player's name
     */
    public String getRedPlayerName() {
        return redPlayerName;
    }

    /**
     *
     * @return the yellow player's name
     */
    public String getYellowPlayerName() {
        return yellowPlayerName;
    }
}

