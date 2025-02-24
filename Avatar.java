import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * This class provides the controls and sprite for our main player avatar.
 * 
 * @Maggie Jiang
 * 
 */

public class Avatar extends Actor {
    // Gameplay variables
    private int velocity = 0; // The character's vertical velocity
    private int gravity = 1; // Gravity pulling the character down
    private int maxJumpVelocity = -30; // Maximum upward velocity for a fully charged jump
    private int minJumpVelocity = -10; // Initial jump velocity for a short jump
    private int jumpCharge = 0; // Tracks how long the jump key is held
    private boolean isJumping = false; // Tracks if the character is in the air
    private boolean keyReleased = true; // Tracks if the jump key has been released
    public static boolean isDead = false; // Tracks if Avatar dies
    public static boolean isDamaged = false; // Tracks if Avatar is damaged
    public static boolean onGround = true;
    private boolean wasOnPlatform = false;
    private boolean isMoving = false;
    public static String facing = "right";
    public boolean isOnGround = true;
    
    World currentWorld = getWorld();

    // Image variables
    GreenfootImage[] idleRight = new GreenfootImage[6];
    GreenfootImage[] idleLeft = new GreenfootImage[6];
    
    GreenfootImage[] runRight = new GreenfootImage[6];
    GreenfootImage[] runLeft = new GreenfootImage[6];

    GreenfootImage[] jumpRight = new GreenfootImage[7];
    GreenfootImage[] jumpLeft = new GreenfootImage[7];
    
    GreenfootImage[] deathRight = new GreenfootImage[7];
    GreenfootImage[] deathLeft = new GreenfootImage[7];
    
    GreenfootImage[] damageRight = new GreenfootImage[4];
    GreenfootImage[] damageLeft = new GreenfootImage[4];
    
    // Animation timer
    SimpleTimer animationTimer = new SimpleTimer();

    public Avatar() {
        isDead = false;
        // Load idleRight image
        for (int i = 0; i < 6; i++) {  // Fixed the starting index here
            idleRight[i] = new GreenfootImage("images/sprites/avatar/idle/idle" + (i + 1) + ".png");
            idleRight[i].scale(52, 42);
        }

        // Load idleLeft images
        for (int i = 0; i < 6; i++) {  
            idleLeft[i] = new GreenfootImage("images/sprites/avatar/idle/idle" + (i + 1) + ".png");
            idleLeft[i].mirrorHorizontally();
            idleLeft[i].scale(52, 42); 
        }

        // Load runRight images
        for (int i = 0; i < 6; i++) {
            runRight[i] = new GreenfootImage("images/sprites/avatar/run/run" + (i + 1) + ".png");
            runRight[i].scale(52, 42);
        }

        // Load runLeft images
        for (int i = 0; i < 6; i++) {
            runLeft[i] = new GreenfootImage("images/sprites/avatar/run/run" + (i + 1) + ".png");
            runLeft[i].mirrorHorizontally();
            runLeft[i].scale(52,42); 
        }

        // Load jumpRight images
        for (int i = 0; i < 7; i++) {
            jumpRight[i] = new GreenfootImage("images/sprites/avatar/jump/jump" + (i + 1) + ".png");
            jumpRight[i].scale(53,42);
        }

        // Load jumpLeft images
        for (int i = 0; i < 7; i++) {
            jumpLeft[i] = new GreenfootImage("images/sprites/avatar/jump/jump" + (i + 1) + ".png");
            jumpLeft[i].mirrorHorizontally();
            jumpLeft[i].scale(53, 42); 
        }

        // Load deathRight images
        for (int i = 0; i < 7; i++) {
            deathRight[i] = new GreenfootImage("images/sprites/avatar/death/death" + (i + 1) + ".png");
            deathRight[i].scale(80, 52);
        }

        // Load deathLeft images
        for (int i = 0; i < 7; i++) {
            deathLeft[i] = new GreenfootImage("images/sprites/avatar/death/death" + (i + 1) + ".png");
            deathLeft[i].mirrorHorizontally();
            deathLeft[i].scale(80, 52);
        }

        // Load damageRight images
        for (int i = 0; i < 4; i++) {
            damageRight[i] = new GreenfootImage("images/sprites/avatar/damage/damage" + (i + 1) + ".png");
            damageRight[i].scale(52,42);
        }

        // Load damageLeft images
        for (int i = 0; i < 4; i++) {
            damageLeft[i] = new GreenfootImage("images/sprites/avatar/damage/damage" + (i + 1) + ".png");
            damageLeft[i].mirrorHorizontally();
            damageLeft[i].scale(52,42);
        }

        // Set the initial image to idleRight[0]
        setImage(idleRight[0]);
        animationTimer.mark();
    }

