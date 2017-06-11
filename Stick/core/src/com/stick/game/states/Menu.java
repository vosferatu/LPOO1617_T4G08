package com.stick.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.stick.game.StickGame;

/**
 * menu
 */

public class Menu extends State {

    public Texture back;
    private Stage stage;
    private ImageButton play, sound;

    public Menu(StateManager manager){
        super(manager);
        back = new Texture("bg.png");
        Texture play = new Texture("play.png");
        Texture music = new Texture("sound.png");
        camera.setToOrtho(false, StickGame.V_WIDTH/2, StickGame.V_HEIGHT/2);
        Drawable drawable = new TextureRegionDrawable(new TextureRegion(play));
        Drawable drawab = new TextureRegionDrawable(new TextureRegion(music));
        this.stage = new Stage();
        this.play = new ImageButton(drawable);
        this.play.setSize(camera.viewportWidth/2, camera.viewportHeight/2);
        this.play.setPosition(camera.position.x+(camera.viewportWidth/4), camera.position.y + (camera.viewportHeight/4));
        sound = new ImageButton(drawab);
        sound.setSize(camera.viewportWidth/3, camera.viewportHeight/3);
        sound.setPosition(camera.position.x-camera.viewportWidth/2, camera.position.y-camera.viewportHeight/2);
        stage.addActor(this.play);
        stage.addActor(sound);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(back, 0, 0, camera.viewportWidth, camera.viewportHeight);
        batch.end();
        stage.draw();
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void handleInput() {
        if(play.isPressed()){
            manager.pop();
            dispose();
            manager.push(new Play(manager));
        }
        if(sound.isPressed()){
            if(StickGame.song.isPlaying())
                StickGame.song.pause();
            else StickGame.song.play();
        }
    }

    @Override
    public void dispose() {
        back.dispose();
    }
}
