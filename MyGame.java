import greenfoot.*;

public class MyGame extends World
{
    public final String[] numImage = {"images"};
    public static Level level;                      
    public static int speed;                            //variable for speed of platforms 
    
    public static boolean boost = true;                        //variable for whether boost is activated or not

    public MyGame()
    {
        super(400,600,1, false);

        setBackground(new GreenfootImage("FinalISPBackground.png"));
        Greenfoot.setWorld(new MenuScreen());

        level = new Level(1);
        addObject(level, 0,0);
    }

    public void act()
    {
    }
}
