import greenfoot.*;

public class Level extends Actor
{
    //timers
    SimpleTimer platformTimer = new SimpleTimer();      //timer teacking time between platform spawns
    SimpleTimer levelTimer = new SimpleTimer();         //timer tracking level length
    SimpleTimer enemyTimer = new SimpleTimer();         //timer tracking time between enemy spawns

    //level variables
    int lvl;                                            //level player is on
    int platformNum;                                    //number of platforms yet to be spawned
    int platSpawnRate;                                  //milliseconds between platform spawns

    int enemyNum;                                       //number of enemies left to spawn
   
    MyGame world;                                       
    
    public Level(int lvl)
    {
        this.lvl = lvl;
        
        setImage((GreenfootImage) null);
        //num of platforms and enemies in the level
        platformNum = 10 + (lvl * 10);
        enemyNum = 1 + (lvl * 2);
        
        //spawn rate and speed of platform
        platSpawnRate = 1200;
        MyGame.speed = (int) (lvl * 1.5) + 2;

        //if boost is activated, change spawn rate and speed of platforms
        if(MyGame.boost)
        {
            platSpawnRate = 500;
            MyGame.speed = 40;
        }

        //start timers
        levelTimer.mark();
        platformTimer.mark();
        enemyTimer.mark();
    }

    public void act()
    {
        world = (MyGame) getWorld();

        spawnPlatform();
        removeBoost();
        spawnEnemy();

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
            if(platformNum == 0)
            {
                xPos = world.getWidth() /2;

                //shop spawn
                ShopIcon shopIcon = new ShopIcon();
                world.addObject(shopIcon, 340, -130); 
            }

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
        if(Greenfoot.getRandomNumber(100) <= 100 && platformNum != 0)
        {
            world.addObject(new Coin(), xPos, -70);
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
            platSpawnRate = 1200;
        }
    }
    
    /** 
     * spawn enemy moving right to left at random y value near the uppper
     * half of the screen
     */
    private void spawnEnemy()
    {
        //spawn enemy every 5000 millis
        if(enemyTimer.millisElapsed() >= 5000 && enemyNum > 0)
        {
            int y = Greenfoot.getRandomNumber(300);
            
            enemyNum--;
            
            //create enemy and spawn at random y value
            Enemy enemy = new Enemy(MyGame.speed);
            world.addObject(enemy, 1, y + 50);
            enemyTimer.mark();    
        }
    }
}
