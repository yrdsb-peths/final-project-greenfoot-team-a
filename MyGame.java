import greenfoot.*;

public class MyGame extends World
{
    public static Level level;

    public MyGame()
    {
        super(400,600,1);
        level = new Level(1);

        addObject(level, 0,0);
    }

    public void act()
    {
    }
}
