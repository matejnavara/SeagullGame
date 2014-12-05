package uk.co.greedygull.screen;

import java.util.ArrayList;

import uk.co.greedygull.entities.Ammo;
import uk.co.greedygull.entities.EntityManager;
import uk.co.greedygull.entities.Player;
import uk.co.greedygull.entities.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class GameScreen extends Screen {

	private Player player;
	private ArrayList<Ammo> ammo;
	private Map map;
	private EntityManager entityManager;
	
	private Vector2 SCROLLSPEED = new Vector2(0,-5f) ;

	@Override
	public void create() {

		map = new Map();
		entityManager = new EntityManager(5);
		
	}
	
	@Override
	public void update() {
		map.update();
		
		entityManager.update();
		
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.begin();
		map.render(sb);		
		entityManager.render(sb);
		sb.end();		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

}
