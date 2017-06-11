package com.stick.game.scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.stick.game.StickGame;

/**
 * Created by joaomendes on 6/4/17.
 */

public class Hud {
    public Stage stage;
    private Viewport viewport;

    private int score;
    Label scoreLabel;

    public Hud(SpriteBatch sb){
        this.score = 0;
        this.viewport = new FitViewport(StickGame.V_WIDTH, StickGame.V_HEIGHT, new OrthographicCamera());
        this.stage = new Stage(this.viewport, sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);//size of the stage

        this.scoreLabel = new Label (String.format("%03d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table.add(scoreLabel).expandX().padTop(10);

        this.stage.addActor(table);
    }

    public void addScore() {
        score++;
        scoreLabel.setText(String.format("%03d", score));
        System.out.println(""+score);
    }

    public void dispose(){
        stage.dispose();
    }

    public int getScore(){
        return score;
    }
}
