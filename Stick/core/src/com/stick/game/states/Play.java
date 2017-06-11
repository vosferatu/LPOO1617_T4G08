package com.stick.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.stick.game.StickGame;
import com.stick.game.objects.Bird;
import com.stick.game.objects.Tube;
import com.stick.game.scenes.Hud;

/**
 * play screen
 */

public class Play extends State {

    private Bird bird;
    private Texture back, ground;
    private static final int SPACE = 64, TUBES = 4, OFFSET = -20;
    private Vector2 pos1, pos2;
    private Hud hud;
    private int frames = 0;

    private Array<Tube> tubes = new Array<Tube>();


    public Play(StateManager manager){
        super(manager);
        hud = new Hud(StickGame.batch);
        bird = new Bird(25,100);
        camera.setToOrtho(false, StickGame.V_WIDTH/2, StickGame.V_HEIGHT/2);
        back = new Texture("bg.png");
        ground = new Texture("ground.png");

        for(int i = 1; i < TUBES+1; i++){
            tubes.add(new Tube(i* (SPACE+Tube.WIDTH)));
        }

        pos1 = new Vector2(camera.position.x - (camera.viewportWidth/2), OFFSET);
        pos2 = new Vector2((camera.position.x - (camera.viewportWidth/2))+ground.getWidth(), OFFSET);
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(back, camera.position.x-(camera.viewportWidth/2), 0, StickGame.V_WIDTH/2, StickGame.V_HEIGHT/2);
        batch.draw(bird.getTexture(), bird.getPos().x, bird.getPos().y,
                bird.getTexture().getRegionWidth()/2, bird.getTexture().getRegionHeight()/2, bird.getTexture().getRegionWidth(),
                bird.getTexture().getRegionHeight(), 1, 1, bird.getRotation());
        batch.draw(ground, pos1.x, pos1.y); batch.draw(ground, pos2.x, pos2.y);

        for(Tube tube : tubes){
            batch.draw(tube.getTop(), tube.getPosTop().x, tube.getPosTop().y);
            batch.draw(tube.getBot(), tube.getPosBot().x, tube.getPosBot().y);
        }
        batch.end();

        batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
    }

    @Override
    public void update(float dt) {
        handleInput();
        updateGround();
        bird.update(dt);
        camera.position.x = bird.getPos().x + 40;
        frames++;

        for(Tube tube : tubes){
            if(camera.position.x - (camera.viewportWidth/2) > tube.getPosTop().x + tube.getTop().getWidth()){
                tube.reposition(tube.getPosTop().x+((Tube.WIDTH+SPACE)*TUBES));
            }
            if(tube.collision(bird.getRect())){
                manager.pop(); this.dispose(); manager.push(new Over(manager, hud.getScore()));
                break;
            }
            if(tube.point(bird.getRect()) && frames > 60) {
                hud.addScore(); frames = 0;
            }
        }

        if(bird.getPos().y <= ground.getHeight()+OFFSET){
            manager.pop(); this.dispose(); manager.push(new Over(manager, hud.getScore()));
        }

        camera.update();
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched() || Gdx.input.getGyroscopeX() != 0 ||
                Gdx.input.getGyroscopeY() != 0 || Gdx.input.getGyroscopeZ() != 0){
            bird.jump();
        }
    }

    @Override
    public void dispose() {
        bird.dispose();
        ground.dispose();
        for(Tube tube : tubes) tube.dispose();
        back.dispose();
        if(hud.getScore() > StickGame.prefs.getInteger("highscore"))
            StickGame.prefs.putInteger("highscore", hud.getScore());
        System.out.println(""+StickGame.prefs.getInteger("highscore"));
        hud.dispose();
    }

    private void updateGround(){
        if(camera.position.x -(camera.viewportWidth/2) > pos1.x + ground.getWidth()){
            pos1.add(ground.getWidth()*2, 0);
        }
        if(camera.position.x -(camera.viewportWidth/2) > pos2.x + ground.getWidth()){
            pos2.add(ground.getWidth()*2, 0);
        }
    }
}
