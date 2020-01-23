/**
 * This class implements a player object containing the properties name, color of the stones,
 * the stack of the stones and if he is on turn.
 *
 *
 * @author  Rahel Krubally
 * @version 1.0
 * @since   2019-12-1
 */
package countFour.model;
import javafx.scene.paint.Color;
import java.util.ArrayList;

public class Player {
    private Color color;
    private String name;
    private ArrayList<Stone> stones;
    private boolean turn;

    /**
     * @param color      color that will be set for the player
     * @param playerName name that will be set for the player
     * @param turn       whether or not the player is on turn
     */
    public Player(String playerName, Color color, boolean turn) {
        stones = new ArrayList<>();
        this.name = playerName;
        this.color = color;
        this.turn = turn;
        fillStones();
    }

    /**
     * @return the players name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name name can be set for player
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the color of the player
     */
    public Color getColor() {
        return color;
    }

    /**
     * @return whether or not it's the players turn
     */
    public boolean onTurn() {
        return turn;
    }

    /**
     * @return the players stones
     */
    public ArrayList getStones() {
        return stones;
    }

    /**
     * @return the stone the player throws
     */
    public Stone playStone() {
        Stone stone = stones.get(stones.size() - 1);
        stones.remove(stones.size() - 1);
        return stone;
    }

    /**
     * @return the amount of stones the player has
     */
    public int countStones() {
        return stones.size();
    }

    /**
     * fills the players stones
     */
    public void fillStones() {
        for (int x = 0; x < 21; x++) {
            stones.add(new Stone(this));
        }
    }

    /**
     * changes whether or not it's the player's turn
     */
    public void changeTurn() {
        this.turn = !this.turn;
    }
}

