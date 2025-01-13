import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

public class ScoreLabel extends Actor
{
    private String value;
    private int fontSize;
    private Color lineColor = Color.BLACK;
    private Color fillColor = Color.WHITE;
    
    private static final Color transparent = new Color(0,0,0,0);
    
    public ScoreLabel(int value, int fontSize) {
        this(Integer.toString(value), fontSize);
    }
    
    public ScoreLabel(String value, int fontSize) {
        this.value = value;
        this.fontSize = fontSize;
        updateImage();
    }
    
    public void setValue(String value) {
        this.value = value;
        updateImage();
    }
    
    public void setValue(int value) {
        this.value = (Integer.toString(value));
        updateImage();
    }
    
    private void updateImage() {
        setImage(new GreenfootImage(value, fontSize, fillColor, transparent, lineColor));
    }
}
