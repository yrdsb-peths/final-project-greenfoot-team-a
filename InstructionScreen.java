import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class InstructionScreen extends World
{
    private String [] instructions1 = {                    //first line of instructions
        "To play this game, you must use the arrow keys",
        "Your goal is to avoid running into the enemy birds",
        "After completing a level, you have the option of",
        "To enter the shop press [ENTER]!",
        "And that's it! Now try your best and have fun!" 
    };
    
    private String [] instructions2 = {                    //second line of instructions
        "to jump from one platform to the other.",
        "while collecting as many coins as you can!",
        "entering the shop and purchasing helpful items.",
        "Each item in the shop will cost 5 coins.",
        "To begin the game, click Next"
    };
    
    private int currentIndex = 0;             //tracks index of instructions
    private Label instructionLabel1;          //label for instructions1
    private Label instructionLabel2;          //label for instructions2
    private Button nextButton;                //button to move between screens

    public InstructionScreen() {
        super(400, 600, 1);
        setBackground(new GreenfootImage("mainBackground.png"));
        
        //updates instructions for current index
        instructionLabel1 = new Label(instructions1[currentIndex]);
        instructionLabel2 = new Label(instructions2[currentIndex]);
        addObject(instructionLabel1, 200, 175); //adds top label to the world
        addObject(instructionLabel2, 200, 225); //adds bottom label to the world

        addObject(new Button(this::previousInstruction), 75, 500); //spawn back button
        addObject(new Button(this::nextInstruction), 330, 500);    //spawn next button
       
        if(currentIndex < 3) //add text to button
        {
            addObject(new Label("Back"), 70, 500);
            addObject(new Label("Next"), 328, 500);
        }
    }

    /**
     * sets the world to the previous instruction screen
     */
    private void previousInstruction() {
        if (currentIndex <= 0) {
            titleScreen(); //go back to title screen if on first instruction screen
        } else {
            currentIndex--;
            updateInstruction();
        }
    }

    /**
     * sets the world to the next instruction screen
     */
    private void nextInstruction() {
        if (currentIndex == instructions1.length - 1) {
            startGame(); //start the game if there are no more instructions
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

    private void titleScreen() {
        Greenfoot.setWorld(new MenuScreen());
    }
    
    /**
     * changes instruction text
     */
    private void updateInstruction() {
        instructionLabel1.setText(instructions1[currentIndex]);
        instructionLabel2.setText(instructions2[currentIndex]);
    }
}
