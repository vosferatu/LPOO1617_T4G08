package com.stick.game.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.awt.TexturePaint;
import java.util.Random;

import javax.xml.soap.Text;

/**
 * represents a pair of tubes
 */

public class Tube {

    private Texture top, bot;
    private Vector2 posBot, posTop;
    private Random rand;
    private static final int MOD = 76, GAP = 45, OFFSET = 60;
    public static final int WIDTH = 26;
    private Rectangle topRect, botRect, midRect;

    public Tube(float x){
        bot = new Texture("bot.png"); top = new Texture("top.png");

        rand = new Random();

        posTop = new Vector2(x, rand.nextInt(MOD) + GAP + OFFSET);
        posBot = new Vector2(x, posTop.y - GAP - bot.getHeight());
        topRect = new Rectangle(posTop.x, posTop.y, top.getWidth(), top.getHeight());
        botRect = new Rectangle(posBot.x, posBot.y, bot.getWidth(), top.getHeight());
        midRect = new Rectangle(posTop.x+(WIDTH/2),posTop.y-GAP, 1, GAP);
    }

    public void reposition(float x){
        posTop.set(x, rand.nextInt(MOD) + GAP + OFFSET);
        posBot.set(x, posTop.y - GAP - bot.getHeight());

        topRect.setPosition(posTop.x, posTop.y);
        botRect.setPosition(posBot.x, posTop.y);
        midRect.setPosition(posBot.x+(WIDTH/2),posTop.y-GAP);
    }

    public boolean collision(Rectangle rect){
        return (topRect.overlaps(rect) || botRect.overlaps(rect));
    }

    public boolean point(Rectangle rect){
        return midRect.overlaps(rect);
    }

    public Texture getTop() {
        return top;
    }

    public Texture getBot() {
        return bot;
    }

    public Vector2 getPosBot() {
        return posBot;
    }

    public Vector2 getPosTop() {
        return posTop;
    }

    public void dispose(){
        bot.dispose();
        top.dispose();
    }

    public Rectangle getMid() {
        return midRect;
    }
}
