import greenfoot.*;

public class Platform extends Actor
{
    //String[] imagesArr = {};
    int type;                    //variable for type of platform that is to be spawned (if == 0, last platform of level)
    int speed;

    public Platform(int type, int speed)
    {
        this.type = type;
        this.speed = speed;
        
        GreenfootImage image = new GreenfootImage("temporaryPlatform.png");

        //if last platform in level
        if(type == 0)
        {
            image.scale(400, 80);
        }
        setImage(image);
    }

    public void act()
    {
        //move platform down
        setLocation(getX(), getY() + speed);
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
            if(getY() + 60 >= 598)
            {
                world.removeObject(this);   
            }
        }
    }
}
