package uk.co.greedygull.entities;

import uk.co.greedygull.Assets;

import com.badlogic.gdx.math.Vector2;

/**
 * Creates an enemy
 * @author Matej
 *
 */
public class Enemy extends Entity{
	
	private int num = 0;
	private int attack = 100;
	private boolean canAttack = true;
	
	private long lastAnim;
	private int currentAnim = 0;

	/**
	 * Spawns an enemy entity at set position and direction
	 * @param pos
	 * @param direction
	 */
	public Enemy(Vector2 pos, Vector2 direction) {
		super(Assets.ENEMY, pos, new Vector2(Assets.ENEMY.getWidth(),Assets.ENEMY.getHeight()), direction);
	}

	/**
	 * The update() increments a Sin wave for the enemy position as well looping through animation frames held in @Assets.enemyAnim[]
	 */
	@Override
	public void update() {
		num++;
		pos.add(new Vector2((float)(3 * Math.sin((num * 0.5 * Math.PI) / 100)),direction.y));
		
		//ENEMY ANIMATION
		if(System.currentTimeMillis() - lastAnim >= 300){	
			if(currentAnim < 2){
				currentAnim++;
			} else {
				currentAnim = 0;
			}
			lastAnim = System.currentTimeMillis();		
			}
		
		this.texture = Assets.enemyAnim[currentAnim];
		
	}
	
	
	/**
	 * Returns the private variable {@link #attack}
	 * @return
	 */
	public int getAttack(){
		return attack;
	}
	
	/**
	 * Sets the private boolean variable {@link #canAttack}
	 */
	public void attack(){
		canAttack = false;
	}
	
	/**
	 * Returns the private boolean variable {@link #canAttack} 
	 * @return
	 */
	public boolean canAttack(){
		return canAttack;
	}
	
	/**
	 * Checks if entity is out of bounds, returns true/false
	 * @return
	 */
	public boolean checkEnd(){
		return pos.y < 0-texture.getHeight();
	}


}
