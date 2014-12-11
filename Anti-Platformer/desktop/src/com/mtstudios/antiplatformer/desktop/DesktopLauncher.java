package com.mtstudios.antiplatformer.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mtstudios.antiplatformer.AntiPlatformer;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Anti-Platformer 0.8.0";
		config.width = 1024;
		config.height = 600;
		
		
		new LwjglApplication(new AntiPlatformer(), config);
	}
}
