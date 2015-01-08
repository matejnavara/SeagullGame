package uk.co.greedygull.entities;

import uk.co.greedygull.Assets;

import com.badlogic.gdx.math.Vector2;

/**
 * Creates a dropping
 * @author Matej
 *
 */
public class Ammo extends Entity{
	
	final static int DROPSPEED = 500;
	final static float DROPPING = 150;
	public Boolean active;

	/**
	 * Spawns the dropping as position pos.
	 * @param pos
	 */
	public Ammo(Vector2 pos) {
		super(Assets.AMMO, pos, new Vector2(Assets.AMMO.getWidth(),Assets.AMMO.getHeight()),new Vector2(0,-1));	
		this.active = true;
	}

	/**
	 * The update() continuously increments the direction and reduces the scale of the entity.
	 */
	@Override
	public void update() {
		pos.add(direction);
		scale.add(-(scale.x/DROPPING),-(scale.y/DROPPING));
	}
	
	public void render(){
		
	}
	
	/**
	 * Checks if entity is out of bounds, returns true/false
	 * @return
	 */
	public boolean checkEnd(){
		return pos.y < 0-texture.getHeight();
	}
	


}
