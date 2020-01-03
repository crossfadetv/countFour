package countFour.model;

import javafx.collections.ObservableList;
import javafx.scene.paint.Color;

import java.util.Observable;

public class Game extends Observable {
    public static final int ROWS = 6;
    public static final int COLUMNS = 7;
    public static final int FIELDSIZE = 100;
    private ObservableList<Player> players;
    private Stone[][] stoneContainerGrid = new Stone[COLUMNS][ROWS];

    public Stone throwStone(int column) {
        column = column - 1;
        Stone stone = getPlayerOnTurn().playStone();

        for (int row = ROWS - 1; row >= 0; row--) {
            if (stoneContainerGrid[column][row] == null && row <= 5) {
                stoneContainerGrid[column][row] = stone;
                stone.setTranslateX(column * FIELDSIZE + 50); //hässlich mit dem 50
                stone.setTranslateY(row * FIELDSIZE + 150); //hässlich mit dem 150
                System.out.println(column + " " + row);
                checkForWinner(getPlayerOnTurn(), column, row);
                changePlayerTurn();
                return stone;
            } else {
            }
        } return null; //TODO: introduce Exception Handling!
    }

    public Player getPlayerOnTurn() {
        Player playerOnTurn = null;
        for (Player player : players) {
            if (player.onTurn()) {
                playerOnTurn = player;
            }
        }
        return playerOnTurn;
    }


    public void changePlayerTurn() {
        for (Player player : players) {
            player.changeTurn();
        }
    }

    public void checkForWinner(Player player, int column, int row) {
        int counter = 0;
        Color currentColor = player.getColor();
        //check vertical wins
        for (int x = row; x <= row + 3; x++) {
            if (x < ROWS && stoneContainerGrid[column][x] != null && stoneContainerGrid[column][x].getColor() == currentColor) {
                counter++;
                if (counter > 3) {
                    System.out.println(player.getName() + " has won");
                    return;
                }
            } else {
                counter = 0;
            }
        }
        //check horizontal wins
        for (int x = 0; x < COLUMNS; x++) {
            if (stoneContainerGrid[x][row] != null && stoneContainerGrid[x][row].getColor() == currentColor) {
                counter++;
                if (counter > 3) {
                    System.out.println(player.getName() + " has won");
                    return;
                }
            } else {
                counter = 0;
            }
        }

        //check diagonal wins
        //descending
        for (int x = 0; x < COLUMNS - 3; x++) {
            for (int y = 0; y < ROWS - 3; y++) {
                for (int offset = 1; offset <= 3; ) {
                    if (stoneContainerGrid[x][y] != null && stoneContainerGrid[x + offset][y + offset] != null && stoneContainerGrid[x][y].getColor() == stoneContainerGrid[x + offset][y + offset].getColor()) {
                        offset++;
                        if (offset > 3) {
                            System.out.println(player.getName() + " has won");
                            return;                        }
                    } else { break; }
                }
            }
        }

        //ascending diagonals
        for (int x = 0; x < COLUMNS - 3; x++) {
            for (int y = 3; y < ROWS; y++) {
                for (int offset = 1; offset <= 3; ) {
                    if (stoneContainerGrid[x][y] != null && stoneContainerGrid[x + offset][y - offset] != null && stoneContainerGrid[x][y].getColor() == stoneContainerGrid[x + offset][y - offset].getColor()) {
                        offset++;
                        if (offset > 3) {
                            System.out.println(player.getName() + " has won");
                            return;
                        }
                    } else { break; }
                }
            }
        }
    }

    //TODO: introduce endGame method

}
