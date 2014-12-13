package com.mtstudios.antiplatformer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Floor{
	Vector2 position, size;
	Texture floor;
	Rectangle bounds;
	
	float scaleX = Gdx.graphics.getWidth()/1300f;
	float scaleY = Gdx.graphics.getHeight()/660f;
	
	public Floor (Vector2 position, Vector2 size){
		this.position = position;
		this.size = size;

		bounds = new Rectangle(position.x, position.y, (1300*scaleX), (70*scaleY));
		floor = new Texture(Gdx.files.internal("Floor Sprite.png"));
	}
	
	public void update(){
		bounds.set(position.x, position.y, (1300*scaleX), (70*scaleY));
	}
	
	public void draw(SpriteBatch batch){
			batch.draw(floor, position.x, position.y, size.x, size.y);
			
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
