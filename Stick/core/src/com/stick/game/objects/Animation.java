package com.stick.game.objects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

import javax.xml.soap.Text;

/**
 * makes the bird flap
 */

public class Animation {
    private Array<TextureRegion> frames;
    private float maxTime, currentTime;
    private int frameCount, frame;

    public Animation(TextureRegion text, int count, float cycleTime){
        frames = new Array<TextureRegion>();
        int frameWidth = text.getRegionWidth()/count;
        for(int i = 0; i < count; i++){
            frames.add(new TextureRegion(text, i*frameWidth, 0, frameWidth, text.getRegionHeight()));
        }
        frameCount = count;
        maxTime = cycleTime/count;
        frame = 0;
    }

    public void update(float dt){
        currentTime += dt;
        if(currentTime > maxTime){
            frame++;
            currentTime = 0;
        }

        if(frame >= frameCount) frame = 0;
    }

    public TextureRegion getFrame(){
        return frames.get(frame);
    }
}
