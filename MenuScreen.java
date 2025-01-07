import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MenuScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MenuScreen extends World
{

    /**
     * Constructor for objects of class MenuScreen.
     * 
     */
    public MenuScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        
       
         
        setBackground(new GreenfootImage("FinalISPBackground.png"));
        addObject(new Label ("Welcome to the __________ Game!"),300, 200); 
        addObject(new Label ("Click the Start Button to read the instructions and begin the game!"),300, 220);
        addObject(new Button(this::startInstructions), 300, 270); 
        
        String startTwo = "Start";
        addObject(new Label ("Start"), 300, 270);
    }

    private void startInstructions() 
    {
        Greenfoot.setWorld(new InstructionScreen());
    }
}

