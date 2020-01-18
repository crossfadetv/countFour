package countFour.model;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import java.io.File;
import java.util.ArrayList;
import java.util.Observable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class Game extends Observable {
    public static final int ROWS = 6;
    public static final int COLUMNS = 7;
    public static final int FIELDSIZE = 100;
    private ArrayList<Player> players;
    private Stone[][] stoneContainerGrid = new Stone[COLUMNS][ROWS];
    private boolean hasGameEnded = false;
    private boolean isDraw = false;
    private boolean muteAudio = false;

    public ArrayList getPlayers() {
        return players;
    }



    public Stone playMove(int column) {
        column = column - 1;
        for (int row = ROWS - 1; row >= 0; row--) {
            if (stoneContainerGrid[column][row] == null /*&& row <= 5*/) {
                Stone stone = getPlayerOnTurn().playStone();
                stoneContainerGrid[column][row] = stone;


                //Animation Setup
                double dropDuration = 500;
                //Audio Setup
                if (!this.muteAudio) {

                    ScheduledExecutorService scheduler
                            = Executors.newSingleThreadScheduledExecutor();

                    Runnable task = () -> {
                        Media drop = new Media(new File("src/countFour/view/audio/drop.mp3").toURI().toString());
                        MediaPlayer playDrop = new MediaPlayer(drop);
                        playDrop.setAutoPlay(true);
                        playDrop.play();
                    };
                    int delay = (int) dropDuration-50;
                    scheduler.schedule(task, delay, TimeUnit.MILLISECONDS);
                    //scheduler.shutdown();
                }


                //Set Start Position
                stone.setTranslateX(column * FIELDSIZE + 50); //hässlich mit dem 50
                stone.setTranslateY(50);
                //Set End Position
                KeyFrame startPos = new KeyFrame(Duration.millis(dropDuration),
                        new KeyValue(stone.translateXProperty(),column*FIELDSIZE+50),
                        new KeyValue(stone.translateYProperty(),row * FIELDSIZE + 150));
                //Invoke Animation
                Timeline tl = new Timeline();
                tl.getKeyFrames().addAll(startPos);
                tl.setCycleCount(1);
                tl.play();

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
                System.out.println("vertical counter: "+ counter);
                if (counter > 3) {
                    System.out.println(player.getName() + " has won");
                    setHasGameEnded(true);
                    return;
                }
            } else {
                counter = 0;
                System.out.println("vertical counter back to 0");
            }
        }
        counter =0;
        System.out.println("counter is "+ counter);
        //check horizontal wins
        for (int x = 0; x < COLUMNS; x++) {
            if (stoneContainerGrid[x][row] != null && stoneContainerGrid[x][row].getColor() == currentColor) {
                counter++;
                System.out.println("horizontal counter: "+ counter);
                if (counter > 3) {
                    System.out.println(player.getName() + " has won");
                    setHasGameEnded(true);
                    return;
                }
            } else {
                counter = 0;
                System.out.println("horizontal counter back to 0");
            }
        }

        //check diagonal wins
        //descending
        for (int x = 0; x < COLUMNS - 3; x++) {
            for (int y = 0; y < ROWS - 3; y++) {
                for (int offset = 1; offset <= 3; ) {
                    if (stoneContainerGrid[x][y] != null && stoneContainerGrid[x + offset][y + offset] != null && stoneContainerGrid[x][y].getColor() == stoneContainerGrid[x + offset][y + offset].getColor()) {
                        offset++;
                        System.out.println("descending offset: "+ offset);
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
                        System.out.println("ascending offset: "+ offset);
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
    public void setMuteAudio() {
            this.muteAudio=!this.muteAudio;
    }
    public boolean getMuteAudio() {
        return muteAudio;
    }

   }
