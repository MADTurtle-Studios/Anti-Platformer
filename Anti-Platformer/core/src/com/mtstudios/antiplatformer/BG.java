package com.mtstudios.antiplatformer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class BG {
	Vector2 position, size;
	Texture BG;
	
	public BG (Vector2 position, Vector2 size){
		this.position = position;
		this.size = size;

		BG = new Texture(Gdx.files.internal("Play BG.png"));
	}
	
	public void update(){
		
	}
	
	public void draw(SpriteBatch batch){
		batch.draw(BG, position.x, position.y, size.x, size.y);

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
	
	public void setSize(Vector2 size) {
		this.size = size;
	}
}