    private int imageIndex = 0;
    private int damageLoops = 0;
    public void animateAvatar() {
        // Ensures the loop breaks when enough time is passed
        if (animationTimer.millisElapsed() < 100) {
            return;
        }
        animationTimer.mark();
        
        World currentWorld = getWorld();
        
        // If the avatar is dead, play the death animation and stop after the last frame
        if (isDead) {
            if (currentWorld instanceof MyGame){
                int avatarX = getX();
                Greenfoot.setWorld(new FallingWorld(avatarX, facing));
            }
            if (facing.equals("right")) {
                imageIndex = imageIndex % deathRight.length;
                setImage(deathRight[imageIndex]);
                imageIndex++;

                if (imageIndex >= deathRight.length) {
                    imageIndex = deathRight.length - 1; // Keep on the last frame
                    // Set the image to the last frame of the death animation
                    setImage(deathRight[imageIndex]);
                    // Stops game here
                    return;
                }
            } else {
                imageIndex = imageIndex % deathLeft.length;
                setImage(deathLeft[imageIndex]);
                imageIndex++;

                if (imageIndex >= deathLeft.length) {
                    imageIndex = deathLeft.length - 1; // Keep on the last frame
                    // Set the image to the last frame of the death animation
                    setImage(deathLeft[imageIndex]);
                    // Stops game here
                    return;
                }
            }
            return;
        }
        
        // Loops through the damaged animation here
        if (isDamaged) {
            if (facing.equals("right")) {
                imageIndex = imageIndex % damageRight.length;
                setImage(damageRight[imageIndex]);
                imageIndex++;
            } else {
                imageIndex = imageIndex % damageLeft.length;
                setImage(damageLeft[imageIndex]);
                imageIndex++;
            }

            // Check if two loops of the damage animation have completed
            if (imageIndex >= damageRight.length || imageIndex >= damageLeft.length) {
                damageLoops++; // Increase loop count
                imageIndex = 0; // Reset animation index for the next loop

                if (damageLoops >= 2) {
                    isDamaged = false; // Turn off the damage state after two loops
                    damageLoops = 0; // Reset loop count for next damage animation
                }
            }
            return; // Exit early to prevent further updates
        }

        // Loops through the jumping animations here
        if (isJumping) {
            if (facing.equals("right")) {
                imageIndex = imageIndex % jumpRight.length;
                setImage(jumpRight[imageIndex]);
                imageIndex++;
            } else {
                imageIndex = imageIndex % jumpLeft.length;
                setImage(jumpLeft[imageIndex]);
                imageIndex++;
            }
            return;
        }

        // Loops idle animations
        if (!isMoving) {
            if (facing.equals("right")) {
                imageIndex = imageIndex % idleRight.length;
                setImage(idleRight[imageIndex]);
                imageIndex++;
            } else {
                imageIndex = imageIndex % idleLeft.length;
                setImage(idleLeft[imageIndex]);
                imageIndex++;
            }
            return;
        }

        // Loops running animations
        if (isMoving) {
            if (facing.equals("right")) {
                imageIndex = imageIndex % runRight.length;
                setImage(runRight[imageIndex]);
                imageIndex++;
            } else {
                imageIndex = imageIndex % runLeft.length;
                setImage(runLeft[imageIndex]);
                imageIndex++;
            }
            return;
        }
    }

    public void checkWarp() {
        if (getX() < 1) {
            setLocation(getWorld().getWidth() - 2, getY()); // Warp to the right side if off screen
        }
        if (getX() > getWorld().getWidth() - 2) {
            setLocation(0, getY()); // Wrap to the left side if off screen
        }
    }

    public void checkKeys() {
        if (Greenfoot.isKeyDown("D") && !isDead){
            isDead = true; // Changes boolean given condition
            imageIndex = 0;
        }
        
        
        if (Greenfoot.isKeyDown("F") && !isDamaged) {
            isDamaged = true; // Changes boolean given condition
            imageIndex = 0;
        }

        if (Greenfoot.isKeyDown("left") && !isDead) {
            move(-5); // Moves to the left
            facing = "left"; // Changes variable to "left"
            if (!isMoving) {
                isMoving = true; // Changes boolean to moving
                imageIndex = 0; // Resets the animation
            }
        } else if (Greenfoot.isKeyDown("right") && !isDead) {
            move(5); // Moves to the right
            facing = "right"; // Changes variable to "right"
            if (!isMoving) {
                isMoving = true; // Changes boolean to moving
                imageIndex = 0; // resets the animation
            }
        } else {
            if (isMoving) {
                isMoving = false; // Changes boolean if no input detected
                imageIndex = 0;
            }
        }
    }

