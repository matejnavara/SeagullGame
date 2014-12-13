package uk.co.greedygull.entities;

import uk.co.greedygull.Assets;


import uk.co.greedygull.Constants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Target extends Entity {
	
	//private float mapSpeed = Constants.MAP_SPEED;
	private final int SCORE = 100;
	public boolean hit;


	public Target(Texture texture, Vector2 pos, Vector2 direction, boolean hit) {
		super(texture, pos, new Vector2(Assets.TARGET.getWidth(),Assets.TARGET.getHeight()), direction);
	}

	@Override
	public void update() {
		pos.add(direction);
		
		if(!hit && pos.y < 0 - Assets.TARGET.getHeight()){
			float x = MathUtils.random(0, Constants.VIEWPORT_WIDTH - Assets.TARGET.getWidth());
			float y = Constants.VIEWPORT_HEIGHT;
			pos.set(x, y);
		}
		
			
		}
		
	
	
	public int getScore(){
		return SCORE;
	}

}
