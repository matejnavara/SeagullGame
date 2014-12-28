package uk.co.greedygull.entities;

import uk.co.greedygull.Assets;

import com.badlogic.gdx.math.Vector2;

public class PlayerShadow extends Entity{
	
	

	public PlayerShadow( Vector2 pos, Vector2 direction) {
		super(Assets.PLAYERSHADOW, 
				pos, 
				new Vector2(Assets.PLAYERSHADOW.getWidth(),Assets.PLAYERSHADOW.getHeight()), 
				direction);
	}

	@Override
	public void update() {
		
	}

}
