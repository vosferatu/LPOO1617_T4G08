package com.stick.game.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * 1 state
 */

public abstract class State {

    protected Vector2 pointer;
    protected OrthographicCamera camera;
    protected StateManager manager;

    public State(StateManager manager){
        this.manager = manager;
        this.pointer = new Vector2();
        this.camera = new OrthographicCamera();
    }

    public abstract void render(SpriteBatch batch);
    public abstract void update(float dt);
    protected abstract void handleInput();
    public abstract void dispose();
}
