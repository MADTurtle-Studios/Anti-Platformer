package com.mtstudios.antiplatformer;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class SplashScreen implements Screen{

	Stage stage;
	SpriteBatch batch;
	
	Image logo;
	
	Game game;

	public SplashScreen(Game game){
		this.game = game;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.act();

		batch.begin();
		stage.draw();	
		batch.end();

	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void show() {
		float scaleX = (Gdx.graphics.getWidth()/1300f);
		float scaleY = (Gdx.graphics.getHeight()/660f);

		batch = new SpriteBatch();
		stage = new Stage();

		logo = new Image(new Texture(Gdx.files.internal("Splash Screen.png")));
		logo.setSize(1300*scaleX, 660*scaleY);
		logo.setPosition(0, 0);

		stage.addActor(logo);
		
		stage.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(0.25f)));
		
		stage.addAction(Actions.sequence(Actions.delay(3f), Actions.fadeOut(0.5f), Actions.run(new Runnable(){
			public void run(){
				game.setScreen(new Menu(game));
			}
		})));
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
