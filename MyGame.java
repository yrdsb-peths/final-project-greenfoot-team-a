import greenfoot.*;

public class MyGame extends World
{
    public static Level level;                //current level active
    public static int speed;                  //speed of platforms 
    
    //boolean variables tracking active powerups
    public static boolean boost = false;                
    public static boolean shield = false;   

    public MyGame()
    {
        super(400,600,1, false);

        setBackground(new GreenfootImage("FinalISPBackground.png"));
        Greenfoot.setWorld(new MenuScreen());

        level = new Level(-1);
        addObject(level, 0,0);

        Platform platform = new Platform(0);
        addObject(platform, getWidth()/2, 590);
    }

    public void act()
    {
    }
}
