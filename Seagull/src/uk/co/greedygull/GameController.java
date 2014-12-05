package uk.co.greedygull;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import uk.co.greedygull.util.CameraHelper;

public class GameController extends InputAdapter {

	private static final String TAG = GameController.class.getName();
	
	public CameraHelper cameraHelper;

	public Sprite player;
	public Sprite[] sprites;
	public int selectedSprite;

	public GameController() {
		init();
	}

	private void init() {
		Gdx.input.setInputProcessor(this);
		cameraHelper = new CameraHelper();
		initObjects();
		initPlayer();
	}
	
	public void update(float deltaTime) {
		handleInput(deltaTime);
		updateObjects(deltaTime);
		cameraHelper.update(deltaTime);
	}
	
	private void initPlayer() {
		Texture texture = new Texture(Constants.PLAYER_TEX);
		Sprite pyr = new Sprite(texture);
		pyr.setSize(1,1);
		pyr.setOrigin(pyr.getWidth() / 2.0f, pyr.getHeight() / 2.0f);
		pyr.setPosition(0, 0);
		player = pyr;
		cameraHelper.setTarget(pyr);
	}

	private void initObjects() {
		sprites = new Sprite[5];

		Texture texture = new Texture(Constants.FISH_TEX);

		for (int i = 0; i < sprites.length; i++) {
			Sprite spr = new Sprite(texture);
			spr.setSize(1, 1);
			spr.setOrigin(spr.getWidth() / 2.0f, spr.getHeight() / 2.0f);
			float randomX = MathUtils.random(-2.0f, 2.0f);
			float randomY = MathUtils.random(-5.0f, 5.0f);
			spr.setPosition(randomX, randomY);
			sprites[i] = spr;
		}
	}
	

//	private Pixmap createPixmap(int width, int height) {
//		Pixmap pixmap = new Pixmap(width, height, Format.RGBA8888);
//		pixmap.setColor(1, 0, 0, 0.5f); 
//		pixmap.fill();
//		pixmap.setColor(1, 1, 0, 1);
//		pixmap.drawLine(0, 0, width, height);
//		pixmap.drawLine(width, 0, 0, height);
//		return pixmap;
//	}

	private void updateObjects(float deltaTime) {
		for(int i = 0; i < sprites.length; i++){
		float rotation = sprites[i].getRotation();
		rotation += 90 * deltaTime;
		rotation %= 360;
		sprites[i].setRotation(rotation);
		}
	}

	private void handleInput(float deltaTime) {
		if (Gdx.app.getType() != ApplicationType.Desktop)
			return;

		float sprMove = 5 * deltaTime;
		if (Gdx.input.isKeyPressed(Keys.UP))
			moveSprite(0, sprMove);
		if (Gdx.input.isKeyPressed(Keys.DOWN))
			moveSprite(0, -sprMove);
		if (Gdx.input.isKeyPressed(Keys.LEFT))
			moveSprite(-sprMove, 0);
		if (Gdx.input.isKeyPressed(Keys.RIGHT))
			moveSprite(sprMove, 0);
		
		if(Gdx.input.isKeyPressed(Keys.N))
			cameraHelper.addZoom(sprMove);		
		if(Gdx.input.isKeyPressed(Keys.M))
			cameraHelper.addZoom(-sprMove);

	}

	private void moveSprite(float x, float y) {
		player.translate(x, y);
	}

	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Keys.R) {
			init(); // Reset
			Gdx.app.debug(TAG, "Game restarted");
			}
//		 else if (keycode == Keys.SPACE) {
//			selectedSprite = (selectedSprite + 1) % sprites.length;
//			if (cameraHelper.hasTarget()){
//				cameraHelper.setTarget(sprites[selectedSprite]);
//			}
//			Gdx.app.debug(TAG, "Sprite #" + selectedSprite + " selected");
//		} else if (keycode == Keys.ENTER) {
//			cameraHelper.setTarget(cameraHelper.hasTarget() ? null : sprites[selectedSprite]);
//			Gdx.app.debug(TAG, "Camera follow enabled: " + cameraHelper.hasTarget());
//		}
		return false;
	}

}
