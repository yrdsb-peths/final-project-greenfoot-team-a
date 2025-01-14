import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This class creates button that will be used throughout the game
 * 
 * @CarlisaWong
 */
public class Button extends Actor
{
    private Runnable action;

    public Button(Runnable action) 
    {
        this.action = action;
        GreenfootImage image = new GreenfootImage("images/button4.png");
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
