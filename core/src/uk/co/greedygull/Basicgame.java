package uk.co.greedygull;


import uk.co.greedygull.screen.MenuScreen;
import uk.co.greedygull.screen.ScreenManager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * The core game class that is initiated upon runtime, this creates the orthographic camera and sprite batch used by all other classes.
 * As well as the preferences used to store persistent high score data.
 * @author Matej
 *
 */
public class Basicgame extends Game {

	private static OrthographicCamera camera;
	private SpriteBatch batch;
	
	public static Preferences prefs;
	
	@Override
	public void create() {
		prefs = Gdx.app.getPreferences("GullPreferences");
		
		prefs.getInteger("stamina", 0);
		prefs.getFloat("firerate", 0);
		prefs.getInteger("speed", 0);
		
		camera = new OrthographicCamera(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
		
		batch = new SpriteBatch();
		ScreenManager.setScreen(new MenuScreen());
	}

	@Override
	public void render() {
				
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		if(ScreenManager.getCurrentScreen() != null)
			batch.setProjectionMatrix(camera.combined);
			ScreenManager.getCurrentScreen().render(batch);
			ScreenManager.getCurrentScreen().update();
		

	}

	@Override
	public void resize(int width, int height) {
		if(ScreenManager.getCurrentScreen() != null)
			camera.viewportWidth = (Constants.VIEWPORT_HEIGHT/height) * width;
			camera.update();
	}

	@Override
	public void pause() {
		if(ScreenManager.getCurrentScreen() != null)
			ScreenManager.getCurrentScreen().pause();
	}

	@Override
	public void resume() {
		if(ScreenManager.getCurrentScreen() != null)
			ScreenManager.getCurrentScreen().resume();
	}

	@Override
	public void dispose() {
		if(ScreenManager.getCurrentScreen() != null)
			ScreenManager.getCurrentScreen().dispose();
		batch.dispose();
	}
	
	public static OrthographicCamera getCam(){
		return camera;
	}
}
