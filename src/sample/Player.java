package sample;

import com.sun.org.apache.regexp.internal.REDebugCompiler;
import javafx.scene.paint.Color;

public class Player {
    public static Color color = Color.RED;
    private String name;

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
}
