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
	
	float scaleX = Gdx.graphics.getWidth()/1300f;
	float scaleY = Gdx.graphics.getHeight()/660f;
	
	public Shark(Vector2 position, Vector2 size){
		this.position = position;
		this.size = size;

		bounds = new Rectangle(position.x + (26*scaleX), position.y + (31*scaleY), (268*scaleX), (61*scaleY));
		shark = new Texture(Gdx.files.internal("Shark Art.png"));
	}
	
	public void update(){
		bounds.set(position.x + (26*scaleX), position.y + (31*scaleY), (268*scaleX), (61*scaleY));
	}
	
	public void draw(SpriteBatch batch){
		batch.draw(shark, position.x -= 7*scaleX, position.y, size.x, size.y);

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
