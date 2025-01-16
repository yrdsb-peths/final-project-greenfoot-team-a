import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class ShopWorld extends World
{
    public MyGame gameWorld;
    static Shield shield = new Shield();
    static Fish fish = new Fish();
    
    public ShopWorld(MyGame gameWorld) {    
        super(700, 400, 1);
        setBackground(new GreenfootImage("images/sprites/shopInside.png"));
        this.gameWorld = gameWorld;
        
        addObject(shield, 390, 230);
        addObject(fish, 200, 240);
    }
    
    public void act() {
        checkItems();
    }
    
    public void checkItems() {
        if(Greenfoot.mouseClicked(shield)) {
            removeObject(shield);
            gameWorld.numCoins = gameWorld.numCoins - 5;
        }
        if(Greenfoot.mouseClicked(fish)) {
            removeObject(fish);
            gameWorld.numCoins = gameWorld.numCoins - 5;
        }
    }
    
    public void returnToGame() {
        Greenfoot.setWorld(gameWorld);
    }
}
