package uk.co.greedygull.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import uk.co.greedygull.Basicgame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Seagull";
		config.width = 480;
		config.height = 700;
		config.resizable = false;
		new LwjglApplication(new Basicgame(), config);
	}
}
