import greenfoot.*;

public class MyGame extends World
{
    public static Level level;                          //current level active
    public static int speed;                            //speed of platforms 
    
    //boolean variables tracking active powerups
    public static boolean boost = false;                
    public static boolean shield = false;   
    
    public static int score = 0;                         //track player score
    static ScoreLabel scoreLabel;
    
    public MyGame()
    {
        super(400,600,1, false);

        setBackground(new GreenfootImage("FinalISPBackground.png"));
        Greenfoot.setWorld(new MenuScreen());

        level = new Level(1);
        addObject(level, 0,0);
        
        scoreLabel = new ScoreLabel(score, 70);
        addObject(scoreLabel, 300, 70);
    }

    public void act()
    {
    }
    
    public static void increaseScore(int addScore) {
        score += addScore;
        scoreLabel.setValue(score);
    }
    
}
