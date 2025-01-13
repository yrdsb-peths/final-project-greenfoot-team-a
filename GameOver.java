import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class GameOver extends World
{
    Label gameOverLabel = new Label("Game Over", 90);
    Label highScoresLabel = new Label("Recent Scores: ", 35);
    Label instructionLabel = new Label("Press [ENTER] to play again", 40);
    
    public GameOver()
    {    
        super(600, 400, 1); 
        setBackground(new GreenfootImage("FinalISPBackground.png"));
        
        addObject(gameOverLabel, 295, 75);
        addObject(highScoresLabel, 295, 140);
        addObject(instructionLabel, 300, 360);
    }
    
    public void act() {
        if(Greenfoot.isKeyDown("enter")) {
            Greenfoot.setWorld(new MyGame());
        }
    }
}
