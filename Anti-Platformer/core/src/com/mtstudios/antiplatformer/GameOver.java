package com.mtstudios.antiplatformer;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;

public class GameOver implements Screen{
	
	Stage stage;
	Image failMsg;
	Image fader;
		
	TextureAtlas playAtlas;
	Button playButton;
	ButtonStyle buttonStyle;
	Skin playSkin;
	
	Play play;
	Menu menu;
		
	SpriteBatch batch;
	Image bg;
	
	Game game;
	
	public GameOver(Game game){
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
		float scaleY = (Gdx.graphics.getHeight()/660f);;
		
		batch = new SpriteBatch();
				
		stage = new Stage();
		
		bg = new Image(new Texture(Gdx.files.internal("Menu BG.png")));
		bg.setPosition(0,0); 
		bg.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		failMsg = new Image(new Texture(Gdx.files.internal("GameOver.png")));
		failMsg.setSize(765*scaleX, 126*scaleY);
		failMsg.setPosition((Gdx.graphics.getWidth()/2) - (failMsg.getWidth()/2), Gdx.graphics.getHeight() - 150);		
		
		playSkin = new Skin();
		playAtlas = new TextureAtlas("buttons/button.pack");
		playSkin.addRegions(playAtlas);
		
		buttonStyle = new ButtonStyle();
		buttonStyle.up = playSkin.getDrawable("button");
		buttonStyle.over = playSkin.getDrawable("buttonpressed");
		buttonStyle.down = playSkin.getDrawable("buttonpressed");
		
		playButton = new Button(buttonStyle);
		playButton.setSize(246*scaleX, 102*scaleY);
		playButton.setPosition((Gdx.graphics.getWidth()/2) - (playButton.getWidth()/2), Gdx.graphics.getHeight()/2);
		
		stage.addActor(bg);
		stage.addActor(failMsg);
		stage.addActor(playButton);
		
		stage.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(0.5f)));
		
		Gdx.input.setInputProcessor(stage);
		
		playButton.addListener(new InputListener(){
			public boolean touchDown(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y, int pointer, int button) {
				stage.addAction(Actions.sequence(Actions.fadeOut(0.25f), Actions.run(new Runnable(){
					public void run(){
						game.setScreen(new Play(game));
					}
				})));
				return true;
			}
		});
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
		Audio.dispose();
		
	}

}

