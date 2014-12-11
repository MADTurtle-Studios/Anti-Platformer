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
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.act();

		batch.begin();
		stage.draw();	
		batch.end();

	}

	@Override
	public void resize(int width, int height) {
		float scaleX = (Gdx.graphics.getWidth()/1300f);
		float scaleY = (Gdx.graphics.getHeight()/660f);
		
		batch = new SpriteBatch();
		stage = new Stage();
		
		logo = new Image(new Texture(Gdx.files.internal("Title.png")));
		logo.setSize(250*scaleX, 250*scaleY);
		logo.setPosition((Gdx.graphics.getWidth()/2) - (logo.getWidth()/2), Gdx.graphics.getHeight()/2);
		
		stage.addActor(logo);

	}

	@Override
	public void show() {
		float scaleX = (Gdx.graphics.getWidth()/1300f);
		float scaleY = (Gdx.graphics.getHeight()/660f);

		batch = new SpriteBatch();
		stage = new Stage();
		
		logo = new Image(new Texture(Gdx.files.internal("Title.png")));
		logo.setSize(765*scaleX, 126*scaleY);
		logo.setPosition((Gdx.graphics.getWidth()/2) - (logo.getWidth()/2), Gdx.graphics.getHeight()/2);
		
		stage.addActor(logo);
		
		if (true){
			stage.addAction(Actions.sequence(Actions.delay(1f), Actions.fadeOut(0.25f), Actions.run(new Runnable(){
				public void run(){
					game.setScreen(new Menu(game));
				}
			})));
		}
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
