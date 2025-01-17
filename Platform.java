import greenfoot.*;

public class Platform extends Actor
{
    int platformNum;                 //platform number
    int height;                      //height of image  
    int lvl;
    
    GreenfootImage[] platformImgs = {new GreenfootImage("images/sprites/platformMed.png"),          //array for possible platforms
                                     new GreenfootImage("images/sprites/platformSmall.png")};     
    MyGame world;

    public Platform(int platformNum, int lvl)
    {
        GreenfootImage image = platformImgs[Greenfoot.getRandomNumber(2)];
        image.scale(this.getImage().getWidth()*10, this.getImage().getHeight());
        
        this.lvl = lvl;
        this.platformNum = platformNum;
        height = this.getImage().getHeight();


        //if last platform in level
        if(platformNum == 0)
        {
            image.scale(this.getImage().getWidth()*100, height * 2);
        }
        setImage(image);
            }

    public void act()
    {
        world = (MyGame) getWorld();
        movePlatform();
        
        //pause game if platform 0 has reached bottom of screen
        if(platformNum == 0 && getY() >= 590)
        {
            MyGame.start = false;
            
            if(Greenfoot.isKeyDown("SPACE"))
            {
                MyGame.start = true;
                world.removeObject(this);
            }
        }
        
        removePlatform();
    }
    
    /**
     * move platform based on game speed if game has started
     */
    private void movePlatform()
    {
        if(MyGame.start)//!((platformNum == 0 && getY() > 600 - height) || (MyGame.start == false)))&& getY() > 600 - (reversedPlatNum * 100))))
        {
            setLocation(getX(), getY() + MyGame.speed);
        }
    }
    
    /**
     * remove platform if the platform reaches bottom of screen
     */
    private void removePlatform()
    {
        if(platformNum != 0)
        {
            if(getY() >= 600 + height / 2)
            {

                world.removeObject(this);   
            }
        }
    }
    
    public int getHeight(){
        int platformHeight = this.getImage().getHeight();
        return platformHeight;
    }
}
 