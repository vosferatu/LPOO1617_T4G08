package com.stick.game.states;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * control states
 */

public class StateManager {
    private Stack<State> states;
    private Sound wing;

    public StateManager(){
        states = new Stack<State>();
    }

    public void render(SpriteBatch batch){
        states.peek().render(batch);
    }

    public void update(float dt){
        states.peek().update(dt);
    }

    public void push(State state){
        states.push(state);
    }

    public void pop(){
        states.pop().dispose();
    }
}
