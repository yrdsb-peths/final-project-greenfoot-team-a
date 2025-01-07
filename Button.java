import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Button here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Button extends Actor
{
     private Runnable action;

    public Button(Runnable action) 
    {
        this.action = action;
        GreenfootImage image = new GreenfootImage("images/button.png");
        setImage(image);
    }

    public void act() 
    {
        if (Greenfoot.mouseClicked(this)) 
        {
            if (action != null) {
                action.run();
            }
        }
    }
}
