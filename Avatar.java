import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This class provides the controls and sprite for our main player avatar.
 * 
 * @Maggie Jiang
 * 
 */

public class Avatar extends Actor
{
    GreenfootImage sussyImage= new GreenfootImage("images/sussy.png");
    private SimpleTimer timer = new SimpleTimer();
    
    private int velocity;
    private int gravity=1;
    private int counter = 0;
    /**
     * Act - do whatever the Avatar wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the//Contructor environment.
     */
    
    public Avatar(){
        setImage(sussyImage);
        sussyImage.scale(75,80);
        velocity = 0;
    }
    
    public void checkWarp(){
        //Check if the avatar goes out of the right edge (X > world width)
        if (getX()<1) {
            setLocation(getWorld().getWidth()-2, getY()); //Warp to the right side
        }
        // Check if the avatar goes out of the left edge (X > world width)
        if (getX()>getWorld().getWidth()-2) {
            setLocation(0, getY());  // Wrap to the right side
        }
    }
    
    public void checkKeys(){
        fall(); // Applies gravity to character
        if(Greenfoot.isKeyDown("left"))
        {
            move(-5);
        }
        if(Greenfoot.isKeyDown("right"))
        {
            move(5);
        }
        if(Greenfoot.isKeyDown("up")){
           jump();
        }
    }
    
    public void jump(){
        if(getY() > getWorld().getHeight() - 50){
            velocity= - 20;
        }
    } 
    
    public void fall(){
        setLocation(getX(), getY() + velocity); // Updates location
        if(getY()> getWorld().getHeight() - 50){
            velocity = 0; // Makes sure character does not fall beyond surface
        } else {
            velocity += gravity; // Makes character fall with gravity if not on surface
        }
    }
    public void act()
    {
        fall();
        checkWarp();
        checkKeys();
    }
}
