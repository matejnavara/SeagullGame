package uk.co.greedygull.entities;

import java.util.ArrayList;

import uk.co.greedygull.Assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;

public class Player extends Entity{
	
	private EntityManager entityManager;
	private boolean canShoot;
	private long lastFire;
	
	private static int stamina = 1000;

	public Player(Vector2 pos, Vector2 scale, Vector2 direction, EntityManager entityManager) {
		super(Assets.PLAYER, pos, scale, direction);
		this.entityManager = entityManager;
		canShoot = true;
	}

	@Override
	public void update() {
		stamina--;
		pos.add(direction);
		
		if((pos.x > 0) && (Gdx.input.isKeyPressed(Keys.LEFT))){
			//if((pos.x > 0) && (Gdx.input.isKeyPressed(Keys.LEFT)))
			setDirection(-300, 0);
		
		} else if(Gdx.input.isKeyPressed(Keys.RIGHT)){
			//if(pos.x + "player width" < Gdx.graphics.getWidth())
			setDirection(300, 0);
		
		} else
			setDirection(0,0);
		
		if(Gdx.input.isKeyPressed(Keys.SPACE)){
			this.shoot();
			}
		}
	
	
	public static int getStamina(){
		return stamina;
	}
	
	public void setStamina(int s){
		stamina = s;
	}
	
	
	public void shoot(){
		if(System.currentTimeMillis() - lastFire >= 500){
			entityManager.addEntity(new Ammo(pos.cpy().add(Assets.PLAYER.getWidth()/2,0)));
			lastFire = System.currentTimeMillis();

		}
	}
	
	
}
