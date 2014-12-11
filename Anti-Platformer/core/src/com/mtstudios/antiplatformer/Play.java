package com.mtstudios.antiplatformer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Play implements Screen{
	Image fader;
	
	SpriteBatch batch;							
	Texture turtle;								
	Player player;								
												
	Stage stage;								
												
	ArrayList<Floor> floors;
	Iterator<Floor> floorIterator;				
	Floor floor1;
	Floor floor2;
	
	PlayBackground bg;
	
	Shark shark1;
	Shark shark2;
	Shark shark3;
	Shark shark4;
	Shark shark5;
	
	Vector2 turtleposition;
	
	Game game;
	
	boolean alive = true;
	float scaleX = Gdx.graphics.getWidth()/1300f;
	float scaleY = Gdx.graphics.getHeight()/660f;
	
	ArrayList<Shark> sharks;
	Iterator<Shark> sharkIterator;
	
	Random random = new Random();
	
	float vertPlace1 = random.nextInt(284) + 126;
	int vertPlace2 = random.nextInt(284) + 126;
	int vertPlace3 = random.nextInt(284) + 126;
	int vertPlace4 = random.nextInt(284) + 126;
	int vertPlace5 = random.nextInt(284) + 126;
	
	int horizPlace1 = random.nextInt(512) + 1024;
	int horizPlace2 = random.nextInt(768) + 1024;
	int horizPlace3 = random.nextInt(1024) + 1024;
	int horizPlace4 = random.nextInt(1024) + 1024;
	int horizPlace5 = random.nextInt(1024) + 1024;
	
	public Play(Game game){
		this.game = game;
	}

	@Override
	public void render(float delta) {
		if(!alive){
			if(shark1.getPosition().x > -236 && shark2.getPosition().x > -236 && shark3.getPosition().x > -236 && 
					shark4.getPosition().x > -236 && shark5.getPosition().x > -236){
				
				player.setPosition(new Vector2(player.getPosition().x -= 7, player.getPosition().y));
				
				shark1.setPosition(new Vector2(shark1.getPosition().x -= 3, shark1.getPosition().y));
				shark2.setPosition(new Vector2(shark2.getPosition().x -= 3, shark2.getPosition().y));
				shark3.setPosition(new Vector2(shark3.getPosition().x -= 3, shark3.getPosition().y));
				shark4.setPosition(new Vector2(shark4.getPosition().x -= 3, shark4.getPosition().y));
				shark5.setPosition(new Vector2(shark5.getPosition().x -= 3, shark5.getPosition().y));
				
			}
			
			if(player.getPosition().x < -472){
				stage.addAction(Actions.sequence(Actions.fadeIn(0.25f), Actions.run(new Runnable(){
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
		
		bg.update();
		
		stage.act();
		
		shark1.update();
		shark2.update();
		shark3.update();
		shark4.update();
		shark5.update();
		
		batch.begin();
		bg.draw(batch);
		batch.end();
		
		batch.begin();		
		batch.draw(player.getCurrentFrame(), player.getPosition().x, player.getPosition().y);		
		batch.end();
		
		floorIterator = floors.iterator();
		while(floorIterator.hasNext()){
			Floor cur = floorIterator.next();
			batch.begin();
			cur.draw(batch);
			cur.update();
			if(floor1.getPosition().x < -Gdx.graphics.getWidth()){
				floor1.setPosition(new Vector2(Gdx.graphics.getWidth(), 10));
			}else{
				floor1.setPosition(new Vector2(floor1.getPosition().x -= 3, 10));
			}

			if(floor2.getPosition().x < -Gdx.graphics.getWidth()){
				floor2.setPosition(new Vector2(Gdx.graphics.getWidth(), 10));
			}else{
				floor2.setPosition(new Vector2(floor2.getPosition().x -= 3, 10));
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
			
			if(shark1.getPosition().x < -236){
				vertPlace1 = random.nextInt(284) + 126;
				horizPlace1 = random.nextInt(512) + 1024;
				
				shark1.setPosition(new Vector2 (horizPlace1, vertPlace1));
			}
			
			if(shark2.getPosition().x < -236){
				vertPlace2 = random.nextInt(284) + 126;
				horizPlace2 = random.nextInt(768) + 1024;
				
				shark2.setPosition(new Vector2 (horizPlace2, vertPlace2));
			}
			
			if(shark3.getPosition().x < -236){
				vertPlace3 = random.nextInt(284) + 126;
				horizPlace3 = random.nextInt(1024) + 1024;
				
				shark3.setPosition(new Vector2 (horizPlace3, vertPlace3));
			}
			
			if(shark4.getPosition().x < -236){
				vertPlace4 = random.nextInt(284) + 126;
				horizPlace4 = random.nextInt(1024) + 1024;
				
				shark4.setPosition(new Vector2 (horizPlace4, vertPlace4));
			}
			
			if(shark5.getPosition().x < -236){
				vertPlace5 = random.nextInt(284) + 126;
				horizPlace5 = random.nextInt(1024) + 1024;
				
				shark5.setPosition(new Vector2 (horizPlace5, vertPlace5));
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
		
	    batch = new SpriteBatch();
		stage = new Stage();
		Audio.create();
		
		fader = new Image(new Texture(Gdx.files.internal("Fader.png")));
		fader.setPosition(0, 0);
		fader.setSize(1300*scaleX, 660*scaleY);
		fader.setColor(1, 1, 1, 1);
				
		bg = new PlayBackground(new Vector2(0,0), new Vector2(1300*scaleX, 660*scaleY));

		stage.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(0.25f)));
		
		turtle = new Texture(Gdx.files.internal("NewTurtleSS.png"));
		
		floor1 = new Floor(new Vector2(0, 10), new Vector2(1300*scaleX, 118));
		floor2 = new Floor(new Vector2(Gdx.graphics.getWidth(), 10), new Vector2(1300*scaleX, 118));
		
		floors = new ArrayList<Floor>();
		
		floors.add(floor1);
		floors.add(floor2);
		
		shark1 = new Shark(new Vector2(horizPlace1, vertPlace1), new Vector2 (200, 81));
		shark2 = new Shark(new Vector2(horizPlace2, vertPlace2), new Vector2 (200, 81));
		shark3 = new Shark(new Vector2(horizPlace3, vertPlace3), new Vector2 (200, 81));
		shark4 = new Shark(new Vector2(horizPlace4, vertPlace4), new Vector2 (200, 81));
		shark5 = new Shark(new Vector2(horizPlace5, vertPlace5), new Vector2 (200, 81));
		
		sharks = new ArrayList<Shark>();
		
		sharks.add(shark1);
		sharks.add(shark2);
		sharks.add(shark3);
		sharks.add(shark4);
		sharks.add(shark5);
		
		stage.addActor(fader);
		stage.addAction(Actions.sequence(Actions.alpha(1), Actions.fadeOut(0.5f)));
		
		if(Gdx.files.local("player.dat").exists()){
			try {
				player = new Player(new Vector2(50, 330), "NewTurtleSS.png");
				player.setPosition(Player.readPlayer());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("Save data found - Reading File...");
		}else{
			player = new Player(new Vector2 (50, 330), "NewTurtleSS.png");
			try {
				Player.savePlayer(player);
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("No save data found - Creating save file...");
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
		try {
			Player.savePlayer(player);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Audio.dispose();
		
	}

}
