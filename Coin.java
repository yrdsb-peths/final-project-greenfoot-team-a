import greenfoot.*;

public class Coin extends Actor
{
    int frame = 0;           //counter for animation
    int animationTimer;      //speed of animation
    GreenfootImage[] images = new GreenfootImage[6];

    public Coin()
    {
        setImage(images[0]);

        //fill array with animation frames
        for(int i = 0; i < 6; i++)
        {
            images[i] = new GreenfootImage("images/sprites/coin/coin" + (i+1)+ ".png");
            images[i].scale(190,190);
        }
    }

    public void act()
    {
        //change frame of animation
        animationTimer++;
        if(animationTimer % 5 == 0)
        {
            animate();
        }
        
        if(MyGame.start)
        {
            setLocation(getX(), getY() + MyGame.speed);
        }
        removeCoin();
    
    }

    private void removeCoin()
    {
        if(getY() > 650)
        {
            MyGame world = (MyGame) getWorld();
            world.removeObject(this);
        }
    }
    
    private void animate()
    {
        //change image to next frame
        setImage(images[frame]);

        if(frame == 5)
        {
            frame = 0;
        }

        frame++;
    }
}
