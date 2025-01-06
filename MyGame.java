import greenfoot.*;

public class MyGame extends World{

    SimpleTimer platformTimer = new SimpleTimer();            //timer that keeps track of time between platform spawns 

    public static int speed = 3;                              //int for speed of platforms
    int lvl = 1;                                              //int for what level player is on
    int platformNum;
  
    public MyGame()
    {
        super(400,600,1);
        platformTimer.mark();
    }

    public void act()
    {
        if(platformTimer.millisElapsed() >=1000)
        {
            addPlatform();
            platformTimer.mark();
        }
    }

    /**
     * add platform to top of screen at random x value
     */
    public void addPlatform()
    {
        Platform platform = new Platform();
        addObject(platform, Greenfoot.getRandomNumber(getWidth()), -800);
    }

    public void displayLvl()
    {
        
    }
}
