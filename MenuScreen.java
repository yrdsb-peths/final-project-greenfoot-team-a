import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class MenuScreen extends World
{
    /**
     * Constructor for objects of class MenuScreen.
     * 
     */
    public MenuScreen()
    {    
        // Create a new world with 400x600 cells with a cell size of 1x1 pixels.
        super(400, 600, 1); 

        //add game title
        setBackground(new GreenfootImage("mainBackground.png"));
        addObject(new TitleLabel("Welcome to", 45),200, 150); 
        addObject(new TitleLabel("SCAREDY CAT", 55), 200, 200);
        addObject(new TitleLabel("Press the Start Button", 30),200, 320);
        addObject(new TitleLabel("to read the Instructions!", 30), 200, 350);
        
        //add start button
        addObject(new Button(this::startInstructions), 200, 450);
        addObject(new TitleLabel ("Start", 25), 197, 450);
        
        prepare();
    }

    private void startInstructions() 
    {
        Greenfoot.setWorld(new InstructionScreen());
    }
    
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        //add picture of avatar
        Avatar avatar = new Avatar();
        avatar.sleepFor(-1); //stop the avatar actor from calling act()
        addObject(avatar, 200, 260);
    }
}

