package sample;

import com.sun.org.apache.regexp.internal.REDebugCompiler;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Player {
    private Color color = Color.RED;
    private String name;
    private ArrayList<Stone> stones;

    public Player(){
        stones = new ArrayList<>();
        fillStones();
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

/*    public void setColor(Color color){
        this.color = color;
    }*/
    public Color getColor() {
        return color;
    }

    public Stone playStone(){
        Stone stone = stones.get(stones.size()-1);
        stones.remove(stones.size()-1);
        return stone;
    }

    public void fillStones(){
        for(int x=0; x<=21;x++){
            stones.add(new Stone(Color.RED));
        }
    }
}
