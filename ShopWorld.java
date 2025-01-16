import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class ShopWorld extends World
{
    public MyGame gameWorld;                        //holds game data from current game
    
    static Shield shield = new Shield();            //actor for shield powerup sprite
    static Fish fish = new Fish();                  //actor for fish collectable sprite
    
    public static int shopCoins = MyGame.numCoins;  //gets the number of coins from current game
    public static ScoreLabel coins;                 //displays coin count
    public static int shopFish = MyGame.numFish;    //gets the number of fish from current game
    
    SimpleTimer fundsTimer = new SimpleTimer();     //timer to track funds text display
    
    public ShopWorld(MyGame gameWorld) {    
        super(700, 400, 1);
        setBackground(new GreenfootImage("images/sprites/shopInside.png"));
        this.gameWorld = gameWorld; //gets the current game
        
        //displays coint count
        coins = new ScoreLabel(shopCoins, 35);
        addObject(coins, 670, 30);
        Coin coin = new Coin();
        coin.sleepFor(-1);
        addObject(coin, 610, 30);
        addObject(new TitleLabel(":", 30), 635, 30);
        
        //add powerups
        addObject(shield, 390, 230);
        addObject(fish, 200, 235);
        
        //returns to the current game
        addObject(new TitleLabel("Press [ESCAPE] to exit", 30), 360, 370);
    }
    
    public void act() {
        checkItems();
        returnToGame();
    }
    
    /**
     * checks if player buys powerups and if they have enough coins
     */
    public void checkItems() {
        TitleLabel funds = new TitleLabel("Insufficient Funds", 40);
        fundsTimer.mark();
        
        if(Greenfoot.mouseClicked(shield) && shopCoins - 5 >= 0) {
            removeObject(shield); //remove actor from screen
            shopCoins -= 5; //player "buys" the item
            coins.setValue(shopCoins); //change coin display
        } 
        else if(Greenfoot.mouseClicked(shield)) { //player doesn't have enough coins
            addObject(funds, 360, 100);
        }
        if(Greenfoot.mouseClicked(fish) && shopCoins - 5 >= 0) {
            removeObject(fish); //remove actor from screen
            shopCoins -= 5; //player "buys" the item
            shopFish++; //count the new fish
            coins.setValue(shopCoins); //change coin display
        } 
        else if(Greenfoot.mouseClicked(fish)) { //player doesn't have enough coins
            addObject(funds, 360, 100);
        }
        
        if(fundsTimer.millisElapsed() > 200) { //remove text
            removeObject(funds);
            fundsTimer.mark();
        }
    }
    
    /**
     * returns the player to their game
     */
    public void returnToGame() {
        if(Greenfoot.isKeyDown("escape")) {
            gameWorld.coinLabel.setValue(shopCoins);
            Greenfoot.setWorld(gameWorld);
        }
    }
}
