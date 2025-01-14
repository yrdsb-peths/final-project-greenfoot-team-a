import greenfoot.*;

public class Platform extends Actor
{

    int type;                                            //variable for type of platform that is to be spawned (if == 0, last platform of level)

    GreenfootImage[] platformImgs = {new GreenfootImage("images/sprites/platformMed.png"),          //array for possible platforms
                                     new GreenfootImage("images/sprites/platformSmall.png")};     

    public Platform(int type)
    {
        this.type = type;
        
        GreenfootImage image = platformImgs[Greenfoot.getRandomNumber(1)];
        image.scale(this.getImage().getWidth() * 10, this.getImage().getHeight() * 10);

        //if last platform in level
        if(type == 0)
        {
            image.scale(600, this.getImage().getHeight()* 25);
        }
        setImage(image);
    }

    public void act()
    {
        //move platform down platform is last one & at bottom of screen
        if(!(type == 0 && getY() >= 600 - 20))
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
        if(type != 0)
        {
            MyGame world = (MyGame) getWorld();
            if(getY() >= 600 + this.getImage().getHeight() / 2)
            {

                world.removeObject(this);   
            }
        }
    }
}
