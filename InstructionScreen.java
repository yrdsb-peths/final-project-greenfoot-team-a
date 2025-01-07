import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class InstructionScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class InstructionScreen extends World
{

    private String[] instructions = {
        "In order to defeat the enemy, you must quickly type words that appear on a screen.",
        "then you must press space for a new word to appear",
        "The enemy will slowly move towards you and if the enemy touches you, you lose.",
        "Otherwise you win and the score you get depends on the word you type. ",
        "To begin the game, click the button, Next." 
    };
    private int currentIndex = 0;
    private Label instructionLabel;
    private Button nextButton;

    public InstructionScreen() {
        super(600, 400, 1);
        setBackground(new GreenfootImage("7481714.png"));
        instructionLabel = new Label(instructions[currentIndex]);
        addObject(instructionLabel, 300, 200); 

        addObject(new Button(this::previousInstruction), 100, 300); 
        addObject(new Button(this::nextInstruction), 500, 300); 
       
        if(currentIndex < 3)
        {
            addObject(new Label("Back"), 100, 270);
            addObject(new Label("Next"), 500, 270);
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
