import greenfoot.*;

public class MyWorld extends World 
{    
    public MyWorld() {
        super(400, 600, 1);
        Avatar avatar = new Avatar();
        addObject(avatar, 200, 300);

    }
}
