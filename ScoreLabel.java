import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * 
 * the ScoreLabel class is a sepereate class from Label that allows 
 * a for its text to change during runtime
 * 
 */
public class ScoreLabel extends Actor
{
    private String value;                   //represents a number value as a string
    private int fontSize;                   //size of the font
    private Color lineColor = Color.BLACK;
    private Color fillColor = Color.WHITE; 
    
    private static final Color transparent = new Color(0,0,0,0);
    
    /**
     * takes an int value and converts into String in the ScoreLabel
     */
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
    
    /**
     * sets a new int value that is converted to String
     */
    public void setValue(int value) {
        this.value = (Integer.toString(value));
        updateImage();
    }
    
    /**
     * updates the label "image" to show a new value
     */
    private void updateImage() {
        setImage(new GreenfootImage(value, fontSize, fillColor, transparent, lineColor));
    }
}