    public void checkJump() {
        boolean jumpKeyHeld = Greenfoot.isKeyDown("up");
    
        // Jump if the key is pressed and released, the player is not dead, and it is not already jumping
        if (jumpKeyHeld && keyReleased && !isDead && isOnGround) {
            // Only start the jump if we aren't already jumping
            if (!isJumping) {
                velocity = minJumpVelocity; // Start the jump with a minimum velocity
                isJumping = true;  // Set jumping flag to true
                jumpCharge = 0;  // Start charging the jump (if needed)
                keyReleased = false;  // Prevent repeated jumping while key is held
            }
        }
    
        // Handle jump charging while the jump key is held down
        if (jumpKeyHeld && isJumping) {
            if (jumpCharge < 15) {  // Limit the jump charge (optional)
                jumpCharge++;
                velocity--; // Increase jump velocity (going up)
                if (velocity < maxJumpVelocity) {
                    velocity = maxJumpVelocity; // Cap the velocity to prevent excessive jump height
                }
            }
        }
    
        // Reset key release state when the jump key is released
        if (!jumpKeyHeld) {
            keyReleased = true;  // Allow for a new jump next time the key is pressed
        }
    
        // Optionally limit the maximum velocity (if needed)
        velocity = Math.min(velocity, 20);
    }


    public void fall() {
        int avatarWidth = getImage().getWidth();
        int avatarHeight = getImage().getHeight();
        
        int firstTouch = 0;
    
        // Check for platforms directly below the Avatar
        int offsetY = avatarHeight / 2; // Check from the center downwards
        
        // Find the platform below the avatar
        Actor platform = getOneIntersectingObject(Platform.class);
    
        // No platform below, apply gravity and let avatar fall
        if (platform == null || (platform!=null&&velocity<=0)) {
            setLocation(getX(), getY() + velocity); 
            velocity += gravity;  // Apply gravity to velocity
            firstTouch=0;
    
            // If the avatar hits the ground, stop falling
            if (getY() >= getWorld().getHeight() - offsetY) {
                isOnGround=true;
                setLocation(getX(), getWorld().getHeight() - offsetY);  // Snap to ground
                velocity = 0; // Stop falling
                isDead = true;
                isJumping = false; // Allow jumping again
                MyGame.gameOver();
            }
        }
        else if (platform != null&&velocity>0) {
            if(velocity>0){
                if (firstTouch == 0){
                    setLocation(getX(), platform.getY() - (platform.getImage().getHeight() / 2) - offsetY);
                    firstTouch++;
                }
                    // If falling, ensure avatar is directly above the platform (at the top edge of platform)
                setLocation(getX(), getY() + velocity);
                isOnGround=true;
                velocity = MyGame.speed;  // Stop vertical movement (gravity or falling)
                isJumping = false;  // You're no longer jumping because you've landed
                if (velocity < 0) {
                    // If velocity is negative (jumping), allow upward movement
                    setLocation(getX(), getY() + velocity); // Move up if jumping
                    velocity--;  // Slow down the upward movement (gravity)
                }
            }
        }
    }
        
    public void collision() {
        Actor coin = getOneIntersectingObject(Coin.class); //assign interescting coin an actor
        if(coin != null) {
            getWorld().removeObject(coin); //remove coin actor that interesects with avatar
            MyGame.increaseScore(100); //increase score
            MyGame.increaseCoins(); //increase coin count
        }
        Actor enemy = getOneIntersectingObject(Enemy.class);
        if(enemy != null) {
            isDead = true;
            MyGame.gameOver();
        }
    }
        
    public void checkShop() {
        World currentWorld = getWorld();
    
        if (currentWorld instanceof MyGame) {
            MyGame gameWorld = (MyGame) currentWorld; // Safe cast after type check
            Label shopLabel = new Label("Press [ENTER] to enter shop", 20); // Instruct player how to enter shop
            
            Actor shop = getOneIntersectingObject(ShopIcon.class); // Check when avatar is near shop
            if (shop != null) {
                gameWorld.addObject(shopLabel, 200, 300); // Show instruction when near the shop
                if (Greenfoot.isKeyDown("enter")) {
                    gameWorld.removeObject(shopLabel); // Remove instruction
                    gameWorld.enterShop(); // Switch to ShopWorld screen
                }
            }
            gameWorld.removeObject(shopLabel); // Remove instruction if not near shop
        }
    }
    
    public void act() {
        fall();
        animateAvatar();
        checkJump();
        checkWarp();
        checkKeys();
        collision();
        checkShop();
    }
}
