package uk.co.greedygull.entities;

import uk.co.greedygull.Assets;
import uk.co.greedygull.Constants;
import uk.co.greedygull.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class EntityManager {
	
	private final Array<Entity> entities = new Array<Entity>();
	private final Player player;

	
	
	public EntityManager(int amount){
		player = new Player(new Vector2(Gdx.graphics.getWidth()/2, 250),
				new Vector2(Assets.PLAYER.getWidth(),Assets.PLAYER.getHeight()), 
				new Vector2(0,0), 
				this);
		for(int i = 0; i < amount; i++){
			float x = MathUtils.random(0, Constants.VIEWPORT_WIDTH - Assets.TARGET.getWidth());
			float y = MathUtils.random(Constants.VIEWPORT_HEIGHT, (Constants.VIEWPORT_HEIGHT)*2);
			addEntity(new Target(new Vector2(x,y),new Vector2(0,-1.66f)));
			//TODO FIX OBVIOUS HACK.............................^^^^^
		}
	}
	
	public void update(){
		for(Entity e : entities){
			e.update();
		}
		for(Ammo a : getAmmo()){
			if(a.checkEnd())
				entities.removeValue(a, false);
		}
		player.update();
		checkCollisions();
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
	
	private void checkCollisions(){
		for(Target t : getTargets()){
			for(Ammo a : getAmmo()){
				if(a.pos.y < 100){
				if(t.getBounds().contains(a.getBounds())){
					entities.removeValue(t, false);
					entities.removeValue(a, false);
					GUI.addScore(t.getScore());
					System.out.println("HIT");
				}
				}
			}
		}
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
	
	private Array<Ammo> getAmmo(){
		Array<Ammo> ammo = new Array<Ammo>();
		for(Entity e : entities){
			if(e instanceof Ammo){
				ammo.add((Ammo) e);
			}
		}
		return ammo;
	}

}
