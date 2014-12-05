package uk.co.greedygull.entities;

import uk.co.greedygull.Assets;


import uk.co.greedygull.Constants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Target extends Entity {
	
	private float mapSpeed = Constants.MAP_SPEED;

	public Target(Vector2 pos, Vector2 direction) {
		super(Assets.TARGET, pos, direction);
	}

	@Override
	public void update() {
		pos.add(direction);
		
		if(pos.y < 0){
			float x = MathUtils.random(0, Constants.VIEWPORT_WIDTH - Assets.TARGET.getWidth());
			float y = Constants.VIEWPORT_HEIGHT;
			pos.set(x, y);
		}
		
	}

}
