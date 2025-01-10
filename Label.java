import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


/**
 * A Label class that allows you to display a textual value on screen.
 * 
 * The Label is an actor, so you will need to create it, and then add it to the world
 * in Greenfoot.  If you keep a reference to the Label then you can change the text it
 * displays.  
 *
 * @author Amjad Altadmri 
 * @version 1.1
 */
public class Label extends Actor
{
    private GreenfootImage image;
     
    /**
     * Create a new label, initialise it with the needed text and the font size 
     */
    
    public Label(String text) {
        setText(text);
    }
    
    public Label(String text, int size) {
        image = new GreenfootImage(text, size, Color.BLACK, null);
        setImage(image);
    }
    
    public void setText(String text) {
        image = new GreenfootImage(text, 13, Color.BLACK, null);
        setImage(image);
    }
}