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
            images[i].scale(130,130);
            
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

        setLocation(getX(), getY() + MyGame.speed);
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
