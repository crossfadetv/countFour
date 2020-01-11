package countFour.model;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Observable;


public class Game extends Observable {
    public static final int ROWS = 6;
    public static final int COLUMNS = 7;
    public static final int FIELDSIZE = 100;
    private ArrayList<Player> players;
    private Stone[][] stoneContainerGrid = new Stone[COLUMNS][ROWS];
    private boolean hasGameEnded = false;
    private boolean isDraw = false;

    public ArrayList getPlayers() {
        return players;
    }


    public Stone playMove(int column) {
        column = column - 1;
        for (int row = ROWS - 1; row >= 0; row--) {
            if (stoneContainerGrid[column][row] == null /*&& row <= 5*/) {
                Stone stone = getPlayerOnTurn().playStone();
                stoneContainerGrid[column][row] = stone;
                stone.setTranslateX(column * FIELDSIZE + 50); //hässlich mit dem 50
                stone.setTranslateY(row * FIELDSIZE + 150); //hässlich mit dem 150
                System.out.println(column + " " + row);
                checkForWinner(getPlayerOnTurn(), column, row);
                if (!getHasGameEnded()) {
                    changePlayerTurn();
                }
                return stone;
            } else {
            }
        }
        return null;
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
                    setHasGameEnded(true);
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
                    setHasGameEnded(true);
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
                            setHasGameEnded(true);
                            return;
                        }
                    } else {
                        break;
                    }
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
                            setHasGameEnded(true);
                            return;
                        }
                    } else {
                        break;
                    }
                }
            }
        }

        //check for draw
        int draw = 0;
        for(Player p : players){
            draw += p.countStones();
        }
        if(draw == 0){
            System.out.println("draw");
            setHasGameEnded(true);
            setIsDraw(true);
        }
    }

    public void startGame(String redPlayerName, String yellowPlayerName) {
        Player redPlayer = new Player(redPlayerName, Color.RED, true);
        Player yellowPlayer = new Player(yellowPlayerName, Color.YELLOW, false);
        players = new ArrayList<>();
        players.add(redPlayer);
        players.add(yellowPlayer);
    }

    public void setHasGameEnded(boolean hasGameEnded) {
        this.hasGameEnded = hasGameEnded;
    }

    public boolean getHasGameEnded() {
        return hasGameEnded;
    }

    public void setIsDraw(boolean isDraw){
        this.isDraw = isDraw;
    }

    public boolean getIsDraw(){
        return isDraw;
    }

   }
