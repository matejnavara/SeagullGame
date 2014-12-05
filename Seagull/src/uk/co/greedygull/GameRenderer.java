package uk.co.greedygull;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.Disposable;

public class GameRenderer implements Disposable {

	private static final String TAG = GameRenderer.class.getName();

	private OrthographicCamera camera;
	private SpriteBatch spriteBatch;
	
	private GameController gameController;

	public GameRenderer(GameController gameController) {
		this.gameController = gameController;
		init();
	}

	private void init() {
		spriteBatch = new SpriteBatch();
		camera = new OrthographicCamera(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
		camera.position.set(0,0,0);
		camera.update();

	}

	public void render() {
		renderMaps();
		renderObjects();
		renderPlayer();
	}
	
	private void renderMaps(){
		gameController.cameraHelper.applyTo(camera);
		spriteBatch.setProjectionMatrix(camera.combined);
		
		spriteBatch.begin();
		spriteBatch.draw(Assets.instance.map, 1024,2048);
		spriteBatch.end();
		
	}
	
	private void renderObjects() {
		gameController.cameraHelper.applyTo(camera);
		spriteBatch.setProjectionMatrix(camera.combined);
		spriteBatch.begin();
		for(Sprite sprite : gameController.sprites){
			sprite.draw(spriteBatch);
		}
		spriteBatch.end();
	}
	
	private void renderPlayer() {
		gameController.cameraHelper.applyTo(camera);
		spriteBatch.setProjectionMatrix(camera.combined);
		spriteBatch.begin();
		Sprite sprite = gameController.player;
		sprite.draw(spriteBatch);
		spriteBatch.end();
	}

	public void resize(int width, int height) {
		camera.viewportWidth = (Constants.VIEWPORT_HEIGHT/height) * width;
		camera.update();
	}

	@Override
	public void dispose() {
		spriteBatch.dispose();

	}

}
