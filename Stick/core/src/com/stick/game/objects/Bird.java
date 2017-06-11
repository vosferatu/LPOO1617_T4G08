package com.stick.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * represents the bird
 */

public class Bird {

    private Vector2 pos, speed;
    private static final int GRAVITY = -15, MOVE = 50;
    private Rectangle rect;
    private Animation birdAnim;
    private static Sound wing;

    public Bird(int x, int y){
        pos = new Vector2(x,y);

        speed = new Vector2(0,0);
        Texture text = new Texture("birdanim.png");
        birdAnim = new Animation(new TextureRegion(text), 3, 0.5f);

        rect = new Rectangle(pos.x, pos.y, text.getWidth()/3, text.getHeight());
        wing = Gdx.audio.newSound(Gdx.files.internal("wing.ogg"));
    }

    public void update(float dt){
        birdAnim.update(dt);

        if(pos.y > 0) speed.add(0, GRAVITY);

        speed.scl(dt); pos.add(MOVE * dt, speed.y); speed.scl(1/dt);

        if(pos.y < 0) pos.y = 0;

        rect.setPosition(pos.x, pos.y);
    }

    public void jump(){
        speed.y = 230;
        //wing.play(0.25f);
    }

    public Rectangle getRect(){
        return rect;
    }

    public Vector2 getPos() {
        return pos;
    }

    public TextureRegion getTexture() {
        return birdAnim.getFrame();
    }

    public void dispose(){
        //birdAnim.dispose();
        wing.dispose();
    }

    public float getRotation() {
        if (speed.y >= 45) return 45f;
        if (speed.y < -45) return -45f;
        return speed.y;
    }

}
