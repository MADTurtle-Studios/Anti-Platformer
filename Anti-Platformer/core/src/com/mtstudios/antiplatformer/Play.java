package com.mtstudios.antiplatformer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

public class Play implements Screen{
	Image fader;
	
	SpriteBatch batch;							
	Texture turtle;								
	Player player;								
												
	Stage stage;								
	
	Label scoreMsg;
	LabelStyle style;
	BitmapFont font;
	
	ArrayList<Floor> floors;
	Iterator<Floor> floorIterator;	
	
	ArrayList<Fish> bgs;
	Iterator<Fish> bgIterator;
	
	Floor floor1;
	Floor floor2;
	
	Fish fish1;
	Fish fish2;
	PlayBackground playBG;
	
	Shark shark1;
	Shark shark2;
	Shark shark3;
	Shark shark4;
	Shark shark5;
	
	Vector2 turtleposition;
	
	Game game;
	
	boolean alive = true;
	boolean smashPlayed = false;
	
	float scaleX = Gdx.graphics.getWidth()/1300f;
	float scaleY = Gdx.graphics.getHeight()/660f;
	
	public static int score = 0;
	
	ArrayList<Shark> sharks;
	Iterator<Shark> sharkIterator;
	
	Random random = new Random();
	
	float Y1 = random.nextInt(360)*scaleY + (160*scaleY);
	float Y2 = random.nextInt(360)*scaleY + (160*scaleY);
	float Y3 = random.nextInt(360)*scaleY + (160*scaleY);
	float Y4 = random.nextInt(360)*scaleY + (160*scaleY);
	float Y5 = random.nextInt(360)*scaleY + (160*scaleY);
	
	float X1 = random.nextInt(650)*scaleX + (1300*scaleX);
	float X2 = random.nextInt(975)*scaleX + (1300*scaleX);
	float X3 = random.nextInt(1300)*scaleX + (1300*scaleX);
	float X4 = random.nextInt(1300)*scaleX + (1300*scaleX);
	float X5 = random.nextInt(1300)*scaleX + (1300*scaleX);
	
	public Play(Game game){
		this.game = game;
	}
	
	public void smashSound(){
		if(Audio.song.isPlaying()){
			Audio.smash.play();
		}
		smashPlayed = true;
	}
	
