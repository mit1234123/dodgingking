package de.unvilgames.dodgingking.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import de.unvilgames.dodgingking.DodgingKing;

public class DesktopLauncher {

	static void pack() {
		TexturePacker.Settings settings = new TexturePacker.Settings();
		settings.maxWidth = 2048;
		settings.maxHeight = 2048;
		settings.pot = true;
		// WORKING DIR: C:\Users\timjk\libgdx projects\Dodgingking\android\assets

		TexturePacker.process(settings, "../../assets", "packed", "game");
	}

	public static void main (String[] arg) {
		pack();
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new DodgingKing(), config);
	}
}
