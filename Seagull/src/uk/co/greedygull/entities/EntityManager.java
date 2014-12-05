package uk.co.greedygull.entities;

import uk.co.greedygull.Assets;
import uk.co.greedygull.Constants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class EntityManager {
	
	private final Array<Entity> entities = new Array<Entity>();
	private final Player player;

	
	
	public EntityManager(int amount){
		player = new Player(new Vector2(Gdx.graphics.getWidth()/2, 250), new Vector2(0,0), this);
		for(int i = 0; i < amount; i++){
			float x = MathUtils.random(0, Constants.VIEWPORT_WIDTH - Assets.TARGET.getWidth());
			float y = MathUtils.random(Constants.VIEWPORT_HEIGHT, (Constants.VIEWPORT_HEIGHT)*2);
			addEntity(new Target(new Vector2(x,y),new Vector2(0,-1.66f)));
		}
	}
	
	public void update(){
		for(Entity e : entities){
			e.update();
		}
		player.update();
	}
	
	public void render(SpriteBatch sb){
		for(Entity e : entities){
			e.render(sb);
		}
		player.render(sb);
	}
	
	public void addEntity(Entity entity){
		entities.add(entity);
	}
	
	private Array<Target> getTargets(){
		Array<Target> trgt = new Array<Target>();
		for(Entity e : entities){
			if(e instanceof Target){
				trgt.add((Target) e);
			}
		}
		return trgt;
	}

}
