import greenfoot.*;

public class MyGame extends World
{
    public static Level level;                //current level active
    public static int speed;                  //speed of platforms 
    
    //boolean variables tracking active powerups
    public static boolean boost = false;                
    public static boolean shield = false;   
    
    public static int numCoins = 0;                      //track number of coins
    public static ScoreLabel coinLabel;                  //displays current coin count
    public static int score = 0;                         //track player score
    public static ScoreLabel scoreLabel;                 //displays current score
    public static int numFish = 0;                       //track fish score
    public static ScoreLabel fishLabel;                  //displays current fish count
    
    public MyGame()
    {
        super(400,600,1, false);
        
        setBackground("mainBackground.png");
        
        Avatar avatar = new Avatar();
        addObject(avatar, 200, 550);
        
        //displays score on  game world
        scoreLabel = new ScoreLabel(score, 35);
        addObject(scoreLabel, 350, 20);
        addObject(new Label("Score: ", 30), 270, 20);
        
        //displays coin count on game world
        coinLabel = new ScoreLabel(numCoins, 35);
        addObject(coinLabel, 370, 55);
        Coin coin = new Coin();
        coin.sleepFor(-1);
        addObject(coin, 315, 55);
        addObject(new Label(":", 30), 340, 55);
        
        //displays fish count on game world
        fishLabel = new ScoreLabel(numFish, 35);
        addObject(fishLabel, 380, 100);
        Fish fish = new Fish();
        fish.sleepFor(-1);
        addObject(fish, 330, 100);
        addObject(new Label(": ", 30), 355, 100);
        
        level = new Level(-1);
        addObject(level, 0,0);

        Platform platform = new Platform(0);
        addObject(platform, getWidth()/2, 590);
    }

    public void act() {
        coinLabel.setValue(ShopWorld.shopCoins);
        fishLabel.setValue(ShopWorld.shopFish);
    }
    
    public void enterShop() {
        Greenfoot.setWorld(new ShopWorld(this));
    }
    
    public static void increaseCoins() {
        numCoins++;
    }
    
    public static void increaseScore(int addScore) {
        score += addScore;
        scoreLabel.setValue(score);
    }
    
    public static void increaseFish(int addFish) {
        numFish += addFish;
        fishLabel.setValue(numFish);
    }
}
