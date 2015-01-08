package uk.co.greedygull.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

/**
 * Creates a target
 * @author Matej
 *
 */
public class Target extends Entity {
	
	private final int SCORE = 100;
	public boolean hit;


	/**
	 * The constructor for a target takes in texture (for randomized texture inputs), position, direction and a hit boolean to identify when a target has been hit.
	 * @param texture
	 * @param pos
	 * @param direction
	 * @param hit
	 */
	public Target(Texture texture, Vector2 pos, Vector2 direction, boolean hit) {
		super(texture, pos, new Vector2(texture.getWidth(),texture.getHeight()), direction);
	}

	/**
	 * The update method just increments the position based on direction
	 */
	@Override
	public void update() {
		pos.add(direction);
	}

	/**
	 * Returns the private final score of the target to add to player's score
	 * @return
	 */
	public int getScore(){
		return SCORE;
	}
	
	/**
	 * Checks if entity is out of bounds, returns true/false
	 * @return
	 */
	public boolean checkEnd(){
		return pos.y < 0-texture.getHeight();
	}

}
