import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This class will allow the shop icon to appear on the large platform
 */
public class ShopIcon extends Actor
{
    /**
     * Act - do whatever the ShopIcon wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public ShopIcon() {
        GreenfootImage image = new GreenfootImage("images/sprites/shopOutside.png"); 
        image.scale(150, 150);
        setImage(image);
    }
    
    public void act()
    {
        if(getY() <= 500)
        {
            setLocation(getX(), getY() + MyGame.speed);
        }
    }
}
