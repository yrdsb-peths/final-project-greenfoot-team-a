import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class InstructionScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class InstructionScreen extends World
{

    private String [] instructions = {
        "To play this game, you must use the _____ buttons to jump from one platform to another",
        "During the game, you will encounter cat enemies you must dodge and coin to collect. ",
        "You must also choose between going into the shop for weapons",
        "Or using a trampoline for boost for the next level",
        "To begin the game, click the button, Next." 
    };
    
    private int currentIndex = 0;
    private Label instructionLabel;
    private Button nextButton;

    public InstructionScreen() {
        super(600, 400, 1);
        setBackground(new GreenfootImage("FinalISPBackground.png"));
        instructionLabel = new Label(instructions[currentIndex]);
        addObject(instructionLabel, 300, 200); 

        addObject(new Button(this::previousInstruction), 90, 325); 
        addObject(new Button(this::nextInstruction), 510, 325); 
       
        if(currentIndex < 3)
        {
            addObject(new Label("Back"), 90, 325);
            addObject(new Label("Next"), 510, 325);
        }
        
    }

    private void previousInstruction() {
        if (currentIndex > 0) {
            currentIndex--;
            updateInstruction();
        }
    }

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
    
    private void startGame() {
        Greenfoot.setWorld(new MyGame());
    }

    private void updateInstruction() {
        instructionLabel.setText(instructions[currentIndex]);
    }
}
