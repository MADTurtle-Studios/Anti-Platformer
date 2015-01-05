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
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class Credits implements Screen{

	Stage stage;
	
	TextureAtlas mutedAtlas;
	TextureAtlas backAtlas;
	Button backButton;
	ButtonStyle backStyle;
	ButtonStyle mutedStyle;
	Skin mutedSkin;
	Skin backSkin;
	
	Table table;

	SpriteBatch batch;
	Image bg;
	Image floor;
	Image fader;
	
	Label text;
	BitmapFont font;
	LabelStyle style;
	
	float scaleX = (Gdx.graphics.getWidth()/1300f);
	float scaleY = (Gdx.graphics.getHeight()/660f);
	
	Game game;

	public Credits (Game game){
		this.game = game;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		table.setPosition(650*scaleX, table.getY() + (0.75f*scaleY));
		
		stage.act();

		batch.begin();
		stage.draw();	
		batch.end();
	}

	@Override
	public void show() {
		batch = new SpriteBatch();

		stage = new Stage();
		
		fader = new Image(new Texture(Gdx.files.internal("Fader.png")));
		fader.setPosition(0, 0);
		fader.setSize(1300*scaleX, 660*scaleY);
		fader.setColor(1, 1, 1, 1);

		bg = new Image(new Texture(Gdx.files.internal("Menu BG.png")));
		bg.setPosition(0, 0);
		bg.setSize(1300*scaleX, 660*scaleY);
		
		floor = new Image(new Texture(Gdx.files.internal("Floor Sprite.png")));
		floor.setPosition(0, 0);
		floor.setSize(1300*scaleX, 150*scaleY);
		
		table = new Table();
		
		font = new BitmapFont(Gdx.files.internal("font.fnt"), false);
		style = new LabelStyle(font, Color.WHITE);
		
		text = new Label("Anti-Platformer\n\nA MADTurtle Studios Game\n\n\n\nMusic: Spazzmatica Polka " +
				"\n\nKevin MacLeod (incompetech.com) " +
				"\n\n\n\nLicensed under Creative Commons: By Attribution 3.0 " +
				"\n\nhttp://creativecommons.org/licenses/by/3.0/" +
				"\n\n\n\nFont: Press Start K \n\ncodeman38 (www.zone38.net)" +
				"\n\n\n\nLicense can be found at: \n\nhttp://www.1001fonts.com/press-start-font.html#more" +
				"\n\n\n\nPart of the Young Enterprise scheme" +
				"\n\nhttp://www.young-enterprise.org.uk/" +
				"\n\n\n\nContact Us: \n\n\nFacebook: MADTurtle.Studios \n\nTwitter: @MADTurtleStudio" +
				"\n\nEmail: madturtle.studios@gmail.com \n\nWeb: https://madturtlestudios.wordpress.com/" +
				"\n\n\n\nThanks for playing!", style);
		text.setWrap(true);
		text.setAlignment(Gdx.graphics.getWidth()/2);
		table.add(text).width(1144*scaleX);
		table.setPosition(650*scaleX, -1000*scaleY);
		text.setPosition(650*scaleX, -1000*scaleY);
		text.setFontScale(0.75f*scaleX, 0.75f*scaleY);

		backSkin = new Skin();
		backAtlas = new TextureAtlas("buttons/back.pack");
		backSkin.addRegions(backAtlas);

		backStyle = new ButtonStyle();
		backStyle.up = backSkin.getDrawable("buttonpressed");
		backStyle.over = backSkin.getDrawable("button");
		backStyle.down = backSkin.getDrawable("button");

		backButton = new Button(backStyle);
		backButton.setSize(68*scaleX, 68*scaleX);
		backButton.setPosition(10*scaleX, Gdx.graphics.getHeight()- (75*scaleX));
		
		stage.addActor(bg);
		stage.addActor(backButton);
		stage.addActor(table);
		stage.addActor(floor);
		stage.addActor(fader);
		
		fader.addAction(Actions.sequence(Actions.alpha(1), Actions.fadeOut(0.25f), Actions.run(new Runnable(){
			public void run(){
				stage.clear();
				stage.addActor(bg);
				stage.addActor(backButton);
				stage.addActor(table);
				stage.addActor(floor);
			}
		})));
		
		Gdx.input.setInputProcessor(stage);
		
		backButton.addListener(new InputListener(){
			public boolean touchDown(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y, int pointer, int button) {
				stage.addActor(fader);
				fader.addAction(Actions.sequence(Actions.fadeIn(0.25f), Actions.run(new Runnable(){
					public void run(){
						Audio.song.pause();
						game.setScreen(new Menu(game));
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

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

}
