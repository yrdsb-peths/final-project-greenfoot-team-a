import greenfoot.*;

public class Platform extends Actor
{
    int platformNum;                 //platform number
    int height;                      //height of image                  
    GreenfootImage[] platformImgs = {new GreenfootImage("images/sprites/platformMed.png"),          //array for possible platforms
                                     new GreenfootImage("images/sprites/platformSmall.png")};     

    public Platform(int platformNum)
    {
        GreenfootImage image = platformImgs[Greenfoot.getRandomNumber(2)];
        image.scale(this.getImage().getWidth() * 13, this.getImage().getHeight() * 13);
        
        this.platformNum = platformNum;
        height = this.getImage().getHeight();

        //if last platform in level
        if(platformNum == 0)
        {
            image.scale(800, height * 30);
        }
        setImage(image);
    }

    public void act()
    {
        //move platform down platform if not last one & at bottom of screen 
        if(!((platformNum == 0 && getY() > 600 - height) || (MyGame.start == false && getY() > (platformNum * 100) - 300)))
        {
            setLocation(getX(), getY() + MyGame.speed);
        }
        removePlatform();
    }

    /**
     * remove platform if the platform reaches bottom of screen
     */
    private void removePlatform()
    {
        if(platformNum != 0)
        {
            MyGame world = (MyGame) getWorld();
            if(getY() >= 600 + height / 2)
            {

                world.removeObject(this);   
            }
        }
    }
}
 