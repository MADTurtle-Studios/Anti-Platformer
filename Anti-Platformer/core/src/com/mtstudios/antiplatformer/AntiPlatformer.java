package com.mtstudios.antiplatformer;

import com.badlogic.gdx.Game;

public class AntiPlatformer extends Game{
	Game game;
	
	@Override
	public void create () {
		game = this;
		setScreen(new SplashScreen(game));
	}
	
	public void dispose(){

	}

	@Override
	public void render () {
		super.render();
	}
}

//Total number of lines (including AndroidLauncher.java) = 1248