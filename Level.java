import greenfoot.*;

public class Level extends Actor
{
    SimpleTimer platformTimer = new SimpleTimer();      //timer for time between platform spawns
    SimpleTimer boostTimer = new SimpleTimer();         //timer for boosts

    public static int speed;                            //variable for speed of platforms 
    int lvl;                                            //variable for level player is on
    int platformNum;                                    //variable counting fown platforms yet to be spawned

    MyGame world;
    
    public Level(int lvl)
    {
        this.lvl = lvl;

        platformNum = 10 + (lvl * 10);
        speed = (int) (lvl * 1.5) + 2;

        platformTimer.mark();
    }

    public void act()
    {
        world = (MyGame) getWorld();
        spawnPlatform();

        //remove last platform if space is pressed and start next level
        if(platformNum == 0 && Greenfoot.isKeyDown("SPACE"))
        {
            world.removeObjects(world.getObjects(Platform.class));

            MyGame.level = new Level(1 + lvl);
            world.addObject(MyGame.level,0,0);
            world.removeObject(this);
        }
    }

    /**
     * add platform to top of screen at random x value
     */
    private void spawnPlatform()
    {
        if(platformTimer.millisElapsed() >=1000 && platformNum > 0)
        {
            int xPos = Greenfoot.getRandomNumber(world.getWidth());
            
            platformNum--;
            System.out.println(platformNum);

            //create new platform object
            Platform platform = new Platform(platformNum, speed);

            //if last platform, set xPos to be center of screen
            if(platformNum == 0){xPos = 0 + platform.getImage().getWidth() / 2;}

            world.addObject(platform, xPos, -800);
            platformTimer.mark();
        }
    }
}
