import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Fish extends Actor
{
    GreenfootImage fish = new GreenfootImage("images/sprites/powerup/fish.png");
    public Fish() {
        setImage(fish);
        fish.scale(150,150);
    }
}
