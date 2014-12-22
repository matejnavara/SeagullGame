package uk.co.greedygull.entities;


import uk.co.greedygull.Assets;
import uk.co.greedygull.Constants;
import uk.co.greedygull.util.CameraHelper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;

public class Player extends Entity{
	
	private EntityManager entityManager;
	private boolean canShoot;
	public boolean canSwoop;
	private long lastFire;
	private boolean hasStamina;
	
	public static boolean gameOver;
	
	
	private static int stamina;

	public Player(Vector2 pos, Vector2 scale, Vector2 direction, EntityManager entityManager) {
		super(Assets.PLAYER, pos, scale, direction);
		this.entityManager = entityManager;
		stamina = 500;
		canShoot = true;
		canSwoop = false;
		hasStamina = true;
		gameOver = false;
	}
	
	public void playerShadow(){
		
	}

	@Override
	public void update() {
		stamina--;
		
		//MOVEMENT CONTROLS	
		if(stamina > 0){
		if((pos.x > 0) && (Gdx.input.isKeyPressed(Keys.LEFT))){
			setDirection(-300, 0);
		
		} else if((pos.x +Assets.PLAYER.getWidth() < Assets.MAP.getWidth()) && (Gdx.input.isKeyPressed(Keys.RIGHT))){
			setDirection(300, 0);
		
		} else
			setDirection(0,0);
		
		pos.add(direction);
		}
	
		
		if(stamina <= 0){
			System.out.println("OUT OF STAMINA");
			hasStamina = false;
			setDirection(0, -50);
			pos.add(direction);
			if(pos.y < 0 - Assets.PLAYER.getHeight()){
				gameOver = true;
			}
		}
	
		
		pos.add(direction);
		
		//SHOOTING ANIMATION
		if(Gdx.input.isKeyPressed(Keys.SPACE)){
			this.shoot();
			}
		
		//SWOOPING ANIMATION
		//Swooping in
		if(canSwoop){
			if(scale.y > 10){
				scale.add(-1, -1);
			} else {
				canSwoop = false;
			}
		}
		//Swooping out
		if(!canSwoop && (scale.y < Assets.PLAYER.getHeight()-1))
				scale.add(1, 1);
				//System.out.println("SWOOPING OUT");
				canShoot = true;
				
		}
		
	public boolean hasStamina(){
		return hasStamina;
	}
	
	public static boolean isGameover(){
		return gameOver;
	}
	
	public static int getStamina(){
		return stamina;
	}
	
	public void addStamina(int s){
		stamina = stamina + s;
	}
	
	
	public void shoot(){
		if(canShoot){
			if(System.currentTimeMillis() - lastFire >= 500){
				entityManager.addEntity(new Ammo(pos.cpy().add(Assets.PLAYER.getWidth()/2,0)));
				lastFire = System.currentTimeMillis();
			}		
		}
	}
	
	public void swoop(){
		canShoot = false;
		canSwoop = true;
	}
	
	
}
