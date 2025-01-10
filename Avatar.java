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
    private int maxJumpVelocity = -20; // Maximum upward velocity for a fully charged jump
    private int minJumpVelocity = -10; // Initial jump velocity for a short jump
    private int jumpCharge = 0; // Tracks how long the jump key is held
    private int maxChargeTime = 15; // Limit for charging the jump
    private boolean isJumping = false; // Tracks if the character is in the air
    private boolean keyReleased = true; // Tracks if the jump key has been released

    private int groundLevel; // Ground level based on the world's height
    
    private int frame = 0; 
    
    public Avatar() {
        setImage(sussyImage);
        sussyImage.scale(75, 80);
        groundLevel = getWorld() == null ? 0 : getWorld().getHeight() - 100;
    }

    @Override
    protected void addedToWorld(World world) {
        groundLevel = world.getHeight() - 100; // Initialize ground level after the actor is added
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
        }
        if (Greenfoot.isKeyDown("right")) {
            move(5);
        }
    }

    public void checkJump() {
        boolean jumpKeyHeld = Greenfoot.isKeyDown("up");

        // Handle jump initiation
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
            if (jumpCharge < maxChargeTime) {
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
        if (getY() >= groundLevel) {
            setLocation(getX(), groundLevel); // Snap to ground level
            velocity = 0; // Stop falling
            isJumping = false; // Allow jumping again
        } else {
            velocity += gravity; // Apply gravity while in the air
        }
    }
    
    public void animateRight() {
        
    }
    
    public void animateLeft() {
        
    }
    
    public void act() {
        fall();
        checkJump();
        checkWarp();
        checkKeys();
    }
}
