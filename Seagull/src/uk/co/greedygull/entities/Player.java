package uk.co.greedygull.entities;


import uk.co.greedygull.Assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;

public class Player extends Entity{
	
	private EntityManager entityManager;
	private boolean canShoot;
	public boolean canSwoop;
	private long lastFire;
	
	
	private static int stamina = 5000;

	public Player(Vector2 pos, Vector2 scale, Vector2 direction, EntityManager entityManager) {
		super(Assets.PLAYER, pos, scale, direction);
		this.entityManager = entityManager;
		canShoot = true;
		canSwoop = false;
	}
	
	public void playerShadow(){
		
	}

	@Override
	public void update() {
		stamina--;
		
		//MOVEMENT CONTROLS	
		if((pos.x > 0) && (Gdx.input.isKeyPressed(Keys.LEFT))){
			setDirection(-300, 0);
		
		} else if((pos.x +Assets.PLAYER.getWidth() < Assets.MAP.getWidth()) && (Gdx.input.isKeyPressed(Keys.RIGHT))){
			setDirection(300, 0);
		
		} else
			setDirection(0,0);
		
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
