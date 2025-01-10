import greenfoot.*;

public class Level extends Actor
{
    SimpleTimer platformTimer = new SimpleTimer();      //timer for time between platform spawns
    SimpleTimer levelTimer = new SimpleTimer();         //timer for level length

    int lvl;                                            //variable for level player is on
    int platformNum;                                    //variable counting fown platforms yet to be spawned
    int platSpawnRate;                                  //variable for milliseconds between platform spawns
    
    MyGame world;
    
    public Level(int lvl)
    {
        this.lvl = lvl;

        //set number of platforms & speed for level
        platformNum = 10 + (lvl * 10);
        platSpawnRate = 1300;
        MyGame.speed = (int) (lvl * 1.5) + 2;

        if(MyGame.boost)
        {
            platSpawnRate = 500;
            MyGame.speed = 40;
        }

        //start timers
        levelTimer.mark();
        platformTimer.mark();
    }

    public void act()
    {
        world = (MyGame) getWorld();

        spawnPlatform();
        removeBoost();

        //remove last platform if space is pressed and start next level
        if(platformNum == 0 && Greenfoot.isKeyDown("SPACE"))
        {
            world.removeObjects(world.getObjects(Platform.class));

            //add higher level object, remove this level
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
        if(platformTimer.millisElapsed() >= platSpawnRate && platformNum > 0)
        {
            int xPos = Greenfoot.getRandomNumber(world.getWidth());
            
            platformNum--;

            //create new platform object
            Platform platform = new Platform(platformNum);

            //if last platform, set xPos to be center of screen
            if(platformNum == 0){xPos = 0 + platform.getImage().getWidth() / 2;}

            //add new platform
            world.addObject(platform, xPos, -50);
            platformTimer.mark();

            coinSpawn(xPos);
        }
    }

    /**
     * add spawn coin on platform 
     */
    private void coinSpawn(int xPos)
    {
        //spawn coins at 10% spawn rate (10% chance random number <= 10)
        if(Greenfoot.getRandomNumber(100) <= 10)
        {
            world.addObject(new Coin(), xPos, -80);
        }
    }

    /**
     * deactivate boost and return speed to normal
     */
    private void removeBoost()
    {
        if(MyGame.boost && levelTimer.millisElapsed() >= 7000)
        {
            MyGame.boost = false;
            MyGame.speed = (int) (lvl * 1.5) + 2;
            platSpawnRate = 1300;
        }

    }
}
