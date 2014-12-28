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
		super(texture, pos, new Vector2(texture.getWidth(),texture.getHeight()), direction);
	}

	@Override
	public void update() {
		pos.add(direction);
		
		if(!hit && pos.y < 0 - this.getHeight()){
			float x = MathUtils.random(0, Constants.VIEWPORT_WIDTH - this.getWidth());
			float y = Constants.VIEWPORT_HEIGHT;
			pos.set(x, y);
		}
		
			
		}
		
//	public static Texture[] targetsTex = new Texture[]{
//		 Assets.TARGET_1,
//		 Assets.TARGETHIT_1,
//		 Assets.TARGET_2,
//		 Assets.TARGETHIT_2
//	};
	
	public int getScore(){
		return SCORE;
	}

}
