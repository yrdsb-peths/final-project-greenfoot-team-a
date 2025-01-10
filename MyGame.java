import greenfoot.*;

public class MyGame extends World
{
    public static Level level;

    public MyGame()
    {
        super(400,600,1);
        
        setBackground(new GreenfootImage("FinalISPBackground.png"));
        Greenfoot.setWorld(new MenuScreen());

        level = new Level(1);
        addObject(level, 0,0);
    }

    public void act()
    {
        
    }
}
