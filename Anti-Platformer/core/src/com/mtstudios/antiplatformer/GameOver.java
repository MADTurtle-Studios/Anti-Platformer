package com.mtstudios.antiplatformer;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

public class GameOver implements Screen{

	Stage stage;
	Image failMsg;

	TextureAtlas playAtlas;
	Button playButton;
	ButtonStyle buttonStyle;
	Skin playSkin;
	
	Label scoreMsg;
	LabelStyle style;
	BitmapFont font;
	
	Label highscoreMsg;
	
	TextureAtlas mutedAtlas;
	TextureAtlas unmutedAtlas;
	Button soundButton;
	ButtonStyle unmutedStyle;
	ButtonStyle mutedStyle;
	Skin mutedSkin;
	Skin unmutedSkin;

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
		playButton.setPosition((Gdx.graphics.getWidth()/2) - (playButton.getWidth()/2), (Gdx.graphics.getHeight()/2)-(100*scaleY));
		
		if(Play.score > Menu.prefs.getInteger("highscore")){
			Menu.prefs.putInteger("highscore", Play.score);
			Menu.prefs.flush();
		}
		
		font = new BitmapFont(Gdx.files.internal("font.fnt"), false);
		style = new LabelStyle(font, Color.WHITE);
		scoreMsg = new Label("Score: " + Play.score, style);
		scoreMsg.setFontScale(1*scaleX, 1*scaleY);
		scoreMsg.setPosition((650*scaleX) - (scoreMsg.getWidth()/((1300/Gdx.graphics.getWidth())*2.5f)), (Gdx.graphics.getHeight()/2)+(50*scaleY));

		highscoreMsg = new Label("Highscore: " + Menu.prefs.getInteger("highscore"), style);
		highscoreMsg.setFontScale(1*scaleX, 1*scaleY);
		highscoreMsg.setPosition((650*scaleX) - (highscoreMsg.getWidth()/((1300/Gdx.graphics.getWidth())*2.5f)), Gdx.graphics.getHeight()/2);
		
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
		soundButton.setPosition(Gdx.graphics.getWidth()- (75*scaleX), Gdx.graphics.getHeight()- (75*scaleX));

		stage.addActor(bg);
		stage.addActor(failMsg);
		stage.addActor(playButton);
		stage.addActor(soundButton);
		stage.addActor(scoreMsg);
		stage.addActor(highscoreMsg);
		
		stage.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(0.25f)));
		
		if(Menu.prefs.getBoolean("soundOn") == true){
			soundButton.setStyle(unmutedStyle);
		}else{
			soundButton.setStyle(mutedStyle);
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
				if(Menu.prefs.getBoolean("soundOn") == false){
					soundButton.setStyle(unmutedStyle);
					Audio.song.play();
					Audio.song.setLooping(true);
					
					Menu.prefs.putBoolean("soundOn", true);
					Menu.prefs.flush();
				}else{
					soundButton.setStyle(mutedStyle);
					Audio.song.pause();

					Menu.prefs.putBoolean("soundOn", false);
					Menu.prefs.flush();
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

