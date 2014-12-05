package uk.co.greedygull;


import uk.co.greedygull.screen.GameScreen;
import uk.co.greedygull.screen.MenuScreen;
import uk.co.greedygull.screen.ScreenManager;
import uk.co.greedygull.util.CameraHelper;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Basicgame implements ApplicationListener {

	private OrthographicCamera camera;
	private CameraHelper cameraHelper;
	private SpriteBatch batch;
	
	private boolean paused;

	@Override
	public void create() {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		
		Assets.instance.init(new AssetManager());
		camera = new OrthographicCamera(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
		camera.position.set(camera.viewportWidth/2f, camera.viewportHeight/2f, 0);
//		cameraHelper = new CameraHelper();
//		cameraHelper.applyTo(camera);
		camera.update();
		
		batch = new SpriteBatch();
		ScreenManager.setScreen(new GameScreen());
		
		paused = false;
	}

	@Override
	public void render() {
				
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
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
		paused = true;
	}

	@Override
	public void resume() {
		if(ScreenManager.getCurrentScreen() != null)
			ScreenManager.getCurrentScreen().resume();
		paused = false;
	}

	@Override
	public void dispose() {
		if(ScreenManager.getCurrentScreen() != null)
			ScreenManager.getCurrentScreen().dispose();
		Assets.instance.dispose();
		batch.dispose();
	}
}
