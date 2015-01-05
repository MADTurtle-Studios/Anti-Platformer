package com.mtstudios.antiplatformer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Player{
	Vector2 position, size;
	String textureLoc;
	
	float scaleX = Gdx.graphics.getWidth()/1300f;
	float scaleY = Gdx.graphics.getHeight()/660f;
	
	public static final int col = 19;
	public final static int row = 1;
	
	Animation playerAnimation;
	Texture playerTexture;
	TextureRegion[] frames;
	TextureRegion currentFrame;
	float stateTime;
	
	float playerSpeed = 14;
	
	Rectangle bounds1;
	Rectangle bounds2;
	Rectangle screenBounds;
	
	public Player(Vector2 position, Vector2 size, String textureLoc){
		this.position = position;
		this.size = size;
		
		bounds1 = new Rectangle(position.x + (33*scaleX), position.y + (26*scaleY), (96*scaleX), (73*scaleY));
		bounds2 = new Rectangle(position.x + (128*scaleX), position.y + (26*scaleY), (54*scaleX), (39*scaleY));
		screenBounds = new Rectangle(0, Gdx.graphics.getHeight(), Gdx.graphics.getWidth(), 100);
		
		playerTexture = new Texture(Gdx.files.internal("NewTurtleSS.png"));
		TextureRegion[][] tmp = TextureRegion.split(playerTexture, playerTexture.getWidth()/col, playerTexture.getHeight()/row);
		frames = new TextureRegion[col*row];
		
		int index = 0;
		for(int i=0; i < row; i++){
			for(int j=0; j < col; j++){
				frames[index++] = tmp[i][j];
			}
		}
		
		playerAnimation = new Animation(1, frames);
		stateTime = 0f;
		currentFrame = playerAnimation.getKeyFrame(0);
	}
	
	public void update(){
		
		bounds1.set(position.x + (33*scaleX), position.y + (26*scaleY), (96*scaleX), (73*scaleY));
		bounds2.set(position.x + (128*scaleX), position.y + (26*scaleY), (54*scaleX), (39*scaleY));
		screenBounds.set(0, Gdx.graphics.getHeight(), Gdx.graphics.getWidth(), 100);
		
		if(stateTime < 19){
			stateTime += Gdx.graphics.getDeltaTime()*playerSpeed;
		}else{

			stateTime = 0;
		}
		
		currentFrame = playerAnimation.getKeyFrame(0 + stateTime);
		
		if(Gdx.input.isKeyPressed(Keys.W)||Gdx.input.isKeyPressed(Keys.UP)||Gdx.input.isTouched()){
			if(bounds1.overlaps(screenBounds)){
				position.y -= 0.0001f;
			}else{
				position.y += 4f*scaleY;
			}
		}
		else{ 
			position.y -= 2.5f*scaleY;
		}

	}
	
	public void draw(SpriteBatch batch){
		batch.draw(getCurrentFrame(), position.x, position.y, size.x, size.y);
	}
	
	public Vector2 getPosition(){
		return position;
	}
	
	public void setPosition(Vector2 position){
		this.position = position;
	}

	public Animation getPlayerAnimation() {
		return playerAnimation;
	}

	public void setPlayerAnimation(Animation playerAnimation) {
		this.playerAnimation = playerAnimation;
	}

	public TextureRegion[] getFrames() {
		return frames;
	}

	public void setFrames(TextureRegion[] frames) {
		this.frames = frames;
	}

	public TextureRegion getCurrentFrame() {
		return currentFrame;
	}

	public void setCurrentFrame(TextureRegion currentFrame) {
		this.currentFrame = currentFrame;
	}

	public float getStateTime() {
		return stateTime;
	}

	public void setStateTime(float stateTime) {
		this.stateTime = stateTime;
	}

	public Rectangle getBounds1() {
		return bounds1;
	}

	public void setBounds1(Rectangle bounds1) {
		this.bounds1 = bounds1;
	}

	public Rectangle getBounds2() {
		return bounds2;
	}

	public void setBounds2(Rectangle bounds2) {
		this.bounds2 = bounds2;
	}

	public Vector2 getSize() {
		return size;
	}

	public void setSize(Vector2 size) {
		this.size = size;
	}
	
}
