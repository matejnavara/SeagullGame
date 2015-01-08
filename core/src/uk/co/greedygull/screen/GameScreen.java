package uk.co.greedygull.screen;

import uk.co.greedygull.Basicgame;
import uk.co.greedygull.GUI;
import uk.co.greedygull.entities.EntityManager;
import uk.co.greedygull.entities.Map;
import uk.co.greedygull.entities.Player;
import uk.co.greedygull.util.CameraHelper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * This creates the main gameplay screen with the entity manager and GUI
 * @author Matej
 *
 */
public class GameScreen extends Screen {

	private Map map;
	private GUI gui;
	private EntityManager entityManager;
	private CameraHelper cameraHelper;
	private boolean paused;


	/**
	 * Initialises the camera helper(used for player tracking), map, entity manager and GUI required for the game screen
	 */
	@Override
	public void create() {
		
		cameraHelper = new CameraHelper();	
		map = new Map();
		entityManager = new EntityManager();
		gui = new GUI();
		paused = false;
		
	}
	
	/**
	 * The update method checks for a pause state, if not paused it will call all the update methods for map, entities and GUI
	 */
	@Override
	public void update() {
		if(GUI.getPaused()){
			pause();
		}
		
		if(!GUI.getPaused()){
			resume();
		}
		
		if(!paused){
		cameraHelper.setTarget(entityManager.player);
		cameraHelper.update(Gdx.graphics.getDeltaTime());
		
		map.update();
		entityManager.update();
		gui.update();
		
		if(Player.isGameover()){
			ScreenManager.setScreen(new GameoverScreen());
		}
		
		}
		
	}

	/**
	 * The render method draws the map and entities before rendering the GUI so as this can stay constant while the player moves 
	 */
	@Override
	public void render(SpriteBatch sb) {
		cameraHelper.applyTo(Basicgame.getCam());
		sb.begin();
		map.render(sb);		
		entityManager.render(sb);
		sb.end();
		sb.setProjectionMatrix(Basicgame.getCam().combined);
		gui.render(sb);
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void dispose() {
		
	}

	@Override
	public void pause() {
		paused = true;
		
	}

	@Override
	public void resume() {
		paused = false;
		
	}
	

}
