import greenfoot.*;

public class Platform extends Actor
{

    int type;                    //variable for type of platform that is to be spawned (if == 0, last platform of level)

    public Platform(int type)
    {
        this.type = type;
        
        GreenfootImage image = new GreenfootImage("temporaryPlatform.png");
        image.scale(200,50);
        //if last platform in level
        if(type == 0)
        {
            image.scale(400, 50);
        }
        setImage(image);
    }

    public void act()
    {
        //move platform down platform is last one & at bottom of screen
        if(!(type == 0 && getY() >= 600 - this.getImage().getHeight()/2))
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
