package countFour.model;

import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Player {
    private Color color;
    private String name;
    private ArrayList<Stone> stones;
    private boolean turn;

    public Player(String playerName, Color color, boolean turn){
        stones = new ArrayList<>();
        this.name = playerName;
        this.color = color;
        this.turn = turn;
        fillStones();
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color getColor() {
        return color;
    }

    public boolean onTurn(){
        return turn;
    }

    public ArrayList getStones() {
        return stones;
    }
    public Stone playStone(){
        Stone stone = stones.get(stones.size()-1);
        stones.remove(stones.size()-1);
        return stone;
    }

    public int countStones(){
       return stones.size();
    }

    public void fillStones(){
        for(int x=0; x<21;x++){
            stones.add(new Stone(this));
        }
    }

    public void changeTurn(){
            this.turn = !this.turn;
        }
    }

