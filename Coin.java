import greenfoot.*;

public class Coin extends Actor
{
    int frame = 0;           //counter for animation
    int animationTimer;      //speed of animation
    GreenfootImage firstFrame = new GreenfootImage("images/sprites/coin/coin1.png");
    GreenfootImage[] images = new GreenfootImage[6];

    public Coin()
    {
        firstFrame.scale(190,190);
        setImage(firstFrame);
        
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