	@Override
	public void render(float delta) {
		if(!alive){
			if(!smashPlayed){
				smashSound();
			}
			
			if(shark1.getPosition().x > (-300*scaleX) && shark2.getPosition().x > (-300*scaleX) && shark3.getPosition().x > (-300*scaleX) && 
					shark4.getPosition().x > (-300*scaleX) && shark5.getPosition().x > (-300*scaleX)){
				
				player.setPosition(new Vector2(player.getPosition().x -= (13*scaleX), player.getPosition().y));
				
				shark1.setPosition(new Vector2(shark1.getPosition().x -= (3*scaleX), shark1.getPosition().y));
				shark2.setPosition(new Vector2(shark2.getPosition().x -= (3*scaleX), shark2.getPosition().y));
				shark3.setPosition(new Vector2(shark3.getPosition().x -= (3*scaleX), shark3.getPosition().y));
				shark4.setPosition(new Vector2(shark4.getPosition().x -= (3*scaleX), shark4.getPosition().y));
				shark5.setPosition(new Vector2(shark5.getPosition().x -= (3*scaleX), shark5.getPosition().y));
				
			}
			
			if(player.getPosition().x < (-600*scaleX)){
				fader.addAction(Actions.sequence(Actions.fadeIn(0.25f), Actions.run(new Runnable(){
					public void run(){
						game.setScreen(new GameOver(game));
					}
				})));
			}
		}	
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		player.update();
		
		floor1.update();
		floor2.update();
		
		playBG.update();
		fish1.update();
		fish2.update();
		
		stage.act();
		scoreMsg.setText("Score: " + score);
		
		shark1.update();
		shark2.update();
		shark3.update();
		shark4.update();
		shark5.update();
		
		batch.begin();
		playBG.draw(batch);
		batch.end();
		
		bgIterator = bgs.iterator();
		while(bgIterator.hasNext()){
			Fish cur = bgIterator.next();
			batch.begin();
			cur.draw(batch);
			cur.update();
			if(fish1.getPosition().y < -Gdx.graphics.getHeight()){
				fish1.setPosition(new Vector2(Gdx.graphics.getHeight(), 0));
			}else{
				fish1.setPosition(new Vector2(fish1.getPosition().x -= (0.1f*scaleX), 0));
			}

			if(fish2.getPosition().y < -Gdx.graphics.getHeight()){
				fish2.setPosition(new Vector2(Gdx.graphics.getHeight(), 0));
			}else{
				fish2.setPosition(new Vector2(fish2.getPosition().x -= (0.1f*scaleX), 0));
			}
			batch.end();
		}
		
		batch.begin();		
		player.draw(batch);		
		batch.end();
		
		floorIterator = floors.iterator();
		while(floorIterator.hasNext()){
			Floor cur = floorIterator.next();
			batch.begin();
			cur.draw(batch);
			cur.update();
			if(floor1.getPosition().x < -Gdx.graphics.getWidth()){
				floor1.setPosition(new Vector2(Gdx.graphics.getWidth(), 0));
			}else{
				floor1.setPosition(new Vector2(floor1.getPosition().x -= (3*scaleX), 0));
			}

			if(floor2.getPosition().x < -Gdx.graphics.getWidth()){
				floor2.setPosition(new Vector2(Gdx.graphics.getWidth(), 10));
			}else{
				floor2.setPosition(new Vector2(floor2.getPosition().x -= (3*scaleX), 0));
			}
			batch.end();
		}	
		sharkIterator = sharks.iterator();
		
		while(sharkIterator.hasNext()){
			Shark cur2 = sharkIterator.next();
			batch.begin();
			cur2.draw(batch);
			cur2.update();
			
			if(player.getBounds1().overlaps(cur2.getBounds()) || player.getBounds2().overlaps(cur2.getBounds()) || player.getBounds1().overlaps(floor1.getBounds()) || player.getBounds2().overlaps(floor1.getBounds())|| player.getBounds1().overlaps(floor2.getBounds()) || player.getBounds2().overlaps(floor2.getBounds())){
				alive = false;
			}
			
			if(shark1.getPosition().x < (-300*scaleX)){
				if(alive){
					score++;
				}
				Y1 = random.nextInt(360)*scaleY + (160*scaleY);
				X1 = random.nextInt(650)*scaleX + (1300*scaleX);
				
				shark1.setPosition(new Vector2 (X1, Y1));
			}
			
			if(shark2.getPosition().x < (-300*scaleX)){
				if(alive){
					score++;
				}
				Y2 = random.nextInt(360)*scaleY + (160*scaleY);
				X2 = random.nextInt(975)*scaleX + (1300*scaleX);
				
				shark2.setPosition(new Vector2 (X2, Y2));
			}
			
			if(shark3.getPosition().x < (-300*scaleX)){
				if(alive){
					score++;
				}
				Y3 = random.nextInt(360)*scaleY + (160*scaleY);
				X3 = random.nextInt(1300)*scaleX + (1300*scaleX);
				
				shark3.setPosition(new Vector2 (X3, Y3));
			}
			
			if(shark4.getPosition().x < (-300*scaleX)){
				if(alive){
					score++;
				}
				Y4 = random.nextInt(360)*scaleY + (160*scaleY);
				X4 = random.nextInt(1300)*scaleX + (1300*scaleX);
				
				shark4.setPosition(new Vector2 (X4, Y4));
			}
			
			if(shark5.getPosition().x < (-300*scaleX)){
				if(alive){
					score++;
				}
				Y5 = random.nextInt(360)*scaleY + (160*scaleY);
				X5 = random.nextInt(1300)*scaleX + (1300*scaleX);
				
				shark5.setPosition(new Vector2 (X5, Y5));
			}
			batch.end();
		}
		
		batch.begin();
		stage.draw();
		batch.end();
		
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void show() {
		score = 0;
		
	    batch = new SpriteBatch();
		stage = new Stage();
		
		font = new BitmapFont(Gdx.files.internal("font.fnt"), false);
		style = new LabelStyle(font, Color.WHITE);
		scoreMsg = new Label("Score: " + score, style);
		scoreMsg.setFontScale(1*scaleX, 1*scaleY);
		scoreMsg.setPosition((650*scaleX) - (scoreMsg.getWidth()/((1300/Gdx.graphics.getWidth())*2.5f)), (Gdx.graphics.getHeight()-(100*scaleY)));
		
		stage.addActor(scoreMsg);
		
		playBG = new PlayBackground((new Vector2(0, 0)), (new Vector2(1300*scaleX, 660*scaleY)));
		
		fader = new Image(new Texture(Gdx.files.internal("Fader.png")));
		fader.setPosition(0, 0);
		fader.setSize(1300*scaleX, 660*scaleY);
		fader.setColor(1, 1, 1, 1);
				
		fish1 = new Fish(new Vector2(0,0), new Vector2(1300*scaleX, 660*scaleY));
		fish2 = new Fish(new Vector2(0, Gdx.graphics.getHeight()), new Vector2(1300*scaleX, 660*scaleY));
		
		bgs = new ArrayList<Fish>();
		
		bgs.add(fish1);
		bgs.add(fish2);
				
		turtle = new Texture(Gdx.files.internal("NewTurtleSS.png"));
		
		floor1 = new Floor(new Vector2(0, 0), new Vector2(1300*scaleX, (150*scaleY)));
		floor2 = new Floor(new Vector2(Gdx.graphics.getWidth(), 0), new Vector2(1300*scaleX, (150*scaleY)));
		
		floors = new ArrayList<Floor>();
		
		floors.add(floor1);
		floors.add(floor2);
		
		shark1 = new Shark(new Vector2(X1, Y1), new Vector2 (316*scaleX, 129*scaleY));
		shark2 = new Shark(new Vector2(X2, Y2), new Vector2 (316*scaleX, 129*scaleY));
		shark3 = new Shark(new Vector2(X3, Y3), new Vector2 (316*scaleX, 129*scaleY));
		shark4 = new Shark(new Vector2(X4, Y4), new Vector2 (316*scaleX, 129*scaleY));
		shark5 = new Shark(new Vector2(X5, Y5), new Vector2 (316*scaleX, 129*scaleY));
		
		sharks = new ArrayList<Shark>();
		
		sharks.add(shark1);
		sharks.add(shark2);
		sharks.add(shark3);
		sharks.add(shark4);
		sharks.add(shark5);
		
		stage.addActor(fader);
		fader.addAction(Actions.sequence(Actions.alpha(1), Actions.fadeOut(0.25f)));

		player = new Player(new Vector2(50*scaleX, 330*scaleY), new Vector2(183*scaleX, 100*scaleY), "NewTurtleSS.png");
		
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
