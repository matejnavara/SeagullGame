package uk.co.greedygull.entities;

import uk.co.greedygull.Assets;

import com.badlogic.gdx.math.Vector2;

/**
 * Creates the player shadow
 * @author Matej
 *
 */
public class PlayerShadow extends Entity{
	
	private long lastAnim;
	public boolean canSwoop;
	private int currentAnim = 0;

	public PlayerShadow( Vector2 pos, Vector2 direction) {
		super(Assets.PLAYERSHADOW, 
				pos, 
				new Vector2(Assets.PLAYERSHADOW.getWidth(),Assets.PLAYERSHADOW.getHeight()), 
				direction);
		
		canSwoop = false;
	}

	/**
	 * The update method handles the animation
	 */
	@Override
	public void update() {
		if(!canSwoop)
		if(System.currentTimeMillis() - lastAnim >= 200){	
			if(currentAnim < 2){
				currentAnim++;
			} else {
				currentAnim = 0;
			}
			
			System.out.println("ANIMATING image: " + currentAnim);
			lastAnim = System.currentTimeMillis();		
			}
		
		this.texture = Assets.shadowAnim[currentAnim];
	}

}
