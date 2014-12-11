package com.mtstudios.antiplatformer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class PlayBackground {
	Vector2 position, size;
	Texture menuBG;
	
	public PlayBackground (Vector2 position, Vector2 size){
		this.position = position;
		this.size = size;

		menuBG = new Texture(Gdx.files.internal("Play BG.png"));
	}
	
	public void update(){
		
	}
	
	public void draw(SpriteBatch batch){
		batch.draw(menuBG, position.x, position.y, size.x, size.y);

	}

	public Vector2 getPosition() {
		return position;
	}

	public Vector2 getSize() {
		return size;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}
}
