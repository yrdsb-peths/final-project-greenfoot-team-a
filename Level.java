import greenfoot.*;

public class Level extends Actor
{
    SimpleTimer platformTimer = new SimpleTimer();      //timer for time between platform spawns
    SimpleTimer boostTimer = new SimpleTimer();         //timer for boosts
    SimpleTimer enemyTimer = new SimpleTimer();         //timer for time between enemy spawns
    
    public static int speed;                            //variable for speed of platforms 
    int lvl;                                            //variable for level player is on
    int platformNum;                                    //variable counting fown platforms yet to be spawned
    int enemyNum;                                       //variable to count number of enemies left to spawn
    
    MyGame world;
    
    public Level(int lvl)
    {
        this.lvl = lvl;

        platformNum = 10 + (lvl * 10);
        enemyNum = 1 + (lvl * 2);
        speed = (int) (lvl * 1.5) + 2;

        platformTimer.mark();
        enemyTimer.mark();
    }

    public void act()
    {
        world = (MyGame) getWorld();
        spawnPlatform();
        spawnEnemy();

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
            System.out.println("ENEMY " + enemyNum);
            
            //create enemy and spawn at random y value
            Enemy enemy = new Enemy(speed);
            world.addObject(enemy, 1, y + 50);
            enemyTimer.mark();    
        }
    }
}
