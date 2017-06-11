package com.stick.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.stick.game.StickGame;

/**
 * game over state
 */

public class Over extends State {

    private int score = 0;
    public Texture back, over;
    BitmapFont font = new BitmapFont();

    public Over(StateManager manager, int score) {
        super(manager);
        back = new Texture("bg.png");
        over = new Texture("over.png");
        camera.setToOrtho(false, StickGame.V_WIDTH/2, StickGame.V_HEIGHT/2);
        this.score = score;
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(back, 0, 0, camera.viewportWidth, camera.viewportHeight);
        batch.draw(over, camera.position.x-(over.getWidth()/2), camera.position.y);
        font.draw(batch, "Score: " + score,
                camera.position.x-(over.getWidth()/2), camera.position.y -(over.getHeight()*2));
        font.draw(batch, "Highscore: "+StickGame.prefs.getInteger("highscore"),
                camera.position.x-(over.getWidth()/2), camera.position.y +over.getHeight()*3);
        batch.end();
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            manager.pop();
            dispose();
            manager.push(new Menu(manager));
        }
    }

    @Override
    public void dispose() {
        back.dispose();
        over.dispose();
        font.dispose();
    }
}
