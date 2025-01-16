import greenfoot.*;

public class MyGame extends World
{
    public static Level level;                //current level active
    public static int speed;                  //speed of platforms 
    
    //boolean variables tracking active powerups
    public static boolean shield = false;   
    
    public static int score = 0;                         //track player score
    static ScoreLabel scoreLabel;                        //displays current score
    public static int numFish = 0;                       //track fish score
    static ScoreLabel fishLabel;                         //displays current fish count
    
    public static boolean start = false;

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
        
        for(int i = 0; i < 6; i++)
        {
            int xPos = Greenfoot.getRandomNumber(getWidth() / 2);
            if(i == 0)
            {
                xPos = getWidth() / 2;
            }
            addObject(new Platform(i, -1), xPos, 590 - (i* 110));
        }
    }

    public void act()
    {
        
    }
    
    public void initialSetip()
    {
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
