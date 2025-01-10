import greenfoot.*;

public class MyWorld extends World 
{    
    public MyWorld() {
        Avatar avatar = new Avatar();
        addObject(avatar, 200, 300);

        super(400, 600, 1);
    }
}
