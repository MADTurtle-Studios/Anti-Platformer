package com.mtstudios.antiplatformer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Shark {
	
	Vector2 position, size;
	Texture shark;
	Rectangle bounds;
	
	public Shark(Vector2 position, Vector2 size){
		this.position = position;
		this.size = size;

		bounds = new Rectangle(position.x + 20.48f, position.y + 16.5f, 168.6f, 44.9f);
		shark = new Texture(Gdx.files.internal("Shark Art.png"));
	}
	
	public void update(){
		bounds.set(position.x + 20.48f, position.y + 16.5f, 168.6f, 44.9f);
	}
	
	public void draw(SpriteBatch batch){
		batch.draw(shark, position.x -= 7, position.y, size.x, size.y);

	}

	public Vector2 getPosition() {
		return position;
	}

	public Vector2 getSize() {
		return size;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

}
