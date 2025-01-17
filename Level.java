import greenfoot.*;

public class Level extends Actor
{
    //timers
    SimpleTimer platformTimer = new SimpleTimer();      //timer teacking time between platform spawns
    public static SimpleTimer levelTimer = new SimpleTimer();         //timer tracking level length
    SimpleTimer enemyTimer = new SimpleTimer();         //timer tracking time between enemy spawns

    //level variables
    int lvl;                                            //level player is on
    int platformNum;                                    //number of platforms yet to be spawned
    int platSpawnRate;                                  //milliseconds between platform spawns
    int enemyNum;                                       //number of enemies left to spawn
   
    MyGame world;  
    Platform lastPlatform;                                     
    
    public Level(int lvl)
    {
        this.lvl = lvl;
        setImage((GreenfootImage)null);

        //num of platforms and enemies in the level
        platformNum = 20 + (lvl * 10);
        enemyNum = 1 + (lvl * 2);

        //spawn rate and speed of platform
        MyGame.speed = lvl + 2;
        platSpawnRate = (int)1800/MyGame.speed;


        //start timers
        levelTimer.mark();
        platformTimer.mark();
        enemyTimer.mark();
    }

    public void act()
    {
        world = (MyGame) getWorld();
        
        spawnEnemy();                  //spawn enemy
        checkBoost();                  //check boost status and start or end boost
        
        //spawn platforms if game started
        if(MyGame.start)
        {
            spawnPlatform();
        }
        
        //start game is space is pressed and game not started yet
        if(MyGame.start == false && Greenfoot.isKeyDown("Space"))
        {
            MyGame.start = true;
            
            world.addObject(new Platform(1, lvl),Greenfoot.getRandomNumber(world.getWidth()), -20);
            platformTimer.mark();
        }
    }

    /**
     * check is boost is active, start/end boost, and change platform speed accordingly
     */
    private void checkBoost()
    {
         //if boost is activated, change spawn rate and speed of platforms
         if(!world.getObjects(Boost.class).isEmpty())
         {
            platSpawnRate = 500;
            MyGame.speed = 40;

            //reset speed if more then 7 sec passed
            if(levelTimer.millisElapsed() >= 7000)
            {
                platSpawnRate = 1000;
                MyGame.speed = (int)(lvl*1.5) + 2;

                world.removeObjects(world.getObjects(Boost.class));
            }
         } 
    }

    /**
     * add platform to top of screen at random x value
     */
    public void spawnPlatform()
    {
        if(platformTimer.millisElapsed() >= platSpawnRate && platformNum > 0)
        {
            int xPos = Greenfoot.getRandomNumber(world.getWidth());
            
            platformNum--;

            //add platform to screen
            if(platformNum == 0)
            {
                xPos = world.getWidth()/2;
                //shop spawn
                ShopIcon shopIcon = new ShopIcon();
                world.addObject(shopIcon, 340, -130); 
            }
            
            world.addObject(new Platform(platformNum, lvl), xPos, -50);
            
            platformTimer.mark();
            coinSpawn(xPos);
        }
        
        //if last platform in level, add new level and remove current
        if(platformNum == 0)
        {
            //add higher level object, remove this level
            MyGame.level = new Level(1 + lvl);
            world.addObject(MyGame.level,0,0);
            world.removeObject(this);
        }
    }

    /**
     * add spawn coin on platform 
     */
    private void coinSpawn(int xPos)
    {
        //spawn coins at 15% spawn rate (15% chance random number <= 15)
        if(Greenfoot.getRandomNumber(100) <= 15 && platformNum != 0)
        {
            world.addObject(new Coin(), xPos, -75);
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
