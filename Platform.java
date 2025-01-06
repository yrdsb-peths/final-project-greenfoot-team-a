import greenfoot.*;

public class Platform extends Actor
{
    public Platform()
    {
        GreenfootImage image = new GreenfootImage("temporaryPlatform.png");
        image.scale(50,100);
        setImage(image);
    }

    public void act()
    {
        //move platform down
        setLocation(getX(), getY() + MyGame.speed);
        removePlatform();
    }

    /**
     * remove platform if the platform reaches bottom of screen
     */
    private void removePlatform()
    {
        MyGame world = (MyGame) getWorld();
        if(getY() + 60 >= 598)
        {
            world.removeObject(this);   
        }
    }
}
