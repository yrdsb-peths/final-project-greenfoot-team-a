import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Enemy extends Actor
{
    public int frame = 0;                            //counter for animation frame
    public int speed;                                //speed of enemy
    public int animationTimer;                       //speed of animation
   
    GreenfootImage[] right = new GreenfootImage[5];  //array to hold animation frames
    
    public void act()
    {
        //change frame of animation 
        animationTimer++;
        if(animationTimer % 5 == 0) {
            animateRight();
        }
        
        //move enemy
        setLocation(getX() + (int)(speed * 1.5), getY());
        removeEnemy();
    }
    
    public Enemy(int speed) {
        this.speed = speed; 
        setImage(right[0]);
        
        //fill array with animation frames
        for(int i = 0; i <= 4; i++) {
            right[i] = new GreenfootImage("images/sprites/enemy/enemy" + i + ".png");
        }
    }
    
    public void animateRight() {
        //change image to next frame
        setImage(right[frame]);
        if(frame == 4) {
            frame = 0;
        }
        frame++;
    }
    
    /**
     * removes enemy when it reaches the edge of the screen
     */
    private void removeEnemy() {
        MyGame world = (MyGame) getWorld(); 
        if(isAtEdge()) {
            world.removeObject(this);
        }
    }
}
