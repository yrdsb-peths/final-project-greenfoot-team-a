import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Exit Button that will return you to the Game
 */
    public class ShopWorld extends World {
    public ShopWorld() {
        super(400, 600, 1);
        
        //Add Exit Button
        Button exitButton = new Button(() -> Greenfoot.setWorld(new MyGame())); 
        addObject(exitButton, 90, 325); 
        
    }
}

