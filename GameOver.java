import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class GameOver extends World
{
    TitleLabel gameOverLabel = new TitleLabel("Game Over", 80);
    TitleLabel highScoresLabel = new TitleLabel("Scores:              Fish: ", 40);
    TitleLabel instructionLabel = new TitleLabel("Press [ENTER] to \nplay again", 45);
    
    //tracks scores
    ScoreLabel scoreLabel1; 
    ScoreLabel scoreLabel2;
    ScoreLabel scoreLabel3;
    ScoreLabel scoreLabel4;
    ScoreLabel scoreLabel5;
    ScoreLabel scoreLabel6;
    
    //tracks fish collected
    ScoreLabel fishLabel1; 
    ScoreLabel fishLabel2;
    ScoreLabel fishLabel3;
    ScoreLabel fishLabel4;
    ScoreLabel fishLabel5;
    ScoreLabel fishLabel6;
    
    public GameOver()
    {    
        super(400,600, 1); 
        setBackground(new GreenfootImage("mainBackground.png"));
        
        addObject(gameOverLabel, getWidth()/2, 75);
        addObject(highScoresLabel, getWidth()/2, 140);
        addObject(instructionLabel, getWidth()/2, 550);
        
        //get scores
        Integer score1 = Scores.returnScoreValue(1);
        Integer score2 = Scores.returnScoreValue(2);
        Integer score3 = Scores.returnScoreValue(3);
        Integer score4 = Scores.returnScoreValue(4);
        Integer score5 = Scores.returnScoreValue(5);
        Integer score6 = Scores.returnScoreValue(6);
        
        //get number of fish
        Integer fish1 = Scores.returnFishValue(1);
        Integer fish2 = Scores.returnFishValue(2);
        Integer fish3 = Scores.returnFishValue(3);
        Integer fish4 = Scores.returnFishValue(4);
        Integer fish5 = Scores.returnFishValue(5);
        Integer fish6 = Scores.returnFishValue(6);
        
        //adds score if it exists
        if(score1 != null && fish1 != null) {
            scoreLabel1 = new ScoreLabel(score1, 35); 
            addObject(scoreLabel1, 65, 190); 
            fishLabel1 = new ScoreLabel(fish1, 35);
            addObject(fishLabel1, 350, 190);
        }
        if(score2 != null && fish2 != null) {
            scoreLabel2 = new ScoreLabel(score2, 35); 
            addObject(scoreLabel2, 65, 240); 
            fishLabel2 = new ScoreLabel(fish2, 35);
            addObject(fishLabel2, 350, 240);
        }
        if(score3 != null && fish3 != null) {
            scoreLabel3 = new ScoreLabel(score3, 35); 
            addObject(scoreLabel3, 65, 290); 
            fishLabel3 = new ScoreLabel(fish3, 35);
            addObject(fishLabel3, 350, 290);
        }
        if(score4 != null && fish4 != null) {
            scoreLabel4 = new ScoreLabel(score4, 35); 
            addObject(scoreLabel4, 65, 340); 
            fishLabel4 = new ScoreLabel(fish4, 35);
            addObject(fishLabel4, 350, 340);
        }
        if(score5 != null && fish5 != null) {
            scoreLabel5 = new ScoreLabel(score5, 35); 
            addObject(scoreLabel5, 65, 390); 
            fishLabel5 = new ScoreLabel(fish5, 35);
            addObject(fishLabel5, 350, 390);
        }
        if(score6 != null && fish6 != null) {
            scoreLabel6 = new ScoreLabel(score6, 35); 
            addObject(scoreLabel6, 65, 440); 
            fishLabel6 = new ScoreLabel(fish6, 35);
            addObject(fishLabel6, 350, 440);
        }
    }
    
    public void act() {
        if(Greenfoot.isKeyDown("enter")) {
            //reset stats
            MyGame.resetScore();
            MyGame.resetCoins();
            MyGame.resetFish();
            //go back to new game
            Greenfoot.setWorld(new MyGame());
        }
    }
}
