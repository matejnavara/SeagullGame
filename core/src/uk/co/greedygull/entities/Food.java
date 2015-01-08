package uk.co.greedygull.entities;

import uk.co.greedygull.Assets;
import uk.co.greedygull.Constants;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
/**
 * Creates food
 * @author Matej
 *
 */
public class Food extends Entity{
	
	private int score = 500;

	public Food(Vector2 pos, Vector2 direction) {
		super(Assets.FOOD, pos, new Vector2(Assets.FOOD.getWidth(),Assets.FOOD.getHeight()), direction);
		
	}

	@Override
	public void update() {
		pos.add(direction);
		
		if(pos.y < 0 - Assets.FOOD.getHeight()){
			float x = MathUtils.random(0, Constants.VIEWPORT_WIDTH - Assets.FOOD.getWidth());
			float y = Constants.VIEWPORT_HEIGHT;
			pos.set(x, y);
			}
		}
	
	public int getScore(){
		return score;
	}
	
	}


