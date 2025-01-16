import greenfoot.*;

public class MyGame extends World
{
    public static Level level;                //current level active
    public static int speed;                  //speed of platforms 
    
    //boolean variables tracking active powerups
    public static boolean boost = false;                
    public static boolean shield = false;   
    
    public static int numCoins = 0;                      //track number of coins
    public static int score = 0;                         //track player score
    static ScoreLabel scoreLabel;                        //displays current score
    public static int numFish = 0;                       //track fish score
    static ScoreLabel fishLabel;                         //displays current fish count
    
    public MyGame()
    {
        super(400,600,1, false);
        
        setBackground("mainBackground.png");
        
        scoreLabel = new ScoreLabel(score, 30);
        addObject(scoreLabel, 350, 20);
        addObject(new Label("Score: ", 30), 270, 20);
        
        fishLabel = new ScoreLabel(numFish, 30);
        addObject(fishLabel, 380, 60);
        addObject(new Label("Fish: ", 30), 340, 60);
        
        level = new Level(-1);
        addObject(level, 0,0);

        Platform platform = new Platform(0);
        addObject(platform, getWidth()/2, 590);
    }

    public void act()
    {
        
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
