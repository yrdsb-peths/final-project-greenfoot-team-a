import greenfoot.*;

public class Coin extends Actor
{
    public Coin(){}

    public void act()
    {
        setLocation(getX(), getY() + MyGame.speed);
    }
}
