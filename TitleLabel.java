import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class TitleLabel extends Actor
{
    private String text;
    private int fontSize;
    private Color lineColor = Color.WHITE;
    private Color fillColor = Color.BLACK;
    private static final Color transparent = new Color(0,0,0,0);
    
    public TitleLabel(String text, int fontSize) {
        this.text = text;
        this.fontSize = fontSize;
        updateImage();
    }
    
    private void updateImage() {
        setImage(new GreenfootImage(text, fontSize, fillColor, transparent, lineColor));
    }
}
