package com.mtstudios.antiplatformer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class Audio {
	
	public static Music song;
	public static Sound smash;
	
	public static void create(){
		song = Gdx.audio.newMusic(Gdx.files.internal("Spazzmatica Polka.mp3"));
		smash = Gdx.audio.newSound(Gdx.files.internal("Smash SFX.mp3"));
	}
	
	public static void dispose(){
		song.dispose();
		smash.dispose();
	}

}
