package com.stick.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.stick.game.states.Menu;
import com.stick.game.states.StateManager;

public class StickGame extends ApplicationAdapter {

    public static SpriteBatch batch;
    public static Preferences prefs;
    private StateManager manager;

    public static final int V_WIDTH = 250;
    public static final int V_HEIGHT = 400;
    public static final float PPM = 4f;

    public static Music song;

	@Override
	public void create () {
		batch = new SpriteBatch();
        prefs = Gdx.app.getPreferences("score");
        if(!prefs.contains("highscore"))
            prefs.putInteger("highscore", 0);
        manager = new StateManager();
        manager.push(new Menu(manager));
        song = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
        song.setLooping(true); song.setVolume(0.2f); song.play();
	}
	
	@Override
	public void dispose () {
        super.dispose();
		batch.dispose();
        song.dispose();
        prefs.flush();
	}

	@Override
    public void render(){
        super.render();
        Gdx.gl.glClearColor(15/255f,120/255f,230/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        manager.update(Gdx.graphics.getDeltaTime());
        manager.render(batch);
    }

    public SpriteBatch getBatch() {
        return batch;
    }
}