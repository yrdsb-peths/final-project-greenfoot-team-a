import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This class provides the controls and sprite for our main player avatar.
 * 
 * @Maggie Jiang
 * 
 */

public class Avatar extends Actor {
    GreenfootImage sussyImage = new GreenfootImage("images/sussy.png");

    private int velocity = 0; // The character's vertical velocity
    private int gravity = 1; // Gravity pulling the character down
    private int maxJumpVelocity = -15; // Maximum upward velocity for a fully charged jump
    private int minJumpVelocity = -5; // Initial jump velocity for a short jump
    private int jumpCharge = 0; // Tracks how long the jump key is held
    private boolean isJumping = false; // Tracks if the character is in the air
    private boolean keyReleased = true; // Tracks if the jump key has been released

    // Image variables
    GreenfootImage[] idleRight = new GreenfootImage[6];
    GreenfootImage[] idleLeft = new GreenfootImage[6];
    GreenfootImage[] runRight = new GreenfootImage[6];
    GreenfootImage[] runLeft = new GreenfootImage[6];
    // Variables to handle direction cat is facing
    String facing = "right";
    Boolean isMoving = false;
    SimpleTimer animationTimer = new SimpleTimer();

    public Avatar() {
    
        // Load idleRight images (starting from 0 to 5)
        for(int i = 0; i < 6; i++) {  // Fixed the starting index here
            idleRight[i] = new GreenfootImage("images/sprites/avatar/idle/idle" + (i + 1) + ".png");
            idleRight[i].scale(100, 100);
        }
    
        // Load idleLeft images (mirroring them horizontally)
        for(int i = 0; i < 6; i++) {  
            idleLeft[i] = new GreenfootImage("images/sprites/avatar/idle/idle" + (i + 1) + ".png");
            idleLeft[i].mirrorHorizontally();
            idleLeft[i].scale(100, 100); 
        }
    
        // Load runRight images
        for(int i = 0; i < 6; i++) {
            runRight[i] = new GreenfootImage("images/sprites/avatar/run/run" + (i + 1) + ".png");
            runRight[i].scale(100, 100);
        }
    
        // Load runLeft images (mirroring them horizontally)
        for(int i = 0; i < 6; i++) {
            runLeft[i] = new GreenfootImage("images/sprites/avatar/run/run" + (i + 1) + ".png");
            runLeft[i].mirrorHorizontally();
            runLeft[i].scale(100, 100); 
        }
    
        // Set the initial image to idleRight[0]
        setImage(idleRight[0]);
        animationTimer.mark();
    }

    int imageIndex = 0;
    public void animateAvatar()
    {
        if(animationTimer.millisElapsed() < 100)
        {
            return;
        }
        animationTimer.mark();
        if(isMoving == false)
        {
            if(facing.equals("right"))
            {
                setImage(idleRight[imageIndex]);
                imageIndex = (imageIndex + 1) % idleRight.length;
            }
            else
            {
                setImage(idleLeft[imageIndex]);
                imageIndex = (imageIndex + 1) % idleLeft.length;
            }
        }
        else
        {
            if(facing.equals("right"))
            {
                setImage(runRight[imageIndex]);
                imageIndex = (imageIndex + 1) % runRight.length;
            }
            else
            {
                setImage(runLeft[imageIndex]);
                imageIndex = (imageIndex + 1) % runLeft.length;
            }
        }
    }

    public void checkWarp() {
        if (getX() < 1) {
            setLocation(getWorld().getWidth() - 2, getY()); // Warp to the right side
        }
        if (getX() > getWorld().getWidth() - 2) {
            setLocation(0, getY()); // Wrap to the left side
        }
    }

    public void checkKeys() {
        if (Greenfoot.isKeyDown("left")) {
            move(-5);
            facing = "left";
            if(isMoving == false) {
                isMoving = true;
                imageIndex = 0;
            }
        } else if (Greenfoot.isKeyDown("right")) {
            move(5);
            facing = "right";
            if(isMoving == false) {
                isMoving = true;
                imageIndex = 0;
            }
        } else {
            if(isMoving == true)
            {
                isMoving = false;
                imageIndex = 0;
            }
        }
    }

    public void checkJump() {
        boolean jumpKeyHeld = Greenfoot.isKeyDown("up");

        if (jumpKeyHeld && keyReleased) {
            if (!isJumping) {
                velocity = minJumpVelocity; // Start the jump
                isJumping = true;
                jumpCharge = 0; // Start charging the jump
            }
            keyReleased = false; // Prevent repeated jumping
        }

        // Handle jump charging while key is held
        if (jumpKeyHeld && isJumping) {
            // Tracks how long button was held (max 15 game cycles)
            if (jumpCharge < 15) {
                jumpCharge++;
                velocity--; // Increase jump strength
                if (velocity < maxJumpVelocity) {
                    velocity = maxJumpVelocity; // Cap the jump velocity
                }
            }
        }

        // Reset when key is released
        if (!jumpKeyHeld) {
            keyReleased = true; // Allow the next jump
        }
    }

    public void fall() {
        setLocation(getX(), getY() + velocity); // Updates location

        // If the character hits or sinks below the ground, reset
        if (getY() >= getWorld().getHeight() - 100) {
            setLocation(getX(), getWorld().getHeight() - 100); // Snap to ground level
            velocity = 0; // Stop falling
            isJumping = false; // Allow jumping again
        } else {
            velocity += gravity; // Apply gravity while in the air
        }
    }

    public void act() {
        fall();
        animateAvatar();
        checkJump();
        checkWarp();
        checkKeys();
        System.out.println(isMoving);
    }
}
