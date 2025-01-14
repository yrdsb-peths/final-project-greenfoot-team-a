import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class InstructionScreen extends World
{
    //Instructions for the Game
    private String [] instructions = {
        "To play this game, you must use the _____ buttons to jump from one platform to another",
        "During the game, you will encounter cat enemies you must dodge and coin to collect. ",
        "You must also choose between going into the shop for weapons",
        "Or using a trampoline for boost for the next level",
        "To begin the game, click the button, Next." 
    };
    
    // Keep track of Instructions
    private int currentIndex = 0;
    
    // Instruction Screen Label
    private Label instructionLabel;
    
    // New button
    private Button nextButton;

    public InstructionScreen() {
        super(600, 400, 1);
        setBackground(new GreenfootImage("FinalISPBackground.png"));
        
        //The Instructions of the Game
        instructionLabel = new Label(instructions[currentIndex]);
        addObject(instructionLabel, 300, 200); 
        
        // Button for back and next button for Instruction Screen
        addObject(new Button(this::previousInstruction), 90, 325); 
        addObject(new Button(this::nextInstruction), 510, 325); 
       
        if(currentIndex < 3)
        {
            //Instruction Button Label
            addObject(new Label("Back"), 90, 325);
            addObject(new Label("Next"), 510, 325);
        }
        
    }

    //Keep track of back button and instructions
    private void previousInstruction() {
        if (currentIndex > 0) {
            currentIndex--;
            updateInstruction();
        }
    }
    
    //Keep track of next button, instructions and whether player wants to start game
    private void nextInstruction() {
        if (currentIndex == instructions.length - 1) {
            startGame();
        }
        else
        {
            currentIndex++;
            updateInstruction();
        }
    }
    
    //Starts the game and goes to game screen
    public void startGame() {
        Greenfoot.setWorld(new MyGame());
    }

    //Update instructions based on what player clicks with mouse
    private void updateInstruction() {
        instructionLabel.setText(instructions[currentIndex]);
    }
}
