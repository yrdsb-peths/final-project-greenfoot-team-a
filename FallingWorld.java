import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class FallingWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FallingWorld extends World
{

    /**
     * Constructor for objects of class FallingWorld.
     * 
     */
    public FallingWorld(int avatarX, String facing)
    {    
        super(400,600,1, false);
        setBackground("mainBackground.png");
        Avatar avatar = new Avatar();
        addObject(avatar, avatarX, 20);
    }
    
    public void act(){
        System.out.println(Avatar.facing);
        int delay = 0;
        delay++;
        if (delay >= 200){
            Greenfoot.setWorld(new GameOver());
        }
    }
}
