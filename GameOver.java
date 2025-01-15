import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class GameOver extends World
{
    Label gameOverLabel = new Label("Game Over", 80);
    Label highScoresLabel = new Label("Recent Scores: ", 35);
    Label instructionLabel = new Label("Press [ENTER] to \nplay again", 45);
    
    public GameOver()
    {    
        super(400,600, 1); 
        setBackground(new GreenfootImage("mainBackground.png"));
        
        addObject(gameOverLabel, getWidth()/2, 75);
        addObject(highScoresLabel, getWidth()/2, 140);
        addObject(instructionLabel, getWidth()/2, 380);
    }
    
    public void act() {
        if(Greenfoot.isKeyDown("enter")) {
            Greenfoot.setWorld(new MyGame());
        }
    }
}
