import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This class provides the controls and sprite for our main player avatar.
 * 
 * @Maggie Jiang
 * 
 */

public class Avatar extends Actor
{
    SimpleTimer animationTimer = new SimpleTimer();
    GreenfootImage sussyImage= new GreenfootImage("images/sussy.png");
    /**
     * Act - do whatever the Avatar wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the//Contructor environment.
     */
    
    public Avatar(){
        setImage(sussyImage);
        sussyImage.scale(75,80);
    }
    
    
    public void act()
    {
        if(Greenfoot.isKeyDown("left"))
        {
            move(-10);
        }
        else if(Greenfoot.isKeyDown("right"))
        {
            move(10);
        }
        System.out.println(getX());
        if (getX()<1) {
            setLocation(getWorld().getWidth()-2, getY());  // Wrap to the right side
        }
        
        // Check if the avatar goes out of the right edge (X > world width)
        if (getX()>getWorld().getWidth()-2) {
            setLocation(0, getY());  // Wrap to the left side
        }
        
        if(Greenfoot.isKeyDown("up")){
            int y=getY() - 5;
            setLocation(getX(), y);
            ,,,
        }
    }
}
