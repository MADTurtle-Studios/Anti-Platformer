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

	TextureAtlas playAtlas;
	Button playButton;
	ButtonStyle buttonStyle;
	Skin playSkin;

	TextureAtlas mutedAtlas;
	TextureAtlas unmutedAtlas;
	Button soundButton;
	ButtonStyle unmutedStyle;
	ButtonStyle mutedStyle;
	Skin mutedSkin;
	Skin unmutedSkin;

	boolean muted = false;

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
		float scaleY = (Gdx.graphics.getHeight()/660f);

		batch = new SpriteBatch();

		stage = new Stage();

		bg = new Image(new Texture(Gdx.files.internal("Menu BG.png")));
		bg.setPosition(0, 0);
		bg.setSize(1300*scaleX, 660*scaleY);

		failMsg = new Image(new Texture(Gdx.files.internal("GameOver.png")));
		failMsg.setSize(765*scaleX, 126*scaleY);
		failMsg.setPosition((Gdx.graphics.getWidth()/2) - (failMsg.getWidth()/2), (Gdx.graphics.getHeight()-(150*scaleY)));

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

		//Unmuted Start.
		unmutedSkin = new Skin();
		unmutedAtlas = new TextureAtlas("buttons/unmuted.pack");
		unmutedSkin.addRegions(unmutedAtlas);

		unmutedStyle = new ButtonStyle();
		unmutedStyle.up = unmutedSkin.getDrawable("button");
		unmutedStyle.over = unmutedSkin.getDrawable("buttonpressed");
		unmutedStyle.down = unmutedSkin.getDrawable("buttonpressed");
		//Unmuted End. Muted Start.
		mutedSkin = new Skin();
		mutedAtlas = new TextureAtlas("buttons/muted.pack");
		mutedSkin.addRegions(mutedAtlas);

		mutedStyle = new ButtonStyle();
		mutedStyle.up = mutedSkin.getDrawable("button");
		mutedStyle.over = mutedSkin.getDrawable("buttonpressed");
		mutedStyle.down = mutedSkin.getDrawable("buttonpressed");
		//Muted End. Sound Button Start.

		soundButton = new Button(unmutedStyle);
		soundButton.setSize(68*scaleX, 68*scaleX);
		soundButton.setPosition(Gdx.graphics.getWidth()- 75, Gdx.graphics.getHeight()-75);

		stage.addActor(bg);
		stage.addActor(failMsg);
		stage.addActor(playButton);
		stage.addActor(soundButton);
		
		stage.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(0.25f)));
		
		if(Audio.song.isPlaying()){
			soundButton.setStyle(unmutedStyle);
			Audio.song.play();
			Audio.song.setLooping(true);
			muted = false;
		}else{
			soundButton.setStyle(mutedStyle);
			Audio.song.pause();
			muted = true;
		}

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
		
		soundButton.addListener(new InputListener(){
			public boolean touchDown(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y, int pointer, int button) {
				if(muted){
					soundButton.setStyle(unmutedStyle);
					Audio.song.play();
					Audio.song.setLooping(true);
					muted = false;
				}else{
					soundButton.setStyle(mutedStyle);
					Audio.song.pause();
					muted = true;
				}
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

